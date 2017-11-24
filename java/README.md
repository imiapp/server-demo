# server-demo-java

> 本目录为[java版server-sdk](https://github.com/imiapp/imi-sdk/tree/master/Server-SDK/Java/Server)对接示例代码目录：

## 一、目录描述：

- server-demo-src-x.x.x为示例代码目录，**x.x.x**为版本号；

- “Server-SDK对接Demo代码说明x.x.x.pdf”为代码说明文档，及http接口设计参考；此文档的版本号与server-demo-src的版本号一一对应。

## 二、代码描述：

- server-demo从**v1.0.2**开始已经集成了IMI [JavaScript-SDK](https://github.com/imiapp/imi-sdk/tree/master/Server-SDK/JavaScript)代码，可以直接运行本示例代码进行演示页面访问，访问地址：**http://host:port/index**

## 三、业务逻辑描述：

> 授权交互调用逻辑：

1. 首先通过SDK调用`IMIAuthorizationRouter.createChannel()`接口创建MappingServer通信通道，将返回的通道信息`ChannelInfo`响应给第三方页面（生成二维码）或第三方app；

2. 第三方页面或第三方App需要获取授权信息则通过SDK调用`IMIAuthorizationRouter.getAuthorizationInfo()`接口获取授权信息`AuthorizationInfo`：

   a、若`AuthorizationInfo`为`null`则说明从mapping server上未收到app推送的数据，正常返回，等待下一次获取；

   b、若`AuthorizationInfo`非`null`则说明正常获取到app推送的授权信息，此时获取的授权人信息Authorizer一定是非`null`的，而授权登录用户信息`LoginUserInfo`与授权身份信息`IdentityCardInfo`就不一定非`null`，根据接口调用时传的参数scope决定；

3. 第三方用户Http接口响应信息`AuthorizationInfoRes`可根据业务需求自行从授权信息`AuthorizationInfo`对象中获取并组装。  

      #### 授权信息封装示例：

         // 第三方用户应用接口返回授权信息类，第三方用户根据自己需要的数据定义此类的属性
         AuthorizationInfoRes infoRes = null;

         // 调用SDK获取授权信息，这里可能会throw Exception，请在整体逻辑中使用try-catch进行保护
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

## 四、版本日志

  | 版本号   | 版本描述                                     |
  | ----- | ---------------------------------------- |
  | 1.0.0 | 基于server-sdk-1.0.0基础版本发布 |
  | 1.0.1 | 基于server-sdk-1.0.1，去掉回调处理 |
  | 1.0.2 | 基于server-sdk-1.0.1，集成js-sdk内容 |
  | 1.0.3 | 基于server-sdk-1.0.2，通信通道实体`ChannelInfo`中添加生成二维码的原始数据信息属性`qrData` |
  | 1.0.4 | 更新server-sdk-1.0.3版本，添加sdk依赖包引用 |
