/*
Navicat MySQL Data Transfer

Source Server         : xiaomo2017(106.15.188.160)
Source Server Version : 50717
Source Host           : 106.15.188.160:3306
Source Database       : xiaomo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-06 17:45:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of USER
-- ----------------------------
INSERT INTO `USER` VALUES ('1', 'xiaomo', '25');
