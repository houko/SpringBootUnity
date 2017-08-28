/*
Navicat MySQL Data Transfer

Source Server         : xiaomo2017(106.15.188.160)
Source Server Version : 50717
Source Host           : 106.15.188.160:3306
Source Database       : xiaomo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-28 16:50:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', '2017-08-11 17:22:32', 'string', '2017-08-11 17:34:06', '11dbb64dac563eb66c5bedca104ef581', 'yg7djoh6yf', '0', 'string');

-- ----------------------------
-- Table structure for change_log
-- ----------------------------
DROP TABLE IF EXISTS `change_log`;
CREATE TABLE `change_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `online_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of change_log
-- ----------------------------
INSERT INTO `change_log` VALUES ('1', '2017-08-11 17:35:34', 'string', '2017-08-11 17:35:34', 'string');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------

-- ----------------------------
-- Table structure for shikigame
-- ----------------------------
DROP TABLE IF EXISTS `shikigame`;
CREATE TABLE `shikigame` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `get_way` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `seiyou` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `star` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shikigame
-- ----------------------------
INSERT INTO `shikigame` VALUES ('1', null, '匣中少女', null, '', '抽卡、碎片合成', 'http://uus-ng.img.d.cn/snapshot/201706/999/image/388/388/hd/20170628104915566.jpeg', 'SR', '小清水亚美', '女', '★★');
INSERT INTO `shikigame` VALUES ('2', null, '小松丸', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201706/999/image/388/388/hd/20170628103321193.jpeg', 'SR', '芽野爱衣', '女', '★★');
INSERT INTO `shikigame` VALUES ('3', null, '以津真天', null, '', '抽奖、碎片', 'http://uus-ng.img.d.cn/snapshot/201706/999/image/388/388/hd/20170628103321133.jpeg', 'SR', '佐藤聪美', '女', '★★');
INSERT INTO `shikigame` VALUES ('4', null, '鸩', null, '', '碎片、抽卡', 'http://uus-ng.img.d.cn/snapshot/201706/999/image/388/388/hd/20170628103321247.jpeg', 'SR', '户松遥', '女', '★★');
INSERT INTO `shikigame` VALUES ('5', null, '彼岸花', null, '', '抽卡、碎片合成', 'http://uus-ng.img.d.cn/snapshot/201706/999/image/388/388/hd/20170628103321038.jpeg', 'SSR', '大原沙耶香', '女', '★★');
INSERT INTO `shikigame` VALUES ('6', null, '万年竹', null, '', '神龛兑换', 'http://uus-ng.img.d.cn/snapshot/201704/999/image/388/388/hd/20170425111533689.jpeg', 'SR', '立花慎之介', '男', '★★');
INSERT INTO `shikigame` VALUES ('7', null, '荒', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201704/999/image/388/388/hd/20170425110822062.jpeg', 'SSR', '平川大辅', '男', '★★');
INSERT INTO `shikigame` VALUES ('8', null, '金鱼姬', null, '', '活动、抽卡', 'http://uus-ng.img.d.cn/snapshot/201704/999/image/388/388/hd/20170425102028952.jpeg', 'SR', '内田真礼', '女', '★★');
INSERT INTO `shikigame` VALUES ('9', null, '夜叉', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170215123933616.jpeg', 'SR', '小西克幸', '男', '★★');
INSERT INTO `shikigame` VALUES ('10', null, '烟烟罗', null, '', '抽卡、碎片合成', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170215123707014.jpeg', 'SR', '甲斐田裕子', '女', '★★');
INSERT INTO `shikigame` VALUES ('11', null, '辉夜姬', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170215113029024.jpeg', 'SSR', '竹达彩奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('12', null, '花鸟卷', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170215112700922.jpeg', 'SSR', '早见沙织', '女', '★★');
INSERT INTO `shikigame` VALUES ('13', null, '古笼火', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170214170613314.jpeg', 'R', '松冈祯丞', '男', '★★');
INSERT INTO `shikigame` VALUES ('14', null, '黑童子', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170214170026831.jpeg', 'SR', '杉田智和', '男', '★★');
INSERT INTO `shikigame` VALUES ('15', null, '白童子', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170214165135364.jpeg', 'SR', '中村悠一', '男', '★★');
INSERT INTO `shikigame` VALUES ('16', null, '青坊主', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201702/999/image/388/388/hd/20170214172404430.jpeg', 'SR', '细谷佳正', '男', '★★');
INSERT INTO `shikigame` VALUES ('17', null, '提灯小僧', null, '', '抽卡，碎片', 'http://uus-ng.img.d.cn/snapshot/201611/999/image/388/388/hd/20161114141227302.jpeg', 'N', '悠木碧', '男', '★★');
INSERT INTO `shikigame` VALUES ('18', null, '一目连', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201611/999/image/388/388/hd/20161111163947709.jpeg', 'SSR', '绿川光', '男', '★★');
INSERT INTO `shikigame` VALUES ('19', null, '般若', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201611/999/image/388/388/hd/20161111163328046.jpeg', 'SR', '梶裕贵', '男', '★★');
INSERT INTO `shikigame` VALUES ('20', null, '唐纸伞妖', null, '', '抽卡，碎片', 'http://uus-ng.img.d.cn/snapshot/201611/999/image/388/388/hd/20161103100753671.jpeg', 'N', '小林优', '男', '★★');
INSERT INTO `shikigame` VALUES ('21', null, '寄生魂', null, '', '章节十二', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161014155409432.jpeg', 'N', '无', '男', '★★');
INSERT INTO `shikigame` VALUES ('22', null, '帚神', null, '', '妖气封印、章节十四', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161014154807857.jpeg', 'N', '-', '男', '★★');
INSERT INTO `shikigame` VALUES ('23', null, '盗墓小鬼', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161014154249087.jpeg', 'N', '新谷真弓', '男', '★★');
INSERT INTO `shikigame` VALUES ('24', null, '赤舌', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161014153630952.jpeg', 'N', '森久保祥太郎', '男', '★★');
INSERT INTO `shikigame` VALUES ('25', null, '首无', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161013132716253.jpeg', 'R', '石川界人', '男', '★★');
INSERT INTO `shikigame` VALUES ('26', null, '络新妇', null, '', '抽奖、碎片', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161012162340089.jpeg', 'SR', '井上喜久子', '女', '★★');
INSERT INTO `shikigame` VALUES ('27', null, '樱花妖', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161012155327721.jpeg', 'SR', '能登麻美子', '女', '★★');
INSERT INTO `shikigame` VALUES ('28', null, '妖刀姬', null, '', '抽卡、碎片', 'http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161012152409709.jpeg', 'SSR', '井泽诗织', '女', '★★');
INSERT INTO `shikigame` VALUES ('29', null, '清姬', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926104658402.jpeg', 'SR', '行成桃姬', '女', '★★');
INSERT INTO `shikigame` VALUES ('30', null, '河童', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926104424533.jpeg', 'R', '保志总一朗', '女', '★★');
INSERT INTO `shikigame` VALUES ('31', null, '鸦天狗', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926102711340.jpeg', 'R', '小林优', '男', '★★');
INSERT INTO `shikigame` VALUES ('32', null, '食发鬼', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926102255825.jpeg', 'R', '间宫康弘', '男', '★★');
INSERT INTO `shikigame` VALUES ('33', null, '山童', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926102013107.jpeg', 'R', '保志总一朗', '女', '★★');
INSERT INTO `shikigame` VALUES ('34', null, '觉', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926101743950.jpeg', 'R', '由加奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('35', null, '青蛙瓷器', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926101422781.jpeg', 'R', '吉野裕行', '男', '★★');
INSERT INTO `shikigame` VALUES ('36', null, '犬神', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926101100598.jpeg', 'SR', '关俊彦', '男', '★★');
INSERT INTO `shikigame` VALUES ('37', null, '三尾狐', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926100725543.jpeg', 'R', '泽城美雪', '女', '★★');
INSERT INTO `shikigame` VALUES ('38', null, '独眼小僧', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926095821891.jpeg', 'R', '小林优', '男', '★★');
INSERT INTO `shikigame` VALUES ('39', null, '管狐', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926095543173.jpeg', 'R', '松田健一郎', '男', '★★');
INSERT INTO `shikigame` VALUES ('40', null, '萤草', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926095401921.jpeg', 'R', '诹访彩花', '女', '★★');
INSERT INTO `shikigame` VALUES ('41', null, '九命猫', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926095022134.jpeg', 'R', '新谷真弓', '女', '★★');
INSERT INTO `shikigame` VALUES ('42', null, '童男', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926094814879.jpeg', 'R', '井上麻里奈', '男', '★★★');
INSERT INTO `shikigame` VALUES ('43', null, '吸血姬', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926094600059.jpeg', 'SR', '由加奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('44', null, '惠比寿', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926094357103.jpeg', 'SR', '茶风林', '男', '★★');
INSERT INTO `shikigame` VALUES ('45', null, '跳跳哥哥', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926094121029.jpeg', 'SR', '远藤大辅', '男', '★★');
INSERT INTO `shikigame` VALUES ('46', null, '雪女', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926093300209.jpeg', 'SR', '诹访彩花', '女', '★★');
INSERT INTO `shikigame` VALUES ('47', null, '茨木童子', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160926093041921.jpeg', 'SSR', '福山润', '男', '★★');
INSERT INTO `shikigame` VALUES ('48', null, '荒川之主', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923170213025.jpeg', 'SSR', '子安武人', '男', '★★★');
INSERT INTO `shikigame` VALUES ('49', null, '酒吞童子', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923165754494.jpeg', 'SSR', '阪口周平', '男', '★★★');
INSERT INTO `shikigame` VALUES ('50', null, '小鹿男', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923165614197.jpeg', 'SSR', '川西健吾', '男', '★★★');
INSERT INTO `shikigame` VALUES ('51', null, '阎魔', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923165311983.jpeg', 'SSR', '能登麻美子', '女', '★★');
INSERT INTO `shikigame` VALUES ('52', null, '山兔', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923165039787.jpeg', 'R', '丰崎爱生', '女', '★★');
INSERT INTO `shikigame` VALUES ('53', null, '鲤鱼精', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201701/999/image/388/388/hd/20170119113153623.jpeg', 'R', '悠木碧', '女', '★★');
INSERT INTO `shikigame` VALUES ('54', null, '白狼', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923163557641.jpeg', 'SR', '桑岛法子', '女', '★★');
INSERT INTO `shikigame` VALUES ('55', null, '桃花妖', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160923163207205.jpeg', 'SR', '水树奈奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('56', null, '妖狐', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920181314803.jpeg', 'SR', '岛崎信长', '男', '★★');
INSERT INTO `shikigame` VALUES ('57', null, '椒图', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920180651005.jpeg', 'R', '能登麻美子', '女', '★★');
INSERT INTO `shikigame` VALUES ('58', null, '鬼女红叶', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920180215320.jpeg', 'SR', '桑岛法子', '女', '★★');
INSERT INTO `shikigame` VALUES ('59', null, '大天狗', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920175835892.jpeg', 'SSR', '前野智昭', '男', '★★');
INSERT INTO `shikigame` VALUES ('60', null, '两面佛', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920175602498.jpeg', 'SSR', '井上和彦', '男', '★★');
INSERT INTO `shikigame` VALUES ('61', null, '青行灯', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920175352199.jpeg', 'SSR', '水树奈奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('62', null, '姑获鸟', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920175142834.jpeg', 'SR', '行成桃姬', '女', '★★');
INSERT INTO `shikigame` VALUES ('63', null, '二口女', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920174727153.jpeg', 'SR', '新谷真弓', '女', '★★');
INSERT INTO `shikigame` VALUES ('64', null, '凤凰火', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920174540667.jpeg', 'SR', '井上麻里奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('65', null, '骨女', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920174232252.jpeg', 'SR', '诹访彩花', '女', '★★');
INSERT INTO `shikigame` VALUES ('66', null, '鬼使白', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920174054507.jpeg', 'SR', '铃村健一', '男', '★★');
INSERT INTO `shikigame` VALUES ('67', null, '鬼使黑', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920155914788.jpeg', 'SR', '中井和哉', '男', '★★');
INSERT INTO `shikigame` VALUES ('68', null, '海坊主', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920155617178.jpeg', 'SR', '关俊彦', '男', '★★');
INSERT INTO `shikigame` VALUES ('69', null, '傀儡师', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920155205681.jpeg', 'SR', '能登麻美子', '女', '★★');
INSERT INTO `shikigame` VALUES ('70', null, '孟婆', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920151321465.jpeg', 'SR', '钉宫理惠', '女', '★★');
INSERT INTO `shikigame` VALUES ('71', null, '判官', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920151047481.jpeg', 'SR', '石田彰', '男', '★★');
INSERT INTO `shikigame` VALUES ('72', null, '食梦貘', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920150850532.jpeg', 'SR', '西谷修一', '男', '★★');
INSERT INTO `shikigame` VALUES ('73', null, '妖琴师', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920150636794.jpeg', 'SR', '岛崎信长', '男', '★★');
INSERT INTO `shikigame` VALUES ('74', null, '镰鼬', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920150447931.jpeg', 'SR', '间宫康弘', '男', '★★');
INSERT INTO `shikigame` VALUES ('75', null, '蝴蝶精', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920150221571.jpeg', 'R', '悠木碧', '女', '★★');
INSERT INTO `shikigame` VALUES ('76', null, '狸猫', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160920145918831.jpeg', 'R', '保志总一朗', '男', '★★');
INSERT INTO `shikigame` VALUES ('77', null, '跳跳弟弟', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919192452025.jpeg', 'R', '高山南', '男', '★★');
INSERT INTO `shikigame` VALUES ('78', null, '跳跳妹妹', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919192104507.jpeg', 'R', '诹访彩花', '女', '★★');
INSERT INTO `shikigame` VALUES ('79', null, '铁鼠', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919191747925.jpeg', 'R', '石田彰', '女', '★★');
INSERT INTO `shikigame` VALUES ('80', null, '童女', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919111719964.png', 'R', '加隈亚衣', '女', '★★');
INSERT INTO `shikigame` VALUES ('81', null, '巫蛊师', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919111049876.jpeg', 'R', '间宫康弘', '男', '★★');
INSERT INTO `shikigame` VALUES ('82', null, '武士之灵', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919110029060.jpeg', 'R', '井上和彦', '男', '★★');
INSERT INTO `shikigame` VALUES ('83', null, '雨女', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919103128584.jpeg', 'R', '加隈亚衣', '女', '★★');
INSERT INTO `shikigame` VALUES ('84', null, '饿鬼', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919101843632.jpeg', 'R', '井上麻里奈', '女', '★★');
INSERT INTO `shikigame` VALUES ('85', null, '座敷童子', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919102039254.jpeg', 'R', '竹内顺子', '女', '★★');
INSERT INTO `shikigame` VALUES ('86', null, '兵佣', null, '', '抽卡,碎片', 'http://uus-ng.img.d.cn/snapshot/201609/999/image/388/388/hd/20160919102003545.jpeg', 'R', '石田彰', '男', '★★');

-- ----------------------------
-- Table structure for system_set
-- ----------------------------
DROP TABLE IF EXISTS `system_set`;
CREATE TABLE `system_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `beian_number` varchar(255) DEFAULT NULL,
  `beian_url` varchar(255) DEFAULT NULL,
  `from_year` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `site_name` varchar(255) DEFAULT NULL,
  `to_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_set
-- ----------------------------
INSERT INTO `system_set` VALUES ('1', '2017-08-28 16:46:20', 'xiaomo', '2017-08-28 16:46:25', '我的备案号', 'https://xxx.xxx.xxx', '2015', 'https://image.xiaom.info/logo/avatar.png', '小莫的博客', '2017');

-- ----------------------------
-- Table structure for technology
-- ----------------------------
DROP TABLE IF EXISTS `technology`;
CREATE TABLE `technology` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of technology
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `register_time` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `validate_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for works
-- ----------------------------
DROP TABLE IF EXISTS `works`;
CREATE TABLE `works` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `complete_time` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of works
-- ----------------------------
