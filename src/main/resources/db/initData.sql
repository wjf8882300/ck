/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.0.18-nt : Database - ck
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ck` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ck`;

/*Data for the table `ck_t_login_log` */

insert  into `ck_t_login_log`(`id`,`user_id`,`record_status`,`ip_address`,`create_user`,`create_date`,`last_update_user`,`last_update_date`,`version`,`memo`) values ('2f3b0768-1959-4f91-b514-8cc252aa3924','1','有效',NULL,'1','2018-05-27','1','2018-05-27',0,NULL),('91483632-3b10-424d-b75f-6c68a3a94f1f','1','有效',NULL,'1','2018-05-27','1','2018-05-27',0,NULL),('96f574a7-d487-46f5-afc4-6291b824050f','1','有效',NULL,'1','2018-05-27','1','2018-05-27',0,NULL),('b414ebca-fcb6-4066-9289-e5c738a6c3f9','1','有效',NULL,'1','2018-05-27','1','2018-05-27',0,NULL),('c0a16a6a-00dd-4423-a0d7-1fb7bb2ddfeb','1','有效',NULL,'1','2018-05-27','1','2018-05-27',0,NULL);

/*Data for the table `ck_t_menu` */

insert  into `ck_t_menu`(`id`,`menu_name`,`menu_level`,`menu_flag`,`menu_url`,`parent_id`,`menu_icon`,`menu_desc`,`is_enabled`,`menu_type`,`menu_sort`,`record_status`,`create_user`,`create_date`,`last_update_user`,`last_update_date`,`version`,`memo`) values ('1','基础管理','1','menu_system','/','0','','','01','01',0,'有效','1','2018-05-27','1','2018-05-27',0,NULL),('2','用户管理','2','menu_user','/ck/user','1','','','01','01',1,'有效','1','2018-05-27','1','2018-05-27',0,NULL),('3','角色管理','2','menu_role','/ck/role','1','','','01','01',2,'有效','1','2018-05-27','1','2018-05-27',0,NULL),('4','菜单管理','2','menu_menu','/ck/menu','1','','','01','01',3,'有效','1','2018-05-27','1','2018-05-27',0,NULL);

/*Data for the table `ck_t_role` */

insert  into `ck_t_role`(`id`,`role_name`,`role_desc`,`role_key`,`record_status`,`create_user`,`create_date`,`last_update_user`,`last_update_date`,`version`,`memo`) values ('1','系统管理员','','ROLE_ADMIN','有效','1','2018-05-27','1','2018-05-27',0,NULL);

/*Data for the table `ck_t_role_menu` */

insert  into `ck_t_role_menu`(`id`,`role_id`,`menu_id`,`record_status`,`create_user`,`create_date`,`last_update_user`,`last_update_date`,`version`,`memo`) values ('1','1','1','有效','1','2018-05-27','1','2018-05-27',0,NULL),('2','1','2','有效','1','2018-05-27','1','2018-05-27',0,NULL),('3','1','3','有效','1','2018-05-27','1','2018-05-27',0,NULL),('4','1','4','有效','1','2018-05-27','1','2018-05-27',0,NULL);

/*Data for the table `ck_t_user` */

insert  into `ck_t_user`(`id`,`login_name`,`login_password`,`cust_name`,`credentials_code`,`mobile`,`email`,`record_status`,`create_user`,`create_date`,`last_update_user`,`last_update_date`,`version`,`memo`) values ('1','admin','e10adc3949ba59abbe56e057f20f883e','管理员','','13800138000','admin@163.com','有效','1','2018-05-27','1','2018-05-27',0,NULL);

/*Data for the table `ck_t_user_role` */

insert  into `ck_t_user_role`(`id`,`user_id`,`role_id`,`record_status`,`create_user`,`create_date`,`last_update_user`,`last_update_date`,`version`,`memo`) values ('1','1','1','有效','1','2018-05-27','1','2018-05-27',0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
