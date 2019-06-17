/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - ykg
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ykg` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ykg`;

/*Table structure for table `com` */

DROP TABLE IF EXISTS `com`;

CREATE TABLE `com` (
  `username` varchar(10) DEFAULT NULL,
  `state` varchar(11) DEFAULT NULL,
  `site` varchar(10) DEFAULT NULL,
  `CODE` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `com` */

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `site` varchar(10) DEFAULT NULL,
  `CODE` varchar(10) DEFAULT NULL,
  `exist` char(1) NOT NULL COMMENT '1:使用 0:停用',
  `shopnmae` varchar(20) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `pic` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `createtime` datetime NOT NULL COMMENT '生产日期',
  PRIMARY KEY (`iid`),
  CONSTRAINT `fk_user_item` FOREIGN KEY (`iid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `items` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `site` varchar(10) DEFAULT NULL,
  `CODE` varchar(10) DEFAULT NULL,
  `exist` char(1) NOT NULL COMMENT '1:使用 0:停用',
  `username` varchar(100) DEFAULT NULL,
  `shopnmae` varchar(20) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `pic` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `createtime` datetime NOT NULL COMMENT '生产日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`site`,`CODE`,`exist`,`username`,`shopnmae`,`money`,`number`,`pic`,`createtime`) values (1,'00','01','1','00','黑人',9,10,'null','2018-01-12 00:00:00'),(2,'00','02','1','01','云南白药',19.9,10,'null','2018-01-12 00:00:00'),(3,'00','03','1','02','佳洁士',3.5,10,'null','2018-01-12 00:00:00'),(4,'01','01','1','00','签字笔',2,10,'null','2018-01-02 00:00:00'),(5,'01','02','1','01','按动中性笔',3,10,'null','2018-01-12 00:00:00'),(6,'01','03','1','02','黑笔',1.5,10,'null','2018-01-12 00:00:00'),(7,'02','01','1','00','爱国者静音鼠标',38.9,10,'null','2018-01-12 00:00:00'),(8,'02','02','1','01','英菲克静音充电鼠标',36.8,10,'null','2018-01-12 00:00:00'),(9,'02','03','1','02','Razer游戏鼠标',469,10,'null','2018-01-12 00:00:00');

/*Table structure for table `pro` */

DROP TABLE IF EXISTS `pro`;

CREATE TABLE `pro` (
  `pid` int(11) NOT NULL,
  `pname` varchar(20) DEFAULT NULL,
  `shop_price` varchar(20) DEFAULT NULL,
  `image` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `site` varchar(20) DEFAULT NULL,
  `CODE` varchar(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pro` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(11) DEFAULT NULL,
  `site` varchar(10) DEFAULT NULL,
  `CODE` varchar(10) DEFAULT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`pid`,`state`,`site`,`CODE`,`pname`,`shop_price`,`image`) values (1,'1','00','01','黑人',9,'null'),(2,'1','00','02','云南白药',19.9,'null'),(3,'1','00','03','佳洁士',3.5,'null'),(4,'1','01','11','签字笔',2,'null'),(5,'1','01','12','按动中性笔',3,'null'),(6,'1','01','13','黑笔',1.5,'null'),(7,'1','02','21','爱国者静音鼠标',38.9,'null'),(8,'1','02','22','英菲克静音充电鼠标',36.8,'null'),(9,'1','02','23','Razer游戏鼠标',469,'null');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`) values (1,'01'),(2,'02'),(3,'03');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`uid`,`uname`) values (1,'00'),(2,'01'),(3,'02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
