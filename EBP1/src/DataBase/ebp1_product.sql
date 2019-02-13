/*
MySQL Data Transfer
Source Host: localhost
Source Database: ebp1
Target Host: localhost
Target Database: ebp1
Date: 2013/8/28 10:31:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for connection
-- ----------------------------
CREATE TABLE `connection` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) DEFAULT NULL,
  `similarity` float(45,11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=913 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for connection_keywords
-- ----------------------------
CREATE TABLE `connection_keywords` (
  `keyword_id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) DEFAULT NULL,
  `weight` float(11,8) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`keyword_id`),
  KEY `connectionkeyword_connection` (`topic_id`),
  CONSTRAINT `connectionkeyword_connection` FOREIGN KEY (`topic_id`) REFERENCES `connection` (`topic_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4188 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fans
-- ----------------------------
CREATE TABLE `fans` (
  `fans_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `zombieType` int(1) DEFAULT NULL,
  PRIMARY KEY (`fans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fans_keywords
-- ----------------------------
CREATE TABLE `fans_keywords` (
  `fansKeyWord_id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) DEFAULT NULL,
  `weight` float(11,8) DEFAULT NULL,
  `fans_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fansKeyWord_id`),
  KEY `fanskeyword_fanspoiresult` (`fans_id`),
  CONSTRAINT `fanskeywords_fans` FOREIGN KEY (`fans_id`) REFERENCES `fans` (`fans_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1366 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hotwords
-- ----------------------------
CREATE TABLE `hotwords` (
  `hotwords_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `word` varchar(255) DEFAULT NULL,
  `weight` float(11,8) DEFAULT NULL,
  PRIMARY KEY (`hotwords_id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
CREATE TABLE `parameter` (
  `parameter_id` int(11) NOT NULL AUTO_INCREMENT,
  `acquireTimer` int(11) DEFAULT NULL,
  `acquireNum` int(11) DEFAULT NULL,
  `weiboNum` int(11) DEFAULT NULL,
  `cache_dir` varchar(255) DEFAULT NULL,
  `tools_dir` varchar(255) DEFAULT NULL,
  `chineseFilter` int(1) DEFAULT NULL,
  `communitiesNum` int(11) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `weibo_account` varchar(255) DEFAULT NULL,
  `source_count_num` int(11) DEFAULT NULL,
  `community_uuid` varchar(255) DEFAULT NULL,
  `connections_account` varchar(255) DEFAULT NULL,
  `connections_pwd` varchar(255) DEFAULT NULL,
  `connections_forumUuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`parameter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for releasehistory
-- ----------------------------
CREATE TABLE `releasehistory` (
  `release_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `isDraft` int(1) DEFAULT NULL,
  PRIMARY KEY (`release_id`),
  KEY `releasehistory_user` (`user_id`),
  CONSTRAINT `releasehistory_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source
-- ----------------------------
CREATE TABLE `source` (
  `source_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`source_id`),
  KEY `source_user` (`user_id`),
  CONSTRAINT `source_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_keywords
-- ----------------------------
CREATE TABLE `source_keywords` (
  `sourceKeyWordId` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) DEFAULT NULL,
  `weight` float(11,8) DEFAULT NULL,
  `source_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sourceKeyWordId`),
  KEY `source_keywords-source` (`source_id`),
  CONSTRAINT `source_keywords-source` FOREIGN KEY (`source_id`) REFERENCES `source` (`source_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for statuses
-- ----------------------------
CREATE TABLE `statuses` (
  `status_id` varchar(20) NOT NULL DEFAULT '',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_text` text,
  `user_screenname` varchar(255) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for statuses_comments
-- ----------------------------
CREATE TABLE `statuses_comments` (
  `comment_id` varchar(20) NOT NULL,
  `status_id` varchar(20) NOT NULL,
  `comment_user` varchar(255) NOT NULL,
  `comment_text` text,
  `comment_date` datetime DEFAULT NULL,
  `ignored` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_status` (`status_id`),
  CONSTRAINT `comment_status` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tools_status
-- ----------------------------
CREATE TABLE `tools_status` (
  `tools_id` int(11) NOT NULL AUTO_INCREMENT,
  `tools_name` varchar(255) DEFAULT NULL,
  `tools_status` varchar(255) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tools_id`),
  KEY `toolsStatus----user` (`user_id`),
  CONSTRAINT `toolsStatus----user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topics
-- ----------------------------
CREATE TABLE `topics` (
  `topic_id` varchar(255) NOT NULL,
  `title` text,
  `weibo_status_id` varchar(255) DEFAULT NULL,
  `published_date` datetime DEFAULT NULL,
  `weibo_comment_id` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topics_replies
-- ----------------------------
CREATE TABLE `topics_replies` (
  `topic_id` varchar(255) DEFAULT NULL,
  `published_date` datetime DEFAULT NULL,
  `reply_text` text,
  `reply_id` varchar(255) NOT NULL DEFAULT '',
  `author` varchar(255) DEFAULT NULL,
  `ignored` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_info` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `parameter` VALUES ('1', '86400', '10', '100', 'Cache\\', 'Tools\\', '1', '100', '2.00LIpOdCXkJlxB21bf0c9fd7kZubcE', 'FamilyLove', '50', '4b8b866c-9492-40a8-9add-25698ab04603', 'yanghf@cn.ibm.com', 'info0701', '7b6ed63f-fbbc-4292-ab42-a68c0f679ecb');
INSERT INTO `user` VALUES ('1', '刘紫薇', '8805080621', 'admin', '刘紫薇', 'liui@cn.ibm.com', '刘紫薇');
INSERT INTO `user` VALUES ('2', 'goshan', 'qiuhan', 'admin', '邱晗', 'liuzwei@cn.ibm.com', '邱晗');
INSERT INTO `user` VALUES ('3', '徐磊', 'xulei', 'admin', '徐磊', 'liuzwei@cn.ibm.com', '徐磊');
INSERT INTO `user` VALUES ('4', '陶一鸣', 'taoyiming', 'admin', '陶一鸣', 'liuzwei@cn.ibm.com', '陶一鸣');
INSERT INTO `user` VALUES ('5', '杨华峰', '1234', 'admin', '杨华峰', 'liuzwei@cn.ibm.com', '杨华峰');
INSERT INTO `user` VALUES ('6', '王又立', 'wangyouli', 'admin', '王又立', 'wylibj@cn.ibm.com', '王又立');
INSERT INTO `user` VALUES ('8', 'admin', 'admin', 'admin', 'admin', 'admin@admin', 'admin');
