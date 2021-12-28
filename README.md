# SchwiLibrary

```SQL
/*
 Navicat Premium Data Transfer

 Source Server         : learnMySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : mylibrary

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 28/12/2021 14:40:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for libbook
-- ----------------------------
DROP TABLE IF EXISTS `libbook`;
CREATE TABLE `libbook`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `status` int(0) NULL DEFAULT 0 COMMENT '书籍状态\r\n0正常\r\n1封禁',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '作者',
  `borrow` int(0) NULL DEFAULT 0 COMMENT '借出状态\r\n0正常\r\n1借出',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '介绍',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `borrowtimes` int(0) NULL DEFAULT 0 COMMENT '借出次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for libborrow
-- ----------------------------
DROP TABLE IF EXISTS `libborrow`;
CREATE TABLE `libborrow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `status` int(0) NULL DEFAULT 0 COMMENT '记录状态',
  `borrow` int(0) NULL DEFAULT 0 COMMENT '借出状态\r\n0:jiechu\r\n1:guihuan',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `bid` int(0) NULL DEFAULT NULL COMMENT '书籍id',
  `borrowtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '借出时间',
  `duration` int(0) NULL DEFAULT 0 COMMENT '借阅天数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `libborrow_uidfk`(`uid`) USING BTREE,
  INDEX `libborrow_pidfk`(`bid`) USING BTREE,
  CONSTRAINT `libborrow_pidfk` FOREIGN KEY (`bid`) REFERENCES `libbook` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `libborrow_uidfk` FOREIGN KEY (`uid`) REFERENCES `libuser` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for libuser
-- ----------------------------
DROP TABLE IF EXISTS `libuser`;
CREATE TABLE `libuser`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `status` int(0) NULL DEFAULT 0 COMMENT '账号状态\r\n0正常\r\n1封禁',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `role` int(0) NULL DEFAULT 0 COMMENT '权限\r\n0普通\r\n1管理员',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `borrowtimes` int(0) NULL DEFAULT 0 COMMENT '借书次数',
  `borrownow` int(0) NULL DEFAULT 0 COMMENT '当前借书数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```
