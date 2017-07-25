# server-demo-java

> 本目录为java版server-sdk对接示例代码目录：

## 一、目录描述：

- server-demo-src-x.x.x为示例代码目录，**x.x.x**为版本号；


- “Server-SDK对接Demo代码说明x.x.x.pdf”为代码说明文档，及http接口设计参考；此文档的版本号与server-demo-src的版本号一一对应。

## 二、代码描述：

- server-demo从**v1.0.2**开始已经集成了JavaScript-SDK代码，可以直接运行本示例代码进行演示页面访问，首页访问地址：**http://host:port/index**

## 三、业务逻辑描述：

> 授权交互调用逻辑：

1. 首先通过SDK调用`IMIAuthorizationRouter.createChannel()`接口创建MappingServer通信通道，将返回的通道信息`ChannelInfo`响应给第三方页面（生成二维码）或第三方app；

2. 第三方页面或第三方App需要获取授权信息则通过SDK调用`IMIAuthorizationRouter.getAuthorizationInfo()`接口获取授权信息`AuthorizationInfo`：

   a、若`AuthorizationInfo`为`null`则说明从mapping server上未收到app推送的数据，正常返回，等待下一次获取；

   b、若`AuthorizationInfo`非`null`则说明正常获取到app推送的授权信息，此时获取的授权人信息Authorizer一定是非`null`的，而授权登录用户信息`LoginUserInfo`与授权身份信息`IdentityCardInfo`就不一定非`null`，根据接口调用时传的参数scope决定；

3. 第三方用户Http接口响应信息`AuthorizationInfoRes`可根据业务需求自行从授权信息`AuthorizationInfo`对象中获取并组装。

