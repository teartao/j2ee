/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : mvc_test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2015-03-21 13:57:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `longid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double(255,1) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`longid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('1', '阳春面', '5.0', null, null);
INSERT INTO `good` VALUES ('10', 'hsdf', '5.0', null, null);
INSERT INTO `good` VALUES ('11', 'hsdddf', '5.0', null, 'description');
INSERT INTO `good` VALUES ('1426126483430', 'hehe', '12.0', '../css/image/logo.png', '福满多');
INSERT INTO `good` VALUES ('1426127094634', 'name-11', '12.1', '../css/image/logo.png', '福满多');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `longid` varchar(255) NOT NULL DEFAULT '',
  `title` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`longid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1406107317276', 'asd221', '2014-7-28', 'admin', null);
INSERT INTO `news` VALUES ('1406114338491', 'ccc', '2014-7-23', 'admin', 'bbbb');
INSERT INTO `news` VALUES ('1406114357747', 'ddd', '2014-7-23', 'admin', 'bbbb');
INSERT INTO `news` VALUES ('1406114398949', 'eee', '2014-7-23', 'admin', 'bbbb');
INSERT INTO `news` VALUES ('1406114412148', 'aaaa2', '2014-7-23', 'admin', 'bbbb3');
INSERT INTO `news` VALUES ('1406114412149', '古古怪怪', '古古怪怪', '古古怪怪', '古古怪怪');
INSERT INTO `news` VALUES ('1426246884136', 'bean', '2015-3-13', 'admin', 'bbbb3');
INSERT INTO `news` VALUES ('1426508369168', 'bean', '2015-3-16', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426508404383', 'news111', '2015-3-16', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426640883152', 'news111', '2015-3-18', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426641373162', 'news111', '2015-3-18', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426641474521', 'news111', '2015-3-18', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426641511875', 'news111', '2015-3-18', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426649684112', 'news111', '2015-3-18', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('1426650079278', 'news111', '2015-3-18', 'admin', '啊啊啊啊');
INSERT INTO `news` VALUES ('3445643645645', '阿斯达斯', '2015-3-18', 'asd', '啊啊啊啊');
INSERT INTO `news` VALUES ('5323524242342', 'ffff', '56', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `longid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1426650079278', 'admin', 'passaa', 'admin');
INSERT INTO `user` VALUES ('14', 'user1', 'testPassword', null);
