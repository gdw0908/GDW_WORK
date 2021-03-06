/*
SQLyog Enterprise - MySQL GUI v8.0
Host - 8.0.12-community-nt : Database - gdw
*********************************************************************
Server version : 8.0.12-community-nttt
*/

SET NAMES UTF8;

SET SQL_MODE='';

CREATE DATABASE IF NOT EXISTS `GDW`;

USE `GDW`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `shop_admin` */
DROP TABLE IF EXISTS `GDW_ADMIN`;

CREATE TABLE `GDW_ADMIN` (
  `ADMIN_ID` CHAR(11) NOT NULL  COMMENT  '관리자 ID',
  `ADMIN_PASS` VARCHAR(100) DEFAULT NULL COMMENT  '관리자 패스워드',
  `ADMIN_NAME` VARCHAR(20) DEFAULT NULL COMMENT  '관리자 이름',
  `ADMIN_USE_YN`  CHAR(1) DEFAULT NULL COMMENT  '관리자 사용여부',
  `ADMIN_GRADE_FLAG` CHAR(1) DEFAULT NULL COMMENT  '관리자 권한',
  `REG_USERID` VARCHAR(20) DEFAULT NULL COMMENT  '등록자',
  `REG_DTTIME` DATETIME DEFAULT NULL COMMENT  '등록일자',
  `MOD_USERID` VARCHAR(20) DEFAULT NULL COMMENT  '수정자',
  `MOD_DTTIME` DATETIME DEFAULT NULL COMMENT  '수정일자',
  PRIMARY KEY  (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '관라자 테이블';
/*Data for the table `shop_admin` */


/*DATA FOR THE TABLE `SHOP_PRODUCT_QUESTION` */

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
