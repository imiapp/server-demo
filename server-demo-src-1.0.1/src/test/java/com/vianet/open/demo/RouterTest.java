package com.vianet.open.demo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.vianet.open.exceptions.IMIRpcException;
import com.vianet.open.factory.JwtDataParseFactory;
import com.vianet.open.info.AuthorizationInfo;
import com.vianet.open.info.ChannelInfo;
import com.vianet.open.json.JsonDataInfo;
import com.vianet.open.router.IMIAuthorizationRouter;
import com.vianet.open.util.HttpParamsUtil;
import com.vianet.open.vo.GetAuthorizationInfoParams;
import com.vianet.open.vo.GetChannelInfoParams;

public class RouterTest {

    @Test
    public String testCreateChannel() {
        try {

            // 版本数据与授权域数据根据JS-SDK定义
            String version = "1.0.0";
            Set<String> scope = new HashSet<String>();
            scope.add("snsapi_info");
            scope.add("snsapi_idcard");

            GetChannelInfoParams params = new GetChannelInfoParams();
            params.setScope(scope);
            params.setVersion(version);

            ChannelInfo info = IMIAuthorizationRouter.createChannel(params);
            System.out.printf("createChannel 响应：ChannelInfo=[%s]\n", JSONObject.toJSONString(info));
            return info.getTopicId();
        } catch (IMIRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testGetAuthorizationInfo() {
        try {

            String topicId = "82ece22d6a09492d8d0d5a5757e58ce2";

            GetAuthorizationInfoParams params = new GetAuthorizationInfoParams();
            params.setTopicId(topicId);
            params.setScope(JwtDataParseFactory.SCOPE_POOL);

            AuthorizationInfo authorizeInfo = IMIAuthorizationRouter.getAuthorizationInfo(params);
            System.out.printf("getAuthorizationInfo 响应：AuthorizationInfo=[%s]\n", JSONObject.toJSONString(authorizeInfo));
        } catch (IMIRpcException e) {
            e.printStackTrace();
        }
    }

    private static void createJWT(){

        String snsapi_info = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NksifQ==.eyJjaGFyc2V0IjoidXRmLTgiLCJjbGFpbSI6eyJhc3NlcnRpb24iOnsiaW1hZ2UiOnsiZGF0YSI6Ii9pcGZzL1FtZG1RMXh3UXdINHl0MzRIcTFlbmlOZEFwZ2hhMmYydG42VVlyMUVjTVBFdTkiLCJ0eXBlIjoiL2ltYWdlL3BuZyJ9LCJtb2JpbGUiOiIxMzY4MzIzNjczOCIsInVzZXJOYW1lIjoi5Lir5Lir5q2j5byPIiwiZW1haWwiOiIifX0sInR5cGUiOiJVc2VyTG9naW5JbmZvQ2xhaW0iLCJAY29udGV4dCI6Imh0dHA6Ly93d3cuYmxvY2tjZXJ0cy5vcmcvc2NoZW1hLzEuMi9jb250ZXh0Lmpzb24iLCJpYXQiOjE0OTkwNTI2NTM4MjIsImlzc3VlciI6eyJuYW1lIjoi5Lir5Lir5q2j5byPIiwiaGFzaGVkIjpmYWxzZSwicHVibGljS2V5IjoiMHhiMTc1ZmVkNmJkYmUxZjhkYjRjZWJhM2RhOWE3MDdkMWEwY2U3MzFkIiwidnBvcnRJZCI6IjB4NDU3YjE1NzNkODE3MzJjY2FhOGQ4Mjg3Mjc5YmZhNDhhOTE1ZWNmMSJ9fQ==./vQwAwJi7eAYNp6UCLlUAmP8XAZrYCejb7mq/snt83MeV3VWfYWWE44KSS8IcMoX4/PUSxn9BIr0Jh1ix/zmHhw=";
        String snsapi_idcard = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NksifQ==.eyJAY29udGV4dCI6ICJodHRwOi8vd3d3LmJsb2NrY2VydHMub3JnL3NjaGVtYS8xLjIvY29udGV4dC5qc29uIiwidHlwZSI6ICJDaXRpemVuSWRlbnRpdHlDZXJ0aWZpY2F0ZSIsImNoYXJzZXQiOiAidXRmLTgiLCJpYXQiOiAxNDk5MDQ5MzY4MDAwLCJqd3QiOiAiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKRlV6STFOa3NpZlE9PS5leUpqYUdGeWMyVjBJam9pZFhSbUxUZ2lMQ0pqYkdGcGJTSTZleUpoYzNObGNuUnBiMjRpT25zaWFXMWhaMlVpT25zaVpHRjBZU0k2SWk5cGNHWnpMeUlzSW5SNWNHVWlPaUl2YVcxaFoyVXZTbEJISW4wc0ltUnZZaUk2SWlJc0luTmxlQ0k2SXVXbHN5SXNJbUYxZEdodmNtbDBlU0k2SXVXeHNlUzRuQ0lzSW01aGJXVWlPaUxtc2FUa3VhYmt1cG9pTENKamFXNGlPaUl6TnpJNU1qZ3hPVGt3TURJd016RXhNaklpTENKa2IyVWlPaUl5TURJd0xUQTJMVEU1SWl3aVpHOXBJam9pTWpBeE5DMHdOaTB4T1NKOUxDSjBlWEJsSWpvaTVMaXQ1WTJPNUxxNjVyQ1I1WVd4NVpLTTVadTk1YkdGNXJDUjZMcXI1THU5NksrQkluMHNJblI1Y0dVaU9pSkRhWFJwZW1WdVNXUmxiblJwZEhsRGJHRnBiU0lzSWtCamIyNTBaWGgwSWpvaWFIUjBjRG92TDNkM2R5NWliRzlqYTJObGNuUnpMbTl5Wnk5elkyaGxiV0V2TVM0eUwyTnZiblJsZUhRdWFuTnZiaUlzSW1saGRDSTZNVFE1T1RBME9USTNOekU0T0N3aWFYTnpkV1Z5SWpwN0ltNWhiV1VpT2lMbXNhVGt1YWJrdXBvaUxDSm9ZWE5vWldRaU9tWmhiSE5sTENKd2RXSnNhV05MWlhraU9pSXdlR0l4TnpWbVpXUTJZbVJpWlRGbU9HUmlOR05sWW1FelpHRTVZVGN3TjJReFlUQmpaVGN6TVdRaUxDSjJjRzl5ZEVsa0lqb2lNSGcwTlRkaU1UVTNNMlE0TVRjek1tTmpZV0U0WkRneU9EY3lOemxpWm1FME9HRTVNVFZsWTJZeEluMTkubmhGYktEM1ZBOW5MNklFRDRrQXVqd0pxQlI3RHBRenhCQ2VUWkVyL2NmOU93d05kNnpJd0xoVjRjMlVodzliUldNWVBoZWNDVUNzOTFiMjZEVURuMHhzPSIsImlzc3VlciI6IHsibmFtZSI6ICLkvZvlsbHluILnpoXln47ljLrooYzmlL/mnI3liqHkuK3lv4PmvJTnpLoiLCJ2cG9ydElkIjogIjB4NDQ1NGQ2ZTRjNDgxMDk2ODhjN2FiOWYwYTg4ZWM3Yjk1N2U2ODY4MiIsImhhc2hlZCI6IGZhbHNlLCJwdWJsaWNLZXkiOiAiMHhmYTc0Mzc3ZjViOTQ4MjQ3NmMyMjI1ZmQyYzk2NTlmZWQ4NWFmNTczIn19.gOgbOwJUhOHsBL4fk+jBIhpa+QBFguXI8NiHD/AY28kdRK0VEmgt86Wm6lds8hNr43NOo3/nOMleeSAWJWReQRw=";

        JSONObject payload = new JSONObject();
        payload.put("snsapi_info", snsapi_info);
        payload.put("snsapi_idcard", snsapi_idcard);

        System.out.println("payload: " + payload.toJSONString());

        JsonDataInfo jsonDataInfo = JSONObject.parseObject(payload.toJSONString(), JsonDataInfo.class);

        System.out.println("jsonDataInfo: " + jsonDataInfo.toString());


        String bean2jsonStrWithJWT = HttpParamsUtil.bean2JSONStrWithJWT(jsonDataInfo);
        System.out.println("jwt: " + bean2jsonStrWithJWT);


        /**
         * {"jwt":"eyJhbGciOiJFUzI1NksiLCJ0eXAiOiJKV1QifQ==.eyJAY29udGV4dCI6Imh0dHA6Ly93d3cuYmxvY2tjZXJ0cy5vcmcvc2NoZW1hLzEuMi9jb250ZXh0Lmpzb24iLCJzbnNhcGlfaWRjYXJkIjoiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKRlV6STFOa3NpZlE9PS5leUpBWTI5dWRHVjRkQ0k2SUNKb2RIUndPaTh2ZDNkM0xtSnNiMk5yWTJWeWRITXViM0puTDNOamFHVnRZUzh4TGpJdlkyOXVkR1Y0ZEM1cWMyOXVJaXdpZEhsd1pTSTZJQ0pEYVhScGVtVnVTV1JsYm5ScGRIbERaWEowYVdacFkyRjBaU0lzSW1Ob1lYSnpaWFFpT2lBaWRYUm1MVGdpTENKcFlYUWlPaUF4TkRrNU1EUTVNelk0TURBd0xDSnFkM1FpT2lBaVpYbEtNR1ZZUVdsUGFVcExWakZSYVV4RFNtaGlSMk5wVDJsS1JsVjZTVEZPYTNOcFpsRTlQUzVsZVVwcVlVZEdlV015VmpCSmFtOXBaRmhTYlV4VVoybE1RMHBxWWtkR2NHSlRTVFpsZVVwb1l6Tk9iR051VW5CaU1qUnBUMjV6YVdGWE1XaGFNbFZwVDI1emFWcEhSakJaVTBrMlNXazVjR05IV25wTWVVbHpTVzVTTldOSFZXbFBhVWwyWVZjeGFGb3lWWFpUYkVKSVNXNHdjMGx0VW5aWmFVazJTV2xKYzBsdVRteGxRMGsyU1hWWGJITjVTWE5KYlVZeFpFZG9kbU50YkRCbFUwazJTWFZYZUhObFV6UnVRMGx6U1cwMWFHSlhWV2xQYVV4dGMyRlVhM1ZoWW10MWNHOXBURU5LYW1GWE5HbFBhVWw2VG5wSk5VMXFaM2hQVkd0M1RVUkpkMDE2UlhoTmFrbHBURU5LYTJJeVZXbFBhVWw1VFVSSmQweFVRVEpNVkVVMVNXbDNhVnBIT1hCSmFtOXBUV3BCZUU1RE1IZE9hVEI0VDFOS09VeERTakJsV0VKc1NXcHZhVFZNYVhRMVdUSlBOVXh4TmpWeVExSTFXVmQ0TlZwTFRUVmFkVGsxWWtkR05YSkRValpNY1hJMVRIVTVOa3NyUWtsdU1ITkpibEkxWTBkVmFVOXBTa1JoV0ZKd1pXMVdkVk5YVW14aWJsSndaRWhzUkdKSFJuQmlVMGx6U1d0Q2FtSXlOVEJhV0dnd1NXcHZhV0ZJVWpCalJHOTJURE5rTTJSNU5XbGlSemxxWVRKT2JHTnVVbnBNYlRsNVduazVlbGt5YUd4aVYwVjJUVk0wZVV3eVRuWmlibEpzWlVoUmRXRnVUblppYVVselNXMXNhR1JEU1RaTlZGRTFUMVJCTUU5VVNUTk9la1UwVDBOM2FXRllUbnBrVjFaNVNXcHdOMGx0TldoaVYxVnBUMmxNYlhOaFZHdDFZV0pyZFhCdmFVeERTbTlaV0U1dldsZFJhVTl0V21oaVNFNXNURU5LZDJSWFNuTmhWMDVNV2xocmFVOXBTWGRsUjBsNFRucFdiVnBYVVRKWmJWSnBXbFJHYlU5SFVtbE9SMDVzV1cxRmVscEhSVFZaVkdOM1RqSlJlRmxVUW1wYVZHTjZUVmRSYVV4RFNqSmpSemw1WkVWc2EwbHFiMmxOU0djd1RsUmthVTFVVlROTk1sRTBUVlJqZWsxdFRtcFpWMFUwV2tSbmVVOUVZM2xPZW14cFdtMUZNRTlIUlRWTlZGWnNXVEpaZUVsdU1Ua3VibWhHWWt0RU0xWkJPVzVNTmtsRlJEUnJRWFZxZDBweFFsSTNSSEJSZW5oQ1EyVlVXa1Z5TDJObU9VOTNkMDVrTm5wSmQweG9WalJqTWxWb2R6bGlVbGROV1ZCb1pXTkRWVU56T1RGaU1qWkVWVVJ1TUhoelBTSXNJbWx6YzNWbGNpSTZJSHNpYm1GdFpTSTZJQ0xrdlp2bHNiSGx1SUxucG9YbG40N2xqTHJvb1l6bWxML21uSTNsaXFIa3VLM2x2NFBtdkpUbnBMb2lMQ0oyY0c5eWRFbGtJam9nSWpCNE5EUTFOR1EyWlRSak5EZ3hNRGsyT0Roak4yRmlPV1l3WVRnNFpXTTNZamsxTjJVMk9EWTRNaUlzSW1oaGMyaGxaQ0k2SUdaaGJITmxMQ0p3ZFdKc2FXTkxaWGtpT2lBaU1IaG1ZVGMwTXpjM1pqVmlPVFE0TWpRM05tTXlNakkxWm1ReVl6azJOVGxtWldRNE5XRm1OVGN6SW4xOS5nT2diT3dKVWhPSHNCTDRmaytqQklocGErUUJGZ3VYSThOaUhEL0FZMjhrZFJLMFZFbWd0ODZXbTZsZHM4aE5yNDNOT28zL25PTWxlZVNBV0pXUmVRUnc9IiwiaXNzdWVyIjp7InB1YmxpY0tleSI6IjB4NzQzMzUyZjc3MDc4YTEyZjMwZDM3ZDAxNzgzNzA2ZDViNmRmZjgwOSIsImhhc2hlZCI6ZmFsc2UsInZwb3J0SWQiOiIweDkxMzFkMTllZDBiNjY2ZWI1Y2MxY2U3OGM4YmY1MmU0ZDA4MmRmNTUiLCJuYW1lIjoiRGVmYXVsdE5hbWUifSwic25zYXBpX2luZm8iOiJleUowZVhBaU9pSktWMVFpTENKaGJHY2lPaUpGVXpJMU5rc2lmUT09LmV5SmphR0Z5YzJWMElqb2lkWFJtTFRnaUxDSmpiR0ZwYlNJNmV5SmhjM05sY25ScGIyNGlPbnNpYVcxaFoyVWlPbnNpWkdGMFlTSTZJaTlwY0daekwxRnRaRzFSTVhoM1VYZElOSGwwTXpSSWNURmxibWxPWkVGd1oyaGhNbVl5ZEc0MlZWbHlNVVZqVFZCRmRUa2lMQ0owZVhCbElqb2lMMmx0WVdkbEwzQnVaeUo5TENKdGIySnBiR1VpT2lJeE16WTRNekl6Tmpjek9DSXNJblZ6WlhKT1lXMWxJam9pNUxpcjVMaXI1cTJqNWJ5UElpd2laVzFoYVd3aU9pSWlmWDBzSW5SNWNHVWlPaUpWYzJWeVRHOW5hVzVKYm1adlEyeGhhVzBpTENKQVkyOXVkR1Y0ZENJNkltaDBkSEE2THk5M2QzY3VZbXh2WTJ0alpYSjBjeTV2Y21jdmMyTm9aVzFoTHpFdU1pOWpiMjUwWlhoMExtcHpiMjRpTENKcFlYUWlPakUwT1Rrd05USTJOVE00TWpJc0ltbHpjM1ZsY2lJNmV5SnVZVzFsSWpvaTVMaXI1TGlyNXEyajVieVBJaXdpYUdGemFHVmtJanBtWVd4elpTd2ljSFZpYkdsalMyVjVJam9pTUhoaU1UYzFabVZrTm1Ka1ltVXhaamhrWWpSalpXSmhNMlJoT1dFM01EZGtNV0V3WTJVM016RmtJaXdpZG5CdmNuUkpaQ0k2SWpCNE5EVTNZakUxTnpOa09ERTNNekpqWTJGaE9HUTRNamczTWpjNVltWmhORGhoT1RFMVpXTm1NU0o5ZlE9PS4vdlF3QXdKaTdlQVlOcDZVQ0xsVUFtUDhYQVpyWUNlamI3bXEvc250ODNNZVYzVldmWVdXRTQ0S1NTOEljTW9YNC9QVVN4bjlCSXIwSmgxaXgvem1IaHc9IiwidHlwZSI6IkFwaUNvbW11bmljYXRpb25QYXJhbWV0ZXIiLCJpYXQiOjE1MDAyOTEyMjU5OTh9.61u6aklLvwfPA3CMbzdN1PQOFvulj64DQoMW8fiW5QBj8BHhG2bw8MR4lmZiHZDIBQsDDJpp09oe51VdsCk7oBs="}

         */

    }

    public static void main(String[] args) {

//        String imiConfigPath = "/home/imi/imi-config.properties";
//        String imiKsPath = "/home/imi/imi-ks";
//
//        try {
//            IMIConfiguration.initConfigPath(imiConfigPath, imiKsPath);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        RouterTest tester = new RouterTest();

        tester.testGetAuthorizationInfo();

//        createJWT();








//        String snsapi_info = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NksifQ==.eyJjaGFyc2V0IjoidXRmLTgiLCJjbGFpbSI6eyJhc3NlcnRpb24iOnsiaW1hZ2UiOnsiZGF0YSI6Ii9pcGZzL1FtZG1RMXh3UXdINHl0MzRIcTFlbmlOZEFwZ2hhMmYydG42VVlyMUVjTVBFdTkiLCJ0eXBlIjoiL2ltYWdlL3BuZyJ9LCJtb2JpbGUiOiIxMzY4MzIzNjczOCIsInVzZXJOYW1lIjoi5Lir5Lir5q2j5byPIiwiZW1haWwiOiIifX0sInR5cGUiOiJVc2VyTG9naW5JbmZvQ2xhaW0iLCJAY29udGV4dCI6Imh0dHA6Ly93d3cuYmxvY2tjZXJ0cy5vcmcvc2NoZW1hLzEuMi9jb250ZXh0Lmpzb24iLCJpYXQiOjE0OTkwNTI2NTM4MjIsImlzc3VlciI6eyJuYW1lIjoi5Lir5Lir5q2j5byPIiwiaGFzaGVkIjpmYWxzZSwicHVibGljS2V5IjoiMHhiMTc1ZmVkNmJkYmUxZjhkYjRjZWJhM2RhOWE3MDdkMWEwY2U3MzFkIiwidnBvcnRJZCI6IjB4NDU3YjE1NzNkODE3MzJjY2FhOGQ4Mjg3Mjc5YmZhNDhhOTE1ZWNmMSJ9fQ==./vQwAwJi7eAYNp6UCLlUAmP8XAZrYCejb7mq/snt83MeV3VWfYWWE44KSS8IcMoX4/PUSxn9BIr0Jh1ix/zmHhw=";
//        String snsapi_idcard = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NksifQ==.eyJAY29udGV4dCI6ICJodHRwOi8vd3d3LmJsb2NrY2VydHMub3JnL3NjaGVtYS8xLjIvY29udGV4dC5qc29uIiwidHlwZSI6ICJDaXRpemVuSWRlbnRpdHlDZXJ0aWZpY2F0ZSIsImNoYXJzZXQiOiAidXRmLTgiLCJpYXQiOiAxNDk5MDQ5MzY4MDAwLCJqd3QiOiAiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKRlV6STFOa3NpZlE9PS5leUpqYUdGeWMyVjBJam9pZFhSbUxUZ2lMQ0pqYkdGcGJTSTZleUpoYzNObGNuUnBiMjRpT25zaWFXMWhaMlVpT25zaVpHRjBZU0k2SWk5cGNHWnpMeUlzSW5SNWNHVWlPaUl2YVcxaFoyVXZTbEJISW4wc0ltUnZZaUk2SWlJc0luTmxlQ0k2SXVXbHN5SXNJbUYxZEdodmNtbDBlU0k2SXVXeHNlUzRuQ0lzSW01aGJXVWlPaUxtc2FUa3VhYmt1cG9pTENKamFXNGlPaUl6TnpJNU1qZ3hPVGt3TURJd016RXhNaklpTENKa2IyVWlPaUl5TURJd0xUQTJMVEU1SWl3aVpHOXBJam9pTWpBeE5DMHdOaTB4T1NKOUxDSjBlWEJsSWpvaTVMaXQ1WTJPNUxxNjVyQ1I1WVd4NVpLTTVadTk1YkdGNXJDUjZMcXI1THU5NksrQkluMHNJblI1Y0dVaU9pSkRhWFJwZW1WdVNXUmxiblJwZEhsRGJHRnBiU0lzSWtCamIyNTBaWGgwSWpvaWFIUjBjRG92TDNkM2R5NWliRzlqYTJObGNuUnpMbTl5Wnk5elkyaGxiV0V2TVM0eUwyTnZiblJsZUhRdWFuTnZiaUlzSW1saGRDSTZNVFE1T1RBME9USTNOekU0T0N3aWFYTnpkV1Z5SWpwN0ltNWhiV1VpT2lMbXNhVGt1YWJrdXBvaUxDSm9ZWE5vWldRaU9tWmhiSE5sTENKd2RXSnNhV05MWlhraU9pSXdlR0l4TnpWbVpXUTJZbVJpWlRGbU9HUmlOR05sWW1FelpHRTVZVGN3TjJReFlUQmpaVGN6TVdRaUxDSjJjRzl5ZEVsa0lqb2lNSGcwTlRkaU1UVTNNMlE0TVRjek1tTmpZV0U0WkRneU9EY3lOemxpWm1FME9HRTVNVFZsWTJZeEluMTkubmhGYktEM1ZBOW5MNklFRDRrQXVqd0pxQlI3RHBRenhCQ2VUWkVyL2NmOU93d05kNnpJd0xoVjRjMlVodzliUldNWVBoZWNDVUNzOTFiMjZEVURuMHhzPSIsImlzc3VlciI6IHsibmFtZSI6ICLkvZvlsbHluILnpoXln47ljLrooYzmlL/mnI3liqHkuK3lv4PmvJTnpLoiLCJ2cG9ydElkIjogIjB4NDQ1NGQ2ZTRjNDgxMDk2ODhjN2FiOWYwYTg4ZWM3Yjk1N2U2ODY4MiIsImhhc2hlZCI6IGZhbHNlLCJwdWJsaWNLZXkiOiAiMHhmYTc0Mzc3ZjViOTQ4MjQ3NmMyMjI1ZmQyYzk2NTlmZWQ4NWFmNTczIn19.gOgbOwJUhOHsBL4fk+jBIhpa+QBFguXI8NiHD/AY28kdRK0VEmgt86Wm6lds8hNr43NOo3/nOMleeSAWJWReQRw=";
//
//        JSONObject data = new JSONObject();
//        data.put("snsapi_info", snsapi_info);
//        data.put("snsapi_idcard", snsapi_idcard);
//
//        JSONObject result = new JSONObject();
//        result.put("data", data);
//
//        JSONObject ret = new JSONObject();
//        ret.put("result", result);
//        ret.put("retCode", "0000000");
//        ret.put("retInfo", "success");
//
//
//        System.out.println(ret);

//        PullDataResponse res = JSONObject.parseObject(ret.toJSONString(), PullDataResponse.class);
//
//        System.out.println(res.toString());

    }

}
