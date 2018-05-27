/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/27 9:28:40                            */
/*==============================================================*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ck` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ck`;

drop table if exists ck_t_login_log;

drop table if exists ck_t_menu;

drop table if exists ck_t_role;

drop table if exists ck_t_role_menu;

drop table if exists ck_t_user;

drop table if exists ck_t_user_role;

/*==============================================================*/
/* Table: ck_t_login_log                                        */
/*==============================================================*/
create table ck_t_login_log
(
   id                   varchar(50) not null,
   user_id              varchar(50) comment '登录用户ID',
   record_status        varchar(50) comment '记录状态',
   ip_address           varchar(50) comment '登录IP地址',
   create_user          varchar(50) comment '创建人员',
   create_date          date not null comment '创建时间',
   last_update_user     varchar(50) comment '最后更新人员',
   last_update_date     date comment '最后更新时间',
   version              integer comment '版本号',
   memo                 varchar(300) comment '备注',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_login_log comment '系统日志表';

/*==============================================================*/
/* Table: ck_t_menu                                             */
/*==============================================================*/
create table ck_t_menu
(
   id                   varchar(50) not null,
   menu_name            varchar(50) not null comment '菜单名称',
   menu_level           varchar(50) not null comment '菜单级别',
   menu_flag            varchar(50) comment '菜单标识',
   menu_url             varchar(50) comment '菜单地址',
   parent_id            varchar(50) not null comment '父级菜单',
   menu_icon            varchar(50) comment '菜单图标',
   menu_desc            varchar(50) comment '菜单描述',
   is_enabled           varchar(50) comment '是否启用',
   menu_type            varchar(50) comment '菜单类型',
   menu_sort            integer,
   record_status        varchar(50) comment '记录状态',
   create_user          varchar(50) comment '创建人员',
   create_date          date not null comment '创建时间',
   last_update_user     varchar(50) comment '最后更新人员',
   last_update_date     date comment '最后更新时间',
   version              integer comment '版本号',
   memo                 varchar(300) comment '备注',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_menu comment '系统菜单表';

/*==============================================================*/
/* Table: ck_t_role                                             */
/*==============================================================*/
create table ck_t_role
(
   id                   varchar(50) not null,
   role_name            varchar(50) not null comment '角色名称',
   role_desc            varchar(50) comment '角色描述',
   role_key             varchar(50) comment '角色标识',
   record_status        varchar(50) comment '记录状态',
   create_user          varchar(50) comment '创建人员',
   create_date          date not null comment '创建时间',
   last_update_user     varchar(50) comment '最后更新人员',
   last_update_date     date comment '最后更新时间',
   version              integer comment '版本号',
   memo                 varchar(300) comment '备注',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_role comment '系统角色表';

/*==============================================================*/
/* Table: ck_t_role_menu                                        */
/*==============================================================*/
create table ck_t_role_menu
(
   id                   varchar(50) not null,
   role_id              varchar(50) not null comment '角色表主键',
   menu_id              varchar(50) not null comment '菜单表主键',
   record_status        varchar(50) comment '记录状态',
   create_user          varchar(50) comment '创建人员',
   create_date          date not null comment '创建时间',
   last_update_user     varchar(50) comment '最后更新人员',
   last_update_date     date comment '最后更新时间',
   version              integer comment '版本号',
   memo                 varchar(300) comment '备注',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_role_menu comment '系统角色与菜单关联表';

/*==============================================================*/
/* Table: ck_t_user                                             */
/*==============================================================*/
create table ck_t_user
(
   id                   varchar(50) not null,
   login_name           varchar(50) not null comment '登录用户名',
   login_password       varchar(50) not null comment '登录密码',
   cust_name            varchar(50) comment '身份证名称',
   credentials_code     varchar(50) comment '身份证号码',
   mobile               varchar(50) comment '电话',
   email                varchar(50) comment '邮箱',
   record_status        varchar(50) comment '记录状态',
   create_user          varchar(50) comment '创建人员',
   create_date          date not null comment '创建时间',
   last_update_user     varchar(50) comment '最后更新人员',
   last_update_date     date comment '最后更新时间',
   version              integer comment '版本号',
   memo                 varchar(300) comment '备注',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_user comment '系统用户表';

/*==============================================================*/
/* Table: ck_t_user_role                                        */
/*==============================================================*/
create table ck_t_user_role
(
   id                   varchar(50) not null,
   user_id              varchar(50) not null comment '用户表主键',
   role_id              varchar(50) not null comment '角色表主键',
   record_status        varchar(50) comment '记录状态',
   create_user          varchar(50) comment '创建人员',
   create_date          date not null comment '创建时间',
   last_update_user     varchar(50) comment '最后更新人员',
   last_update_date     date comment '最后更新时间',
   version              integer comment '版本号',
   memo                 varchar(300) comment '备注',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_user_role comment '系统用户与角色关联表';

