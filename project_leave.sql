/*
 Navicat Premium Data Transfer

 Source Server         : Nay
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : project_leave

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 22/05/2020 14:22:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for inner_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `inner_admin_role`;
CREATE TABLE `inner_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inner_admin_role
-- ----------------------------
INSERT INTO `inner_admin_role` VALUES (75, 2, 2);
INSERT INTO `inner_admin_role` VALUES (76, 2, 4);
INSERT INTO `inner_admin_role` VALUES (80, 5, 4);
INSERT INTO `inner_admin_role` VALUES (90, 6, 5);
INSERT INTO `inner_admin_role` VALUES (91, 6, 4);
INSERT INTO `inner_admin_role` VALUES (92, 4, 4);
INSERT INTO `inner_admin_role` VALUES (93, 7, 5);
INSERT INTO `inner_admin_role` VALUES (102, 306, 4);
INSERT INTO `inner_admin_role` VALUES (115, 1, 1);

-- ----------------------------
-- Table structure for inner_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `inner_role_auth`;
CREATE TABLE `inner_role_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `auth_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 364 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inner_role_auth
-- ----------------------------
INSERT INTO `inner_role_auth` VALUES (170, 3, 1);
INSERT INTO `inner_role_auth` VALUES (171, 3, 3);
INSERT INTO `inner_role_auth` VALUES (172, 3, 8);
INSERT INTO `inner_role_auth` VALUES (173, 3, 4);
INSERT INTO `inner_role_auth` VALUES (174, 3, 6);
INSERT INTO `inner_role_auth` VALUES (175, 3, 7);
INSERT INTO `inner_role_auth` VALUES (227, 4, 1);
INSERT INTO `inner_role_auth` VALUES (228, 4, 3);
INSERT INTO `inner_role_auth` VALUES (229, 4, 8);
INSERT INTO `inner_role_auth` VALUES (230, 4, 4);
INSERT INTO `inner_role_auth` VALUES (231, 4, 6);
INSERT INTO `inner_role_auth` VALUES (246, 2, 1);
INSERT INTO `inner_role_auth` VALUES (247, 2, 2);
INSERT INTO `inner_role_auth` VALUES (248, 2, 3);
INSERT INTO `inner_role_auth` VALUES (249, 2, 8);
INSERT INTO `inner_role_auth` VALUES (250, 2, 9);
INSERT INTO `inner_role_auth` VALUES (251, 2, 4);
INSERT INTO `inner_role_auth` VALUES (252, 2, 6);
INSERT INTO `inner_role_auth` VALUES (253, 2, 7);
INSERT INTO `inner_role_auth` VALUES (346, 5, 1);
INSERT INTO `inner_role_auth` VALUES (347, 5, 3);
INSERT INTO `inner_role_auth` VALUES (348, 5, 4);
INSERT INTO `inner_role_auth` VALUES (349, 5, 6);
INSERT INTO `inner_role_auth` VALUES (354, 1, 1);
INSERT INTO `inner_role_auth` VALUES (355, 1, 2);
INSERT INTO `inner_role_auth` VALUES (356, 1, 3);
INSERT INTO `inner_role_auth` VALUES (357, 1, 8);
INSERT INTO `inner_role_auth` VALUES (358, 1, 9);
INSERT INTO `inner_role_auth` VALUES (359, 1, 4);
INSERT INTO `inner_role_auth` VALUES (360, 1, 5);
INSERT INTO `inner_role_auth` VALUES (361, 1, 6);
INSERT INTO `inner_role_auth` VALUES (362, 1, 7);
INSERT INTO `inner_role_auth` VALUES (363, 1, 10);

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_admin
-- ----------------------------
DROP TABLE IF EXISTS `s_admin`;
CREATE TABLE `s_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userpswd` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_admin
-- ----------------------------
INSERT INTO `s_admin` VALUES (1, 'firenay', '$2a$10$YNOhsut9ZgaG/8.Kyk/v0.TfUBwNRFZtRAN4o8xk7Gkca/KasUt1q', 'firenay', 'firenay@qq.com', NULL);

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive_name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `phone_num` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, 'firenay', '18173516309', '湖南长沙雨花区', 4);
INSERT INTO `t_address` VALUES (2, 'firenay', '18173516309', '湖南长沙福田区', 4);
INSERT INTO `t_address` VALUES (3, 'firenay', '18173516309', '湖南长沙望城区', 4);

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_acct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pswd` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_acct`(`login_acct`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'firenay', '$2a$10$SCh9boiWPYOORhnjGR43FeTModqHObUYOfVkCaKDkhw.mAnkILYc.', 'lsl', 'firenay@qq.com', NULL);
INSERT INTO `t_admin` VALUES (2, 'admin', '$2a$10$YNOhsut9ZgaG/8.Kyk/v0.TfUBwNRFZtRAN4o8xk7Gkca/KasUt1q', 'admin', 'admin@qq.com', NULL);
INSERT INTO `t_admin` VALUES (3, 'role', '$2a$10$YNOhsut9ZgaG/8.Kyk/v0.TfUBwNRFZtRAN4o8xk7Gkca/KasUt1q', 'role', 'role@qq.com', NULL);
INSERT INTO `t_admin` VALUES (4, 'test', '$2a$10$YNOhsut9ZgaG/8.Kyk/v0.TfUBwNRFZtRAN4o8xk7Gkca/KasUt1q', 'test', 'test@qq.com', NULL);
INSERT INTO `t_admin` VALUES (5, 'vo', '$2a$10$YNOhsut9ZgaG/8.Kyk/v0.TfUBwNRFZtRAN4o8xk7Gkca/KasUt1q', 'vo', 'vo@qq.com', NULL);
INSERT INTO `t_admin` VALUES (6, 'mq', '$2a$10$YNOhsut9ZgaG/8.Kyk/v0.TfUBwNRFZtRAN4o8xk7Gkca/KasUt1q', 'mq', 'mq@qq.com', NULL);
INSERT INTO `t_admin` VALUES (7, 'test3', '$2a$10$SCh9boiWPYOORhnjGR43FeTModqHObUYOfVkCaKDkhw.mAnkILYc.', 'test3', 'test3@qq.com', '2020-05-15 21:15:23');

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES (1, '', '用户模块', NULL);
INSERT INTO `t_auth` VALUES (2, 'user:delete', '删除', 1);
INSERT INTO `t_auth` VALUES (3, 'user:get', '查询', 1);
INSERT INTO `t_auth` VALUES (4, '', '角色模块', NULL);
INSERT INTO `t_auth` VALUES (5, 'role:delete', '删除', 4);
INSERT INTO `t_auth` VALUES (6, 'role:get', '查询', 4);
INSERT INTO `t_auth` VALUES (7, 'role:add', '新增', 4);
INSERT INTO `t_auth` VALUES (8, 'user:save', '保存', 1);
INSERT INTO `t_auth` VALUES (9, 'user:update', '更新', 1);
INSERT INTO `t_auth` VALUES (10, 'role:update', '更新', 4);

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userpswd` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authstatus` int(4) NULL DEFAULT NULL COMMENT '实名认证状态 0 - 未实名认证， 1 - 实名认证申\r\n请中， 2 - 已实名认证',
  `usertype` int(4) NULL DEFAULT NULL COMMENT ' 0 - 个人， 1 - 企业',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accttype` int(4) NULL DEFAULT NULL COMMENT '0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `loginacct`(`loginacct`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (1, 'leave', '$2a$10$zHy5rx6P8FfpgsXjLGHEgeN2mVFcb8P.7s8WRYKKRtXjl22..gGZ.', '莉唔', 'leave@qq.com', 1, 1, '莉唔', '123', 2);
INSERT INTO `t_member` VALUES (2, 'tom', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (4, '1046762075', '$2a$10$rY/ZKqWkGz8sRYm5O1njtepUOdQY9UTY5oLv9l4jEb5KLqc2GmGyG', '通病', '1046762075@qq.com', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_member_confirm_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_confirm_info`;
CREATE TABLE `t_member_confirm_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) NULL DEFAULT NULL COMMENT '会员 id',
  `paynum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '易付宝企业账号',
  `cardnum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member_confirm_info
-- ----------------------------
INSERT INTO `t_member_confirm_info` VALUES (1, 4, 'teste', 'awdawdauiwhduiahwduiahuiwd');
INSERT INTO `t_member_confirm_info` VALUES (2, 4, 'teste', '124125114234');

-- ----------------------------
-- Table structure for t_member_launch_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_launch_info`;
CREATE TABLE `t_member_launch_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) NULL DEFAULT NULL COMMENT '会员 id',
  `description_simple` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简单介绍',
  `description_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细介绍',
  `phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `service_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member_launch_info
-- ----------------------------
INSERT INTO `t_member_launch_info` VALUES (1, 4, 'I love programming', '我是FIRENAY', '15476268412', '1046762075');
INSERT INTO `t_member_launch_info` VALUES (3, 4, 'I love programming', '我是FIRENAY', '15476268412', '1046762075');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, NULL, '系统权限菜单', NULL, 'glyphicon glyphicon-th-list\r\n');
INSERT INTO `t_menu` VALUES (2, 1, ' 控 制 面 板 ', 'main.htm', 'glyphicon glyphicon-dashboard\r\n');
INSERT INTO `t_menu` VALUES (3, 1, '权限管理', NULL, 'glyphicon glyphicon glyphicon-tasks\r\n');
INSERT INTO `t_menu` VALUES (4, 3, ' 用 户 维 护 ', 'user/index.htm', 'glyphicon glyphicon-user\r\n');
INSERT INTO `t_menu` VALUES (5, 3, ' 角 色 维 护 ', 'role/index.htm', 'glyphicon glyphicon-king\r\n');
INSERT INTO `t_menu` VALUES (6, 3, ' 菜 单 维 护 ', 'permission/index.htm', 'glyphicon glyphicon-lock\r\n');
INSERT INTO `t_menu` VALUES (7, 1, ' 业 务 审 核 ', NULL, 'glyphicon glyphicon-ok\r\n');
INSERT INTO `t_menu` VALUES (8, 7, '实名认证审核', 'auth_cert/index.htm', 'glyphicon glyphicon-check\r\n');
INSERT INTO `t_menu` VALUES (9, 7, ' 广 告 审 核 ', 'auth_adv/index.htm', 'glyphicon glyphicon-check\r\n');
INSERT INTO `t_menu` VALUES (10, 7, ' 项 目 审 核 ', 'auth_project/index.htm', 'glyphicon glyphicon-check\r\n');
INSERT INTO `t_menu` VALUES (11, 1, ' 业 务 管 理 ', NULL, 'glyphicon glyphicon-th-large\r\n');
INSERT INTO `t_menu` VALUES (12, 11, ' 资 质 维 护 ', 'cert/index.htm', 'glyphicon glyphicon-picture\r\n');
INSERT INTO `t_menu` VALUES (13, 11, ' 分 类 管 理 ', 'certtype/index.htm', 'glyphicon glyphicon-equalizer\r\n');
INSERT INTO `t_menu` VALUES (14, 11, ' 流 程 管 理 ', 'process/index.htm', 'glyphicon glyphicon-random\r\n');
INSERT INTO `t_menu` VALUES (15, 11, ' 广 告 管 理 ', 'advert/index.htm', 'glyphicon glyphicon-hdd\r\n');
INSERT INTO `t_menu` VALUES (16, 11, ' 消 息 模 板 ', 'message/index.htm', 'glyphicon glyphicon-comment\r\n');
INSERT INTO `t_menu` VALUES (17, 11, ' 项 目 分 类 ', 'projectType/index.htm', 'glyphicon glyphicon-list\r\n');
INSERT INTO `t_menu` VALUES (18, 11, ' 项 目 标 签 ', 'tag/index.htm', 'glyphicon glyphicon-tags\r\n');
INSERT INTO `t_menu` VALUES (19, 1, '参 数 管 理', 'param/index.htm', 'glyphicon glyphicon-list-alt');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `pay_order_num` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝流水号',
  `order_amount` double(10, 5) NULL DEFAULT NULL COMMENT '订单金额',
  `invoice` int(11) NULL DEFAULT NULL COMMENT '是否开发票（0 不开，1 开）',
  `invoice_title` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票抬头',
  `order_remark` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `address_id` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, '202005221331424FFFAC1B533D43778249AFE9A3179259', '2020052222001473990501002289', 1005.00000, 0, '我希望你 别来无恙', '如果哪天我们久别重逢', '2');
INSERT INTO `t_order` VALUES (2, '20200522134637C4B40F59CCA44FEB9C1FF4D167F6D127', '2020052222001473990501001980', 55.00000, 1, '哈哈哈哈', '如果哪天我们久别重逢', '3');
INSERT INTO `t_order` VALUES (3, '20200522140450269686C0F5974319A417146E3049400C', '2020052222001473990501002162', 3485.00000, 0, 'i love you', '如果哪天我们久别重逢, 我希望你 别来无恙', '1');

-- ----------------------------
-- Table structure for t_order_project
-- ----------------------------
DROP TABLE IF EXISTS `t_order_project`;
CREATE TABLE `t_order_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `launch_name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `return_content` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报内容',
  `return_count` int(11) NULL DEFAULT NULL COMMENT '回报数量',
  `support_price` int(11) NULL DEFAULT NULL COMMENT '支持单价',
  `freight` int(11) NULL DEFAULT NULL COMMENT '配送费用',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单表的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_project
-- ----------------------------
INSERT INTO `t_order_project` VALUES (1, '通坎', 'I love programming', '如果哪天我们久别重逢, 我希望你 别来无恙', 100, 10, 5, 1);
INSERT INTO `t_order_project` VALUES (2, '通坎', 'I love programming', '如果哪天我们久别重逢, 我希望你 别来无恙', 5, 10, 5, 2);
INSERT INTO `t_order_project` VALUES (3, '通坎', 'I love programming', '如果哪天我们久别重逢, 我希望你 别来无恙', 345, 10, 35, 3);

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `project_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目描述',
  `money` bigint(11) NULL DEFAULT NULL COMMENT '筹集金额',
  `day` int(11) NULL DEFAULT NULL COMMENT '筹集天数',
  `status` int(4) NULL DEFAULT NULL COMMENT '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败\r\n',
  `deploydate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目发起时间',
  `supportmoney` bigint(11) NULL DEFAULT NULL COMMENT '已筹集到的金额',
  `supporter` int(11) NULL DEFAULT NULL COMMENT '支持人数',
  `completion` int(3) NULL DEFAULT NULL COMMENT '百分比完成度',
  `memberid` int(11) NULL DEFAULT NULL COMMENT '发起人的会员 id',
  `createdate` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目创建时间',
  `follower` int(11) NULL DEFAULT NULL COMMENT '关注人数',
  `header_picture_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头图路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES (1, '通坎', '花香蝶自来', 14556, 30, 0, '2020-05-20 12:54:36', 10000, 2341, NULL, 4, '2020-05-20 12:54:36', 45, 'http://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/24fa438fab29432fac77ca88af0a43b6.jpg');
INSERT INTO `t_project` VALUES (3, '通坎', '花香蝶自来', 100000, 30, 0, '2020-05-20 18:34:24', 25421, 5234, NULL, 4, '2020-05-20 18:34:24', 65, 'https://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/1588007944058.png');

-- ----------------------------
-- Table structure for t_project_item_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_project_item_pic`;
CREATE TABLE `t_project_item_pic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) NULL DEFAULT NULL,
  `item_pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project_item_pic
-- ----------------------------
INSERT INTO `t_project_item_pic` VALUES (1, 1, 'http://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/69f668dd1d0f416390fa0b38624fdf9a.jpg');
INSERT INTO `t_project_item_pic` VALUES (3, 3, 'https://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/1588007944058.png');

-- ----------------------------
-- Table structure for t_project_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_project_tag`;
CREATE TABLE `t_project_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) NULL DEFAULT NULL,
  `tagid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project_tag
-- ----------------------------
INSERT INTO `t_project_tag` VALUES (1, 1, 4);
INSERT INTO `t_project_tag` VALUES (2, 1, 7);
INSERT INTO `t_project_tag` VALUES (3, 1, 9);
INSERT INTO `t_project_tag` VALUES (7, 3, 8);
INSERT INTO `t_project_tag` VALUES (8, 3, 6);
INSERT INTO `t_project_tag` VALUES (9, 3, 10);

-- ----------------------------
-- Table structure for t_project_type
-- ----------------------------
DROP TABLE IF EXISTS `t_project_type`;
CREATE TABLE `t_project_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) NULL DEFAULT NULL,
  `typeid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project_type
-- ----------------------------
INSERT INTO `t_project_type` VALUES (1, 1, 2);
INSERT INTO `t_project_type` VALUES (2, 1, 4);
INSERT INTO `t_project_type` VALUES (7, 3, 2);
INSERT INTO `t_project_type` VALUES (8, 3, 4);

-- ----------------------------
-- Table structure for t_return
-- ----------------------------
DROP TABLE IF EXISTS `t_return`;
CREATE TABLE `t_return`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) NULL DEFAULT NULL,
  `type` int(4) NULL DEFAULT NULL COMMENT '0 - 实物回报， 1 虚拟物品回报',
  `supportmoney` int(11) NULL DEFAULT NULL COMMENT '支持金额',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报内容',
  `count` int(11) NULL DEFAULT NULL COMMENT '回报产品限额，“0”为不限回报数量',
  `signalpurchase` int(11) NULL DEFAULT NULL COMMENT '是否设置单笔限购',
  `purchase` int(11) NULL DEFAULT NULL COMMENT '具体限购数量',
  `freight` int(11) NULL DEFAULT NULL COMMENT '运费，“0”为包邮',
  `invoice` int(4) NULL DEFAULT NULL COMMENT '0 - 不开发票， 1 - 开发票',
  `returndate` int(11) NULL DEFAULT NULL COMMENT '项目结束后多少天向支持者发送回报',
  `describ_pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_return
-- ----------------------------
INSERT INTO `t_return` VALUES (1, 1, 0, 10, '如果哪天我们久别重逢, 我希望你 别来无恙', 5, 0, 8, 5, 0, 30, 'https://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/0839f81cc4c34ed58ca3ca0facf765f1.jpg');
INSERT INTO `t_return` VALUES (2, 1, 0, 213, '如果哪天我们久别重逢, 我希望你 别来无恙2', 52, 0, 82, 10, 0, 302, 'http://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/879fdb3dde734b8c96b4541a233453b9.jpg');
INSERT INTO `t_return` VALUES (3, 3, 0, 10, '如果哪天我们久别重逢, 我希望你 别来无恙', 345, 0, 453, 35, 0, 34, 'http://firenay.oss-cn-shenzhen.aliyuncs.com/20200520/c686874251bc428baa9c26c6508e6019.jpg');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `姓名`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'DBA');
INSERT INTO `t_role` VALUES (3, 'SVIP');
INSERT INTO `t_role` VALUES (4, 'VIP');
INSERT INTO `t_role` VALUES (5, '普通用户');
INSERT INTO `t_role` VALUES (2, '管理员');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, '科技', '开启智慧未来');
INSERT INTO `t_type` VALUES (2, '设计', '创建改变未来');
INSERT INTO `t_type` VALUES (3, '农业', '网络天下肥美');
INSERT INTO `t_type` VALUES (4, '公益', '汇集点点爱心');

-- ----------------------------
-- Table structure for table_emp
-- ----------------------------
DROP TABLE IF EXISTS `table_emp`;
CREATE TABLE `table_emp`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_emp
-- ----------------------------
INSERT INTO `table_emp` VALUES (1, 'firenay', 20);
INSERT INTO `table_emp` VALUES (2, 'lsl', 20);
INSERT INTO `table_emp` VALUES (3, 'test', 19);
INSERT INTO `table_emp` VALUES (4, 'mq', 18);

SET FOREIGN_KEY_CHECKS = 1;
