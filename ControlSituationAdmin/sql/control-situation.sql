-- 开启事务
start transaction;

# DROP DATABASE IF EXISTS control_situation;
# CREATE DATABASE IF NOT EXISTS control_situation DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE control_situation;

-- 禁用外键约束
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 部门
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `sort` int(4) NOT NULL COMMENT '排序',
  `pid` int(4) NOT NULL COMMENT '父部门id',
  `pids` varchar(128) NOT NULL COMMENT '父级ID集合，0,10,101',
  `dept_name` varchar(128) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- 登录记录
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_name` varchar(32) NOT NULL COMMENT '日志名称',
  `user_id` int(4) NOT NULL COMMENT '管理员id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `succeed` tinyint(1) NOT NULL COMMENT '是否执行成功',
  `message` varchar(1024) NOT NULL COMMENT '具体消息',
  `ip` varchar(255) NOT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录记录';

-- ----------------------------
-- 菜单
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `pid` int(6) NOT NULL COMMENT '菜单父编号',
  `pids` varchar(128) NOT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) NOT NULL COMMENT '菜单图标',
  `url` varchar(128) NOT NULL COMMENT 'url地址',
  `sort` int(4) NOT NULL COMMENT '菜单排序号',
  `level` tinyint(1) NOT NULL COMMENT '菜单层级',
  `menu` tinyint(1) NOT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `enable` tinyint(1) NOT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `open` tinyint(1) NOT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- 操作日志
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_type` varchar(8) NOT NULL COMMENT '日志类型',
  `log_name` varchar(16) NOT NULL COMMENT '日志名称',
  `user_id` int(4) NOT NULL COMMENT '用户id',
  `module_name` varchar(16) NOT NULL COMMENT '模块名称',
  `operation_data` text NOT NULL COMMENT '操作数据',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `succeed` varchar(255) NOT NULL COMMENT '是否成功',
  `remark` varchar(512) COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `pid` int(11) NOT NULL COMMENT '父角色ID',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `dept_id` int(11) NOT NULL COMMENT '部门名称',
  `tips` varchar(255) NOT NULL COMMENT '提示',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- 角色菜单关联
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(4) NOT NULL COMMENT '菜单id',
  `role_id` int(4) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- 管理员信息
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(128) NOT NULL COMMENT '头像',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `name` varchar(32) NOT NULL COMMENT '名字',
  `email` varchar(32) NOT NULL COMMENT '电子邮件',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `role_id` int(4) NOT NULL COMMENT '角色id',
  `dept_id` int(4) NOT NULL COMMENT '部门id',
  `status` int(1) NOT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- 提交事务
commit;