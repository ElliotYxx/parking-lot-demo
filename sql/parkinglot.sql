drop schema if exists `parkinglot`;
create schema if not exists `parkinglot` default charset utf8;

use `parkinglot`;

-- ----------------------------
-- 1、角色表
-- ----------------------------
drop table if exists `parkinglot`.`role`;
create table if not exists `parkinglot`.`role`(
    `id` int primary key AUTO_INCREMENT COMMENT '角色id',
    `role_name` VARCHAR(16) NOT NULL COMMENT '角色名称'
)engine = InnoDB default charset utf8 comment '角色信息表';
INSERT INTO `parkinglot`.`role`(role_name) VALUES('USER');
INSERT INTO `parkinglot`.`role`(role_name) VALUES('ADMIN');

-- ----------------------------
-- 2、用户表
-- ----------------------------
drop table if exists `parkinglot`.`user`;
create table if not exists `parkinglot`.`user`(
    `id` bigint primary key auto_increment comment '用户id',
    `username` varchar(50) not null comment '用户名',
    `password` varchar(255) not null comment '用户密码',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `state` int not null default 0 comment '用户状态',
    `role_id` int not null default 1 comment '用户对应的角色id'
)engine = InnoDB default charset utf8 comment '用户信息表';
INSERT INTO `parkinglot`.`user`(username, password, create_time, status, role_id) VALUES('admin', '4b252ef32f83fdec9ce52366a161dbc0', '2020-02-12 17:01:15', 0, 2);

-- ----------------------------
-- 3、停车数据表
-- ----------------------------
drop table if exists `parkinglot`.`parking_record`;
create table if not exists `parkinglot`.`parking_record`(
    `id` bigint primary key auto_increment comment '车辆id',
    `plate_number` varchar(20) not null comment '车牌号码',
    `park_time` datetime not null default current_timestamp comment '进入停车场的时间',
    `left_time` datetime comment '离开停车场的时间',
    `cost` float comment '本次停车收取的费用'
)engine = InnoDB default charset utf8 comment '停车记录表';


-- ----------------------------
-- 4、菜单表
-- ----------------------------
drop table if exists menu;
create table menu (
                          menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
                          menu_name         varchar(50)     not null                   comment '菜单名称',
                          parent_id         bigint(20)      default 0                  comment '父菜单ID',
                          order_num         int(4)          default 0                  comment '显示顺序',
                          url               varchar(200)    default '#'                comment '请求地址',
                          menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
                          create_time       datetime                                   comment '创建时间',
                          remark            varchar(500)    default ''                 comment '备注',
                          primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into menu values('1', '停车管理', '0', '1', '#','M', SYSDATE(), '停车管理');
insert into menu values('2', '系统管理', '0', '2', '#','M', SYSDATE(), '系统管理');
-- 二级菜单
insert into menu values('100',  '用户管理', '2', '1', '/system/user', 'C', SYSDATE(), '用户管理菜单');
insert into menu values('101',  '在线用户', '2', '2', '/monitor/online', 'C', SYSDATE(), '在线用户菜单');
insert into menu values('103',  '数据监控', '2', '3', '/monitor/data', 'C', SYSDATE(), '数据监控菜单');
