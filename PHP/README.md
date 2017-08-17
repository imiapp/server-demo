# server-demo-php

> 本目录为[php版server-sdk](https://github.com/imiapp/imi-sdk/tree/master/Server-SDK/PHP/Server)对接示例代码目录：

## 一、目录描述：

- html_client为客户端目录，主要为演示客户端如何连接php服务端；

- php_server为服务端目录。

## 二、代码描述：

- html_client已经集成了IMI [JavaScript-SDK](https://github.com/imiapp/imi-sdk/tree/master/Server-SDK/JavaScript)代码，可以直接运行本示例代码进行演示页面访问，访问地址：**http://172.16.192.105:85/imi/client/vport.html**
- php_server集成了IMI[PHP-SDK](https://github.com/imiapp/imi-sdk/tree/master/Server-SDK/PHP)

## 三、业务逻辑描述：

> 授权交互调用逻辑：

1. 首先通过PHP SDK调用`$jwt->createChannel`接口创建MappingServer通信通道，将返回的通道信息`ChannelInfo`响应给第三方页面（生成二维码）或第三方app；

2. 第三方页面或第三方App需要获取授权信息则通过SDK调用`$jwt->getAuthorizationInfo();`接口获取授权信息`AuthorizationInfo`：

   a、若`AuthorizationInfo`为`null`则说明从mapping server上未收到app推送的数据，正常返回，等待下一次获取；

   b、若`AuthorizationInfo`非`null`则说明正常获取到app推送的授权信息，此时获取的授权人信息Authorizer一定是非`null`的，而授权登录用户信息`LoginUserInfo`与授权身份信息`IdentityCardInfo`就不一定非`null`，根据接口调用时传的参数scope决定；