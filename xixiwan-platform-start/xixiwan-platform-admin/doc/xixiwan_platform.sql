/*
Navicat MySQL Data Transfer

Source Server         : 192.168.122.23-nacos
Source Server Version : 50724
Source Host           : 192.168.122.23:3306
Source Database       : xixiwan_platform

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-11-15 14:16:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(50) DEFAULT NULL COMMENT '字典编码',
  `name` varchar(50) DEFAULT NULL COMMENT '字典名称',
  `pcode` varchar(50) DEFAULT NULL COMMENT '字典父编码',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('70', 'notice_type', '通知类型', 'top-level', '1', null, null);
INSERT INTO `sys_dict` VALUES ('71', 'notice_type_1', '系统通知', 'notice_type', '1', 'fa-laptop text-green', null);
INSERT INTO `sys_dict` VALUES ('72', 'notice_type_2', '敏感操作', 'notice_type', '2', 'fa-exclamation-triangle text-yellow', null);

-- ----------------------------
-- Table structure for sys_forget
-- ----------------------------
DROP TABLE IF EXISTS `sys_forget`;
CREATE TABLE `sys_forget` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `rtype` tinyint(2) DEFAULT NULL COMMENT '重置类型(1：邮箱  2：手机）',
  `rvalue` varchar(50) DEFAULT NULL COMMENT '重置值',
  `captcha` varchar(10) DEFAULT NULL COMMENT '验证码',
  `used` tinyint(2) DEFAULT '0' COMMENT '是否使用(1：使用  0：未用）',
  `expiredtime` datetime DEFAULT NULL COMMENT '验证码过期时间',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='忘记密码表';

-- ----------------------------
-- Records of sys_forget
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `ip` varchar(20) DEFAULT NULL COMMENT '登录ip',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='登录记录';

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operation`;
CREATE TABLE `sys_log_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `ip` varchar(20) DEFAULT NULL COMMENT '请求ip',
  `requestpath` varchar(128) DEFAULT NULL COMMENT '请求路径',
  `packagename` varchar(128) DEFAULT NULL COMMENT '包名',
  `method` varchar(4) DEFAULT NULL COMMENT '请求方式',
  `methodname` varchar(50) DEFAULT NULL COMMENT '方法名称',
  `methoddescribe` varchar(50) DEFAULT NULL COMMENT '方法描述',
  `parameters` text COMMENT '请求参数',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_log_operation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(50) DEFAULT NULL COMMENT '菜单编码',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `pcode` varchar(50) DEFAULT NULL COMMENT '菜单父编码',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `levels` tinyint(2) DEFAULT NULL COMMENT '菜单层级',
  `mtype` tinyint(2) DEFAULT NULL COMMENT '菜单类型（1：目录  2：菜单  3：按钮）',
  `tips` varchar(50) DEFAULT NULL COMMENT '小贴士',
  `color` varchar(50) DEFAULT NULL COMMENT '按钮颜色',
  `status` tinyint(2) DEFAULT NULL COMMENT '菜单状态（1：启用  0：禁用）',
  `isopen` tinyint(2) DEFAULT NULL COMMENT '是否打开（1：是  0：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', 'sys', '系统管理', 'top-level', 'fa-fw fa-cogs text-yellow', null, '1', '1', '1', '', null, '1', '1');
INSERT INTO `sys_menu` VALUES ('2', 'menu', '菜单管理', 'sys', 'fa-fw fa-list text-red', '/sys/menu/list', '2', '2', '2', 'menu control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('3', 'role', '角色管理', 'sys', 'fa-fw fa-user-secret text-aqua', '/sys/role/list', '3', '2', '2', 'role control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('4', 'user', '用户管理', 'sys', 'fa-fw fa-users text-blue', '/sys/user/list', '4', '2', '2', 'user control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('21', 'menu_search', '搜索', 'menu', 'fa-search', '/sys/menu/selectPage', '1', '3', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('22', 'menu_add', '新增', 'menu', 'fa-plus', '/sys/menu/addMenu', '2', '3', '3', null, 'btn-success', '1', '0');
INSERT INTO `sys_menu` VALUES ('23', 'menu_edit', '修改', 'menu', 'fa-edit', '/sys/menu/editMenu', '3', '3', '3', null, 'btn-info', '1', '0');
INSERT INTO `sys_menu` VALUES ('24', 'menu_delete', '删除', 'menu', 'fa-trash', '/sys/menu/deleteMenu', '4', '3', '3', null, 'btn-danger', '1', '0');
INSERT INTO `sys_menu` VALUES ('31', 'role_search', '搜索', 'role', 'fa-search', '/sys/role/selectPage', '1', '3', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('32', 'role_add', '新增', 'role', 'fa-plus', '/sys/role/addRole', '2', '3', '3', null, 'btn-success', '1', '0');
INSERT INTO `sys_menu` VALUES ('33', 'role_edit', '修改', 'role', 'fa-edit', '/sys/role/editRole', '3', '3', '3', null, 'btn-info', '1', '0');
INSERT INTO `sys_menu` VALUES ('34', 'role_delete', '删除', 'role', 'fa-trash', '/sys/role/deleteRole', '4', '3', '3', null, 'btn-danger', '1', '0');
INSERT INTO `sys_menu` VALUES ('35', 'role_authority', '赋权', 'role', 'fa-key', '/sys/role-menu/authority', '5', '3', '3', null, 'btn-warning', '1', '0');
INSERT INTO `sys_menu` VALUES ('41', 'user_search', '搜索', 'user', 'fa-search', '/sys/user/selectPage', '1', '3', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('196', 'user_edit', '修改', 'user', 'fa-edit', '/sys/user/editUser', '2', '3', '3', null, 'btn-success', '1', '0');
INSERT INTO `sys_menu` VALUES ('197', 'user_authority', '赋权', 'user', 'fa-key', '/sys/user-role/authority', '3', '3', '3', null, 'btn-warning', '1', '0');
INSERT INTO `sys_menu` VALUES ('199', 'dict', '字典管理', 'sys', 'fa-fw fa-book text-fuchsia', '/sys/dict/list', '1', '2', '2', 'dict control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('200', 'dict_search', '搜索', 'dict', 'fa-search', '/sys/dict/selectPage', '1', '3', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('201', 'dict_add', '新增', 'dict', 'fa-plus', '/sys/dict/addDict', '2', '3', '3', null, 'btn-success', '1', '0');
INSERT INTO `sys_menu` VALUES ('202', 'dict_edit', '修改', 'dict', 'fa-edit', '/sys/dict/editDict', '3', '3', '3', null, 'btn-info', '1', '0');
INSERT INTO `sys_menu` VALUES ('203', 'dict_delete', '删除', 'dict', 'fa-trash', '/sys/dict/deleteDict', '4', '3', '3', null, 'btn-danger', '1', '0');
INSERT INTO `sys_menu` VALUES ('204', 'log', '日志管理', 'sys', 'fa-fw fa-list-alt text-light-blue', null, '6', '2', '1', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('205', 'logLogin', '登录日志', 'log', 'fa-fw fa-sign-in text-maroon', '/sys/logLogin/list', '1', '3', '2', 'logLogin control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('206', 'logLogin_search', '搜索', 'logLogin', 'fa-search', '/sys/logLogin/selectPage', '1', '4', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('207', 'logOperation', '操作日志', 'log', 'fa-fw fa-reorder text-purple', '/sys/logOperation/list', '2', '3', '2', 'logOperation control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('208', 'logOperation_search', '搜索', 'logOperation', 'fa-search', '/sys/logOperation/selectPage', '1', '4', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('209', 'notice', '通知管理', 'sys', 'fa-fw fa-bell text-green', '/sys/notice/list', '5', '2', '2', 'notice control', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('210', 'notice_search', '搜索', 'notice', 'fa-search', '/sys/notice/selectPage', '1', '3', '3', null, 'btn-primary', '1', '0');
INSERT INTO `sys_menu` VALUES ('211', 'notice_add', '新增', 'notice', 'fa-plus', '/sys/notice/addNotice', '2', '3', '3', null, 'btn-success', '1', '0');
INSERT INTO `sys_menu` VALUES ('212', 'notice_edit', '修改', 'notice', 'fa-edit', '/sys/notice/editNotice', '3', '3', '3', null, 'btn-info', '1', '0');
INSERT INTO `sys_menu` VALUES ('213', 'notice_delete', '删除', 'notice', 'fa-trash', '/sys/notice/deleteNotice', '4', '3', '3', null, 'btn-danger', '1', '0');
INSERT INTO `sys_menu` VALUES ('214', 'notice_repeal', '撤销', 'notice', 'fa-reply', '/sys/notice/repealNotice', '5', '3', '3', null, 'btn-warning', '1', '0');
INSERT INTO `sys_menu` VALUES ('215', 'notice_release', '发布', 'notice', 'fa-paper-plane', null, '6', '3', '3', null, 'btn-warning', '1', '0');
INSERT INTO `sys_menu` VALUES ('216', 'monitor', '监控管理', 'sys', 'fa-fw fa-binoculars text-lime', null, '7', '2', '1', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('217', 'monitor_server', '服务监控', 'monitor', 'fa-fw fa-heartbeat text-olive', 'http://192.168.0.54:8080', '1', '3', '2', null, null, '1', '0');
INSERT INTO `sys_menu` VALUES ('218', 'monitor_nacos', '注册中心', 'monitor', 'fa-fw fa-bullseye text-teal', 'https://cloud.tencent.com/', '2', '3', '2', null, null, '0', '0');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(50) DEFAULT NULL COMMENT '通知类型',
  `objecttype` tinyint(2) DEFAULT NULL COMMENT '对象类型（1：单个用户  2：多个用户  3：所有用户）',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `modifytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(2) DEFAULT '1' COMMENT '通知状态（1：未发布  2：已发布  3：已撤销）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_user`;
CREATE TABLE `sys_notice_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `noticeid` int(11) DEFAULT NULL COMMENT '通知id',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `isread` tinyint(2) DEFAULT '0' COMMENT '是否阅读（1：是  0：否）',
  `readtime` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='通知和用户关联表';

-- ----------------------------
-- Records of sys_notice_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `authorities` varchar(50) DEFAULT NULL COMMENT '角色权限',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `modifytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '角色状态（1：启用  0：禁用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '超级管理员', '超级管理员', '1', '2018-09-28 10:49:33', '2018-09-28 09:27:19', '1');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '普通用户', '普通用户', '2', '2018-09-28 10:49:33', '2018-09-28 09:27:19', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  `menuid` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('312', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('313', '1', '199');
INSERT INTO `sys_role_menu` VALUES ('314', '1', '200');
INSERT INTO `sys_role_menu` VALUES ('315', '1', '201');
INSERT INTO `sys_role_menu` VALUES ('316', '1', '202');
INSERT INTO `sys_role_menu` VALUES ('317', '1', '203');
INSERT INTO `sys_role_menu` VALUES ('318', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('319', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('320', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('321', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('322', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('323', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('324', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('325', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('326', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('327', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('328', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('329', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('330', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('331', '1', '196');
INSERT INTO `sys_role_menu` VALUES ('332', '1', '197');
INSERT INTO `sys_role_menu` VALUES ('334', '1', '209');
INSERT INTO `sys_role_menu` VALUES ('335', '1', '210');
INSERT INTO `sys_role_menu` VALUES ('336', '1', '211');
INSERT INTO `sys_role_menu` VALUES ('337', '1', '212');
INSERT INTO `sys_role_menu` VALUES ('338', '1', '213');
INSERT INTO `sys_role_menu` VALUES ('339', '1', '214');
INSERT INTO `sys_role_menu` VALUES ('340', '1', '215');
INSERT INTO `sys_role_menu` VALUES ('341', '1', '204');
INSERT INTO `sys_role_menu` VALUES ('342', '1', '205');
INSERT INTO `sys_role_menu` VALUES ('343', '1', '206');
INSERT INTO `sys_role_menu` VALUES ('344', '1', '207');
INSERT INTO `sys_role_menu` VALUES ('345', '1', '208');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态(1：正常  2：禁用  3：锁定  4：删除  5：锁屏  6：过期）',
  `failtimes` tinyint(2) DEFAULT '0' COMMENT '登录失败次数',
  `expiredtime` datetime DEFAULT NULL COMMENT '密码过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'http://192.168.122.18/group2/M00/00/01/wKh6FFviW_eABY0WAATwXy18aas097.jpg', 'admin', '$2a$10$SFgE39OKWE3OGCKC/jjzeevx/b33st1o.Vr9XPwPGx000FAoDDJii', '管理员', '1992-11-01', '1', 'liaoxiting2011@163.com', '17728033082', '2018-11-14 15:52:13', '1', '0', '2019-02-20 13:33:09');
INSERT INTO `sys_user` VALUES ('54', 'http://192.168.122.18/group2/M00/00/01/wKh6FFviW_eABY0WAATwXy18aas097.jpg', 'xixiwan', '$2a$10$2tfF8omMhwYVP97yzF4b2uPO0GECO1vhxsbDoIH9a1k8oxfxBaTEe', '喜喜玩', '2018-09-21', '2', '373002314@qq.com', null, '2018-11-14 15:52:16', '1', '0', '2019-01-11 11:21:59');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('16', '54', '2');
