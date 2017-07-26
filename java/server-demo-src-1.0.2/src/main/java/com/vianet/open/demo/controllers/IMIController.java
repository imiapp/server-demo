package com.vianet.open.demo.controllers;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.vianet.open.demo.constants.Constants;
import com.vianet.open.demo.rest.base.BaseRestResponse;
import com.vianet.open.demo.rest.request.CreateChannelReq;
import com.vianet.open.demo.rest.request.GetAuthorizationInfoReq;
import com.vianet.open.demo.rest.response.AuthorizationInfoRes;
import com.vianet.open.exceptions.IMIRpcException;
import com.vianet.open.info.AuthorizationInfo;
import com.vianet.open.info.Authorizer;
import com.vianet.open.info.ChannelInfo;
import com.vianet.open.info.IdentityCardInfo;
import com.vianet.open.info.LoginUserInfo;
import com.vianet.open.router.IMIAuthorizationRouter;
import com.vianet.open.vo.GetAuthorizationInfoParams;
import com.vianet.open.vo.GetChannelInfoParams;

/**
 *
 * @ClassName: IMIController
 * @Description: MappingService数据转发控制器
 * @author S.J.
 * @date 2017年7月13日 下午3:39:22
 *
 */
@CrossOrigin(origins = {"*"})
@RequestMapping("/imi")
@RestController
public class IMIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IMIController.class);

    /**
     * @Title: createChannel
     * @Description: 获取MappingServer通信标示topicId
     * @return BaseRestResponse
     */
    @PostMapping("/createChannel")
    @ResponseBody
    public BaseRestResponse createChannel(@RequestBody CreateChannelReq req)  {

        LOGGER.info("===/createChannel-入参:[{}]", JSONObject.toJSONString(req));

        if (null == req) {
            return new BaseRestResponse(Constants.ERROR_PARAMS, Constants.ERROR_PARAMS_MSG + "[参数为空]");
        }
        if (StringUtils.isBlank(req.getVersion())) {
            return new BaseRestResponse(Constants.ERROR_PARAMS, Constants.ERROR_PARAMS_MSG + "[version为空]");
        }


        try {
            String scope = req.getScope();
            Set<String> scopeSet = new HashSet<String>();
            if (StringUtils.isBlank(scope)) {
                scopeSet.add("snsapi_info");
                scopeSet.add("snsapi_idcard");
            } else {
                String[] scopeArr = scope.split(",");
                CollectionUtils.addAll(scopeSet, scopeArr);
            }

            GetChannelInfoParams params = new GetChannelInfoParams();
            params.setScope(scopeSet);
            params.setVersion(req.getVersion());

            ChannelInfo channelInfo = IMIAuthorizationRouter.createChannel(params);
            LOGGER.info("===/createChannel-响应:[{}]", JSONObject.toJSONString(channelInfo));

            return new BaseRestResponse(Constants.OK, Constants.OK_MSG, channelInfo);
        } catch (IMIRpcException e) {
            LOGGER.error("===/createChannel-转发异常:", e.getMessage());
            return new BaseRestResponse(Constants.ERROR_SYSTEM, e.getMessage());
        }
    }

    /**
     * @Title: getAuthorizationInfo
     * @Description: 获取授权信息
     * @param req
     * @return BaseRestResponse
     */
    @PostMapping("/getAuthorizationInfo")
    @ResponseBody
    public BaseRestResponse getAuthorizationInfo(@RequestBody GetAuthorizationInfoReq req)  {

        LOGGER.info("===/getAuthorizationInfo-入参:[{}]", JSONObject.toJSONString(req));

        if (null == req) {
            return new BaseRestResponse(Constants.ERROR_PARAMS, Constants.ERROR_PARAMS_MSG + "[参数为空]");
        }
        if (StringUtils.isBlank(req.getTopicId())) {
            return new BaseRestResponse(Constants.ERROR_PARAMS, Constants.ERROR_PARAMS_MSG + "[topicId为空]");
        }
        if (StringUtils.isBlank(req.getScope())) {
            return new BaseRestResponse(Constants.ERROR_PARAMS, Constants.ERROR_PARAMS_MSG + "[scope为空]");
        }

        try {

            String[] scopeArr = req.getScope().split(",");
            Set<String> scopeSet = new HashSet<String>();
            CollectionUtils.addAll(scopeSet, scopeArr);

            GetAuthorizationInfoParams params = new GetAuthorizationInfoParams();
            params.setTopicId(req.getTopicId());
            params.setScope(scopeSet);

            /**
             * 1、若AuthorizationInfo为null则说明从mapping server上未收到app推送的数据，正常返回，等待下一次获取；
             *
             * 2、若AuthorizationInfo非null则说明正常获取到app推送的授权信息，
             *      此时获取的Authorizer一定是非null的，LoginUserInfo与IdentityCardInfo就不一定非null，根据接口调用时传的scope决定；
             */
            // 第三方用户应用接口返回授权信息类，第三方用户根据自己需要的数据定义此类的属性
            AuthorizationInfoRes infoRes = null;

            // 调用SDK获取授权信息
            AuthorizationInfo authorizationInfo = IMIAuthorizationRouter.getAuthorizationInfo(params);

            if (null != authorizationInfo) {
                // 封装第三方应用需要的数据
                infoRes = new AuthorizationInfoRes();

                // 封装授权用户数字身份号
                Authorizer authorizer = authorizationInfo.getAuthorizer();
                infoRes.setVportId(authorizer.getVportId());

                // 封装授权用户信息
                LoginUserInfo loginUserInfo = authorizationInfo.getLoginUserInfo();
                if (null != loginUserInfo) {
                    infoRes.setUserName(loginUserInfo.getUserName());
                    infoRes.setMobile(loginUserInfo.getMobile());
                }

                // 封装授权身份信息
                IdentityCardInfo identityCardInfo = authorizationInfo.getIdentityCardInfo();
                if (null != identityCardInfo) {
                    infoRes.setAuthority(identityCardInfo.getAuthority());
                    infoRes.setCin(identityCardInfo.getCin());
                    infoRes.setDob(identityCardInfo.getDateBirth());
                    infoRes.setDoe(identityCardInfo.getDateExpiry());
                    infoRes.setDoi(identityCardInfo.getDateIssue());
                    infoRes.setName(identityCardInfo.getName());
                    infoRes.setSex(identityCardInfo.getSex());
                }
            }

            LOGGER.info("===/getAuthorizationInfo-响应:[{}]", JSONObject.toJSONString(infoRes));

            return new BaseRestResponse(Constants.OK, Constants.OK_MSG, infoRes);

        } catch (IMIRpcException e) {
            LOGGER.error("===/getAuthorizationInfo-转发异常:", e);
            return new BaseRestResponse(Constants.ERROR_SYSTEM, e.getMessage());
        }

    }

}