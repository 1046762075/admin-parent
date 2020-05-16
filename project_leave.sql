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

 Date: 16/05/2020 19:52:30
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
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 366 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('firenay', '1JcjLwYvljDh+6X3OJOjTg==', '7ySCOqYYIFhA6iwddlczfQ==', '2020-05-14 20:39:28');
INSERT INTO `persistent_logins` VALUES ('firenay', '4t/SYiUVmpH8ggqO+RFlcg==', 'IIfh/xzVNoP0yCjQ0mPWsg==', '2020-05-14 17:19:35');
INSERT INTO `persistent_logins` VALUES ('firenay', '9RcAgsSKpGF2YSwwl/3tMA==', 'VN+DJhe3NYsDMdsXlvY6+A==', '2020-05-14 19:34:50');
INSERT INTO `persistent_logins` VALUES ('firenay', 'EULBfhDFJzzJQMig1vqqIA==', 'qlxeXK4sbTY3rJfVRO25uA==', '2020-05-14 17:11:15');
INSERT INTO `persistent_logins` VALUES ('firenay', 'j/mabJP4SeO0OJHYceCCww==', 'tCKNUBIbvLsL+m3ZaAByXg==', '2020-05-14 21:02:58');
INSERT INTO `persistent_logins` VALUES ('firenay', 'JXWNo3iIvgYqpeoheYvNJA==', 'BrQw0FPU64upXAPVr2lKKw==', '2020-05-14 18:05:00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 313 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `姓名`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 309 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'DBA');
INSERT INTO `t_role` VALUES (3, 'SVIP');
INSERT INTO `t_role` VALUES (4, 'VIP');
INSERT INTO `t_role` VALUES (5, '普通用户');
INSERT INTO `t_role` VALUES (2, '管理员');

SET FOREIGN_KEY_CHECKS = 1;
