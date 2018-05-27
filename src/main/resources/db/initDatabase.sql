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
   user_id              varchar(50) comment '��¼�û�ID',
   record_status        varchar(50) comment '��¼״̬',
   ip_address           varchar(50) comment '��¼IP��ַ',
   create_user          varchar(50) comment '������Ա',
   create_date          date not null comment '����ʱ��',
   last_update_user     varchar(50) comment '��������Ա',
   last_update_date     date comment '������ʱ��',
   version              integer comment '�汾��',
   memo                 varchar(300) comment '��ע',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_login_log comment 'ϵͳ��־��';

/*==============================================================*/
/* Table: ck_t_menu                                             */
/*==============================================================*/
create table ck_t_menu
(
   id                   varchar(50) not null,
   menu_name            varchar(50) not null comment '�˵�����',
   menu_level           varchar(50) not null comment '�˵�����',
   menu_flag            varchar(50) comment '�˵���ʶ',
   menu_url             varchar(50) comment '�˵���ַ',
   parent_id            varchar(50) not null comment '�����˵�',
   menu_icon            varchar(50) comment '�˵�ͼ��',
   menu_desc            varchar(50) comment '�˵�����',
   is_enabled           varchar(50) comment '�Ƿ�����',
   menu_type            varchar(50) comment '�˵�����',
   menu_sort            integer,
   record_status        varchar(50) comment '��¼״̬',
   create_user          varchar(50) comment '������Ա',
   create_date          date not null comment '����ʱ��',
   last_update_user     varchar(50) comment '��������Ա',
   last_update_date     date comment '������ʱ��',
   version              integer comment '�汾��',
   memo                 varchar(300) comment '��ע',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_menu comment 'ϵͳ�˵���';

/*==============================================================*/
/* Table: ck_t_role                                             */
/*==============================================================*/
create table ck_t_role
(
   id                   varchar(50) not null,
   role_name            varchar(50) not null comment '��ɫ����',
   role_desc            varchar(50) comment '��ɫ����',
   role_key             varchar(50) comment '��ɫ��ʶ',
   record_status        varchar(50) comment '��¼״̬',
   create_user          varchar(50) comment '������Ա',
   create_date          date not null comment '����ʱ��',
   last_update_user     varchar(50) comment '��������Ա',
   last_update_date     date comment '������ʱ��',
   version              integer comment '�汾��',
   memo                 varchar(300) comment '��ע',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_role comment 'ϵͳ��ɫ��';

/*==============================================================*/
/* Table: ck_t_role_menu                                        */
/*==============================================================*/
create table ck_t_role_menu
(
   id                   varchar(50) not null,
   role_id              varchar(50) not null comment '��ɫ������',
   menu_id              varchar(50) not null comment '�˵�������',
   record_status        varchar(50) comment '��¼״̬',
   create_user          varchar(50) comment '������Ա',
   create_date          date not null comment '����ʱ��',
   last_update_user     varchar(50) comment '��������Ա',
   last_update_date     date comment '������ʱ��',
   version              integer comment '�汾��',
   memo                 varchar(300) comment '��ע',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_role_menu comment 'ϵͳ��ɫ��˵�������';

/*==============================================================*/
/* Table: ck_t_user                                             */
/*==============================================================*/
create table ck_t_user
(
   id                   varchar(50) not null,
   login_name           varchar(50) not null comment '��¼�û���',
   login_password       varchar(50) not null comment '��¼����',
   cust_name            varchar(50) comment '���֤����',
   credentials_code     varchar(50) comment '���֤����',
   mobile               varchar(50) comment '�绰',
   email                varchar(50) comment '����',
   record_status        varchar(50) comment '��¼״̬',
   create_user          varchar(50) comment '������Ա',
   create_date          date not null comment '����ʱ��',
   last_update_user     varchar(50) comment '��������Ա',
   last_update_date     date comment '������ʱ��',
   version              integer comment '�汾��',
   memo                 varchar(300) comment '��ע',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_user comment 'ϵͳ�û���';

/*==============================================================*/
/* Table: ck_t_user_role                                        */
/*==============================================================*/
create table ck_t_user_role
(
   id                   varchar(50) not null,
   user_id              varchar(50) not null comment '�û�������',
   role_id              varchar(50) not null comment '��ɫ������',
   record_status        varchar(50) comment '��¼״̬',
   create_user          varchar(50) comment '������Ա',
   create_date          date not null comment '����ʱ��',
   last_update_user     varchar(50) comment '��������Ա',
   last_update_date     date comment '������ʱ��',
   version              integer comment '�汾��',
   memo                 varchar(300) comment '��ע',
   primary key (id)
)
engine = innodb
default character set = utf8
collate = utf8_general_ci;

alter table ck_t_user_role comment 'ϵͳ�û����ɫ������';

