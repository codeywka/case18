/*
 Navicat Premium Data Transfer

 Source Server         : yin
 Source Server Type    : MySQL
 Source Server Version : 50549
 Source Host           : localhost:3306
 Source Schema         : yinwenkang

 Target Server Type    : MySQL
 Target Server Version : 50549
 File Encoding         : 65001

 Date: 19/11/2022 16:20:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(8) NULL DEFAULT NULL,
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES (14, '单庆军', '女', 24, '北京', '4444444', 'a178f4@163.com', NULL, NULL);
INSERT INTO `client` VALUES (15, '王文哲', '男', 22, '济南', '33333333', 'a178f5@163.com', NULL, NULL);
INSERT INTO `client` VALUES (16, '叶灵', '女', 18, '上海', '15734924777', 'a178f2@163.com', NULL, NULL);
INSERT INTO `client` VALUES (17, '尹文康', '男', 22, '上海', '1911573724', 'a178f1@163.com', NULL, NULL);
INSERT INTO `client` VALUES (18, '牟佳先', '男', 21, '青岛', '78581888', 'a178f6@163.com', NULL, NULL);
INSERT INTO `client` VALUES (20, '李圣茂', '女', 25, '苏州', '5899524', '22222@qq.com', NULL, NULL);
INSERT INTO `client` VALUES (21, '杨际乾', '男', 24, '北京', '481515551', '1111@qq.com', NULL, NULL);
INSERT INTO `client` VALUES (22, '欧阳凤凤', '女', 28, '济南', '498777741', 'a178f1@163.com', NULL, NULL);
INSERT INTO `client` VALUES (23, '张无忌', '男', 40, '上海', '84111156', 'a178f2@163.com', NULL, NULL);
INSERT INTO `client` VALUES (25, '尹文康', '女', 30, '济南', '484891', '4981@qq.com', NULL, NULL);
INSERT INTO `client` VALUES (26, '单庆军', '女', 28, '苏州', '7814165', '7814165@qq.com', NULL, NULL);
INSERT INTO `client` VALUES (27, '赵日天', '男', 33, '苏州', '48111556', 'dwd@126.com', NULL, NULL);
INSERT INTO `client` VALUES (31, '端木***', '女', 26, '上海', '15554858', '15554858@qq.com', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, '叶璇', '女', 18, '青岛', '59158487', 'a187f1@163.com', 'admin', '123456');
INSERT INTO `user` VALUES (8, '管理员', '男', 22, '济南', '5498489', 'a178@163.com', 'zhangsan', '123');

SET FOREIGN_KEY_CHECKS = 1;
