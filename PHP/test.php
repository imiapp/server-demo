<?php
require_once('lib/Jwt.class.php');

$scope = array('snsapi_idcard','snsapi_info');//需要的权限
$jwt = new Jwt('/imi/imi-config.properties', '/imi/imi-ks');//配置文件位置

//获取topicId
$channel = $jwt->createChannel($scope);
$topicid=$channel['topicId'];
echo "[topicid: $topicid ]";
//获取用户信息,获取前需模拟app扫码
 $info=$jwt->getAuthorizationInfo("34832ed3565c4ee7824af67074a65776",$scope);
 print_r($info);

 
 
 
 
?>
