本SDK实现的功能是从服务器获取通信topicId和读取jwt文件。本SDK内已实现jwt的验签功能。

开发准备：
	1：需要在php服务器端载入keystore，sign两个类库，安装方式请参照具体类库内的README.md文件。

开发DEMO：
	开发demo请参照test.php
	demo说明：	
	1：首先要加载lib/jwt.class.php。
		例：require_once 'lib/jwt.class.php';
		jwt.class.php引用了KeyStore.class.php(KeyStore读写类库)和SignatureVerify.class.php(签名和验签类库)类库。
	
	2:初始化jwt类：
		$scope = array('snsapi_idcard','snsapi_info');//需要的权限，snsapi_idcard为用户身份证信息，snsapi_info为用户信息，改参数未来会有扩展。
		$jwt = new Jwt('/imi/imi-config.properties', '/imi/imi-ks');//配置文件位置
	
	3：调用需要的方法，目前本SDK对外使用的方法有获取topicId方法(createChannel)和获取信息方法(getAuthorizationInfo).
		createChannel：$channel = $jwt->createChannel($scope);  //返回数组包含topicId。
		getAuthorizationInfo：$info=$jwt->getAuthorizationInfo($topicId,$scope); //返回所有符合scope的用户数据。

	4：KeyStore.class.php和SignatureVerify.class.php安装说明：
		4.1：需要在php服务器端安装php-scrypt，secp256k1-php，sha3扩展，需打开PHP自带扩展mcrypt。
		4.2：php-scrypt：https://github.com/DomBlack/php-scrypt
		4.3：secp256k1-php：https://github.com/beyonderyue/secp256k1-php（注意PHP5请用v0.0分支，PHP7请用v0.1分支）
		4.4：sha3：https://github.com/beyonderyue/php-sha3
