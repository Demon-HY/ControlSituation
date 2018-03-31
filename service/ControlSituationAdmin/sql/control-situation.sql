-- 开启事务
start transaction;

# DROP DATABASE IF EXISTS control_situation;
# CREATE DATABASE IF NOT EXISTS control_situation DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE control_situation;

-- 禁用外键约束
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 登录记录
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_name` varchar(32) NOT NULL DEFAULT '' COMMENT '日志名称',
  `user_id` int(4) NOT NULL COMMENT '管理员id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `succeed` tinyint(1) NOT NULL COMMENT '是否执行成功',
  `message` varchar(1024) NOT NULL DEFAULT '' COMMENT '具体消息',
  `ip` varchar(255) NOT NULL DEFAULT '' COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录记录表';


-- ----------------------------
-- 操作日志
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_type` varchar(8) NOT NULL DEFAULT '' COMMENT '日志类型',
  `log_name` varchar(16) NOT NULL DEFAULT '' COMMENT '日志名称',
  `user_id` int(4) NOT NULL COMMENT '用户id',
  `module_name` varchar(16) NOT NULL DEFAULT '' COMMENT '模块名称',
  `operation_data` text NOT NULL COMMENT '操作数据',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `succeed` varchar(255) NOT NULL DEFAULT '' COMMENT '是否成功',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- 菜单
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `pid` int(6) NOT NULL COMMENT '菜单父编号',
  `pids` varchar(128) NOT NULL DEFAULT '' COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `url` varchar(128) NOT NULL DEFAULT '' COMMENT 'url地址',
  `sort` int(4) NOT NULL COMMENT '菜单排序值',
  `level` tinyint(1) NOT NULL COMMENT '菜单层级,(接口没有层级)',
  `is_children` tinyint(1) NOT NULL COMMENT '是否有子节点',
  `menu` tinyint(1) NOT NULL COMMENT '是否是菜单：1-是，0-不是(接口路由)',
  `enable` tinyint(1) NOT NULL COMMENT '菜单状态：1-启用，0-不启用',
  `open` tinyint(1) NOT NULL COMMENT '是否展开：1-打开，0-不打开',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(128) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- 角色菜单关联表，菜单ID和角色ID都是多对多的关系
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(4) NOT NULL COMMENT '菜单ID',
  `role_id` int(4) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- 管理员信息
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '头像',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(46) NOT NULL COMMENT '密码',
  `nick_name` varchar(32) NOT NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(32) NOT NULL DEFAULT '' COMMENT '电子邮件',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '电话',
  `status` int(1) NOT NULL COMMENT '状态：1-启用，2-冻结，3-删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- 用户角色关联表，用户ID和角色ID是一对多关系
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(4) NOT NULL COMMENT '用户ID',
  `role_id` int(4) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- 提交事务
commit;