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
   
 ## 四、开发准备

1. 需要在php服务器端安装php-scrypt，secp256k1-php，sha3扩展，需打开PHP自带扩展mcrypt。 

2. php-scrypt：https://github.com/DomBlack/php-scrypt 

3. secp256k1-php：https://github.com/beyonderyue/secp256k1-php（注意PHP5请用v0.0分支，PHP7请用v0.1分支） 

4. sha3：https://github.com/beyonderyue/php-sha3 
## 五、常见问题

1.编译secp256k1-php时看清自己服务器PHP版本，PHP5和PHP7所需下载版本不同，仔细看清楚项目中的README.md的描述。

2.安装sha3时如果是PHP5没有问题，如果是PHP7需要根据编译make时报错信息修改，

  如：
  
  - /home/***/php-sha3-master/php_sha3.c: In function ‘zif_sha3’:
    /home/***/php-sha3-master/php_sha3.c:56:51: error: macro "RETURN_STRINGL" passed 3 arguments, but takes just 2
    RETURN_STRINGL(hashval, hashbytelen, 1)，根据提示修改php_sha3.c文件第56行，把RETURN_STRINGL(hashval, hashbytelen, 1)的第三个参数去掉就即可.
    
  - /home/***/php-sha3-master/php_sha3.c:62:33: error: macro "RETURN_STRING" passed 2 arguments, but takes just 1
      RETURN_STRING(hex, 1),修改php_sha3.c文件第62行，把RETURN_STRING(hex, 1)第二个参数去掉即可。

3.所有服务器环境配置完成后，如果调用服务器接口，报错如：Invalid Password Provided!，很大可能是sha3不是用的上面"开发准备"中提供的版本，目前github上只有上面提供的sha3版本可用。
