# SpringBootDemo
SpringBoot+SSM框架整合，最简单的实例！

完整教程与说明见：[SpringBoot项目创建与第一个SSM项目示例](https://blog.csdn.net/qq_28379809/article/details/83218797)

该项目为DEMO，仅完成了示例登录功能，若需其他功能请自行添加。

注意，系统运行需要建立对应的数据库和表。
本地导入前请先执行一下resource文件夹下的umanager.sql文件，或者参照下面的说明建库和表。

下面是建表的语句：
```
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'ja', '123', '江苏');
INSERT INTO `user` VALUES ('2', 'BL', '123', '新加坡');
