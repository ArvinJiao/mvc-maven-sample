It's a MVC sample

-- ----------------------------
-- Table structure for users
-- ----------------------------

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '',
  `age` int(11) unsigned NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


tomcat启动:
tomcat:run -Dmaven.tomcat.port=80 -Dmaven.tomcat.path=/ 
jetty启动:
jetty:run -Djetty.port=80
-Djava.net.preferIPv4Stack=true 用来指定ipv4,如出现'Unable to establish loopback connection'异常可以加上该配置