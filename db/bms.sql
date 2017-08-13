/*
Navicat MySQL Data Transfer

Source Server         : mycon
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : bms

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2017-08-13 18:02:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bis_bag_project`
-- ----------------------------
DROP TABLE IF EXISTS `bis_bag_project`;
CREATE TABLE `bis_bag_project` (
  `bag_id` varchar(50) NOT NULL COMMENT '礼包编号',
  `project_id` varchar(50) NOT NULL COMMENT '护理项目编号',
  `project_old_price` double(12,2) DEFAULT NULL COMMENT '项目原价',
  `project_new_price` double(12,2) DEFAULT NULL COMMENT '项目新价格',
  `project_num` int(11) DEFAULT NULL COMMENT '项目数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼包与护理项目关联信息';

-- ----------------------------
-- Records of bis_bag_project
-- ----------------------------
INSERT INTO `bis_bag_project` VALUES ('40000002', '30000001', null, null, null, '2017-04-25 00:15:17', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000001', '30000001', null, null, null, '2017-05-21 22:27:15', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000003', '30000003', null, null, null, '2017-05-25 11:03:03', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000004', '30000003', null, null, null, '2017-05-25 15:03:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000004', '30000004', null, null, null, '2017-05-25 15:03:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000005', '30000003', null, null, null, '2017-06-01 15:29:50', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000005', '30000005', null, null, null, '2017-06-01 15:29:50', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000005', '30000004', null, null, null, '2017-06-01 15:29:50', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000007', '30000008', null, null, null, '2017-06-17 07:13:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000007', '30000016', null, null, null, '2017-06-17 07:13:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000009', '30000015', '0.01', '0.01', '1', '2017-06-30 10:40:08', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000009', '30000007', '68.00', '60.00', '1', '2017-06-30 10:40:08', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000009', '30000008', '268.00', '200.00', '1', '2017-06-30 10:40:08', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000008', '30000007', '68.00', '68.00', '3', '2017-06-30 10:57:59', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000006', '30000015', '0.01', '68.00', '3', '2017-06-30 10:58:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000010', '30000011', '268.00', '268.00', '1', '2017-06-30 10:59:01', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000011', '30000007', '68.00', '68.00', '1', '2017-06-30 14:36:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000011', '30000008', '268.00', '268.00', '1', '2017-06-30 14:36:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000011', '30000011', '268.00', '268.00', '1', '2017-06-30 14:36:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000011', '30000013', '98.00', '98.00', '1', '2017-06-30 14:36:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000013', '30000007', '68.00', '68.00', '1', '2017-06-30 19:13:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000013', '30000008', '268.00', '268.00', '1', '2017-06-30 19:13:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000013', '30000011', '268.00', '268.00', '1', '2017-06-30 19:13:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000013', '30000013', '98.00', '98.00', '1', '2017-06-30 19:13:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000014', '30000007', '68.00', '30.00', '1', '2017-07-01 14:23:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000014', '30000008', '268.00', '80.00', '1', '2017-07-01 14:23:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000015', '30000007', '68.00', '68.00', '1', '2017-07-04 23:08:20', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000016', '30000007', '68.00', '58.00', '1', '2017-07-16 21:56:00', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000012', '30000011', '268.00', '268.00', '1', '2017-08-06 22:27:28', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_bag_project` VALUES ('40000012', '30000015', '0.01', '0.01', '1', '2017-08-06 22:27:28', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_bag_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_bag_record`;
CREATE TABLE `bis_bag_record` (
  `record_id` varchar(50) NOT NULL DEFAULT '',
  `bag_id` varchar(50) DEFAULT NULL COMMENT '礼包名称',
  `valid_day` int(10) DEFAULT NULL COMMENT '有效时间',
  `custom_user_id` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `buy_num` int(10) DEFAULT NULL COMMENT '购买数量',
  `share_num` int(10) DEFAULT NULL COMMENT '记录创建时间',
  `create_time` datetime DEFAULT NULL COMMENT '礼包开始生效时间',
  `order_id` varchar(50) DEFAULT NULL,
  `share_user_id` varchar(10) DEFAULT NULL,
  `bag_time` datetime DEFAULT NULL COMMENT '分享时间',
  `record_type` varchar(10) DEFAULT NULL COMMENT '1 购买礼包项目 2分享领取礼包项目',
  `status` varchar(10) DEFAULT '状态' COMMENT '1有效 2过期',
  `receive_status` varchar(10) DEFAULT '0' COMMENT '领取状态 0未领取 1已领取',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购买礼包记录';

-- ----------------------------
-- Records of bis_bag_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bis_beauty_config`
-- ----------------------------
DROP TABLE IF EXISTS `bis_beauty_config`;
CREATE TABLE `bis_beauty_config` (
  `config_id` varchar(50) NOT NULL COMMENT '配置编号',
  `buy_num` int(11) DEFAULT NULL COMMENT '购买数量',
  `give_num` int(11) DEFAULT NULL COMMENT '赠送数量',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否删除 0有效 1删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='美研币配置';

-- ----------------------------
-- Records of bis_beauty_config
-- ----------------------------
INSERT INTO `bis_beauty_config` VALUES ('170426222547510', null, null, '1', '2017-04-26 22:25:47', 'cb33c25f5c664058a111a9b876152317', '2017-04-26 22:33:22', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('170426223334007', '30', '2', '1', '2017-04-26 22:33:34', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:06:57', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('170426223659759', '30343', '230', '1', '2017-04-26 22:36:59', 'cb33c25f5c664058a111a9b876152317', '2017-04-26 22:37:12', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1705251117350008', '50', '8', '1', '2017-05-25 11:17:35', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:07:00', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1705251505100013', '100', '10', '1', '2017-05-25 15:05:10', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:07:04', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1706011536580010', '10', '1', '1', '2017-06-01 15:36:58', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:07:07', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1706151635390010', '20', '3', '1', '2017-06-15 16:35:39', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:07:11', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1706161138460020', '200', '100', '0', '2017-06-16 11:38:46', 'cb33c25f5c664058a111a9b876152317', '2017-06-16 11:38:46', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1706291029320055', '300', '100', '0', '2017-06-29 10:29:32', 'cb33c25f5c664058a111a9b876152317', '2017-06-29 10:29:32', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1706291031010061', '30000', '1000', '0', '2017-06-29 10:31:01', 'cb33c25f5c664058a111a9b876152317', '2017-06-29 10:31:01', 'cb33c25f5c664058a111a9b876152317', null);
INSERT INTO `bis_beauty_config` VALUES ('1707290905230001', '200', '0', '0', '2017-07-29 09:05:23', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:06:53', 'cb33c25f5c664058a111a9b876152317', '配置34');

-- ----------------------------
-- Table structure for `bis_beauty_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_beauty_record`;
CREATE TABLE `bis_beauty_record` (
  `record_id` varchar(50) NOT NULL COMMENT '记录编号',
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `cdkey` varchar(50) DEFAULT NULL COMMENT '兑换编号',
  `custom_user_id` varchar(50) DEFAULT NULL COMMENT '购买人编号',
  `beauty_num` int(11) DEFAULT NULL COMMENT '美研币数量',
  `give_num` int(11) DEFAULT NULL COMMENT '赠送数量',
  `total_money` double(12,2) DEFAULT '0.00' COMMENT '总价',
  `singe_price` double(12,2) DEFAULT '0.00' COMMENT '单个美研币价值',
  `record_type` varchar(10) DEFAULT NULL COMMENT '1购买美丽币2 兑换美丽币3服务消费',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `vaild_status` varchar(10) DEFAULT '1' COMMENT '美研币状态1有效 2过期',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '店铺编号',
  `remain_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='美研币流水';

-- ----------------------------
-- Records of bis_beauty_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bis_business_order`
-- ----------------------------
DROP TABLE IF EXISTS `bis_business_order`;
CREATE TABLE `bis_business_order` (
  `order_id` varchar(50) NOT NULL COMMENT '订单编号',
  `order_content` varchar(200) DEFAULT NULL COMMENT '订单内容',
  `project_id` varchar(50) DEFAULT NULL COMMENT '项目编号',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '店铺编号',
  `custom_user_id` varchar(50) DEFAULT NULL COMMENT '消费者编号',
  `subscribe_time` datetime DEFAULT NULL COMMENT '预约时间',
  `order_type` varchar(10) DEFAULT NULL COMMENT '订单类型 1美研币 2礼包3项目',
  `order_money` double(12,2) DEFAULT NULL COMMENT '订单金额',
  `buy_num` int(11) DEFAULT '1' COMMENT '购买数量',
  `pay_money` double DEFAULT NULL COMMENT '实际支付金额',
  `extend_beauty_num` int(11) DEFAULT NULL COMMENT '消耗美研币数量',
  `cash_income` double(12,2) DEFAULT NULL COMMENT '现金收入',
  `extend_income` double(12,2) DEFAULT NULL COMMENT '消耗收入',
  `pay_way` varchar(10) DEFAULT NULL COMMENT '支付方式1 微信 2支付宝 3美颜币 4套餐',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `check_code` varchar(50) DEFAULT NULL COMMENT '美研币支付验证码',
  `order_status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `order_remark` varchar(200) DEFAULT NULL COMMENT '订单备注',
  `server_user_id` varchar(50) DEFAULT NULL,
  `handle_user_id` varchar(50) DEFAULT NULL COMMENT '经手员工编号',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `service_time` datetime DEFAULT NULL COMMENT '服务时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '撤销时间',
  `cancel_type` varchar(10) DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `order_source` varchar(10) DEFAULT NULL COMMENT '订单所属 1平台 2商家',
  `modify_status` varchar(10) DEFAULT '0' COMMENT '修改状态 1已经修改0未修改',
  `free_num` int(11) DEFAULT '0' COMMENT '免费美研币',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='营业订单';

-- ----------------------------
-- Records of bis_business_order
-- ----------------------------
INSERT INTO `bis_business_order` VALUES ('20170813001', 'W战警', '30000008', '10000001', '20000001', '2017-08-13 18:30:00', '1', '268.00', '1', null, null, null, null, null, null, null, '0', null, null, null, '2017-08-13 18:01:11', null, null, null, null, '2', '0', '0');

-- ----------------------------
-- Table structure for `bis_cash_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_cash_record`;
CREATE TABLE `bis_cash_record` (
  `record_id` varchar(50) NOT NULL COMMENT '流水号',
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `custom_user_id` varchar(50) DEFAULT NULL COMMENT '购买人编号',
  `beauty_num` int(11) DEFAULT NULL COMMENT '美研币数量',
  `money` double DEFAULT NULL COMMENT '金额',
  `pay_way` varchar(10) DEFAULT NULL COMMENT '支付方式1 微信 2支付宝',
  `cash_type` varchar(10) DEFAULT '1' COMMENT '流水类型1收入 2支出',
  `record_type` varchar(10) DEFAULT NULL COMMENT '记录类型1购买美丽币2购买套餐 3使用消费4 预约',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='现金流水';

-- ----------------------------
-- Records of bis_cash_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bis_coupon_active`
-- ----------------------------
DROP TABLE IF EXISTS `bis_coupon_active`;
CREATE TABLE `bis_coupon_active` (
  `active_id` varchar(50) NOT NULL COMMENT '活动编号',
  `active_name` varchar(50) DEFAULT NULL COMMENT '活动名称',
  `beauty_num` int(11) DEFAULT NULL COMMENT '美研券数量',
  `bond_num` int(11) DEFAULT NULL COMMENT '每张美研券兑换美颜币数量',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否删除 0有效 1删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`active_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bis_coupon_active
-- ----------------------------
INSERT INTO `bis_coupon_active` VALUES ('50000001', '礼券赠送大活动', '100', '10', '1', '2017-04-28 22:50:27', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 11:22:31', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000002', '美研币大赠送', '100', '5', '1', '2017-05-15 22:35:37', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 11:22:22', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000003', '10', '10', '7', '1', '2017-05-15 22:36:50', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 11:22:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000004', 'huodong', '10', '5', '0', '2017-05-25 15:07:14', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 15:07:14', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000005', 'libao', '100', '10', '0', '2017-06-01 15:37:43', 'cb33c25f5c664058a111a9b876152317', '2017-06-01 15:37:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000006', '101', '10', '10', '0', '2017-06-15 16:37:57', 'cb33c25f5c664058a111a9b876152317', '2017-06-15 16:37:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000007', '202', '2', '2', '1', '2017-06-15 16:38:10', 'cb33c25f5c664058a111a9b876152317', '2017-06-15 16:41:28', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000008', '测试', '1', '1000000', '1', '2017-06-24 11:57:12', 'cb33c25f5c664058a111a9b876152317', '2017-06-24 11:58:34', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000009', '测试', '10', '5000', '0', '2017-06-24 11:58:56', 'cb33c25f5c664058a111a9b876152317', '2017-06-24 11:58:56', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_coupon_active` VALUES ('50000010', '测试100', '100', '100', '0', '2017-06-24 12:00:07', 'cb33c25f5c664058a111a9b876152317', '2017-06-24 12:00:07', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_coupon_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_coupon_record`;
CREATE TABLE `bis_coupon_record` (
  `record_id` varchar(50) NOT NULL COMMENT '记录编号',
  `active_id` varchar(50) DEFAULT NULL COMMENT '活动编号',
  `cdkey` varchar(50) DEFAULT NULL COMMENT '兑换码',
  `mobile` varchar(50) DEFAULT NULL COMMENT '兑换手机',
  `status` varchar(10) DEFAULT '1' COMMENT '状态 1未兑换 2已兑换 3已失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `exchange_time` datetime DEFAULT NULL COMMENT '兑换时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='美研券兑换记录表';

-- ----------------------------
-- Records of bis_coupon_record
-- ----------------------------
INSERT INTO `bis_coupon_record` VALUES ('170428225026243', '50000001', '1PRJ55TV4630', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026258', '50000001', '129710P884XH', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026270', '50000001', '13870X2HFNJB', null, '2', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026283', '50000001', '1TJN6JHX3H96', null, '2', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026296', '50000001', '1VT3H2RL471Z', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026311', '50000001', '1V87758HZJ60', null, '2', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026325', '50000001', '10RRTTN2H0PX', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026349', '50000001', '1625905L1NV6', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026370', '50000001', '1138089N4J1D', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026390', '50000001', '1X31XDB36184', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026406', '50000001', '1F6N0D631P63', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026425', '50000001', '1PRZZD3J9D6X', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026436', '50000001', '1X8BB3675T37', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026445', '50000001', '11144B2HL793', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026458', '50000001', '1ZV989FJ82J8', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026476', '50000001', '1NXZ45613L26', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026486', '50000001', '1FX06N6RN172', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026496', '50000001', '19J8DVV17V8Z', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026510', '50000001', '10J8XR78N9J6', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026520', '50000001', '123D5R6N11J2', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026534', '50000001', '1D3V530F55NJ', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026544', '50000001', '1X9P2R62V52L', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026554', '50000001', '1X1P4J5D101T', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026576', '50000001', '123LT1N0Z132', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026585', '50000001', '1X0P84XJZL5T', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026597', '50000001', '1V0HLDJ8VZP4', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026606', '50000001', '1T7RBZ23PBB3', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026616', '50000001', '1TL47HL5F2PD', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026624', '50000001', '15X4XB06HN49', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026631', '50000001', '1RTDLZ99H08D', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026639', '50000001', '1B0JZNN6P95L', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026647', '50000001', '17567F07B2NB', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026656', '50000001', '12LDNXT6N6P8', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026663', '50000001', '1P415LZ9TLDV', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026672', '50000001', '1HT1TF504LT6', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026680', '50000001', '16XD977909H9', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026690', '50000001', '11TB88332PDB', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026699', '50000001', '1F4251PL6VJ1', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026706', '50000001', '12XJVL770F3J', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026714', '50000001', '1PB4H1FZRH63', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026720', '50000001', '189033HF4829', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026733', '50000001', '1Z6V77HB27TF', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026740', '50000001', '105FVPXL9P00', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026748', '50000001', '181TBL33373L', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026756', '50000001', '1B5PF39BRNHD', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026764', '50000001', '1V9DDHX04R81', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026778', '50000001', '1VP2N63T03V9', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026786', '50000001', '17VJ9PL06ZFN', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026793', '50000001', '1145Z23JDTTN', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026804', '50000001', '1P9NN8Z1Z3JR', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026812', '50000001', '1T66BN0HJD1Z', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026820', '50000001', '1349X73TF8X8', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026828', '50000001', '1H0DT0D1PJT9', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026841', '50000001', '11J5PJ791N00', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026848', '50000001', '13X3ZH2PJX2R', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026856', '50000001', '17ZLJTHT6BD8', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026863', '50000001', '1RHFRJ5LVBD0', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026874', '50000001', '1BJ4LZF6J435', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026880', '50000001', '10B70L1PP723', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026888', '50000001', '1DB9B3XZF8BB', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026895', '50000001', '1DPXNRL9R428', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026904', '50000001', '1ZV6PZ6233HH', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026925', '50000001', '1LH3933PD739', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026937', '50000001', '16ZLB9N54F98', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026946', '50000001', '1NJVB934B623', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225026956', '50000001', '1BPFFV90P646', null, '3', '2017-04-28 22:50:26', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027045', '50000001', '13N712F2L0XZ', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027056', '50000001', '1LN8347409J6', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027063', '50000001', '10JD8VTBV35F', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027070', '50000001', '1V94047JTBZ2', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027076', '50000001', '13X6BX320NR3', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027082', '50000001', '102D6TN51H48', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027091', '50000001', '1D1467828F5J', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027098', '50000001', '114H7RP6TF9B', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027106', '50000001', '1VJVPP750N9H', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027114', '50000001', '1THV978B0J45', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027121', '50000001', '12ZBH21112V4', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027129', '50000001', '1LPXHD9N8VTN', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027137', '50000001', '16L327LR0X0V', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027147', '50000001', '1762T4NX3D67', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027156', '50000001', '1RL6X1JRD99H', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027164', '50000001', '1962VB252L4V', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027183', '50000001', '1D8498F72230', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027189', '50000001', '150L2BDRX6B4', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027196', '50000001', '1V5PLBTX7T1V', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027204', '50000001', '1RN518NFFL7V', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027211', '50000001', '1BTL2DV339NL', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027219', '50000001', '1F5766B6Z2HB', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027225', '50000001', '1N2D5BFT90R1', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027234', '50000001', '1LBP2T6Z0851', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027241', '50000001', '126XP4BBN5Z4', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027247', '50000001', '1135BLN7VL80', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027254', '50000001', '1RD31JX1XJ1P', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027261', '50000001', '16TN4V29T78N', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027270', '50000001', '19JH2JX0XF85', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027277', '50000001', '1RJLF2XDB1L4', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027283', '50000001', '1L6BVPTB7F20', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027289', '50000001', '17Z4T2FNV5RB', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027296', '50000001', '1PXN89040VN3', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('170428225027301', '50000001', '1NZ23J94LL09', null, '3', '2017-04-28 22:50:27', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350001', '50000002', '21R739DT', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350002', '50000002', '23464P89', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350003', '50000002', '25FX4JR6', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350004', '50000002', '291H1V9D', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350005', '50000002', '2Z8X5PP8', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350006', '50000002', '2018V5FL', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235350007', '50000002', '2D8BLHR7', null, '3', '2017-05-15 22:35:35', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360008', '50000002', '27VLJ2ZD', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360009', '50000002', '29895R8P', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360010', '50000002', '2P2T748J', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360011', '50000002', '25NR93P3', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360012', '50000002', '2X5NFL3T', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360013', '50000002', '27R2LXJ8', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360014', '50000002', '2J386XHL', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360015', '50000002', '2LNPF013', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360016', '50000002', '27L00070', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360017', '50000002', '2283L8ZF', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360018', '50000002', '23141V17', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360019', '50000002', '2473DFHJ', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360020', '50000002', '2J6X96D2', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360021', '50000002', '2F25150Z', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360022', '50000002', '2XHBLXTN', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360023', '50000002', '2N31ZJJH', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360024', '50000002', '2749DTBD', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360025', '50000002', '21N202N8', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360026', '50000002', '298F9P38', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235360027', '50000002', '2HZP02D4', null, '3', '2017-05-15 22:35:36', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370028', '50000002', '2RF4J0L0', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370029', '50000002', '2L0DTH5J', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370030', '50000002', '24P33D61', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370031', '50000002', '23J21Z95', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370032', '50000002', '2BP1V52X', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370033', '50000002', '2V9L3V68', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370034', '50000002', '2F2560F6', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370035', '50000002', '2P4031H1', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370036', '50000002', '2ZVN4V91', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370037', '50000002', '2142111H', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370038', '50000002', '25F9ZXRL', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370039', '50000002', '2F031062', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370040', '50000002', '27T18BH6', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370041', '50000002', '2P11V88L', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370042', '50000002', '2R6FL6D6', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370043', '50000002', '28PLZ801', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370044', '50000002', '2JF1V1J9', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370045', '50000002', '2P4LJHL8', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370046', '50000002', '2LJDD44T', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370047', '50000002', '2R6VVL0V', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370048', '50000002', '22H2F95R', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370049', '50000002', '29L99RD4', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370050', '50000002', '273318X5', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370051', '50000002', '2R74H421', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370052', '50000002', '2199N736', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370053', '50000002', '213XH687', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370054', '50000002', '2119ZF12', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370055', '50000002', '2590PV90', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370056', '50000002', '2J6H3N2F', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370057', '50000002', '25H25B47', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370058', '50000002', '286LT5V3', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370059', '50000002', '2VF46Z1P', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370060', '50000002', '29PZH6X4', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370061', '50000002', '23NZ7855', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370062', '50000002', '22J5PND7', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370063', '50000002', '2L1HZ676', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370064', '50000002', '24155120', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370065', '50000002', '2R44PL84', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370066', '50000002', '27J7HZ3P', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370067', '50000002', '2L73ZFV6', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370068', '50000002', '234962RJ', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370069', '50000002', '2D1VX68L', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370070', '50000002', '2X0248X9', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370071', '50000002', '276HL6FF', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370072', '50000002', '2D4DRN6H', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370073', '50000002', '25D7L248', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370074', '50000002', '27P5XHX0', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370075', '50000002', '2BLRD138', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370076', '50000002', '2JDH1F3V', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370077', '50000002', '2TT853T6', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370078', '50000002', '2354LN79', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370079', '50000002', '2VJ15N1B', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370080', '50000002', '2F5XJPJ6', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370081', '50000002', '24TPH3P1', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370082', '50000002', '2709RX4T', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370083', '50000002', '28N449HT', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370084', '50000002', '2L502Z86', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370085', '50000002', '2F26X789', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370086', '50000002', '2V1JLBP8', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370087', '50000002', '2686XD5D', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370088', '50000002', '25PH6579', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370089', '50000002', '207H8ZR8', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370090', '50000002', '245DBZ57', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370091', '50000002', '2B24DTZD', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370092', '50000002', '272FLZBF', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370093', '50000002', '22V6H271', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370094', '50000002', '2JX78BXV', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370095', '50000002', '23H8F319', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370096', '50000002', '26L0Z84T', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370097', '50000002', '2T9PR8T5', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370098', '50000002', '2F65LR21', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370099', '50000002', '22ZF7FH1', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152235370100', '50000002', '2110H802', null, '3', '2017-05-15 22:35:37', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152236490101', '50000003', '13802907704', null, '3', '2017-05-23 00:09:38', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152236500102', '50000003', '32260ZDLVX', '13802907704', '2', '2017-05-15 22:36:50', '2017-05-23 00:19:36');
INSERT INTO `bis_coupon_record` VALUES ('1705152236500103', '50000003', '3H9HN405XZ', null, '3', '2017-05-15 22:36:50', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152236500104', '50000003', '3X2V575Z91', null, '3', '2017-05-15 22:36:50', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152236500105', '50000003', '32P0293Z5Z', '13802907704', '2', '2017-05-15 22:36:50', '2017-05-23 00:16:50');
INSERT INTO `bis_coupon_record` VALUES ('1705152236500106', '50000003', '3F346L11LH', '13802907704', '2', '2017-05-15 22:36:50', '2017-05-23 00:18:21');
INSERT INTO `bis_coupon_record` VALUES ('1705152236500107', '50000003', '3B0702D88T', null, '3', '2017-05-15 22:36:50', null);
INSERT INTO `bis_coupon_record` VALUES ('1705152236500108', '50000003', '37869484VF', '13802907704', '2', '2017-05-15 22:36:50', '2017-05-23 00:15:40');
INSERT INTO `bis_coupon_record` VALUES ('1705152236500109', '50000003', '368LFN8441', '13802907704', '3', '2017-05-15 22:36:50', '2017-05-23 00:12:12');
INSERT INTO `bis_coupon_record` VALUES ('1705152236500110', '50000003', '3XP9X5R8L4', null, '3', '2017-05-15 22:36:50', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140014', '50000004', '4932P72F', '15626203027', '2', '2017-05-25 15:07:14', '2017-05-31 17:08:18');
INSERT INTO `bis_coupon_record` VALUES ('1705251507140015', '50000004', '4DB878DN', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140016', '50000004', '4103J446', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140017', '50000004', '49N398ZV', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140018', '50000004', '4R13FZZ6', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140019', '50000004', '4HJ2X5L9', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140020', '50000004', '40PN1PZ3', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140021', '50000004', '42LX0NT4', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140022', '50000004', '4P15T3JN', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1705251507140023', '50000004', '45P18P7B', null, '1', '2017-05-25 15:07:14', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430011', '50000005', '5VX55V1X', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430012', '50000005', '5NB1ZV33', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430013', '50000005', '5B3BXLHD', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430014', '50000005', '5R059Z68', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430015', '50000005', '56T882XX', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430016', '50000005', '515H13F9', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430017', '50000005', '59TZ5267', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430018', '50000005', '59D93XJT', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430019', '50000005', '57BZJ60N', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430020', '50000005', '56V10618', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430021', '50000005', '5PVL4PFD', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430022', '50000005', '52TB7NZR', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430023', '50000005', '5912DV7L', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430024', '50000005', '5J42TH7H', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430025', '50000005', '5D5643XZ', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430026', '50000005', '5B02VJ1D', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430027', '50000005', '53358096', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430028', '50000005', '55052V55', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430029', '50000005', '5HFB97X9', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430030', '50000005', '5LL29T1Z', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430031', '50000005', '5FB39Z51', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430032', '50000005', '5L4HVV1D', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430033', '50000005', '53TJ82Z8', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430034', '50000005', '5828L5X0', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430035', '50000005', '5DDFT6J6', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430036', '50000005', '5B4D70DP', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430037', '50000005', '503R1F8D', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430038', '50000005', '5P9B70P7', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430039', '50000005', '58TLBRL6', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430040', '50000005', '5H8JP9P5', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430041', '50000005', '5R22D1RX', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430042', '50000005', '5D5XPRBD', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430043', '50000005', '5RJ9JXB4', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430044', '50000005', '5F9T1P4P', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430045', '50000005', '56DPZ686', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430046', '50000005', '5HHVT70X', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430047', '50000005', '53TX8HX6', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430048', '50000005', '54HX6DN6', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430049', '50000005', '53826XZR', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430050', '50000005', '589V65R6', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430051', '50000005', '53Z48816', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430052', '50000005', '5JLH8425', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430053', '50000005', '5555JN0N', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430054', '50000005', '57XNBFL0', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430055', '50000005', '5J72RL41', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430056', '50000005', '59J12H34', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430057', '50000005', '5D7N0849', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430058', '50000005', '58VL1086', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430059', '50000005', '5978JN9Z', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430060', '50000005', '54JTTN39', '13802907704', '2', '2017-06-01 15:37:43', '2017-07-08 18:58:23');
INSERT INTO `bis_coupon_record` VALUES ('1706011537430061', '50000005', '5L8B788P', '15626203027', '2', '2017-06-01 15:37:43', '2017-06-01 15:38:35');
INSERT INTO `bis_coupon_record` VALUES ('1706011537430062', '50000005', '5L369460', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430063', '50000005', '5XZD9XR4', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430064', '50000005', '5D3HJT23', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430065', '50000005', '52B30FD0', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430066', '50000005', '50ZFFV3P', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430067', '50000005', '55TT2L6V', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430068', '50000005', '5XRB998F', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430069', '50000005', '586XNV3R', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430070', '50000005', '5D2FH98J', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430071', '50000005', '5725V6T8', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430072', '50000005', '5JDR4LJ3', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430073', '50000005', '50F7L5JT', '13427516023', '2', '2017-06-01 15:37:43', '2017-06-16 11:11:07');
INSERT INTO `bis_coupon_record` VALUES ('1706011537430074', '50000005', '5XL8F5BT', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430075', '50000005', '5FN95T2J', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430076', '50000005', '570VZ32J', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430077', '50000005', '5R0P0L9D', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430078', '50000005', '527FBP5F', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430079', '50000005', '5D12DF6J', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430080', '50000005', '51719778', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430081', '50000005', '54ZNB71V', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430082', '50000005', '5404V8LD', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430083', '50000005', '5148ZPBD', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430084', '50000005', '5XL1P976', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430085', '50000005', '5T7394V1', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430086', '50000005', '5PRVBFP2', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430087', '50000005', '5T33RLHJ', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430088', '50000005', '5N4197T3', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430089', '50000005', '5Z3FDRJ3', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430090', '50000005', '50TJ74Z9', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430091', '50000005', '519FJ93L', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430092', '50000005', '5X8ZDFPJ', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430093', '50000005', '5P1ZVV43', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430094', '50000005', '5009L1J6', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430095', '50000005', '547D33F7', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430096', '50000005', '5RHF358L', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430097', '50000005', '55BF65R0', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430098', '50000005', '52R6NH25', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430099', '50000005', '57P3VL5H', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430100', '50000005', '5585RX19', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430101', '50000005', '5X3N691Z', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430102', '50000005', '5BZNV56Z', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430103', '50000005', '5F56NN81', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430104', '50000005', '56X5N412', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430105', '50000005', '512PD485', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430106', '50000005', '5102TVFZ', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430107', '50000005', '5X807NZN', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430108', '50000005', '5LFJHHZH', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430109', '50000005', '540DT9B1', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706011537430110', '50000005', '5924BL0R', null, '1', '2017-06-01 15:37:43', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570011', '50000006', '6D4302X6', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570012', '50000006', '69433VTB', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570013', '50000006', '6PF791J8', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570014', '50000006', '66HDV8NT', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570015', '50000006', '6ZX9FJF3', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570016', '50000006', '67NJT30D', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570017', '50000006', '61P27D62', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570018', '50000006', '6T8HT4T4', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570019', '50000006', '66X1231F', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151637570020', '50000006', '67DLR6F1', null, '1', '2017-06-15 16:37:57', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151638100021', '50000007', '7ZR2Z56F', null, '3', '2017-06-15 16:38:10', null);
INSERT INTO `bis_coupon_record` VALUES ('1706151638100022', '50000007', '78349662', null, '3', '2017-06-15 16:38:10', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241157120019', '50000008', '8BX5J548', '15626203027', '2', '2017-06-24 11:57:12', '2017-06-24 11:57:35');
INSERT INTO `bis_coupon_record` VALUES ('1706241158560021', '50000009', '96J1B3D9', '15626203027', '2', '2017-06-24 11:58:56', '2017-06-24 11:59:31');
INSERT INTO `bis_coupon_record` VALUES ('1706241158560022', '50000009', '95FBXJX8', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560023', '50000009', '911H8PN8', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560024', '50000009', '9B51FR88', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560025', '50000009', '9BN2JJ34', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560026', '50000009', '9B5R3P9H', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560027', '50000009', '9JTX8313', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560028', '50000009', '99563841', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560029', '50000009', '9D6VR13X', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241158560030', '50000009', '9PXJD7TT', null, '1', '2017-06-24 11:58:56', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070032', '50000010', '100DZ68F', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070033', '50000010', '10T8Z8H9', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070034', '50000010', '1051VVT2', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070035', '50000010', '108N8545', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070036', '50000010', '10P0X9RP', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070037', '50000010', '10407461', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070038', '50000010', '107NHDB3', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070039', '50000010', '1002122D', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070040', '50000010', '108L4NHF', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070041', '50000010', '100TVP4X', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070042', '50000010', '10V3L441', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070043', '50000010', '10J0VN4F', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070044', '50000010', '10RJRBJ6', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070045', '50000010', '10X59F26', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070046', '50000010', '10T4B6Z4', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070047', '50000010', '10XX77FD', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070048', '50000010', '1078J038', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070049', '50000010', '10LJV85F', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070050', '50000010', '100JB10F', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070051', '50000010', '10LB41PD', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070052', '50000010', '10DBVP4P', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070053', '50000010', '103481XH', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070054', '50000010', '10PT77P7', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070055', '50000010', '10871X7P', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070056', '50000010', '106961L9', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070057', '50000010', '105F9VTN', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070058', '50000010', '101R2L6N', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070059', '50000010', '100BZBVF', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070060', '50000010', '10JZTVT8', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070061', '50000010', '10B00054', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070062', '50000010', '10PL6XP7', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070063', '50000010', '10LTTN15', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070064', '50000010', '108ZJ779', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070065', '50000010', '10H9045Z', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070066', '50000010', '1051DX66', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070067', '50000010', '10Z1F4BJ', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070068', '50000010', '10L96F74', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070069', '50000010', '10V6J5TV', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070070', '50000010', '10J7TR31', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070071', '50000010', '10HBPNT8', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070072', '50000010', '10JLJR0P', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070073', '50000010', '105LZ69V', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070074', '50000010', '10B8717Z', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070075', '50000010', '1015L0J6', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070076', '50000010', '10F9FFFN', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070077', '50000010', '1050428T', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070078', '50000010', '105VV57V', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070079', '50000010', '10D3L7VH', '13427516023', '2', '2017-06-24 12:00:07', '2017-06-29 10:29:46');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070080', '50000010', '10TXF9V8', '15622176822', '2', '2017-06-24 12:00:07', '2017-06-28 17:49:14');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070081', '50000010', '1065XT7P', '13802907704', '2', '2017-06-24 12:00:07', '2017-06-29 13:46:50');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070082', '50000010', '10BH1361', '15626203027', '2', '2017-06-24 12:00:07', '2017-06-24 12:00:28');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070083', '50000010', '10T66NFL', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070084', '50000010', '10BP66R9', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070085', '50000010', '10B5B2X2', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070086', '50000010', '10DZ2LX8', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070087', '50000010', '10BD114B', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070088', '50000010', '10RFZ7T2', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070089', '50000010', '10978J1R', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070090', '50000010', '10LT5FPN', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070091', '50000010', '10D1FPBH', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070092', '50000010', '10NVT93F', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070093', '50000010', '10449V96', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070094', '50000010', '10HJ42P6', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070095', '50000010', '10JN38Z5', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070096', '50000010', '10R9842P', '15626203027', '2', '2017-06-24 12:00:07', '2017-06-24 12:00:48');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070097', '50000010', '1085NHX2', '15626203027', '2', '2017-06-24 12:00:07', '2017-06-24 12:01:06');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070098', '50000010', '1038H439', '15626203027', '2', '2017-06-24 12:00:07', '2017-06-24 12:01:25');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070099', '50000010', '10X599TZ', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070100', '50000010', '10XRXH5X', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070101', '50000010', '10ZF66DP', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070102', '50000010', '100VT9T5', '15626203027', '2', '2017-06-24 12:00:07', '2017-06-24 12:01:51');
INSERT INTO `bis_coupon_record` VALUES ('1706241200070103', '50000010', '10NH7HN6', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070104', '50000010', '10B8953X', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070105', '50000010', '1038BD53', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070106', '50000010', '1031L874', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070107', '50000010', '103FT85B', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070108', '50000010', '107BLX3H', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070109', '50000010', '10063ZP4', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070110', '50000010', '10471B6P', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070111', '50000010', '10534D13', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070112', '50000010', '105LTD1H', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070113', '50000010', '1012P890', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070114', '50000010', '10TH0862', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070115', '50000010', '1085F8FX', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070116', '50000010', '10099VJP', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070117', '50000010', '10T30X2R', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070118', '50000010', '100522JL', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070119', '50000010', '107991P3', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070120', '50000010', '103TJXZX', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070121', '50000010', '10RT0NBN', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070122', '50000010', '105241J3', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070123', '50000010', '10VJBN83', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070124', '50000010', '10BL2BT6', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070125', '50000010', '10P54JP1', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070126', '50000010', '101D11V2', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070127', '50000010', '10H25031', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070128', '50000010', '106ZTD1Z', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070129', '50000010', '10H6D843', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070130', '50000010', '108322N8', null, '1', '2017-06-24 12:00:07', null);
INSERT INTO `bis_coupon_record` VALUES ('1706241200070131', '50000010', '102VNP04', null, '1', '2017-06-24 12:00:07', null);

-- ----------------------------
-- Table structure for `bis_custom_user`
-- ----------------------------
DROP TABLE IF EXISTS `bis_custom_user`;
CREATE TABLE `bis_custom_user` (
  `custom_user_id` varchar(50) NOT NULL COMMENT '顾客编号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `username` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nikename` varchar(50) DEFAULT NULL COMMENT '昵称',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信openid',
  `unionid` varchar(50) DEFAULT NULL COMMENT '微信唯一编号',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别1:男2:女3:保密',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `born_date` date DEFAULT NULL COMMENT '出生日期',
  `idno` varchar(20) DEFAULT NULL COMMENT '身份证',
  `address` varchar(100) DEFAULT NULL COMMENT '居住地址',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `enroll_mode` varchar(10) DEFAULT '1' COMMENT '注册方式1微信2商家3后台',
  `enroll_time` datetime DEFAULT NULL COMMENT '注册时间',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否删除 0 有效 1删除',
  `wechat_status` varchar(10) DEFAULT '1' COMMENT '微信状态 1 未订阅 2已订阅 3已退订',
  `beauty_num` int(11) DEFAULT '0',
  `bag_num` int(11) DEFAULT '0',
  PRIMARY KEY (`custom_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='顾客用户信息表';

-- ----------------------------
-- Records of bis_custom_user
-- ----------------------------
INSERT INTO `bis_custom_user` VALUES ('20000001', '13802907704', '蓝枫', null, '蓝枫', 'oT6rnsiiSWnbx32vLkyn7XKzc5eA', null, null, '/imageFile/customImage/20000001.png', '1', null, null, null, null, null, '广东广州', null, '1', '2017-08-13 18:00:05', '0', '2', '0', '0');

-- ----------------------------
-- Table structure for `bis_nurse_bag`
-- ----------------------------
DROP TABLE IF EXISTS `bis_nurse_bag`;
CREATE TABLE `bis_nurse_bag` (
  `bag_id` varchar(50) NOT NULL COMMENT '礼包编号',
  `bag_name` varchar(50) DEFAULT NULL COMMENT '礼包名称',
  `bag_total_price` double(12,2) DEFAULT NULL COMMENT '礼包总价格',
  `cover_photo` varchar(100) DEFAULT NULL COMMENT '礼包封面图片',
  `cover_big_photo` varchar(100) DEFAULT NULL,
  `open_card_num` int(11) DEFAULT NULL COMMENT '开卡数量',
  `remain_num` int(11) DEFAULT NULL COMMENT '卡剩余量',
  `max_buy_num` int(11) DEFAULT NULL COMMENT '最大购买量',
  `min_buy_num` int(11) DEFAULT NULL COMMENT '最少购买量',
  `putaway_time` date DEFAULT NULL COMMENT '上架时间',
  `soldout_time` date DEFAULT NULL COMMENT '下架时间',
  `overdue_time` int(11) DEFAULT NULL COMMENT '礼包过期时间',
  `content` text,
  `status` varchar(10) DEFAULT '1' COMMENT '状态 1 在售 2 下架',
  `sort_no` int(11) DEFAULT NULL,
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否删除 0 有效 1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  `bag_introduce` varchar(1000) DEFAULT NULL COMMENT '礼包介绍',
  `buy_count` int(11) DEFAULT '1' COMMENT '购买次数',
  PRIMARY KEY (`bag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼包信息';

-- ----------------------------
-- Records of bis_nurse_bag
-- ----------------------------
INSERT INTO `bis_nurse_bag` VALUES ('40000001', '护理套餐', '80.00', '/imageFile/bagImage/1705212147290001.jpg', null, '14', '14', '100', '4', '2017-04-18', '2017-04-24', '28', '<p><strong><span style=\"color:#ff0000\">购买说明</span></strong></p><p><strong><span style=\"color:#ff0000\">1、撒飞洒发、sf<br/></span></strong></p><p><strong><span style=\"color:#ff0000\">2测试</span></strong></p>', '1', '4', '1', '2017-04-24 23:41:12', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 10:55:10', 'cb33c25f5c664058a111a9b876152317', '运用以色列新科技加法国产品改变你的肤色。 使肌肤达到深度净化、清洁、紧致、补水的功效', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000002', '大礼包', '32432.00', '/imageFile/bagImage/40000002.jpg', null, '120', null, '50', '10', '2017-04-04', '2017-04-25', '20', '<p>234234324234</p>', '1', '2', '1', '2017-04-25 00:15:17', 'cb33c25f5c664058a111a9b876152317', '2017-04-25 00:21:37', 'cb33c25f5c664058a111a9b876152317', null, '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000003', '美容大礼包', '88888.00', '/imageFile/bagImage/40000003.png', null, '10', '10', '5', '2', '2017-05-25', '2017-05-25', '20', '<p>20内使用，逾期失效~</p>', '1', '1', '1', '2017-05-25 11:03:04', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:51:00', 'cb33c25f5c664058a111a9b876152317', '美容大礼包 不容错过', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000004', 'libao', '100.00', '/imageFile/bagImage/40000004.jpg', null, '100', '100', '5', '1', '2017-05-25', '2017-05-25', '20', null, '1', '1', '1', '2017-05-25 15:03:32', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:51:04', 'cb33c25f5c664058a111a9b876152317', '123', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000005', 'x', '1.00', '/imageFile/bagImage/40000005.jpg', null, '10', '10', '2', '1', '2017-06-01', '2017-06-01', '20', null, '1', '1', '1', '2017-06-01 15:29:50', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:51:08', 'cb33c25f5c664058a111a9b876152317', null, '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000006', '肌肤侦探大礼包', '1.00', '/imageFile/bagImage/1706271132480056S.jpg', '/imageFile/bagImage/1706271132480057B.jpg', '1000', '999', '3', '1', '2017-06-12', '2017-07-12', '30', '<p><img src=\"http://47.93.55.46/msroot/static/weblib/umeditor/jsp/upload/20170622/70781498117243410.jpg\" _src=\"http://47.93.55.46/msroot/static/weblib/umeditor/jsp/upload/20170622/70781498117243410.jpg\"/></p>', '1', '1', '1', '2017-06-06 14:10:21', 'cb33c25f5c664058a111a9b876152317', '2017-06-30 16:28:14', 'cb33c25f5c664058a111a9b876152317', '', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000007', '1+1', '200.00', '/imageFile/bagImage/40000007.jpg', '/imageFile/bagImage/1706170713190037B.jpg', '100', '80', '10', '10', '2017-06-15', '2017-06-30', '20', '<p>购买</p><p>goumai&nbsp;</p>', '1', '2', '1', '2017-06-15 16:33:34', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:41:47', 'cb33c25f5c664058a111a9b876152317', '礼包。。。。。。。', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000008', '缉油特工大礼包', '1.00', '/imageFile/bagImage/40000008S.jpg', '/imageFile/bagImage/40000008B.jpg', '5', '4', '1', '1', '2017-06-30', '2017-07-01', '20', '<p><img src=\"http://47.93.55.46/msroot/static/weblib/umeditor/jsp/upload/20170630/67931498787477517.jpg\" _src=\"http://47.93.55.46/msroot/static/weblib/umeditor/jsp/upload/20170630/67931498787477517.jpg\"/></p>', '1', '1', '1', '2017-06-30 09:49:14', 'cb33c25f5c664058a111a9b876152317', '2017-06-30 16:28:26', 'cb33c25f5c664058a111a9b876152317', '', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000009', '的', '260.01', '/imageFile/bagImage/40000009S.png', '/imageFile/bagImage/40000009B.png', '1', '1', '1', '1', '2017-06-30', '2017-06-30', '20', null, '1', '1', '1', '2017-06-30 10:34:09', 'cb33c25f5c664058a111a9b876152317', '2017-06-30 11:00:06', 'cb33c25f5c664058a111a9b876152317', '', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000010', '上镜神器大礼包', '0.01', '/imageFile/bagImage/40000010S.jpg', '/imageFile/bagImage/40000010B.jpg', '100', '100', '1', '1', '2017-06-30', '2017-07-03', '20', '<p><img src=\"http://47.93.55.46/msroot/static/weblib/umeditor/jsp/upload/20170630/16241498791402954.jpg\" _src=\"http://47.93.55.46/msroot/static/weblib/umeditor/jsp/upload/20170630/16241498791402954.jpg\"/></p>', '1', '1', '1', '2017-06-30 10:57:41', 'cb33c25f5c664058a111a9b876152317', '2017-06-30 16:28:28', 'cb33c25f5c664058a111a9b876152317', '', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000011', '新鲜礼包', '702.00', '/imageFile/bagImage/40000011S.png', '/imageFile/bagImage/40000011B.png', '100', '80', '20', '10', '2017-06-30', '2017-06-30', '20', null, '1', '1', '1', '2017-06-30 14:36:05', 'cb33c25f5c664058a111a9b876152317', '2017-06-30 16:28:21', 'cb33c25f5c664058a111a9b876152317', null, '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000012', '上镜神器礼包', '268.01', '/imageFile/bagImage/1707162156180006S.jpg', '/imageFile/bagImage/40000012B.jpg', '20', '19', '3', '1', '2017-06-30', '2017-08-31', '20', '<p>想美美地在镜头前面展示自己吗~</p><p>想成为自拍女神吗~</p><p>想素颜不吓人吗~</p><p>那就来抢购吧~</p><p>保证一条龙服务~</p><p>来来来~</p>', '1', '1', '0', '2017-06-30 16:32:35', 'cb33c25f5c664058a111a9b876152317', '2017-08-06 22:27:28', 'cb33c25f5c664058a111a9b876152317', '女性肌肤的神器~', '3');
INSERT INTO `bis_nurse_bag` VALUES ('40000013', '111', '702.00', '/imageFile/bagImage/40000013S.jpg', '/imageFile/bagImage/40000013B.jpg', '100', '100', '10', '5', '2017-06-30', '2017-06-30', '20', null, '1', '1', '0', '2017-06-30 19:13:05', 'cb33c25f5c664058a111a9b876152317', '2017-06-30 19:13:05', 'cb33c25f5c664058a111a9b876152317', null, '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000014', '开业大礼包', '110.00', '/imageFile/bagImage/40000014S.jpg', '/imageFile/bagImage/40000014B.jpg', '100', '90', '10', '5', '2017-07-01', '2017-07-28', '20', null, '1', '2', '0', '2017-07-01 14:23:06', 'cb33c25f5c664058a111a9b876152317', '2017-07-01 14:23:32', 'cb33c25f5c664058a111a9b876152317', '', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000015', '欢乐大礼包', '68.00', '/imageFile/bagImage/40000015S.jpg', '/imageFile/bagImage/40000015B.jpg', '100', '97', '5', '2', '2017-07-04', '2017-07-06', '20', null, '1', '1', '0', '2017-07-04 23:07:55', 'cb33c25f5c664058a111a9b876152317', '2017-07-04 23:08:20', 'cb33c25f5c664058a111a9b876152317', '', '1');
INSERT INTO `bis_nurse_bag` VALUES ('40000016', '快乐大礼包', '58.00', '/imageFile/bagImage/40000016S.png', '/imageFile/bagImage/40000016B.png', '100', '87', '4', '2', '2017-07-13', '2017-07-21', '20', null, '1', '2', '0', '2017-07-13 01:15:22', 'cb33c25f5c664058a111a9b876152317', '2017-07-16 21:56:00', 'cb33c25f5c664058a111a9b876152317', '23432', '6');

-- ----------------------------
-- Table structure for `bis_nurse_project`
-- ----------------------------
DROP TABLE IF EXISTS `bis_nurse_project`;
CREATE TABLE `bis_nurse_project` (
  `project_id` varchar(50) NOT NULL COMMENT '项目编号',
  `project_name` varchar(50) DEFAULT NULL COMMENT '护理项目名称',
  `type_id` varchar(50) DEFAULT NULL COMMENT '类型编号',
  `cover_photo` varchar(100) DEFAULT NULL COMMENT '封面图片',
  `cover_big_photo` varchar(100) DEFAULT NULL,
  `use_device` varchar(500) DEFAULT NULL,
  `server_time` int(10) DEFAULT NULL COMMENT '服务时长',
  `rmb_price` double(12,2) DEFAULT NULL COMMENT '人民币售价',
  `beauty_price` int(10) DEFAULT NULL COMMENT '美丽币售价',
  `manual_price` double DEFAULT NULL COMMENT '手工价格',
  `active_price` double(12,2) DEFAULT NULL COMMENT '活动价格',
  `status` varchar(10) DEFAULT '1' COMMENT '状态 1在售 2下架',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `content` text COMMENT '内容描述',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否删除0有效1删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='护理项目信息表';

-- ----------------------------
-- Records of bis_nurse_project
-- ----------------------------
INSERT INTO `bis_nurse_project` VALUES ('30000001', '美白SO1', '170423095335673', '/imageFile/nurseImage/1705212138350001.jpg', null, null, '30', '200.34', '10', '100', '80.00', '1', '2', '<p><strong><span style=\"color:#ff0000\">使用说明</span></strong></p><p>护理采用法国品牌产品，以植物精华气泡深层清洁肌肤，令皮肤柔润嫩滑，远离黑头多油，粉刺。</p><hr/><p><strong><span style=\"color:#ff0000\">护理说明</span></strong></p><p><strong><span style=\"color:#ff0000\"><span style=\"color: rgb(81, 81, 81); font-family: &quot;Arial Normal&quot;, Arial; font-size: 14px;\">晒斑、 色斑、&nbsp; 雀斑、 皮肤干燥、 皮肤暗淡&nbsp;</span></span></strong></p><hr/><p><strong style=\"white-space: normal;\"><span style=\"color:#ff0000\"><span style=\"color: rgb(255, 51, 204); font-family: &quot;Arial Normal&quot;, Arial; font-size: 14px; white-space: normal;\">美容效果</span></span></strong></p><p><strong><span style=\"color:#ff0000\"><span style=\"color: rgb(81, 81, 81); font-family: &quot;Arial Normal&quot;, Arial; font-size: 14px;\"><span style=\"color: rgb(81, 81, 81); font-family: &quot;Arial Normal&quot;, Arial; font-size: 14px; white-space: normal;\">白皙红润、透亮、光泽、嫩滑</span></span></span></strong></p><p><br/></p><p><br/></p>', '1', '2017-04-23 10:34:02', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 10:55:02', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000002', 'ceshi', '170423095347719', '/imageFile/nurseImage/30000002.jpg', null, null, '34', '3.00', '343', '3', '324.00', '1', '1343', '', '1', '2017-04-26 00:14:46', 'cb33c25f5c664058a111a9b876152317', '2017-05-25 10:55:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000003', '清洁', '1705251058280006', '/imageFile/nurseImage/30000003.png', null, null, '60', '100.00', '5', '5', '5.00', '1', '1', '<p>缉油特工</p>', '1', '2017-05-25 11:00:14', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:31:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000004', 'bushui', '1705251452160012', '/imageFile/nurseImage/30000004.jpg', null, null, '10', '10.00', '10', '1', '1.00', '1', '1', null, '1', '2017-05-25 15:00:41', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:31:14', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000005', 'xilian', '1705251058280006', '/imageFile/nurseImage/30000005.jpg', null, null, '10', '10.00', '10', '10', '10.00', '1', '1', null, '1', '2017-06-01 15:28:10', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:31:09', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000006', '缉油特工', '清洁', '/imageFile/nurseImage/30000006.png', null, null, '19', '68.00', '68', '5', '5.00', '1', '1', '<p style=\"margin-top: 0;margin-bottom: 0;margin-left: 0in;direction: ltr;unicode-bidi: embed\"><span style=\"font-size:24px;font-family:方正清刻本悦宋简体;color:#F7C17F\">缉油特工</span><span style=\"font-size: 24px;font-family: 方正清刻本悦宋简体\">深层清洁</span><span style=\"font-family: 方正清刻本悦宋简体;color:#f7c17f\">&nbsp; &nbsp;</span><span style=\"font-size: 24px;font-family: 方正兰亭纤黑_GBK\">可</span><span style=\"font-size: 24px;font-family: 方正兰亭纤黑_GBK\">达皮肤</span><span style=\"font-size:128px;font-family:方正兰亭纤黑_GBK;color:red;text-combine:letters\">0.7</span><span style=\"font-size: 24px;font-family: 方正兰亭纤黑_GBK\">mm</span><span style=\"font-size: 24px;font-family: 方正兰亭纤黑_GBK\">&nbsp; 深度，油光BYE BEY,</span></p><p style=\"margin-top: 0;margin-bottom: 0;margin-left: 0in;direction: ltr;unicode-bidi: embed\"><span style=\"font-size: 24px;font-family: 方正清刻本悦宋简体\">缉拿油光拯救大田！</span></p><p><br/></p>', '0', '2017-06-02 10:43:14', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:43:14', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000007', '缉油特工', '1706021044380011', '/imageFile/nurseImage/1706221518030015S.jpg', '/imageFile/nurseImage/1706221518030016B.jpg', '', '19', '68.00', '1360', '5', '5.00', '1', '2', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170621/92561498031237064.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170621/92561498031237064.jpg\" style=\"box-sizing: border-box;width:100%\"/></p>', '0', '2017-06-02 10:49:55', 'cb33c25f5c664058a111a9b876152317', '2017-08-01 02:20:08', 'ced672de37514478a63eca171b666c62');
INSERT INTO `bis_nurse_project` VALUES ('30000008', 'W战警', '1706021045440013', '/imageFile/nurseImage/1706221523530017S.jpg', '/imageFile/nurseImage/1706221523530018B.jpg', null, '19', '268.00', '3360', '10', null, '1', '1', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/22971498116259592.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/22971498116259592.jpg\"/></p>', '0', '2017-06-02 11:04:28', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:37:36', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000009', '水肌速递', '1706021045250012', '/imageFile/nurseImage/1706221532360027S.jpg', '/imageFile/nurseImage/1706221532360028B.jpg', null, '19', '168.00', '3360', '15', '15.00', '1', '3', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/97921498116743151.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/97921498116743151.jpg\"/></p>', '0', '2017-06-02 11:06:21', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:38:44', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000010', '护理项目', '1706021044380011', '/imageFile/nurseImage/30000010.jpg', null, null, '10', '10.00', '10', '10', null, '1', '1', '<p><img src=\"http://127.0.0.1:8080:8080/msroot/static/weblib/umeditor/jsp/upload/20170606/2421496715336061.jpg\" _src=\"http://127.0.0.1:8080:8080/msroot/static/weblib/umeditor/jsp/upload/20170606/2421496715336061.jpg\"/></p>', '1', '2017-06-06 10:15:38', 'cb33c25f5c664058a111a9b876152317', '2017-06-06 14:16:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000011', '上镜神器', '1706021046510015', '/imageFile/nurseImage/1706221526140019S.jpg', '/imageFile/nurseImage/1706221526140020B.jpg', null, '29', '268.00', '5960', '20', '20.00', '1', '1', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/83731498117478579.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/83731498117478579.jpg\"/></p>', '0', '2017-06-06 14:16:39', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:49:45', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000012', '大眼精灵', '1706021046220014', '/imageFile/nurseImage/1706221533260029S.jpg', '/imageFile/nurseImage/1706221533260030B.jpg', null, '29', '268.00', '5960', '20', '20.00', '1', '5', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/94521498116791775.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/94521498116791775.jpg\"/></p>', '0', '2017-06-06 14:18:11', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:38:52', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000013', '毛毛雷母-小部位', '1705251058510007', '/imageFile/nurseImage/1706221528430021S.jpg', '/imageFile/nurseImage/1706221528430022B.jpg', null, '19', '98.00', '3960', '15', '15.00', '1', '1', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/57161498116500576.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/57161498116500576.jpg\"/></p>', '0', '2017-06-06 14:20:02', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:37:51', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000014', '反孔精英', '1706021047270016', '/imageFile/nurseImage/1706221531210025S.jpg', '/imageFile/nurseImage/1706221531210026B.jpg', null, '29', '268.00', '3360', '15', '15.00', '1', '1', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/76361498116665558.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/76361498116665558.jpg\"/></p>', '0', '2017-06-06 14:22:43', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:38:08', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000015', '肌肤侦探', '1706021047530017', '/imageFile/nurseImage/1706221534440031S.jpg', '/imageFile/nurseImage/1706221534440032B.jpg', '', '19', '20.00', '10', '10', '10.00', '1', '8', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/70411498116862009.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/70411498116862009.jpg\"/></p>', '0', '2017-06-06 14:24:19', 'cb33c25f5c664058a111a9b876152317', '2017-07-27 23:39:47', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000016', '肌肤侦探+缉油特工 活动大礼包', '1706021044380011', '/imageFile/nurseImage/1706221536060033S.jpg', '/imageFile/nurseImage/1706221536060034B.jpg', null, '20', '1.00', '50', '30', '13.00', '1', '6', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/65841498116955601.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/65841498116955601.jpg\"/></p>', '1', '2017-06-15 16:09:17', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:41:56', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000018', '毛毛雷母-大部位', '1705251058510007', '/imageFile/nurseImage/1706221529550023S.jpg', '/imageFile/nurseImage/1706221529550024B.jpg', null, '30', '198.00', '5960', '15', '20.00', '1', '1', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/49901498116581044.jpg\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170622/49901498116581044.jpg\"/></p>', '0', '2017-06-16 09:54:33', 'cb33c25f5c664058a111a9b876152317', '2017-06-22 15:38:36', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000019', '测试项目', '1706021044380011', '/imageFile/nurseImage/30000019S.png', '/imageFile/nurseImage/30000019B.png', '收工', '10', '0.10', '1', '10', '10.00', '1', '1', '<p><img src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170624/80951498277209425.png\" _src=\"http://127.0.0.1:8080/msroot/static/weblib/umeditor/jsp/upload/20170624/80951498277209425.png\"/></p>', '0', '2017-06-24 12:06:52', 'cb33c25f5c664058a111a9b876152317', '2017-07-01 21:02:41', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_project` VALUES ('30000020', '测试产品', '1706021046510015', '/imageFile/nurseImage/30000020S.png', '/imageFile/nurseImage/30000020B.png', '', '30', '0.01', '1', '0', '0.00', '1', '1', null, '0', '2017-08-01 02:02:37', 'ced672de37514478a63eca171b666c62', '2017-08-01 02:19:38', 'ced672de37514478a63eca171b666c62');

-- ----------------------------
-- Table structure for `bis_nurse_type`
-- ----------------------------
DROP TABLE IF EXISTS `bis_nurse_type`;
CREATE TABLE `bis_nurse_type` (
  `type_id` varchar(50) NOT NULL COMMENT '类型编号',
  `type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `type_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='护理类型信息表';

-- ----------------------------
-- Records of bis_nurse_type
-- ----------------------------
INSERT INTO `bis_nurse_type` VALUES ('1705251058510007', '脱毛', '2', 'cici脱毛', '2017-05-25 10:58:51', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:45:12', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021044380011', '清洁', '1', '韩式小气泡', '2017-06-02 10:44:38', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:44:53', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021045250012', '补水', '3', '导入仪', '2017-06-02 10:45:25', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:45:53', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021045440013', '美白', '4', '光护理', '2017-06-02 10:45:44', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:45:44', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021046220014', '眼部', '5', 'Magic eyes', '2017-06-02 10:46:22', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:46:22', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021046510015', '脸部轮廓', '6', '小V脸', '2017-06-02 10:46:51', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:46:51', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021047270016', '嫩肤', '7', '光护理', '2017-06-02 10:47:27', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:47:27', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706021047530017', '皮肤检测', '8', '皮肤检测设备', '2017-06-02 10:47:53', 'cb33c25f5c664058a111a9b876152317', '2017-06-02 10:48:04', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_nurse_type` VALUES ('1706151612520009', '局部护理', '9', '小部分护理', '2017-06-15 16:12:52', 'cb33c25f5c664058a111a9b876152317', '2017-06-15 16:13:11', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_opinion`
-- ----------------------------
DROP TABLE IF EXISTS `bis_opinion`;
CREATE TABLE `bis_opinion` (
  `opinion_id` varchar(50) NOT NULL,
  `custom_user_id` varchar(50) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`opinion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bis_opinion
-- ----------------------------
INSERT INTO `bis_opinion` VALUES ('1706190124150004', '20000019', '擦擦擦阿德', '2017-06-19 01:24:15');
INSERT INTO `bis_opinion` VALUES ('1706231423500012', '20000035', '策的d', '2017-06-23 14:23:50');
INSERT INTO `bis_opinion` VALUES ('1706260909090005', '20000020', '阔以哦', '2017-06-26 09:09:09');
INSERT INTO `bis_opinion` VALUES ('1706260909190006', '20000020', '很奇怪', '2017-06-26 09:09:19');
INSERT INTO `bis_opinion` VALUES ('1706261711180040', '20000041', '哈哈', '2017-06-26 17:11:18');

-- ----------------------------
-- Table structure for `bis_order_deposit`
-- ----------------------------
DROP TABLE IF EXISTS `bis_order_deposit`;
CREATE TABLE `bis_order_deposit` (
  `deposit_id` varchar(50) NOT NULL COMMENT '定金编号',
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `pay_account` varchar(100) DEFAULT NULL COMMENT '买家支付账号',
  `deposit_money` double DEFAULT NULL,
  `pay_way` varchar(10) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `deposit_status` varchar(10) DEFAULT NULL COMMENT '定金状态',
  `back_time` datetime DEFAULT NULL COMMENT '退回时间',
  `custom_user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`deposit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定金信息';

-- ----------------------------
-- Records of bis_order_deposit
-- ----------------------------
INSERT INTO `bis_order_deposit` VALUES ('1708131801090001', '20170813001', 'oT6rnsiiSWnbx32vLkyn7XKzc5eA', '0.01', '1', null, '1', null, '20000001');

-- ----------------------------
-- Table structure for `bis_order_pay`
-- ----------------------------
DROP TABLE IF EXISTS `bis_order_pay`;
CREATE TABLE `bis_order_pay` (
  `pay_id` varchar(50) NOT NULL COMMENT '编号',
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `buy_account` varchar(100) DEFAULT NULL COMMENT '买家支付账号',
  `pay_code` varchar(50) DEFAULT NULL COMMENT '发送给银行或第三方支付平台的支付订单号',
  `pay_way` varchar(10) DEFAULT NULL COMMENT '支付方式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_money` double DEFAULT NULL COMMENT '支付金额',
  `pay_status` varchar(10) DEFAULT NULL COMMENT '支付状态1待支付 2 支付成功 3支付失败',
  `pay_back` varchar(10) DEFAULT NULL COMMENT '支付回执1 已收到 2 未收到',
  `pay_type` varchar(10) DEFAULT NULL COMMENT 'pay_type 1收款 2 退款',
  `error_message` varchar(200) DEFAULT NULL COMMENT '错误信息',
  `nodify_time` datetime DEFAULT NULL COMMENT '通知时间',
  `transaction_id` varchar(50) DEFAULT NULL COMMENT '威富通交易号',
  `out_transaction_id` varchar(50) DEFAULT NULL COMMENT '微信订单号',
  `refund_id` varchar(50) DEFAULT NULL COMMENT '威富通退款单号',
  `auth_code` varchar(50) DEFAULT NULL COMMENT '授权码',
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单支付信息';

-- ----------------------------
-- Records of bis_order_pay
-- ----------------------------
INSERT INTO `bis_order_pay` VALUES ('1708131801090001', '20170813001', 'oT6rnsiiSWnbx32vLkyn7XKzc5eA', 'A1708131801090001', '1', '2017-08-13 18:01:11', null, '0.01', '1', '2', '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `bis_post_menu`
-- ----------------------------
DROP TABLE IF EXISTS `bis_post_menu`;
CREATE TABLE `bis_post_menu` (
  `post_id` varchar(50) NOT NULL COMMENT '职位编号',
  `menu_id` varchar(50) NOT NULL COMMENT '菜单标号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位与菜单关联表';

-- ----------------------------
-- Records of bis_post_menu
-- ----------------------------
INSERT INTO `bis_post_menu` VALUES ('170419202708694', '0', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', 'b5764849b97242d68e88e10551205677', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', 'd7236c0bba754cb0b7ea91b6cf2e161d', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', '436e6843529d49d3869454d59840e137', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', '9067fbad25de49d2af9ee5376c2c9154', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', '3b8d162dddd444f8b1ca5c81139393fa', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', 'c9a6d18b44314eb2aca0babe33d29118', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', '591f5781947b4beda180794ce434e509', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', 'd58049dcf1fd4b7fa7751852e2aabe53', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202708694', '71a88cae8792452a8896ba7063261539', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202736142', '0', '2017-08-13 17:34:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202736142', 'b5764849b97242d68e88e10551205677', '2017-08-13 17:34:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202736142', '436e6843529d49d3869454d59840e137', '2017-08-13 17:34:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202736142', '591f5781947b4beda180794ce434e509', '2017-08-13 17:34:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_post_menu` VALUES ('170419202736142', 'd58049dcf1fd4b7fa7751852e2aabe53', '2017-08-13 17:34:58', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_project_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_project_record`;
CREATE TABLE `bis_project_record` (
  `record_id` varchar(50) NOT NULL DEFAULT '' COMMENT '记录项目',
  `project_id` varchar(50) DEFAULT NULL COMMENT '项目编号',
  `bag_record_id` varchar(50) DEFAULT NULL COMMENT '礼包编号',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '消费店铺',
  `project_status` varchar(10) DEFAULT '状态' COMMENT '1 未使用 2 已使用 3 已过期',
  `draw_time` datetime DEFAULT NULL COMMENT '领取时间',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bis_project_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bis_shop`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop`;
CREATE TABLE `bis_shop` (
  `shop_id` varchar(50) NOT NULL COMMENT '店铺编号',
  `owner_id` varchar(50) DEFAULT NULL COMMENT '店主编号',
  `shop_name` varchar(100) DEFAULT NULL COMMENT '商店名称',
  `short_name` varchar(100) DEFAULT NULL COMMENT '商家简称',
  `month_cash_in` double(12,2) DEFAULT NULL COMMENT '月目标现金收入',
  `month_expend_in` double(12,2) DEFAULT NULL COMMENT '月目标消耗收入',
  `shop_license` varchar(100) DEFAULT NULL COMMENT '商家营业执照',
  `shop_image` varchar(100) DEFAULT NULL COMMENT '商家标志图片',
  `shop_detail_image` varchar(100) DEFAULT NULL,
  `gps_x` double DEFAULT '0',
  `gps_y` double DEFAULT '0',
  `sort_no` int(8) DEFAULT NULL COMMENT '排序号',
  `shop_address` varchar(200) DEFAULT NULL COMMENT '店铺地址',
  `shop_qrcode` varchar(100) DEFAULT NULL COMMENT '店铺二维码',
  `shop_phone` varchar(50) DEFAULT NULL COMMENT '店铺电话',
  `shop_area` double(12,2) DEFAULT NULL COMMENT '店铺面积',
  `shop_project` varchar(100) DEFAULT NULL COMMENT '店铺主营项目',
  `shop_type` varchar(10) DEFAULT NULL COMMENT '店铺类型',
  `shop_intro` text COMMENT '商家介绍',
  `show_status` varchar(10) DEFAULT '1' COMMENT '显示状态 0禁用 1启用',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否删除 0:有效1:删除',
  `shop_remark` varchar(200) DEFAULT NULL,
  `begin_time` varchar(50) DEFAULT NULL COMMENT '营业开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '营业结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺信息表';

-- ----------------------------
-- Records of bis_shop
-- ----------------------------
INSERT INTO `bis_shop` VALUES ('10000001', '1708131726420001', '津滨腾越店', '美研社', '1000.00', '1000.00', '914403000539720576', '/imageFile/shopImage/1708131729400002S.jpg', '/imageFile/shopImage/1708131729400003B.jpeg', '113.327357', '23.131888', '1', '天河区珠江新城华夏路49号津滨腾越大厦南塔1313', '/imageFile/shopQrCode/10000001.png', '020-22123058', '100.00', '美容', '1', null, '1', '0', null, '09:00', '22:00', '2017-08-13 17:29:41', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:29:41', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_shop_collect`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop_collect`;
CREATE TABLE `bis_shop_collect` (
  `collect_id` varchar(50) NOT NULL COMMENT '收藏编号',
  `custom_user_id` varchar(50) DEFAULT NULL COMMENT '消费者编号',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '店铺编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺收藏';

-- ----------------------------
-- Records of bis_shop_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `bis_shop_custom`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop_custom`;
CREATE TABLE `bis_shop_custom` (
  `custom_user_id` varchar(50) NOT NULL COMMENT '顾客编号',
  `shop_id` varchar(50) NOT NULL COMMENT '店铺编号',
  `recent_time` datetime DEFAULT NULL COMMENT '最近到店时间',
  `create_time` datetime DEFAULT NULL COMMENT '第一次关注店时间',
  PRIMARY KEY (`custom_user_id`,`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺与顾客关联信息';

-- ----------------------------
-- Records of bis_shop_custom
-- ----------------------------
INSERT INTO `bis_shop_custom` VALUES ('20000001', '10000001', '2017-08-13 18:01:11', '2017-08-13 18:01:11');

-- ----------------------------
-- Table structure for `bis_shop_loginlog`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop_loginlog`;
CREATE TABLE `bis_shop_loginlog` (
  `log_id` varchar(50) NOT NULL COMMENT '日志编号',
  `session_id` varchar(50) DEFAULT NULL COMMENT '会话ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `account` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `login_time` datetime DEFAULT NULL COMMENT '登陆用户时间',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登陆IP',
  `explorer` varchar(50) DEFAULT NULL COMMENT '登陆浏览器',
  `exit_time` datetime DEFAULT NULL COMMENT '退出时间',
  `exit_type` varchar(10) DEFAULT '1' COMMENT '退出类型 1超时退出 2自动退出',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家登陆日志';

-- ----------------------------
-- Records of bis_shop_loginlog
-- ----------------------------
INSERT INTO `bis_shop_loginlog` VALUES ('1705070143270002', '34C8A62BC6061B241FAF8BFF0AA6A421', '陈骑元', 'chenqiyuan', '2017-05-07 01:43:27', '10000001', '0:0:0:0:0:0:0:1', 'CHROME/58.0.', '2017-05-07 01:43:43', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705070144440004', 'AD6918C0C584D78E0346F41761DEB845', '陈骑元', 'chenqiyuan', '2017-05-07 01:44:44', '10000001', '0:0:0:0:0:0:0:1', 'CHROME/58.0.', '2017-05-07 01:47:54', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705070147570001', '211B2451D51E02DA9FB395E35D801203', '陈骑元', 'chenqiyuan', '2017-05-07 01:52:32', '10000001', '0:0:0:0:0:0:0:1', 'CHROME/58.0.', '2017-05-07 01:52:50', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705070152520001', '8C53EE65F94DEF069E7ABFF948B721E9', '陈骑元', 'chenqiyuan', '2017-05-07 01:53:01', '10000001', '0:0:0:0:0:0:0:1', 'CHROME/58.0.', '2017-05-07 01:57:38', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705070157410001', 'FEEBE63DC7BCEC4F2790C0E120DAD024', '陈骑元', 'chenqiyuan', '2017-05-07 01:57:41', '10000001', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071311020001', 'D27ED3965CA5756F1C8FD1EA0CFC8D48', '陈骑元', 'chenqiyuan', '2017-05-07 13:11:02', '10000001', '127.0.0.1', 'CHROME/58.0.', '2017-05-07 13:13:18', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071314080002', '8878AB224AAD0BEE830F09C335038630', '陈骑元', 'chenqiyuan', '2017-05-07 13:14:08', '10000001', '127.0.0.1', 'CHROME/58.0.', '2017-05-07 13:18:31', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071318390003', '6479813E6E022B9B9FEE6ADCF773C5FF', '陈骑元', 'chenqiyuan', '2017-05-07 13:18:39', '10000001', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071330500001', '1D82D69EFFA94D06BF9A6B2F7A5BE70D', '陈骑元', 'dianpu', '2017-05-07 13:30:50', null, '127.0.0.1', 'CHROME/58.0.', '2017-05-07 13:33:53', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071333550002', 'F240615595E2A82EC65A0E96F9B812F7', '陈骑元', 'dianpu', '2017-05-07 13:33:55', null, '127.0.0.1', 'CHROME/58.0.', '2017-05-07 13:49:36', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071349370001', 'C0877BB57A276EE2C3A104FE0929427F', '陈骑元', 'dianpu', '2017-05-07 14:15:02', null, '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071418580001', 'AA59F628DA420B9E25C86DD34AF5DD0A', '陈骑元', 'dianpu', '2017-05-07 14:18:58', null, '127.0.0.1', 'CHROME/58.0.', '2017-05-07 14:33:09', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071433150001', '618336DC31CDA5468D67958DBDF8C7AB', '陈骑元', 'chenqiyuan', '2017-05-07 14:33:15', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-07 14:34:32', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071434380002', 'E56274E33D53C92352733A01C3D5D931', '陈骑元', 'chenqiyuan', '2017-05-07 14:34:38', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-07 14:34:59', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705071435040003', '430543FC227360D169F0C95C4C60510E', '陈骑元', 'chenqiyuan', '2017-05-07 14:35:04', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705072325300001', 'BFFE3D0F64A4D8CCAE6468EA653F411C', '陈骑元', 'chenqiyuan', '2017-05-08 01:33:51', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705080201540001', '68263F7D5E37BD2C6910513290E5B32E', '陈骑元', 'chenqiyuan', '2017-05-08 02:01:54', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705082352470001', '184030145025758237D1F80887048A18', '陈骑元', 'chenqiyuan', '2017-05-08 23:52:47', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-09 01:34:51', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705090134570001', 'AC9457D4C9AF03523860E8497C5F074D', '陈骑元', 'chenqiyuan', '2017-05-09 01:34:57', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705090208140001', 'BC4F4C69B758F352B89F838ADD48478F', '陈骑元', 'chenqiyuan', '2017-05-09 02:08:14', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705090217240001', 'DA1A270A3D4D3809E69933018E8D9921', '陈骑元', 'chenqiyuan', '2017-05-09 02:17:24', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-09 02:29:42', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705090229440002', '7FB880132FDD4EA55EB5B3A9D86B99B7', '陈骑元', 'chenqiyuan', '2017-05-09 02:29:44', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705092342110001', 'FAB6B714450A8DC2E8B380BF1182DC5B', '陈骑元', 'chenqiyuan', '2017-05-09 23:42:11', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705100132000001', '8698EB3EC60676FCBEEC544341FECFAF', '陈骑元', 'chenqiyuan', '2017-05-10 01:32:00', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705142222010001', '7BDACC51E483F7F0EF8F8EF42F27E2B9', '陈骑元', 'chenqiyuan', '2017-05-14 22:22:01', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-14 23:26:47', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705142327000002', '15AE88943B713213EAF5F2F4A1CFEE88', '陈骑元', 'chenqiyuan', '2017-05-14 23:27:00', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-14 23:27:04', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705142327060003', '5623E098176102A4A657BF533D846B84', '陈骑元', 'chenqiyuan', '2017-05-14 23:27:06', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-15 00:10:05', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705150010100004', '267921419FFC6E4598D33F9157775E94', '陈骑元', 'chenqiyuan', '2017-05-15 00:10:10', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-15 00:32:08', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705150034010005', '6E57B0AF6F2786162A9375CF0B9478C8', '陈骑元', 'chenqiyuan', '2017-05-15 00:34:01', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-15 01:27:13', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705150127200006', 'B9502D1C15619CCA55FE237BD4455728', '陈骑元', 'dianpu', '2017-05-15 01:27:20', '170419004152514', '127.0.0.1', 'CHROME/58.0.', '2017-05-15 01:27:25', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705150127350007', 'C58A0907C363ADC8FE506A020BBA9A1E', '陈骑元', 'chenqiyuan', '2017-05-15 01:27:35', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-15 01:28:02', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705150128050008', '3366C9DA8BA3FC86CA422006B299BEF6', '陈骑元', 'chenqiyuan', '2017-05-15 01:28:05', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705170029470001', 'D788D1D4D0F58ECB5C70C31A2A1A6628', '陈骑元', 'chenqiyuan', '2017-05-17 00:29:47', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-17 01:02:56', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705170103100002', 'E9BFF319DEC6694B3998C29AF575B6B9', '陈骑元', 'chenqiyuan', '2017-05-17 01:40:16', '170422190326011', '127.0.0.1', 'CHROME/58.0.', '2017-05-17 02:01:36', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705170201420003', '0769E44C85FA1CF1F8B86868D28EC3ED', '陈骑元', 'chenqiyuan', '2017-05-17 02:01:42', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705170203040004', '832AFBC31084C8C63BCF81FA97B7989D', '陈骑元', 'chenqiyuan', '2017-05-17 02:03:04', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705172334430001', 'DB3A425883F39B14A3F84C0ACDDE0993', '陈骑元', 'chenqiyuan', '2017-05-17 23:34:43', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705180019110002', '70F0ED9600DE36E00C27F890176BC99F', '陈骑元', 'chenqiyuan', '2017-05-18 00:25:43', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705180024380003', 'A60107F338361DE0074B8BFE18CC0F31', '陈骑元', 'chenqiyuan', '2017-05-18 00:24:38', '170422190326011', '127.0.0.1', '未知浏览器', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705180114220004', '091BEBB555E7C999761DB580033121D4', '陈骑元', 'chenqiyuan', '2017-05-18 01:14:22', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705180127550005', '039E059790587021E38757B4B3081EDD', '陈骑元', 'chenqiyuan', '2017-05-18 01:27:55', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182124180001', '9515F3B8C6AC3BABC7BA0BE5199E23CF', '陈骑元', 'chenqiyuan', '2017-05-18 21:24:18', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182139010001', '8410FB6FA5D02DCED7074D6BF3CDB498', '陈骑元', 'chenqiyuan', '2017-05-18 21:39:01', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182247420001', 'C6770553F7315DDEDBF6762C4B1C14CF', '陈骑元', 'chenqiyuan', '2017-05-18 22:47:42', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182254060002', '8F5CC9F70B9B26B4B0FC4511A8F84CC6', '陈骑元', 'chenqiyuan', '2017-05-18 22:54:06', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182257190003', 'C1DA2B4E3A5D5D223A84A8410E9231F4', '陈骑元', 'chenqiyuan', '2017-05-18 22:57:19', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182258330004', 'D099E6DFA8D73F72BE19C735FF5F02F8', '陈骑元', 'chenqiyuan', '2017-05-18 22:58:33', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705182301480005', '74D65BEA02B8691B77A27D77C6100DE8', '陈骑元', 'chenqiyuan', '2017-05-18 23:01:48', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705190017290001', 'BC3BA0F53698404B48E10020415D9C0E', '陈骑元', 'chenqiyuan', '2017-05-19 00:17:29', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705222340170001', '0955A25460FE8EC01EC6C69FC60424E7', '陈骑元', 'chenqiyuan', '2017-05-22 23:40:17', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705232220300001', 'E1036A2054F740D1FECECD71207C6CB2', '陈骑元', 'chenqiyuan', '2017-05-23 22:20:30', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705242137360001', 'E0C41A93412798912DDB02919FF2A203', '陈骑元', 'chenqiyuan', '2017-05-24 21:37:36', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705242345490001', '896BCF32DA84AA484857B591C337B842', '陈骑元', 'chenqiyuan', '2017-05-24 23:45:49', '170422190326011', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705310137450001', '84546C795D5106E1EB95A258F7BF635B', '陈骑元', 'chenqiyuan', '2017-05-31 01:37:45', '170422190326011', '119.130.231.23', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705310941000002', '8CEA898896F1317B3BC8C5C3142985BB', '张三', 'zhangsan', '2017-05-31 09:41:00', '1705251054130005', '119.129.208.100', 'CHROME/58.0.', '2017-05-31 09:41:19', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705310941240003', '019AFFF6B764454A5883913C05D23034', '001', 'jbty_01', '2017-05-31 09:41:24', '1705251449530011', '119.129.208.100', 'CHROME/58.0.', '2017-05-31 11:42:10', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705311142590004', '9B76B8906EBD2F1E069222AE206BAC79', '001', 'jbty_01', '2017-05-31 11:42:59', '1705251449530011', '119.129.208.100', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705311348130005', '18BFEF468AFE483CFA83E17FCA3819F2', '001', 'jbty_01', '2017-05-31 13:48:13', '1705251449530011', '119.129.208.100', 'CHROME/58.0.', '2017-05-31 13:49:09', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1705311352140007', '9D8DB57B45E48E259064078CE5A4D4EA', '001', 'test', '2017-05-31 13:52:14', '1705311351550006', '119.129.208.100', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705311353120008', 'CD305FFE8A2542D29DB474F66DCF27C9', '001', 'test', '2017-05-31 15:50:02', '1705311351550006', '183.36.81.85', 'CHROME/45.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1705311806310010', '156708688040D46D760DB863DD68B8A4', '001', 'jbty_01', '2017-05-31 18:06:31', '1705251449530011', '119.129.208.100', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010315200001', '5AAE8B00261C50421209371527F2B1A6', '陈骑元', 'chenqiyuan', '2017-06-01 03:15:20', '170422190326011', '119.130.229.207', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010322210001', '6F172A7CAD8B7E67753B0C83FE7CD0A3', '陈骑元', 'chenqiyuan', '2017-06-01 03:22:21', '170422190326011', '119.130.229.207', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010327470001', 'B90DD05E9791B90A22F536C0F712063E', '陈骑元', 'chenqiyuan', '2017-06-01 03:27:47', '170422190326011', '119.130.229.207', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010337100001', '780A8424875AF9FBB65219A60877365D', '陈骑元', 'chenqiyuan', '2017-06-01 03:37:10', '170422190326011', '119.130.229.207', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010900370002', '91C213BB37A653327FE98C4990037CFB', '001', 'jbty_01', '2017-06-01 09:00:37', '1705251449530011', '119.129.208.201', 'CHROME/58.0.', '2017-06-01 09:29:02', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010914170003', '6371429437B350CB0998CD7D6673283A', '陈骑元', 'chenqiyuan', '2017-06-01 09:14:17', '170422190326011', '58.62.52.66', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706010929330004', '7EA8F142E6A5606EA690CD76FF920B3B', '001', 'jbty_01', '2017-06-01 09:29:33', '1705251449530011', '119.129.208.201', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706011555280112', 'ED2CD403DB44C16576EC258E99A90284', '001', 'jbty_01', '2017-06-01 15:55:28', '1705251449530011', '61.140.62.9', 'CHROME/45.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706011810540113', 'F5D4A6E86AC1487A34D29DB572E773BC', '001', 'jbty_01', '2017-06-01 18:10:54', '1705251449530011', '119.129.208.201', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706012240110001', 'EC3D17FB27EF2017623C0F733A428AD7', '陈骑元', 'chenqiyuan', '2017-06-01 22:40:11', '170422190326011', '119.130.231.130', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706020949510010', '130EA7D17C03ACBCA5B590D82D5BF14A', '测试', 'test', '2017-06-02 09:49:51', '1706020949060009', '119.129.208.201', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706050142310003', '3E5DB33AF72E817A7A9AEFECAC1B155F', '测试', 'test', '2017-06-05 01:42:31', '1706020949060009', '218.19.99.176', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706051254060013', '2151F42876A4C8ED98970A59238B4963', '测试', 'test', '2017-06-05 12:54:06', '1706020949060009', '119.129.210.157', 'CHROME/58.0.', '2017-06-05 14:00:10', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706051400130017', '1BFDD125FEB25D7C792A833819623B81', '测试', 'test', '2017-06-05 14:00:13', '1706020949060009', '119.129.210.157', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706060936550003', '275FC15419E1DA44CF1D0436F8E54957', '吴桂玲', 'wuguiling', '2017-06-06 09:36:55', '1706012215580115', '113.111.63.86', 'CHROME/50.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706061428060014', '8BD5DCD852ED2CED862B8B15E3532EB5', '吴桂玲', 'mys_th', '2017-06-06 14:28:06', '1706012023280114', '113.111.63.86', 'CHROME/50.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706061607410025', '27B62630CEABCF805D5CA17C530F5C1B', '测试', 'test', '2017-06-06 16:07:41', '1706020949060009', '113.111.10.227', 'CHROME/45.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706082132000001', '6C2811EEEFEAE5E41948B4985B6DA4C8', '测试', 'test', '2017-06-08 21:32:00', '1706020949060009', '119.145.83.215', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706091118030010', '2095E570D6FB0FADA3DF6E8EF44630F0', '测试', 'test', '2017-06-09 11:18:03', '1706020949060009', '119.129.209.133', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706132230050004', '210A09E2304D06EC9CFA54F1DE16C797', '测试', 'test', '2017-06-13 22:30:05', '1706020949060009', '119.145.82.40', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706141402100029', 'D6063179A38A3E619A16440F7599D77A', '测试', 'test', '2017-06-14 14:02:10', '1706020949060009', '119.129.209.171', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706141638220051', '8B5349B5303C8844F101C5F27C884AC5', '测试', 'test', '2017-06-14 16:38:22', '1706020949060009', '119.129.209.171', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706150239160002', 'AB646CAC1F71F845C7B2BCA0326C0D9F', 'Lindsay', 'testbyLindsay', '2017-06-15 02:39:16', '1706150238040001', '223.73.58.39', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706151653110023', 'A1A6A6DB03AB30DCC0A4A3B027CAD7AA', '陈小妞', 'dianzhang', '2017-06-15 16:53:11', '1706151603050008', '119.131.106.89', 'FIREFOX/51.', '2017-06-15 16:58:21', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706151658500024', 'DEC37FFABF8F84C5386E51B82B6F3BA9', '酥酥酥', 'shxi', '2017-06-15 16:58:50', '1706151601030007', '119.131.106.89', 'FIREFOX/51.', '2017-06-15 17:01:45', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706151702010025', '9ADA191460B1E9673B5AD28922E093B6', '陈小妞', 'dianzhang', '2017-06-15 17:02:01', '1706151603050008', '119.131.106.89', 'FIREFOX/51.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706160942490008', '0545A3E665CAD2761C4286694EF362CC', '陈小妞', 'dianzhang', '2017-06-16 09:42:49', '1706151603050008', '119.131.106.89', 'FIREFOX/51.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706160957240011', '9FC4687F8E1FB4E202D26B9E0C0A07AD', '陈小妞', 'dianzhang', '2017-06-16 09:57:24', '1706151603050008', '119.131.106.89', 'CHROME/58.0.', '2017-06-16 10:48:39', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706161006310016', '7A0EAB8023D352079B96987B95DDC4F5', '测试', 'test', '2017-06-16 10:06:31', '1706020949060009', '119.129.208.93', 'CHROME/58.0.', '2017-06-16 10:06:36', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706161006430017', 'DDCEFC54279C4C1E575D7EA97A7F5B68', '陈小妞', 'dianzhang', '2017-06-16 10:06:43', '1706151603050008', '119.129.208.93', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706161048480018', '46C0F8DBEBFFE777B5AC91A8279CE025', '陈员工', 'yuangong', '2017-06-16 10:48:48', '1706160947290009', '119.131.106.89', 'CHROME/58.0.', '2017-06-16 11:47:24', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706161147310024', '36B5D388618769C48E323778B05F6103', '陈小妞', 'dianzhang', '2017-06-16 11:47:31', '1706151603050008', '119.131.106.89', 'CHROME/58.0.', '2017-06-16 11:59:23', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1706161200070025', 'C9A0B1574484CDC5294DE2340C9E1DAF', '陈员工', 'yuangong', '2017-06-16 12:00:07', '1706160947290009', '119.131.106.89', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706201013400003', '8A7CECE1FC452C4F2B211021F18B2A9B', '测试', 'test', '2017-06-20 10:13:40', '1706020949060009', '119.129.208.50', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706241140330014', 'B2EC3E6F10D34068015A6B6A3471EC23', '测试', 'test', '2017-06-24 11:40:33', '1706020949060009', '119.129.210.144', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706250227300008', '860ABEA59A25A3649D35D72B8AC83ABB', '测试', 'test', '2017-06-25 02:27:30', '1706020949060009', '119.130.229.67', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706252219310013', '9D7DD9C061AC4BB994D5ACE417C0F214', '陈骑元', 'chenqiyuan', '2017-06-25 22:19:32', '170422190326011', '218.19.99.186', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706260848270004', '6B8AC480CDC79A29461542BA2685AEA0', '测试', 'test', '2017-06-26 08:48:27', '1706020949060009', '119.129.128.91', '未知浏览器', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706260925220007', '7EF04D139B5319B83418B04F4C84BCF7', '测试', 'test', '2017-06-26 09:25:22', '1706020949060009', '119.129.128.91', 'CHROME/56.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706280917040012', 'A129606F6C32A2536575DAAF5EE3AD27', '测试', 'test', '2017-06-28 09:17:04', '1706020949060009', '59.42.54.209', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706281009170032', '3690EC86E8AE865E8B9E90CDD5013A61', '测试', 'test', '2017-06-28 10:09:17', '1706020949060009', '119.129.210.198', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706281034170045', '07BF307B051B33AF7406F870124665B9', '测试', 'test', '2017-06-28 10:34:17', '1706020949060009', '119.129.210.198', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706281402390056', 'B6F386C778BBEF0D8D923772766D0F23', '测试', 'test', '2017-06-28 14:02:39', '1706020949060009', '116.22.16.100', 'FIREFOX/54.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706281639370063', 'E6DB4F5D30E12DF410F6ED539E2FFF79', '测试', 'test', '2017-06-28 16:39:37', '1706020949060009', '59.42.54.100', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706281703310069', '44922F3B83E15CED30EA4912729A0570', '员工', 'test_yg', '2017-06-28 17:03:31', '1706281703150068', '119.129.210.198', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706291001400023', '468EA09F8EB46D45DFECE0D4C83C0276', '测试', 'test', '2017-06-29 10:01:40', '1706020949060009', '59.42.54.100', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706291009250027', 'C502C17D01064B5C82C2FB407D1848B4', '测试', 'test', '2017-06-29 10:09:25', '1706020949060009', '119.129.209.198', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706291025020042', '721507CB54E3804F0E128B75BD03D5E7', '陈小妞', 'dianzhang', '2017-06-29 10:31:43', '1706151603050008', '119.129.209.198', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706291027160047', 'E9613C8C8F8F9302B7ABBD77AD212558', '测试', 'test', '2017-06-29 10:27:16', '1706020949060009', '113.67.156.148', 'CHROME/59.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706291410080005', 'B33D9EB63CEB14CF25957D83B6B9A2F8', '测试', 'test', '2017-06-29 14:10:08', '1706020949060009', '59.42.54.100', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706301646120043', '485658C5AD671302665B1ABF203141BC', '测试', 'test', '2017-06-30 16:46:12', '1706020949060009', '117.136.41.57', 'CHROME/52.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706301656000044', '9EC1C936EB02AF2EF623A898DDC8F3FC', '测试', 'test', '2017-06-30 16:56:00', '1706020949060009', '117.136.41.57', 'CHROME/59.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706301706370045', 'B15BAA28A346D425130E4E88E53ABF12', '测试', 'test', '2017-06-30 17:06:37', '1706020949060009', '117.136.41.57', 'CHROME/59.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1706301834190074', 'C789E59484C22BCC4C3007CEEE444AC6', '测试', 'test', '2017-06-30 18:34:19', '1706020949060009', '113.68.67.241', 'CHROME/59.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707011323260021', 'B955038EE464850E47AF39DB1941E570', '测试', 'test', '2017-07-01 13:23:26', '1706020949060009', '119.145.83.39', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707011356300003', 'F5CC2D2856D46C094E7FB1ADAB3ED7FB', '测试', 'test', '2017-07-01 14:11:29', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707011651050001', 'A01066FE79CC0A62E2890F9C4D91BADF', '测试', 'test', '2017-07-01 16:51:05', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707012108440002', 'B97DDF96D6E77DE0AB0BFDFDDFB21768', '测试', 'test', '2017-07-01 21:08:44', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707012220320010', 'A4394D664FCE3003B9AAD427849B760E', '测试', 'test', '2017-07-01 22:20:32', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707012243570001', 'C17031F77B5FF1F380DB3A11F4B4B209', '测试', 'test', '2017-07-01 22:43:57', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707020747180001', 'FB71E2EBF540336252B830C3745D864E', '测试', 'test', '2017-07-02 07:47:18', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707020845290001', 'D1F602E5158FC09F936FF8996FE96B48', '测试', 'test', '2017-07-02 08:45:29', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707040117500001', 'EC02303C3E03939592F4371225699621', '测试', 'test', '2017-07-04 01:17:50', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707042321240005', '0B5F8529DCCDB6F2FE13CB363913833F', '测试', 'test', '2017-07-04 23:21:24', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707042326230001', 'E804109BA4E5E919C1064F5F31F7DEDC', '测试', 'test', '2017-07-04 23:26:23', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707042330370001', '9D6839C3E8668C6D149E553FAF701E1A', '测试', 'test', '2017-07-04 23:30:37', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707042358150001', 'D405E519F8D0CA886E5C8F80B5CFAC1A', '测试', 'test', '2017-07-04 23:58:15', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', '2017-07-05 00:03:21', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707050005390002', '12645F61DDE17016E285F2566CF767F9', '测试', 'test', '2017-07-05 00:05:39', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', '2017-07-05 00:06:07', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707050007080003', 'E89542094F71FEFFC2E61D71E64FD180', '测试', 'test', '2017-07-05 00:07:08', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707050009250004', '83058122B48EC56129D4B2235B5E512C', '测试', 'test', '2017-07-05 00:09:25', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', '2017-07-05 00:17:38', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707050040170001', '93386B53D6EDF3F33606321CE614C755', '测试', 'test', '2017-07-05 00:40:17', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707060023510001', '8F6484E9BE17F0258E3B82EAA32F5F23', '测试', 'test', '2017-07-06 00:23:51', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707062222160001', 'D09733C89B149C5D7D49EEAAC8E4517A', '测试', 'test', '2017-07-06 22:22:16', '1706020949060009', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707081910290001', '2E0F181401D2A92EBB60DD0ADDE4A9B0', '员工', 'test_yg', '2017-07-08 19:10:29', '1706281703150068', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707092323030001', 'EBFE5584A6F77668DCB5528CA8A09711', '001', 'test', '2017-07-09 23:23:03', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707102243200001', '11BD5952E8F77930715ECDF07657701B', '001', 'test', '2017-07-10 22:43:20', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', '2017-07-10 23:16:09', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707102317040005', '1E3642AE38133E0A07D6053235BA30BD', '001', 'test', '2017-07-10 23:17:04', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', '2017-07-10 23:26:21', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707102328090012', '90B109D1907A96723F0E1BC491060A0B', '001', 'test', '2017-07-10 23:28:09', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707112301320001', '694CEB3DF1B661A9EBD93A56B71050BF', '001', 'test', '2017-07-11 23:01:32', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707112329470001', '1295440DA2650E477F6F762DEFE11C02', '001', 'test', '2017-07-12 00:22:39', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120030160001', '19F0F6A9C7865D66BE6660E2608AE501', '001', 'test', '2017-07-12 00:30:16', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120043110001', '34E9FAF76B55D51DBA3CFB3923D1B72F', '001', 'test', '2017-07-12 00:43:11', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120058550001', '672BB84B175B916B3CCFB1FE434C23FD', '001', 'test', '2017-07-12 00:58:55', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120105010001', 'E77A7073D5CA122754AE2BD8F9A0ED7A', '001', 'test', '2017-07-12 01:05:01', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', '2017-07-12 01:17:21', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120117280001', 'F49E7F45B5D5E2ADEA570E6AC1D13F8B', '吴桂玲', 'mys_th', '2017-07-12 01:33:53', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120137190001', '96C8671D12EFE05D7D2F78660F3DB237', '吴桂玲', 'mys_th', '2017-07-12 01:37:19', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120150140002', '0EBE24975DF654FBD50FFA98891A2B12', '吴桂玲', 'mys_th', '2017-07-12 01:50:14', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120152110003', '1635E9DE8885948E72EE00702B228960', '吴桂玲', 'mys_th', '2017-07-12 01:52:11', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120155140004', '958D26A03147B54E9F3FCFDB745BED24', '吴桂玲', 'mys_th', '2017-07-12 01:55:14', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120157320005', '39E473F3E52698061F6FCE64F6E99A5A', '吴桂玲', 'mys_th', '2017-07-12 01:57:32', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120204100006', '606B5A850938EA33AE992A1C1F64742F', '吴桂玲', 'mys_th', '2017-07-12 02:04:10', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120211570007', '219587D6B60CDFE0F980A7004FB0E902', '吴桂玲', 'mys_th', '2017-07-12 02:11:57', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120214000008', '591E885183633AADE1E338A7F2586583', '吴桂玲', 'mys_th', '2017-07-12 02:14:00', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707120215110009', '8DD70CCCFB63E387FDEC80C9F036EA90', '吴桂玲', 'mys_th', '2017-07-12 02:15:11', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707122313170001', '0951DDF571CF38F3554D7492C57814B9', '吴桂玲', 'mys_th', '2017-07-12 23:37:10', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-12 23:37:53', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707122340160001', '43A33C24AE01887E4CD91AE1C84CA476', '001', 'test', '2017-07-12 23:41:34', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', '2017-07-12 23:44:42', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707122344480002', '8F1C28F1542A3B2102355DECAB048210', '吴桂玲', 'mys_th', '2017-07-12 23:44:48', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707122353190003', 'BABF36C0C52779BB0D20D1D3F529EEEC', '001', 'test', '2017-07-12 23:53:19', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', '2017-07-12 23:53:47', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707122353520004', '2538A24189067A1BA994ED995EB20711', '吴桂玲', 'mys_th', '2017-07-12 23:53:52', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707130042510008', '251532E62A6EC1C4EE642042E4EF7953', '吴桂玲', 'mys_th', '2017-07-13 00:42:51', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-13 00:52:27', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707140020030003', 'A4F06D64F7BE2AEE30C7C4F69654B473', '吴桂玲', 'mys_th', '2017-07-14 00:20:03', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707142134460003', '702CB0BCCB2F1DE1D2F640C4A6AF8B27', '吴桂玲', 'mys_th', '2017-07-14 21:34:46', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707160002090001', '9766F4EC92C8E367A074E338549CB707', '吴桂玲', 'mys_th', '2017-07-16 00:02:09', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161529260001', '2A93E62C818518E8002081646500F95B', '吴桂玲', 'mys_th', '2017-07-16 15:29:26', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161603350001', '239FB6C6A7220A54355B10AC65FF6D99', '吴桂玲', 'mys_th', '2017-07-16 16:03:35', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161609160001', '91188C1D86EF8AA73CC6DA34861D45B9', '吴桂玲', 'mys_th', '2017-07-16 16:09:16', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 16:09:56', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161621470002', '1C73E3899C242EADA1537094F7EB01D7', '吴桂玲', 'mys_th', '2017-07-16 16:21:47', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 16:22:35', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161637140001', 'C3CF17567313A18D5380E0B5EBEF9EBB', '陈子华', 'chenzihua', '2017-07-16 16:37:14', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 17:05:23', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161705310001', '6FFAF058D2221CB18CBA7398FF781DF7', '陈子华', 'chenzihua', '2017-07-16 17:05:31', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161709080001', 'BEA2FB05CF4C210CD97FC54133FA4926', '陈子华', 'chenzihua', '2017-07-16 17:09:08', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 17:14:48', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161715020001', 'EB67ABB146BF76E9472D65105D0694CF', '陈子华', 'chenzihua', '2017-07-16 17:15:02', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 17:20:52', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161717410002', 'B26D3C81FF8F3482710EBE1C2D60DF14', '陈子华', 'chenzihua', '2017-07-16 17:17:41', '1706012222090116', '127.0.0.1', '未知浏览器', '2017-07-16 17:22:22', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161718330003', '34D43A377C85660141015CD6790FF7F8', '陈子华', 'chenzihua', '2017-07-16 17:18:33', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 17:19:49', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161720570001', '5833C91772CD06D67387AAC49BC7F7B3', '吴桂玲', 'mys_th', '2017-07-16 17:20:57', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 17:21:22', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161721350002', 'B7A66E16BCF416FE7C8864EF19BE8E19', '陈子华', 'chenzihua', '2017-07-16 17:21:35', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 17:24:22', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707161935000001', '79D8C7F955338AFB9A8A77523C729521', '吴桂玲', 'mys_th', '2017-07-16 19:35:00', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 20:59:26', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162100040001', 'AF786DDD0223BC1D1F72427573F01E9D', '吴桂玲', 'mys_th', '2017-07-16 21:00:04', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 21:21:06', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162119470001', '908DCE005E2FBDABA3B787E6B3FEE53F', '吴桂玲', 'mys_th', '2017-07-16 21:19:47', '1706012023280114', '127.0.0.1', '未知浏览器', '2017-07-16 21:20:27', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162120410002', '0C5DD5E8EA091C54E855F24FF18B0C0E', '陈子华', 'chenzihua', '2017-07-16 21:20:41', '1706012222090116', '127.0.0.1', '未知浏览器', '2017-07-16 21:26:30', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162125170003', '4543725D12CF7463329B89FF00A2280A', '吴桂玲', 'mys_th', '2017-07-16 21:25:17', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 21:25:32', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162125590004', '3157A66B7D822F3E505F112BCC39A31D', '陈子华', 'chenzihua', '2017-07-16 21:25:59', '1706012222090116', '127.0.0.1', 'CHROME/58.0.', '2017-07-16 23:02:50', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162126370005', '6A09339949F7D0BE0F589CDCCEF05406', '陈子华', 'chenzihua', '2017-07-16 21:26:37', '1706012222090116', '127.0.0.1', '未知浏览器', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162304330001', 'D8C4F3A32F6D877C5F4455F5CC81863B', '001', 'test', '2017-07-16 23:04:33', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707162327150001', '5A0023F60ADCFBB55D180202148B0F12', '001', 'test', '2017-07-16 23:27:15', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707170010230001', '1C62D5493B9CC29D955E7FCEB2CB6B24', '001', 'test', '2017-07-17 00:10:23', '1705311351550006', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707172331300001', 'BCA94748590F5B6F9CCC41245B77AD65', '吴桂玲', 'mys_th', '2017-07-17 23:39:23', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-17 23:49:45', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707180032520010', '82833D6DD046BACC71731D9FE4E3A6B4', '吴桂玲', 'mys_th', '2017-07-18 00:32:52', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707182202060004', '85C1A59D3FC170FEA2581D0B8408670C', '吴桂玲', 'mys_th', '2017-07-18 22:02:06', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707182216220001', '5A77098C8D76A1EED7BD64010FDB23DF', '吴桂玲', 'mys_th', '2017-07-18 22:16:22', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707182229460002', 'C4A7E63F3B723855EC4BB277A2F71913', '吴桂玲', 'mys_th', '2017-07-18 22:29:48', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707182248470003', '4CC5D0B7077FBD66306B5DD918898FBA', '吴桂玲', 'mys_th', '2017-07-18 22:48:47', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707182315540004', '48B70FCB81DEE1CFE28BA11D339D9A44', '吴桂玲', 'mys_th', '2017-07-18 23:15:54', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707242316520001', 'E9852E929D39E38D29565CFEC0F57C66', '吴桂玲', 'mys_th', '2017-07-24 23:16:52', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707242327290001', 'AD729B7935D8192E0BEE0D033AD77D4D', '吴桂玲', 'mys_th', '2017-07-24 23:27:29', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707242337440001', '1468918F8D83BE0E9B100635DF0AF72F', '吴桂玲', 'mys_th', '2017-07-24 23:37:44', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707250002350001', '74191CEDA3632D4F14D0B08EEB5EAB6F', '吴桂玲', 'mys_th', '2017-07-25 00:02:35', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-07-25 00:17:27', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1707250024160006', '444221EDF4B79348F7E8EBD336112946', '吴桂玲', 'mys_th', '2017-07-25 00:24:16', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707301351540003', '4CE090E27A2666E106383185D31276DC', '吴桂玲', 'mys_th', '2017-07-30 13:51:54', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707301422270005', 'B55400F7278AA4DDB6D48B7468D06A29', '吴桂玲', 'mys_th', '2017-07-30 14:22:27', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707301516110001', '0E43BDD4A38FAF2747E13D4D95B4B580', '吴桂玲', 'mys_th', '2017-07-30 15:16:11', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1707312042440001', '1A8E1E606A6AAB126338C78CEA4B1A45', '吴桂玲', 'mys_th', '2017-07-31 20:42:44', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1708051550320001', '0206DE38C35C2CC7C7803EC94EAB2922', '吴桂玲', 'mys_th', '2017-08-05 15:50:32', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-08-05 16:12:00', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1708051613040002', '87D48EBA30077E0DA0F84F169229FFF4', '吴桂玲', 'mys_th', '2017-08-05 16:13:04', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', null, '1');
INSERT INTO `bis_shop_loginlog` VALUES ('1708082300410001', '8324CF7F0D755C58AA94D18A10C2368D', '吴桂玲', 'mys_th', '2017-08-08 23:00:41', '1706012023280114', '127.0.0.1', 'CHROME/58.0.', '2017-08-08 23:00:56', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1708131735290006', '9A6E5CE16738CAF08BC11DC7F9CAC583', '财务', 'cw', '2017-08-13 17:35:29', '1708131733450005', '127.0.0.1', 'CHROME/58.0.', '2017-08-13 17:35:42', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1708131736400007', '0A5CA4E4A46DDC2A6A66D4F9AE011D7A', '店长', 'yg', '2017-08-13 17:36:40', '1708131732120004', '127.0.0.1', 'CHROME/58.0.', '2017-08-13 17:36:45', '2');
INSERT INTO `bis_shop_loginlog` VALUES ('1708131736480008', '8C9127EC03267AD5F8418E284086603E', '美容管理店主', 'dz', '2017-08-13 17:36:48', '1708131726420001', '127.0.0.1', 'CHROME/58.0.', null, '1');

-- ----------------------------
-- Table structure for `bis_shop_post`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop_post`;
CREATE TABLE `bis_shop_post` (
  `post_id` varchar(50) NOT NULL DEFAULT '' COMMENT '职位编号',
  `post_name` varchar(50) DEFAULT NULL COMMENT '职位名称',
  `post_code` varchar(50) DEFAULT NULL COMMENT '职位编码',
  `post_desc` varchar(200) DEFAULT NULL COMMENT '职位描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺职位信息表';

-- ----------------------------
-- Records of bis_shop_post
-- ----------------------------
INSERT INTO `bis_shop_post` VALUES ('170419202708694', '店长', '01', '', '2017-04-19 20:27:09', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:32:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_shop_post` VALUES ('170419202719417', '员工', '02', '', '2017-04-19 20:27:19', 'cb33c25f5c664058a111a9b876152317', '2017-07-16 23:01:43', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_shop_post` VALUES ('170419202736142', '财务', '03', '', '2017-04-19 20:27:36', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:34:58', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_shop_post` VALUES ('1706151549060006', '实习生', 'SX', '', '2017-06-15 15:49:06', 'cb33c25f5c664058a111a9b876152317', '2017-07-05 00:06:44', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_shop_user`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop_user`;
CREATE TABLE `bis_shop_user` (
  `shop_user_id` varchar(50) NOT NULL COMMENT '用户编号',
  `user_type` varchar(10) DEFAULT '2' COMMENT '用户类型 1 店主 2员工',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '店铺编号',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `username` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像照片',
  `work_number` varchar(50) DEFAULT NULL COMMENT '工号',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别1:男2:女3:保密',
  `post_code` varchar(10) DEFAULT NULL COMMENT '职位编码',
  `entry_date` date DEFAULT NULL COMMENT '入职日期',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `born_date` date DEFAULT NULL COMMENT '出生日期',
  `idno` varchar(20) DEFAULT NULL COMMENT '身份证',
  `address` varchar(100) DEFAULT NULL COMMENT '居住地址',
  `linkman` varchar(50) DEFAULT NULL COMMENT '紧急联系人',
  `linkphone` varchar(20) DEFAULT NULL COMMENT '紧急联系电话',
  `status` varchar(10) DEFAULT NULL COMMENT '状态0:禁用1:启用 0:离职 1:在职',
  `is_del` varchar(10) DEFAULT NULL COMMENT '是否删除0有效1删除',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`shop_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺员工信息表';

-- ----------------------------
-- Records of bis_shop_user
-- ----------------------------
INSERT INTO `bis_shop_user` VALUES ('1708131726420001', '1', null, 'dz', '美容管理店主', '8q1idbs+7tc=', null, null, '0', null, null, '13702093333', '', '', null, '330326196506050739', null, null, null, '1', '0', '', '2017-08-13 17:26:42', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:36:20', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_shop_user` VALUES ('1708131732120004', '2', '10000001', 'yg', '店长', 'RqusgwwLDV0=', null, '001', '2', '01', '2017-08-13', '13892803333', null, null, null, '330324198111284174', null, null, null, '1', '0', null, '2017-08-13 17:32:12', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:32:12', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `bis_shop_user` VALUES ('1708131733450005', '2', '10000001', 'cw', '财务', 'xkK97M6v0pw=', null, '002', '2', '03', '2017-08-13', '15818896036', null, null, null, '412727197502028045', null, null, null, '1', '0', null, '2017-08-13 17:33:45', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:33:45', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `bis_shop_work`
-- ----------------------------
DROP TABLE IF EXISTS `bis_shop_work`;
CREATE TABLE `bis_shop_work` (
  `work_id` varchar(50) NOT NULL DEFAULT '' COMMENT '工作机编号',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '绑定店铺编号',
  `shop_ip` varchar(50) DEFAULT NULL COMMENT '工作机IP',
  `work_password` varchar(50) DEFAULT NULL COMMENT '工作机密码',
  `whether_set` varchar(10) DEFAULT '0' COMMENT '是否设置密码 0 是 1否',
  `work_sn` varchar(50) DEFAULT NULL COMMENT '工作机SN',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`work_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作机';

-- ----------------------------
-- Records of bis_shop_work
-- ----------------------------

-- ----------------------------
-- Table structure for `bis_sms_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_sms_record`;
CREATE TABLE `bis_sms_record` (
  `record_id` varchar(50) NOT NULL DEFAULT '' COMMENT '记录编号',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `sms_type` varchar(10) DEFAULT NULL COMMENT '短信类型 1普通短信 2验证短信 3支付验证短信',
  `send_user_id` varchar(50) DEFAULT NULL COMMENT '发送用户编号，',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型 1员工 2顾客',
  `create_user_id` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT '0' COMMENT '短信状态  0失败 1成功',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `failure_cause` varchar(200) DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信记录';

-- ----------------------------
-- Records of bis_sms_record
-- ----------------------------
INSERT INTO `bis_sms_record` VALUES ('a297ac75073143b9800f22a6f37c38cf', '13802907704', '749825', '2', null, null, null, '2017-08-13 18:00:16', '1', '2017-08-13 18:00:16', null);

-- ----------------------------
-- Table structure for `bis_wechat_record`
-- ----------------------------
DROP TABLE IF EXISTS `bis_wechat_record`;
CREATE TABLE `bis_wechat_record` (
  `record_id` varchar(50) NOT NULL DEFAULT '' COMMENT '记录编号',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `record_type` varchar(10) DEFAULT '1' COMMENT '记录类型 1默认',
  `send_user_id` varchar(50) DEFAULT NULL COMMENT '发送用户编号，',
  `create_user_id` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT '0' COMMENT ' 信息状态  0失败 1成功',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `failure_cause` varchar(200) DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信记录表';

-- ----------------------------
-- Records of bis_wechat_record
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `sys_catalog`;
CREATE TABLE `sys_catalog` (
  `catalog_id` varchar(50) NOT NULL DEFAULT '' COMMENT '分类科目编号',
  `cascade_id` varchar(500) DEFAULT NULL COMMENT '分类科目语义ID',
  `root_key` varchar(100) DEFAULT NULL COMMENT '科目标识键',
  `root_name` varchar(100) DEFAULT NULL COMMENT '科目名称',
  `catalog_name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父节点编号',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `icon_name` varchar(50) DEFAULT NULL COMMENT '图标名称',
  `is_auto_expand` varchar(10) DEFAULT '0' COMMENT '是否自动展开',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`catalog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类科目';

-- ----------------------------
-- Records of sys_catalog
-- ----------------------------
INSERT INTO `sys_catalog` VALUES ('0e6cca42523b4f95afb8d138dc533e61', '0.002', 'DIC_TYPE', '字典分类科目', '数据字典分类', '0', '1', 'book', '0', '2016-10-07 23:49:14', null, '2016-10-07 23:49:31', null);
INSERT INTO `sys_catalog` VALUES ('4f39839093744dccaedc9d7dcdee4ab3', '0.001', 'PARAM_TYPE', '参数分类', '参数分类科目', '0', '1', 'book', '0', '2016-10-07 23:46:41', null, '2016-10-07 23:47:57', null);
INSERT INTO `sys_catalog` VALUES ('5423b9ba9ac6472c80881827acafe9e9', '0.001.001', 'PARAM_TYPE', '参数分类', '系统参数', '4f39839093744dccaedc9d7dcdee4ab3', '1', 'folder', '1', '2016-10-07 23:47:50', null, null, null);
INSERT INTO `sys_catalog` VALUES ('6a75051434b840a597aeb549e1e47ced', '0.002.001', 'DIC_TYPE', '字典分类科目', '系统管理', '0e6cca42523b4f95afb8d138dc533e61', '2', 'folder', '1', '2016-10-07 23:50:13', null, '2016-10-07 23:57:03', null);
INSERT INTO `sys_catalog` VALUES ('b420860910f54c1d8901f099a9457f52', '0.002.002', 'DIC_TYPE', '字典分类科目', '全局通用', '0e6cca42523b4f95afb8d138dc533e61', '1', 'global', '1', '2016-10-08 00:01:11', null, null, null);
INSERT INTO `sys_catalog` VALUES ('e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', 'DIC_TYPE', '字典分类科目', '业务通用', '0e6cca42523b4f95afb8d138dc533e61', '3', 'menu_config', '1', '2017-04-18 21:53:40', 'cb33c25f5c664058a111a9b876152317', '2017-04-18 21:54:22', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` varchar(50) NOT NULL COMMENT '流水号',
  `cascade_id` varchar(255) NOT NULL COMMENT '节点语义ID',
  `dept_name` varchar(100) NOT NULL COMMENT '组织名称',
  `parent_id` varchar(50) NOT NULL COMMENT '父节点流水号',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '机构代码',
  `manager` varchar(50) DEFAULT NULL COMMENT '主要负责人',
  `phone` varchar(50) DEFAULT NULL COMMENT '部门电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `is_auto_expand` varchar(10) DEFAULT NULL COMMENT '是否自动展开',
  `icon_name` varchar(50) DEFAULT NULL COMMENT '节点图标文件名称',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否已删除 0有效 1删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('0', '0', '组织机构', '-1', null, null, null, null, null, '1', 'dept_config', '1', '顶级机构不能进行移动和删除操作，只能进行修改', '0', '2016-12-29 10:13:20', null, null, null);
INSERT INTO `sys_dept` VALUES ('02448fead1be410ab4f0f7bea7285448', '0.0001', '广州研发中心', '0', null, null, null, null, null, '1', null, '1', null, '0', '2017-01-16 14:03:01', 'cb33c25f5c664058a111a9b876152317', '2017-01-16 14:03:01', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dept` VALUES ('728f6b0a20f0492dbd97b871620c2b19', '0.0001.0001', '天河区办事处', '02448fead1be410ab4f0f7bea7285448', null, null, null, null, null, '1', null, '1', null, '0', '2017-01-16 14:03:16', 'cb33c25f5c664058a111a9b876152317', '2017-01-16 14:03:16', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dept` VALUES ('d065a2309b96474c87b3f1be70e6afca', '0.0001.0003', '越秀区', '02448fead1be410ab4f0f7bea7285448', '999', '1', null, null, null, '1', null, '1', null, '0', '2017-08-01 01:52:29', 'ced672de37514478a63eca171b666c62', '2017-08-01 01:52:29', 'ced672de37514478a63eca171b666c62');
INSERT INTO `sys_dept` VALUES ('fdc7155b279b47f58439c514fade3d8a', '0.0001.0002', '白云区办事处', '02448fead1be410ab4f0f7bea7285448', '', '', '', '', '', '1', '', '2', '', '1', '2017-01-16 14:03:32', 'cb33c25f5c664058a111a9b876152317', '2017-01-16 14:53:27', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `dic_id` varchar(50) NOT NULL COMMENT '字典编号',
  `dic_index_id` varchar(255) DEFAULT NULL COMMENT '所属字典流水号',
  `dic_code` varchar(100) DEFAULT NULL COMMENT '字典对照码',
  `dic_value` varchar(100) DEFAULT NULL COMMENT '字典对照值',
  `show_color` varchar(50) DEFAULT NULL COMMENT '显示颜色',
  `status` varchar(10) DEFAULT '1' COMMENT '当前状态(0:停用;1:启用)',
  `edit_mode` varchar(10) DEFAULT '1' COMMENT '编辑模式(0:只读;1:可编辑)',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`dic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('00540d99c3af415b8ddc338fccff0f3a', '91b68d01dfa64d279e7cb57e5d38e9ab', '2', '美研币消费', '', '1', '1', '2', '2017-05-08 23:54:57', 'cb33c25f5c664058a111a9b876152317', '2017-07-11 23:13:39', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('0499715acb234c178c280791b6499dc2', 'a1c4798afa39494b8127940b31bd1eac', '1', '美容', null, '1', '1', '1', '2017-04-18 23:21:42', 'cb33c25f5c664058a111a9b876152317', '2017-04-18 23:21:42', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('06b192968b5045858a645e1fdfddc7ca', '1b2d3ee7b804457794e7fed66c3ebd09', '4', '半年未到店', '', '1', '1', '4', '2017-04-22 13:32:17', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 17:22:53', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('086769473904465280426d2b7fc8ba65', '64fa433a9d3543c6a2e2a0d23287ab6e', '1', '成功', null, '1', '1', '2', '2017-05-12 01:18:38', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:18:38', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('095a62a069564ff5aa4bbae200871f0a', 'c75fdca39700498094a2fec1b4d79fa7', '1', '微信', null, '1', '1', '1', '2017-04-22 13:23:56', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:23:56', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('0c7ed6c42a1c4690b381a3ca5ca21a00', 'd7066da0ca5542f8acc302972cd8ceb0', '1', '未关注', '', '1', '1', '1', '2017-04-30 15:54:53', 'cb33c25f5c664058a111a9b876152317', '2017-05-01 16:47:44', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('11b823f3b2e14e76bf94347a4a5e578e', 'c48507ef391d4e3d8d9b7720efe4841b', '0', '停用', 'red', '1', '1', '1', '2016-10-07 23:54:37', null, '2017-04-18 00:50:10', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('293adbde400f457a8d947ff5c6341b04', '992a7d6dbe7f4009b30cbae97c3b64a9', '3', '锁定', '#FFA500', '1', '1', '3', '2016-12-18 21:33:42', null, '2017-01-15 21:47:24', null);
INSERT INTO `sys_dictionary` VALUES ('2ac97527c4924127b742dd953d8b53ba', '820d2a68425b4d8d9b423b81d6a0eec1', '0', '保密', '', '1', '1', '3', '2016-10-08 22:45:47', null, '2017-04-30 11:03:27', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('2bfc90a6917545cd87d73fb491292e2b', 'aaec0092a25b485f90c20898e9d6765d', '1', '缺省', '', '1', '1', '1', '2016-12-31 09:19:48', null, '2017-01-16 17:54:22', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('2f79a0aa64af499f9bd503ce5f8ce73f', 'eaaf97adff184b428ee606b892ab9b31', 'pic_weixin', '弹出微信相册发图器', null, '1', '1', '7', '2017-05-03 21:53:44', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:53:44', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('314a20fefaf74a8687fd8fedc34912cd', 'be2679a2f9be47439448de4551511534', '1', '未兑换', null, '1', '1', '1', '2017-04-28 01:00:38', 'cb33c25f5c664058a111a9b876152317', '2017-04-28 01:00:38', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('37ae966093594856be65884bee24f171', 'd7066da0ca5542f8acc302972cd8ceb0', '2', '已关注', 'green', '1', '1', '2', '2017-04-30 15:55:04', 'cb33c25f5c664058a111a9b876152317', '2017-05-01 16:54:52', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('3cf6af08f48e4cec913d09f67a0b3b43', '992a7d6dbe7f4009b30cbae97c3b64a9', '1', '正常', '', '1', '1', '1', '2016-12-18 09:03:21', null, '2016-12-27 17:48:36', null);
INSERT INTO `sys_dictionary` VALUES ('4069fc71ab3c4a3b836d6181a35c7c14', 'c75fdca39700498094a2fec1b4d79fa7', '2', '商家', '', '1', '1', '2', '2017-04-22 13:24:07', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:24:23', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('41f9d326cde54e70ba8b977763759578', '05d2c7ead8a1445baa73aee5280b1159', '4', '预约', null, '1', '1', '4', '2017-05-29 23:37:03', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 23:37:03', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('459cce00ee17411cb79a7380f06a6128', 'eca041691239413cb95f92451e31e847', '2', '兑换美研币', '', '1', '1', '2', '2017-05-30 00:56:30', 'cb33c25f5c664058a111a9b876152317', '2017-05-30 00:56:38', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('4e411ae7b10740abb3238d004df9da55', 'af6737fa3fde49b99f5725b14afd029a', '0', '有效', null, '1', '1', '1', '2017-04-27 23:54:35', 'cb33c25f5c664058a111a9b876152317', '2017-04-27 23:54:35', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('4fc8ddf283344c10b4d85b45752a57a9', 'af205eab56984e5f98c04d7f1408bb3c', '1', '普通短信', null, '1', '1', '1', '2017-05-12 01:16:17', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:16:17', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('542986839016446b92925e62d033bdcc', 'eaaf97adff184b428ee606b892ab9b31', 'scancode_waitmsg', '扫码推事件且弹出“消息接收中”提示框', '', '1', '1', '4', '2017-05-03 21:52:22', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:52:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('56a5235d0ade4b4aaa256bf94285ba62', '05d2c7ead8a1445baa73aee5280b1159', '1', '购买美研币', null, '1', '1', '1', '2017-05-29 23:36:07', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 23:36:07', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('5a3badef1821468a9cebe8d9cb4fdc7b', 'be2679a2f9be47439448de4551511534', '3', '已失效', 'red', '1', '1', '3', '2017-04-28 01:01:31', 'cb33c25f5c664058a111a9b876152317', '2017-04-28 01:01:31', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('60a60c2eac7146b8b8286abe37b11fb9', '4bf8bafb40d142418c48a641009ab0af', '1', '在售', null, '1', '1', '1', '2017-04-23 08:24:03', 'cb33c25f5c664058a111a9b876152317', '2017-04-23 08:24:03', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('6564be2ee7ce4e65b9c9c2c41c435c46', 'eaaf97adff184b428ee606b892ab9b31', 'scancode_push', '扫码推事件', null, '1', '1', '3', '2017-05-03 21:51:51', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:51:51', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('67bc69e6c8ef40bbaa224700cdb8f05e', 'da54941ba36f4e24b818d58389c94017', '1', '系统菜单', null, '1', '1', '1', '2017-04-19 14:08:42', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 14:08:42', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('7726102d72b1471f87603a7074b46681', '9a79e4a0f0cf41e2afda4766e536f51b', '4', '礼包项目', null, '1', '1', '4', '2017-05-08 23:57:51', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:57:51', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('7f502acb5ec842c697dc0eac202f44e9', '835c20e7682c43c5b7f30b76e842f4a9', '7', '已撤销', null, '1', '1', '7', '2017-05-09 00:05:08', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:05:08', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('86b6faf154de4518975e6e59dbaae174', '64fa433a9d3543c6a2e2a0d23287ab6e', '0', '失败', 'red', '1', '1', '1', '2017-05-12 01:17:59', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:17:59', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('90fd2bb4add94f8384887033e73b680f', 'af6737fa3fde49b99f5725b14afd029a', '1', '已删除', 'red', '1', '1', '2', '2017-04-27 23:55:34', 'cb33c25f5c664058a111a9b876152317', '2017-04-27 23:55:34', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('913ca1b4b49a434fb9591f6df0a52af8', 'c6f8b99b95c844b89dc86c143e04a294', '0', '否', null, '1', '0', '1', '2016-10-07 23:53:45', null, '2016-10-11 02:20:43', null);
INSERT INTO `sys_dictionary` VALUES ('91aebfc6adf64d2b833cade9c62b9224', '1b2d3ee7b804457794e7fed66c3ebd09', '3', '三个月未到店', null, '1', '1', '3', '2017-05-29 17:23:13', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 17:23:13', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('93ecb40efd1b457bb597dff11ce6f584', '835c20e7682c43c5b7f30b76e842f4a9', '1', '已预约', null, '1', '1', '1', '2017-05-08 23:59:23', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:59:23', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('9417b2d44c5b415facadfc554e7ae6a5', '9a79e4a0f0cf41e2afda4766e536f51b', '3', '美研币', null, '1', '1', '3', '2017-05-08 23:57:12', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:57:12', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('9561b75566ca4163a134843883f1f06c', '37b3b9f034284335b989babcfb97bc9a', '1', '有', null, '1', '1', '1', '2017-05-29 17:02:02', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 17:02:02', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('984a1a6d12db4ed7add58b548b4bc30e', 'be2679a2f9be47439448de4551511534', '2', '已兑换', 'green', '1', '1', '2', '2017-04-28 01:01:11', 'cb33c25f5c664058a111a9b876152317', '2017-04-28 01:01:11', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('9a287f07d302448dae0908a95947eedc', 'eaaf97adff184b428ee606b892ab9b31', 'location_select', '弹出地理位置选择器', null, '1', '1', '8', '2017-05-03 21:54:05', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:54:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('9c63657b98c444e3bfd8a0a75128de2b', '7a7faf68a5ec4f3cb9f45d89c119b26b', '0', '只读', null, '1', '0', '1', '2016-10-07 23:55:13', null, '2016-10-11 02:20:06', null);
INSERT INTO `sys_dictionary` VALUES ('9e2454ef26414d898ec54faf6b0c7347', 'af205eab56984e5f98c04d7f1408bb3c', '3', '支付短信', null, '1', '1', '3', '2017-05-12 01:16:44', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:16:44', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('9e99e0fa4ba64780890293da81f8f361', 'eaaf97adff184b428ee606b892ab9b31', 'view_limited', '跳转图文消息URL', '', '1', '1', '21', '2017-05-03 21:54:45', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 23:33:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('a33ab9a859fc43cda83af93c085ff045', '91b68d01dfa64d279e7cb57e5d38e9ab', '3', '礼包消费', '', '1', '1', '3', '2017-05-08 23:55:17', 'cb33c25f5c664058a111a9b876152317', '2017-07-11 23:13:26', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('a5c29502a7fa49009db74346b4a71fd4', 'a1c4798afa39494b8127940b31bd1eac', '2', '美甲', null, '1', '1', '1', '2017-04-18 23:21:52', 'cb33c25f5c664058a111a9b876152317', '2017-04-18 23:21:52', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('a96dfb72b7b54e1989569a2b3c5f90ac', 'c48507ef391d4e3d8d9b7720efe4841b', '1', '启用', null, '1', '1', '1', '2016-10-07 23:54:47', null, '2016-10-11 02:20:22', null);
INSERT INTO `sys_dictionary` VALUES ('aefd0aa9ccb548ab93b651174558c3af', 'eaaf97adff184b428ee606b892ab9b31', 'pic_sysphoto', '弹出系统拍照发图', null, '1', '1', '5', '2017-05-03 21:53:02', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:53:02', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('b08055919e9a437fbb816ab86d7bb8d9', '835c20e7682c43c5b7f30b76e842f4a9', '4', '已支付', null, '1', '1', '4', '2017-05-09 00:00:57', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:00:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('b08b385f59d64a889f807436ce2b96e3', '1b2d3ee7b804457794e7fed66c3ebd09', '5', '一年未到店', '', '1', '1', '5', '2017-04-22 13:32:31', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 17:22:47', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('b6458239852849c9bcfb720e65b0b944', 'eaaf97adff184b428ee606b892ab9b31', 'media_id', '下发消息（除文本消息）', null, '1', '1', '9', '2017-05-03 21:54:23', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:54:23', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('baef55cae14f4da8ab5c31e226d20d57', '9c6a916cf8a741a281475ce857d7fef4', '1', '在职', '', '1', '1', '2', '2017-04-19 21:57:37', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 21:57:45', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('be25a95b4dfc4a1e91814dd11ad3cd51', 'eaaf97adff184b428ee606b892ab9b31', 'pic_photo_or_album', '弹出拍照或者相册发图', null, '1', '1', '6', '2017-05-03 21:53:22', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:53:22', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('be4ed4e8ebdf4a529535c97b7fc22035', 'c75fdca39700498094a2fec1b4d79fa7', '3', '后台', '', '1', '1', '3', '2017-04-22 13:24:15', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:24:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('bf8443fe909c4d27ac635543ac084100', '835c20e7682c43c5b7f30b76e842f4a9', '5', '已完成', null, '1', '1', '5', '2017-05-09 00:01:22', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:01:22', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('c07147380f9545ed93d1bc4375255179', '835c20e7682c43c5b7f30b76e842f4a9', '2', '服务中', null, '1', '1', '2', '2017-05-08 23:59:54', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:59:54', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('c09104dec62a41629a6f69a29d8fd450', 'eca041691239413cb95f92451e31e847', '1', '购买美研币', null, '1', '1', '1', '2017-05-30 00:56:19', 'cb33c25f5c664058a111a9b876152317', '2017-05-30 00:56:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('c4bd16a984e34734b1e59c61ac21802b', '37b3b9f034284335b989babcfb97bc9a', '2', '无', null, '1', '1', '2', '2017-05-29 17:02:15', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 17:02:15', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('c52c02a470204331bd3a23613681169b', 'eca041691239413cb95f92451e31e847', '3', '服务消费', null, '1', '1', '3', '2017-05-30 00:56:50', 'cb33c25f5c664058a111a9b876152317', '2017-05-30 00:56:50', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('c676ab6c041a4b098a511794b8d012d5', '05d2c7ead8a1445baa73aee5280b1159', '3', '使用消费', null, '1', '1', '3', '2017-05-29 23:36:52', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 23:36:52', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('c87c798ea0844303b7e0cbb0b566c3ef', 'd7066da0ca5542f8acc302972cd8ceb0', '4', '网页授权', null, '1', '1', '1', '2017-05-20 16:06:11', 'cb33c25f5c664058a111a9b876152317', '2017-05-20 16:06:11', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('ca40ef37acef49f8930fcf22356ba50e', 'c6f8b99b95c844b89dc86c143e04a294', '1', '是', null, '1', '0', '2', '2016-10-07 23:54:09', null, '2016-10-11 02:20:32', null);
INSERT INTO `sys_dictionary` VALUES ('d2cf230ce49040e3bf6e61a972659c09', '992a7d6dbe7f4009b30cbae97c3b64a9', '2', '停用', 'red', '1', '1', '2', '2016-12-18 21:33:25', null, '2016-12-19 21:09:04', null);
INSERT INTO `sys_dictionary` VALUES ('d404e540aab945df84a26e3d30b2dd47', '820d2a68425b4d8d9b423b81d6a0eec1', '2', '女', null, '1', '1', '2', '2016-10-08 22:47:09', null, '2016-12-18 22:44:17', null);
INSERT INTO `sys_dictionary` VALUES ('d4d934eb99ca4e81b03f1e4e1e021395', 'eaaf97adff184b428ee606b892ab9b31', 'click', '点击推事件', null, '1', '1', '1', '2017-05-03 21:51:07', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:51:07', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('d7c7a7706e83454fb70096010cad09f5', '91b68d01dfa64d279e7cb57e5d38e9ab', '1', '常规消费', '', '1', '1', '1', '2017-05-08 23:54:45', 'cb33c25f5c664058a111a9b876152317', '2017-07-11 23:13:16', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('d7f0c4a5480d4dc4b3e6e4c5b405d9cb', '820d2a68425b4d8d9b423b81d6a0eec1', '1', '男', null, '1', '1', '1', '2016-10-08 22:46:49', null, '2016-12-18 22:44:42', null);
INSERT INTO `sys_dictionary` VALUES ('d807ef1939a24076a3fa5055c002a0ae', '4bf8bafb40d142418c48a641009ab0af', '2', '下架', 'red', '1', '1', '2', '2017-04-23 08:24:13', 'cb33c25f5c664058a111a9b876152317', '2017-04-23 08:24:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('dbe48afd75fb4cf584e00d9a5bb1d50b', '835c20e7682c43c5b7f30b76e842f4a9', '3', '待支付', null, '1', '1', '3', '2017-05-09 00:00:21', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:00:21', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('de860d31918249819a00d498f1bf8cd4', 'af205eab56984e5f98c04d7f1408bb3c', '2', '验证短信', null, '1', '1', '2', '2017-05-12 01:16:31', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:16:31', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('df21e386ce2349cd9f79ffd015901331', '9a79e4a0f0cf41e2afda4766e536f51b', '2', '支付宝', null, '1', '1', '2', '2017-05-08 23:56:45', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:56:45', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('df94994bc7ec4f9cae91f3579ab80c9d', '6e189bf50912409cb798133105eda02c', '1', '平台', null, '1', '1', '1', '2017-05-09 00:09:15', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:09:15', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('e0470a65ba1441d3be6d9e429ac136ed', '835c20e7682c43c5b7f30b76e842f4a9', '6', '已过期', null, '1', '1', '6', '2017-05-09 00:01:45', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:01:45', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('e0e59a52f42c4263aac3e9dbbdb496df', '0bf2a3cd7ed44516a261347d47995411', '1', '经典风格', null, '1', '1', '1', '2016-02-17 14:29:30', 'cb33c25f5c664058a111a9b876152317', '2016-02-17 14:29:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('e9d773f1986f45caa553b529691dc3ac', '9c6a916cf8a741a281475ce857d7fef4', '0', '离职', 'red', '1', '1', '1', '2017-04-19 21:57:26', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 21:57:26', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('ef4ac03636b24c01ac1d51f00b0d03fe', '9a79e4a0f0cf41e2afda4766e536f51b', '1', '微信', null, '1', '1', '1', '2017-05-08 23:56:32', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:56:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('f1c0ae8844504f96836b904ce81ac1bc', '7a7faf68a5ec4f3cb9f45d89c119b26b', '1', '可编辑', null, '1', '0', '2', '2016-10-07 23:55:25', null, '2016-10-11 02:19:52', null);
INSERT INTO `sys_dictionary` VALUES ('f25afe00c3924103a061bfb4ead07b2a', '05d2c7ead8a1445baa73aee5280b1159', '2', '购买套餐', null, '1', '1', '2', '2017-05-29 23:36:30', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 23:36:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('f4f8fddb714943188844713d5af27ea3', '1b2d3ee7b804457794e7fed66c3ebd09', '2', '两个月未到店', null, '1', '1', '2', '2017-04-22 13:31:47', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:31:47', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('f6103ab90ae8484baf4951481f69a172', 'd7066da0ca5542f8acc302972cd8ceb0', '3', '已退订', 'red', '1', '1', '3', '2017-04-30 15:55:26', 'cb33c25f5c664058a111a9b876152317', '2017-05-01 16:55:01', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('f99a1fe79cc84d489d134cddaeabb599', 'eaaf97adff184b428ee606b892ab9b31', 'view', '跳转URL', '', '1', '1', '2', '2017-05-03 21:51:27', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:51:59', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('fb51f86c01764ad79d9feb4ca3bd5dab', '6e189bf50912409cb798133105eda02c', '2', '商家', null, '1', '1', '2', '2017-05-09 00:09:30', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:09:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('ff0d605ca6384cc0bd27468c28deebda', 'da54941ba36f4e24b818d58389c94017', '2', '商家菜单', null, '1', '1', '1', '2017-04-19 14:08:59', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 14:08:59', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary` VALUES ('ff99b77926c147b9a648e4d32f0e5d47', '1b2d3ee7b804457794e7fed66c3ebd09', '1', '一个月未到店', null, '1', '1', '1', '2017-04-22 13:31:29', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:31:29', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `sys_dictionary_index`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_index`;
CREATE TABLE `sys_dictionary_index` (
  `dic_index_id` varchar(50) NOT NULL COMMENT '流水号',
  `dic_key` varchar(50) DEFAULT NULL COMMENT '字典标识',
  `dic_name` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `catalog_id` varchar(50) DEFAULT NULL COMMENT '所属分类流水号',
  `catalog_cascade_id` varchar(500) DEFAULT NULL COMMENT '所属分类流节点语义ID',
  `dic_remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`dic_index_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典索引表';

-- ----------------------------
-- Records of sys_dictionary_index
-- ----------------------------
INSERT INTO `sys_dictionary_index` VALUES ('05d2c7ead8a1445baa73aee5280b1159', 'cash_record_type', '现金流水分离', '0e6cca42523b4f95afb8d138dc533e61', '0.002', null, '2017-05-29 23:34:07', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 23:34:07', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('0bf2a3cd7ed44516a261347d47995411', 'layout_style', '界面布局', '6a75051434b840a597aeb549e1e47ced', '0.002.001', null, '2016-02-17 14:29:30', 'cb33c25f5c664058a111a9b876152317', '2016-02-17 14:29:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('1b2d3ee7b804457794e7fed66c3ebd09', 'recent_type', '最近到店类型', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-04-22 13:30:17', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:30:17', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('37b3b9f034284335b989babcfb97bc9a', 'whether_type', '有无类型', '0e6cca42523b4f95afb8d138dc533e61', '0.002', null, '2017-05-29 17:00:24', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 17:00:24', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('4bf8bafb40d142418c48a641009ab0af', 'show_status', '护理项目状态', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', '', '2017-04-18 21:54:59', 'cb33c25f5c664058a111a9b876152317', '2017-04-23 08:23:41', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('64fa433a9d3543c6a2e2a0d23287ab6e', 'sms_status', '短信发送状态', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-05-12 01:17:37', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:17:37', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('6e189bf50912409cb798133105eda02c', 'order_source', '订单归属', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-05-09 00:08:58', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 00:08:58', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('7a7faf68a5ec4f3cb9f45d89c119b26b', 'edit_mode', '编辑模式', 'b420860910f54c1d8901f099a9457f52', '0.002.002', '', '2016-10-07 10:49:16', null, '2016-10-08 00:01:55', null);
INSERT INTO `sys_dictionary_index` VALUES ('820d2a68425b4d8d9b423b81d6a0eec1', 'sex', '性别', '6a75051434b840a597aeb549e1e47ced', '0.002.001', null, '2016-10-08 22:45:28', null, null, null);
INSERT INTO `sys_dictionary_index` VALUES ('835c20e7682c43c5b7f30b76e842f4a9', 'order_status', '订单状态', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-05-08 23:58:21', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:58:21', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('91b68d01dfa64d279e7cb57e5d38e9ab', 'order_type', '订单类型', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-05-08 23:54:13', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:54:13', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('992a7d6dbe7f4009b30cbae97c3b64a9', 'user_status', '用户状态', '6a75051434b840a597aeb549e1e47ced', '0.002.001', null, '2016-12-18 08:57:02', null, null, null);
INSERT INTO `sys_dictionary_index` VALUES ('9a79e4a0f0cf41e2afda4766e536f51b', 'pay_way', '支付方式', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-05-08 23:56:06', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 23:56:06', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('9c6a916cf8a741a281475ce857d7fef4', 'staff_status', '员工状态', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-04-19 21:56:32', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 21:56:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('a1c4798afa39494b8127940b31bd1eac', 'shop_type', '店铺类型', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-04-18 23:21:05', 'cb33c25f5c664058a111a9b876152317', '2017-04-18 23:21:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('aaec0092a25b485f90c20898e9d6765d', 'role_type', '角色类型', '6a75051434b840a597aeb549e1e47ced', '0.002.001', null, '2016-12-31 09:19:18', null, null, null);
INSERT INTO `sys_dictionary_index` VALUES ('af205eab56984e5f98c04d7f1408bb3c', 'sms_type', '短信类型', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-05-12 01:15:46', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:15:46', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('af6737fa3fde49b99f5725b14afd029a', 'is_del', '删除状态', 'b420860910f54c1d8901f099a9457f52', '0.002.002', '', '2017-04-27 23:54:19', 'cb33c25f5c664058a111a9b876152317', '2017-04-27 23:54:49', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('be2679a2f9be47439448de4551511534', 'bond_status', '兑换状态', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-04-28 01:00:14', 'cb33c25f5c664058a111a9b876152317', '2017-04-28 01:00:14', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('c48507ef391d4e3d8d9b7720efe4841b', 'status', '当前状态', 'b420860910f54c1d8901f099a9457f52', '0.002.002', '', '2016-10-07 11:08:12', null, '2016-10-08 00:01:50', null);
INSERT INTO `sys_dictionary_index` VALUES ('c6f8b99b95c844b89dc86c143e04a294', 'is_auto_expand', '是否自动展开', 'b420860910f54c1d8901f099a9457f52', '0.002.002', '', '2016-10-07 13:09:25', null, '2016-10-08 00:01:44', null);
INSERT INTO `sys_dictionary_index` VALUES ('c75fdca39700498094a2fec1b4d79fa7', 'enroll_mode', '注册方式', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-04-22 13:23:27', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:23:27', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('d7066da0ca5542f8acc302972cd8ceb0', 'wechat_status', '微信状态', 'e12d1422a4214a14a6c6c3779258d1a7', '0.002.003', null, '2017-04-30 15:54:24', 'cb33c25f5c664058a111a9b876152317', '2017-04-30 15:54:24', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('da54941ba36f4e24b818d58389c94017', 'menu_type', '菜单类型', '6a75051434b840a597aeb549e1e47ced', '0.002.001', null, '2017-04-19 14:08:06', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 14:08:06', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('eaaf97adff184b428ee606b892ab9b31', 'wechat_menu_type', '微信菜单类型', '6a75051434b840a597aeb549e1e47ced', '0.002.001', null, '2017-05-03 21:50:48', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 21:50:48', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_dictionary_index` VALUES ('eca041691239413cb95f92451e31e847', 'beauty_type', '美研币流水类型', '0e6cca42523b4f95afb8d138dc533e61', '0.002', null, '2017-05-30 00:53:24', 'cb33c25f5c664058a111a9b876152317', '2017-05-30 00:53:24', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `sys_icon`
-- ----------------------------
DROP TABLE IF EXISTS `sys_icon`;
CREATE TABLE `sys_icon` (
  `icon_id` varchar(50) NOT NULL COMMENT '图标编号',
  `icon_name` varchar(50) NOT NULL COMMENT '图标名称',
  `icon_file_name` varchar(50) DEFAULT NULL,
  `icon_path` varchar(100) DEFAULT NULL,
  `icon_type` varchar(50) DEFAULT NULL,
  `icon_size_type` varchar(10) DEFAULT NULL COMMENT '图标尺寸类型 1:16*16 2:24*24 3:32*32  4:其它',
  `status` varchar(10) DEFAULT '1' COMMENT '当前状态 0 停用 1启用',
  `edit_mode` varchar(10) DEFAULT '1' COMMENT '编辑模式(0:只读;1:可编辑)',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '图标创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '图标修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`icon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图标信息';

-- ----------------------------
-- Records of sys_icon
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单编号',
  `cascade_id` varchar(500) DEFAULT NULL COMMENT '分类科目语义ID',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(10) DEFAULT '1' COMMENT '菜单类型 1系统菜单2商家菜单',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '菜单父级编号',
  `icon_name` varchar(50) DEFAULT NULL COMMENT '图标名称',
  `is_auto_expand` varchar(10) DEFAULT '0' COMMENT '是否自动展开',
  `url` varchar(100) DEFAULT NULL COMMENT 'url地址',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` varchar(10) DEFAULT '1' COMMENT '当前状态(0:停用;1:启用)',
  `edit_mode` varchar(10) DEFAULT '1' COMMENT '编辑模式(0:只读;1:可编辑)',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单配置';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0342b1350fd14ce79b8eafeb5d703642', '0.010.001', '意见列表', '1', 'e5c7809747ed41948c696862e5b1830c', '', '1', 'system/opinion/init.jhtml', '', '1', '1', '1', '2017-04-17 22:20:05', 'cb33c25f5c664058a111a9b876152317', '2017-06-18 20:24:02', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('07b5cbef85fd4ba3817168d751d68782', '0.007.003', '礼包分享记录', '1', '9949c0fdf9c346808de6a8644fabc670', '', '1', 'system/finance/initShareBagRecord.jhtml', '', '1', '1', '3', '2017-04-17 22:14:55', 'cb33c25f5c664058a111a9b876152317', '2017-06-28 02:24:09', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('0e76f448c96c428a8a9a65951dbc4c93', '0.011.001', '参数设置', '1', '2c648fcd77614d8893ff48fddb29e3ba', '', '1', 'system/paramConfig/init.jhtml', '', '1', '1', '1', '2017-04-17 22:20:49', 'cb33c25f5c664058a111a9b876152317', '2017-05-11 00:24:18', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('10e4756d11f743e48d1c7842f9375514', '0.008.001', '基本设置', '1', 'b0a5eabb4cfe42a6877fe3bef62e0d92', '', '1', 'system/beautyConfig/init.jhtml', '', '1', '1', '1', '2017-04-17 22:17:36', 'cb33c25f5c664058a111a9b876152317', '2017-04-26 22:02:15', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('1ae9eeb251a243abb4c0c4f3865d6262', '0.001.001', '菜单配置', '1', 'c66886c9ee47415aa81a6589acdb480a', 'menu_config', '1', 'system/menu/init.jhtml', null, '1', '1', '4', '2016-10-07 21:23:38', null, null, null);
INSERT INTO `sys_menu` VALUES ('1bb3706c28fd407c8c043bc1696e5c68', '0.009', '消息管理', '1', '0', null, '1', null, null, '1', '1', '8', '2017-04-17 22:18:36', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:18:36', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('294c3e77cc364b059355e9447b3c2abf', '0.008.002', '礼券管理', '1', 'b0a5eabb4cfe42a6877fe3bef62e0d92', '', '1', 'system/couponActive/init.jhtml', '', '1', '1', '2', '2017-04-17 22:17:56', 'cb33c25f5c664058a111a9b876152317', '2017-04-27 23:36:37', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('2c648fcd77614d8893ff48fddb29e3ba', '0.011', '参数管理', '1', '0', '', '1', '', '', '1', '1', '12', '2017-04-17 22:20:34', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:21:02', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('2d2f7659e65c4a7990ff8cad1203e013', '0.001.010', '微信菜单', '1', 'c66886c9ee47415aa81a6589acdb480a', '', '1', 'system/wechatMenu/init.jhtml', '', '1', '1', '11', '2017-05-03 00:59:27', 'cb33c25f5c664058a111a9b876152317', '2017-05-03 01:01:25', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('30acf69a620b4017ad930417fc75e0b1', '0.006', '营业记录管理', '1', '0', null, '1', null, null, '1', '1', '5', '2017-04-17 22:12:04', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:12:04', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('3b8d162dddd444f8b1ca5c81139393fa', '0.012.004', '顾客管理', '2', 'b5764849b97242d68e88e10551205677', 'custom.png', '1', 'shop/shopManage/initCustomUser.jhtml', '', '1', '1', '4', '2017-05-07 02:02:38', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 16:44:13', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('436e6843529d49d3869454d59840e137', '0.012.002', '营业记录', '2', 'b5764849b97242d68e88e10551205677', 'record.png', '1', 'shop/orderManage/init.jhtml', '', '1', '1', '2', '2017-04-19 14:24:53', 'cb33c25f5c664058a111a9b876152317', '2017-05-08 02:17:02', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('49bdf0b44a804b2ab338d3e8d640f3e1', '0.009.001', '系统消息', '1', '1bb3706c28fd407c8c043bc1696e5c68', '', '1', 'system/wechatRecord/init.jhtml', '', '1', '1', '1', '2017-04-17 22:18:56', 'cb33c25f5c664058a111a9b876152317', '2017-07-23 15:47:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('500a6cf76b78428c9b0fef62da82e66b', '0.003.001', '员工列表', '1', 'b53c5b7fbeee40eda0e55027c368ae73', '', '1', 'system/shopUser/init.jhtml', '', '1', '1', '1', '2017-04-17 22:05:56', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 22:08:35', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('591f5781947b4beda180794ce434e509', '0.012.006', '员工管理', '2', 'b5764849b97242d68e88e10551205677', 'shopuser.png', '1', 'shop/shopManage/initShopUser.jhtml', '', '1', '1', '6', '2017-05-07 02:04:39', 'cb33c25f5c664058a111a9b876152317', '2017-05-26 22:47:50', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('6147e3c63fb549e7a89d580d5d0c1106', '0.002', '商家管理', '1', '0', '', '1', '', '', '1', '1', '1', '2017-04-17 22:02:27', 'cb33c25f5c664058a111a9b876152317', '2017-04-18 01:22:01', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('67b8a34c0f9346a4b1b12bbe2d197218', '0.005', '护理项目管理', '1', '0', null, '1', null, null, '1', '1', '4', '2017-04-17 22:08:18', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:08:18', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('6859506fc9c84556ac5e3bc4e8db61f1', '0.004.001', '顾客列表', '1', '875e7bde4e714b84933b6134dc5c4e6b', '', '1', 'system/customUser/init.jhtml', '', '1', '1', '1', '2017-04-17 22:07:25', 'cb33c25f5c664058a111a9b876152317', '2017-04-22 13:33:28', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('71a88cae8792452a8896ba7063261539', '0.012.008', '店铺设置', '2', 'b5764849b97242d68e88e10551205677', 'sysconfig.png', '1', 'shop/shopManage/initSet.jhtml', '', '1', '1', '8', '2017-05-07 10:30:14', 'cb33c25f5c664058a111a9b876152317', '2017-07-16 00:17:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('74c419ba65be4e4c9d6fce1815e32f53', '0.002.002', '店主列表', '1', '6147e3c63fb549e7a89d580d5d0c1106', '', '1', 'system/shopOwner/init.jhtml', '', '1', '1', '2', '2017-04-17 22:03:41', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 23:46:59', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('7557bb92a6a148369a6d2fc517a6b547', '0.005.003', '分类列表', '1', '67b8a34c0f9346a4b1b12bbe2d197218', '', '1', 'system/nurseType/init.jhtml', '', '1', '1', '3', '2017-04-17 22:10:41', 'cb33c25f5c664058a111a9b876152317', '2017-04-18 21:03:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('800703fe30da49e987581a07330393d4', '0.005.002', '礼包列表', '1', '67b8a34c0f9346a4b1b12bbe2d197218', '', '1', 'system/nurseBag/init.jhtml', '', '1', '1', '2', '2017-04-17 22:10:09', 'cb33c25f5c664058a111a9b876152317', '2017-04-23 18:52:54', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('875e7bde4e714b84933b6134dc5c4e6b', '0.004', '顾客管理', '1', '0', null, '1', null, null, '1', '1', '3', '2017-04-17 22:06:54', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:06:54', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('8e7be354d76c4599bb736549cafd0c58', '0.007.004', '平台财务总控', '1', '9949c0fdf9c346808de6a8644fabc670', '', '1', 'system/finance/initPlatform.jhtml', '', '1', '1', '4', '2017-04-17 22:15:10', 'cb33c25f5c664058a111a9b876152317', '2017-07-06 22:24:53', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('8eefe6e3298c4203b21e85e354b284ab', '0.001.004', '分类科目', '1', 'c66886c9ee47415aa81a6589acdb480a', 'catalog', '1', 'system/catalog/init.jhtml', null, '1', '1', '7', '2016-10-09 21:23:47', null, null, null);
INSERT INTO `sys_menu` VALUES ('9067fbad25de49d2af9ee5376c2c9154', '0.012.003', '预约管理', '2', 'b5764849b97242d68e88e10551205677', 'order.png', '1', 'shop/orderManage/initSubscribeOrder.jhtml', '', '1', '1', '3', '2017-05-07 02:01:44', 'cb33c25f5c664058a111a9b876152317', '2017-05-10 01:31:53', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('94ca69dd59b84054ad056b130d959425', '0.001.003', '键值参数', '1', 'c66886c9ee47415aa81a6589acdb480a', 'param_config', '1', 'system/param/init.jhtml', null, '1', '1', '6', '2016-10-09 21:22:03', null, null, null);
INSERT INTO `sys_menu` VALUES ('9949c0fdf9c346808de6a8644fabc670', '0.007', '财务管理', '1', '0', null, '1', null, null, '1', '1', '6', '2017-04-17 22:13:19', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:13:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('a2c376f98d0b48efade76c3631d2192e', '0.003.002', '职位列表', '1', 'b53c5b7fbeee40eda0e55027c368ae73', '', '1', 'system/shopPost/init.jhtml', '', '1', '1', '2', '2017-04-17 22:06:10', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 13:41:35', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('a5b39c90931b4249a23e30f24303dbfa', '0.001.002', '数据字典', '1', 'c66886c9ee47415aa81a6589acdb480a', 'dictionary', '1', 'system/dictionary/init.jhtml', '', '1', '0', '5', '2016-10-07 21:40:52', null, '2016-10-07 22:12:51', null);
INSERT INTO `sys_menu` VALUES ('b0a5eabb4cfe42a6877fe3bef62e0d92', '0.008', '美研币管理', '1', '0', null, '1', null, null, '1', '1', '7', '2017-04-17 22:17:20', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:17:20', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('b53c5b7fbeee40eda0e55027c368ae73', '0.003', '店铺员工管理', '1', '0', '', '1', '', '', '1', '1', '2', '2017-04-17 22:05:28', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:11:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('b5764849b97242d68e88e10551205677', '0.012', '商家系统菜单', '2', '0', '', '1', '', '', '1', '1', '30', '2017-04-19 14:15:50', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 14:26:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('bae788c74cf5424792b443d2fd55f3f6', '0.002.001', '商家列表', '1', '6147e3c63fb549e7a89d580d5d0c1106', '', '1', 'system/shopSys/init.jhtml', '', '1', '1', '1', '2017-04-17 22:02:42', 'cb33c25f5c664058a111a9b876152317', '2017-05-09 12:09:09', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('c66886c9ee47415aa81a6589acdb480a', '0.001', '系统管理', '1', '0', 'system_manage', '1', '', '', '1', '1', '20', '2016-10-07 17:57:14', null, '2017-04-19 14:16:40', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('c9a6d18b44314eb2aca0babe33d29118', '0.012.005', '护理管理', '2', 'b5764849b97242d68e88e10551205677', 'beauty.png', '1', 'shop/shopManage/init.jhtml', '', '1', '1', '5', '2017-05-07 02:03:11', 'cb33c25f5c664058a111a9b876152317', '2017-05-28 11:37:48', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('d093d7ce95bb48cba4e8b52f0e1439ab', '0.007.001', '现金流水', '1', '9949c0fdf9c346808de6a8644fabc670', '', '1', 'system/finance/initCashRecord.jhtml', '', '1', '1', '1', '2017-04-17 22:14:02', 'cb33c25f5c664058a111a9b876152317', '2017-05-29 23:33:03', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('d525b1f3274244c9af3a06c4e72621d8', '0.001.009', '角色管理', '1', 'c66886c9ee47415aa81a6589acdb480a', 'group_link', '1', 'system/role/init.jhtml', '', '1', '1', '3', '2016-12-31 09:09:32', null, '2017-01-15 19:32:18', null);
INSERT INTO `sys_menu` VALUES ('d58049dcf1fd4b7fa7751852e2aabe53', '0.012.007', '财务管理', '2', 'b5764849b97242d68e88e10551205677', 'finance.png', '1', 'shop/orderManage/initFinance.jhtml', '', '1', '1', '7', '2017-05-07 02:05:22', 'cb33c25f5c664058a111a9b876152317', '2017-06-16 00:50:26', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('d7236c0bba754cb0b7ea91b6cf2e161d', '0.012.001', '我的店面', '2', 'b5764849b97242d68e88e10551205677', 'myhome.png', '1', 'shop/shopManage/goMyShop.jhtml', '', '1', '1', '1', '2017-04-19 14:24:34', 'cb33c25f5c664058a111a9b876152317', '2017-05-07 23:27:13', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('da80f1f2953340a1aa306a59fc456bea', '0.005.001', '护理项目列表', '1', '67b8a34c0f9346a4b1b12bbe2d197218', '', '1', 'system/nurseProject/init.jhtml', '', '1', '1', '1', '2017-04-17 22:08:40', 'cb33c25f5c664058a111a9b876152317', '2017-04-23 08:35:51', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('dfc4df3c7f4341f885caf7aa305f8995', '0.001.007', ' 组织机构', '1', 'c66886c9ee47415aa81a6589acdb480a', 'dept_config', '1', 'system/dept/init.jhtml', '', '1', '1', '1', '2016-12-11 08:38:33', null, '2016-12-11 21:59:55', null);
INSERT INTO `sys_menu` VALUES ('e5c7809747ed41948c696862e5b1830c', '0.010', '意见管理', '1', '0', null, '1', null, null, '1', '1', '10', '2017-04-17 22:19:20', 'cb33c25f5c664058a111a9b876152317', '2017-04-17 22:19:20', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('e77399554d634f4ab21080f382e46370', '0.006.001', '营业记录', '1', '30acf69a620b4017ad930417fc75e0b1', '', '1', 'system/business/init.jhtml', '', '1', '1', '1', '2017-04-17 22:12:26', 'cb33c25f5c664058a111a9b876152317', '2017-05-30 17:01:51', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('ef2adf4fcf1843f588cc79b40c9da357', '0.009.002', '短信记录', '1', '1bb3706c28fd407c8c043bc1696e5c68', '', '1', 'system/smsRecord/init.jhtml', '', '1', '1', '2', '2017-05-12 01:02:40', 'cb33c25f5c664058a111a9b876152317', '2017-05-12 01:05:32', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_menu` VALUES ('f278c724ce8e4649bc2b74333ac0d28c', '0.001.008', '用户管理', '1', 'c66886c9ee47415aa81a6589acdb480a', 'user_config', '1', 'system/user/init.jhtml', '', '1', '1', '2', '2016-12-18 08:41:02', null, '2017-01-15 20:03:32', null);
INSERT INTO `sys_menu` VALUES ('faa6065db71a4c5289f73ed70b106181', '0.007.002', '颜值流水', '1', '9949c0fdf9c346808de6a8644fabc670', '', '1', 'system/finance/initBeautyRecord.jhtml', '', '1', '1', '2', '2017-04-17 22:14:22', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 10:01:35', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `param_id` varchar(50) NOT NULL DEFAULT '' COMMENT '参数编号',
  `param_name` varchar(100) DEFAULT NULL COMMENT '参数名称',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(500) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` varchar(50) DEFAULT NULL COMMENT '目录ID',
  `catalog_cascade_id` varchar(500) DEFAULT NULL COMMENT '分类科目语义ID',
  `param_remark` varchar(200) DEFAULT NULL COMMENT '参数备注',
  `status` varchar(10) DEFAULT '1' COMMENT '当前状态(0:停用;1:启用)',
  `edit_mode` varchar(10) DEFAULT '1' COMMENT '编辑模式(0:只读;1:可编辑)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='键值参数';

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES ('433ce11557c24b5a947c235138820c64', '短信验证码发送限制', 'one_hour_num', '5', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '短信验证码一小时发送限制', '1', '1', '2017-05-21 00:55:54', 'cb33c25f5c664058a111a9b876152317', '2017-05-21 00:56:13', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('48d61efcf98845fb833ca78bc955236c', '支付通知模板编号', 'pay_nodify_key', 'KU58D23fGtmA10KgDPnATX-7yNU48P9tVz2YXbEMTgE', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '', '1', '1', '2017-06-20 01:46:50', 'cb33c25f5c664058a111a9b876152317', '2017-07-14 00:05:54', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('4c8d5cfe75ae4ed5a87895f2237a1e2f', '订单IP地址', 'order_ip', '127.0.0.1', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '订单产生IP地址', '1', '1', '2017-06-04 10:50:30', 'cb33c25f5c664058a111a9b876152317', '2017-06-04 10:50:30', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('556933a0eaf34f8289956bbe28b5ff02', '订单预约过期时间', 'order_overdue_time', '60', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '单位分钟', '1', '1', '2017-07-08 22:22:29', 'cb33c25f5c664058a111a9b876152317', '2017-07-08 22:22:29', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('6010b7ea9a7f4bc7a908c02612e0ac9f', '自动回复信息', 'response_msg', '欢迎关注黄埔区来穗人员服务管理局', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '', '1', '1', '2017-06-16 00:49:22', 'cb33c25f5c664058a111a9b876152317', '2017-07-03 22:27:37', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('65693df209e84166b8f2015005051349', '礼包验证码键', 'bag_check_sms_code', 'SMS_71295405', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '', '1', '1', '2017-06-29 20:53:40', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:02:17', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('674b1071d90e42f4adc8e0ca011d78c1', '预约通知模板编号', 'subscribe_message_key', '1H0RB_Ti5Re_lGvZI0Oan49u42ym6FYrSLjRCkAj7JE', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', null, '1', '1', '2017-06-20 01:46:27', 'cb33c25f5c664058a111a9b876152317', '2017-06-20 01:46:27', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('6a66db3230884d0bad1e46cabf2148ec', '可取消预定时间', 'cancel_time', '60', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:47:57', 'cb33c25f5c664058a111a9b876152317', '2017-07-01 21:54:47', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('6cd477c4b2bc405c864b482d4e73dca8', '每两小时预约上限', 'every_two_limit', '1', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:47:57', 'cb33c25f5c664058a111a9b876152317', '2017-07-01 21:54:47', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('73a3eeafd93e48a186b47ca2f233e6b5', '预约定金', 'subscribe_deposit', '0.01', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:47:57', 'cb33c25f5c664058a111a9b876152317', '2017-07-01 21:54:46', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('788260e3e4fb4914bfb692279e20250a', '微信欢迎消息', 'welcome_msg', '欢迎关注黄埔区来穗人员服务管理局', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '', '1', '1', '2017-04-29 15:04:45', 'cb33c25f5c664058a111a9b876152317', '2017-07-03 22:28:00', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('798b6131843f42b59fcc96845c6794f6', '验证码有效时间', 'check_code_vaild', '10', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', ' 验证码有效时间 单位分钟', '1', '1', '2017-05-20 23:54:57', 'cb33c25f5c664058a111a9b876152317', '2017-05-20 23:54:57', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('8dc4edfe1b9443d8a7b4d64a8a676730', '兑换码生成位数', 'cdkey_num', '8', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '', '1', '1', '2017-05-15 22:31:31', 'cb33c25f5c664058a111a9b876152317', '2017-05-15 22:37:05', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('919bfc40932e45018f8aa319187ae35f', '微信支付开关', 'wechat_pay', 'off', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '微信支付开关 on 打开在填写opendid,off在测试', '1', '1', '2017-06-04 10:49:47', 'cb33c25f5c664058a111a9b876152317', '2017-07-01 21:54:38', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('9bbeacb155174ea88fe6b6f113ce0457', '短信网关', 'sms_url', 'http://gw.api.taobao.com/router/rest', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:44:34', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:15:40', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('aedaf6f528014f8cbc158b07e9a8bf07', '短信模板的键', 'template_key', 'name', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', null, '1', '1', '2017-05-13 00:55:12', 'cb33c25f5c664058a111a9b876152317', '2017-05-13 00:55:12', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('b4c234d0873f44efa1f6d231f8a69d30', '商家端系统标题', 'shop_sys_title', '美容管理商家端平台', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', null, '1', '1', '2017-05-06 15:19:49', 'cb33c25f5c664058a111a9b876152317', '2017-05-06 15:19:49', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('bc11f098150c46a694ba44aac0282a52', '验证码模板键', 'check_code_key', 'checkCode', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', null, '1', '1', '2017-05-21 00:02:46', 'cb33c25f5c664058a111a9b876152317', '2017-05-21 00:02:46', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('bc663ec2277e487280d547d79f1def9c', '验证码短信模板', 'check_sms_code', 'SMS_71295405', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '使用同一个签名，对同一个手机号码发送短信验证码，支持1条/分钟，5条/小时，10条/天。一个手机号码通过阿里大于平台只能收到40条/天', '1', '1', '2017-05-20 23:49:10', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:02:26', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('c6712a77e35d48489ae6827929232beb', '短信签名', 'sms_signe', '美妍社移动应用发布', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:44:35', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:15:41', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('d517cdc7b49f4cc8b93f4ca07c332bc2', '美研币过期时间', 'beauty_overtime', '20', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '1', '2017-04-26 23:18:32', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:07:19', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('d5c2f39ab78e4b0eae83497db6eefed1', '短信AppKey', 'sms_app_key', '23812181', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:44:34', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:15:41', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('e0a180760b4b46f7ae12f09edf8f4514', '系统访问地址', 'request_url', 'http://toonan.ngrok.xiaomiqiu.cn/BMS', '4f39839093744dccaedc9d7dcdee4ab3', '0.001', '', '1', '1', '2017-05-20 00:32:28', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:44:18', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('f19c714cc52c4113aab468cb90d0158a', '人民币兑换', 'exchange_beauty', '1', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', '一元人民币兑换多少个美研币', '1', '1', '2017-04-26 23:17:49', 'cb33c25f5c664058a111a9b876152317', '2017-07-29 09:07:18', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('f36a07b39af64aa4a14bd3176b18191f', '短信AppSecret', 'sms_app_secret', 'e0c94368229a2383f0723ac381e1d895', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:44:34', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:15:41', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_param` VALUES ('ff0fa340423e4eee8dd04457873a402a', '短信模板编号', 'sms_template_code', 'SMS_66795136', '5423b9ba9ac6472c80881827acafe9e9', '0.001.001', null, '1', '0', '2017-05-11 22:44:35', 'cb33c25f5c664058a111a9b876152317', '2017-07-02 10:15:41', 'cb33c25f5c664058a111a9b876152317');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(50) NOT NULL COMMENT ' 流水号',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `status` varchar(10) DEFAULT '1' COMMENT '当前状态 1启用 0禁用',
  `role_type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  `role_remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `edit_mode` varchar(10) DEFAULT '1' COMMENT '编辑模式(0:只读;1:可编辑)',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('c505b664774d431894a761879f5ebed0', '财务', '1', '1', null, '1', 'cb33c25f5c664058a111a9b876152317', '2017-06-15 15:03:39', 'cb33c25f5c664058a111a9b876152317', '2017-06-15 15:03:39');
INSERT INTO `sys_role` VALUES ('fefb51afa97c4a398a11a950ee85ebf8', '23', '1', '1', null, '1', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 14:38:46', 'cb33c25f5c664058a111a9b876152317', '2017-04-19 14:38:46');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(50) NOT NULL COMMENT ' 角色流水号',
  `menu_id` varchar(50) NOT NULL COMMENT '功能模块流水号',
  `grant_type` varchar(10) DEFAULT NULL COMMENT '权限类型 1 经办权限 2管理权限',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单模块-角色关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '0', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'c66886c9ee47415aa81a6589acdb480a', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '6147e3c63fb549e7a89d580d5d0c1106', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'bae788c74cf5424792b443d2fd55f3f6', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '74c419ba65be4e4c9d6fce1815e32f53', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'b53c5b7fbeee40eda0e55027c368ae73', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '500a6cf76b78428c9b0fef62da82e66b', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'a2c376f98d0b48efade76c3631d2192e', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '875e7bde4e714b84933b6134dc5c4e6b', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '6859506fc9c84556ac5e3bc4e8db61f1', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '67b8a34c0f9346a4b1b12bbe2d197218', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'da80f1f2953340a1aa306a59fc456bea', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '800703fe30da49e987581a07330393d4', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '7557bb92a6a148369a6d2fc517a6b547', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '30acf69a620b4017ad930417fc75e0b1', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'e77399554d634f4ab21080f382e46370', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '9949c0fdf9c346808de6a8644fabc670', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'd093d7ce95bb48cba4e8b52f0e1439ab', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'faa6065db71a4c5289f73ed70b106181', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '07b5cbef85fd4ba3817168d751d68782', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '8e7be354d76c4599bb736549cafd0c58', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'b0a5eabb4cfe42a6877fe3bef62e0d92', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '10e4756d11f743e48d1c7842f9375514', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '294c3e77cc364b059355e9447b3c2abf', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '1bb3706c28fd407c8c043bc1696e5c68', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '49bdf0b44a804b2ab338d3e8d640f3e1', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'ef2adf4fcf1843f588cc79b40c9da357', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'e5c7809747ed41948c696862e5b1830c', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', '0342b1350fd14ce79b8eafeb5d703642', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'dfc4df3c7f4341f885caf7aa305f8995', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');
INSERT INTO `sys_role_menu` VALUES ('c505b664774d431894a761879f5ebed0', 'f278c724ce8e4649bc2b74333ac0d28c', '1', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:50:20');

-- ----------------------------
-- Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `role_id` varchar(50) NOT NULL COMMENT '角色编号',
  `user_id` varchar(50) NOT NULL COMMENT '用户编号',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与用户关联';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('c505b664774d431894a761879f5ebed0', 'ced672de37514478a63eca171b666c62', 'cb33c25f5c664058a111a9b876152317', '2017-07-31 21:48:08');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户编号',
  `account` varchar(50) NOT NULL COMMENT '用户登录帐号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `username` varchar(50) NOT NULL COMMENT '用户姓名',
  `lock_num` int(10) DEFAULT '5' COMMENT '锁定次数 默认5次',
  `error_num` int(10) DEFAULT '0' COMMENT '密码错误次数  如果等于锁定次数就自动锁定用户',
  `sex` varchar(10) DEFAULT '3' COMMENT '性别  1:男2:女3:未知',
  `status` varchar(10) DEFAULT '1' COMMENT '用户状态 1:正常2:停用 3:锁定',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型',
  `dept_id` varchar(50) DEFAULT NULL COMMENT '所属部门流水号',
  `mobile` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `qq` varchar(50) DEFAULT NULL COMMENT 'QQ号码',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `idno` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `style` varchar(10) DEFAULT '1' COMMENT '界面风格',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `is_del` varchar(10) DEFAULT '0' COMMENT '是否已删除 0有效 1删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户编号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('cb33c25f5c664058a111a9b876152317', 'super', '0d+ywCe6ffI=', '超级用户', '10', '0', '1', '1', '2', '0', '13802907704', '240823329', '', '240823329@qq.com', '', '1', '', '超级用户，拥有最高的权限', '0', '2017-01-15 09:47:46', null, '2017-08-13 17:57:31', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `sys_user` VALUES ('ced672de37514478a63eca171b666c62', 'CW1', 'FyzQw6C0Ct4=', '陈财务', '10', '0', '1', '1', '1', '0', '13427516666', '984682666', '984682666', '6@163.com', '440181186212025669', '1', '广州市天河区', null, '0', '2017-06-15 15:10:35', 'cb33c25f5c664058a111a9b876152317', '2017-08-01 02:37:27', 'ced672de37514478a63eca171b666c62');

-- ----------------------------
-- Table structure for `wechat_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_menu`;
CREATE TABLE `wechat_menu` (
  `menu_id` varchar(50) NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父级菜单编号',
  `menu_type` varchar(50) DEFAULT NULL COMMENT '菜单类型',
  `menu_key` varchar(50) DEFAULT NULL COMMENT '菜单键值',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `media_id` varchar(50) DEFAULT NULL COMMENT 'media_id',
  `template_id` varchar(50) DEFAULT NULL COMMENT '消息模板编号',
  `is_auto_expand` varchar(10) DEFAULT '0' COMMENT '是否自动展开',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信菜单信息';

-- ----------------------------
-- Records of wechat_menu
-- ----------------------------
INSERT INTO `wechat_menu` VALUES ('84891f85338a41fab8c46a2c2fa1cbbe', '我的', '0', 'view', 'V10003', 'wechat/home/goHome.jhtml', '', null, '1', '3', '', '2017-05-04 00:41:15', 'cb33c25f5c664058a111a9b876152317', '2017-05-20 08:39:58', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `wechat_menu` VALUES ('c8f4944b81054076912219abf76a9ac3', '找店铺', '0', 'view', 'V10001', 'wechat/shop/goFindShop.jhtml', '', null, '1', '1', '', '2017-05-03 22:47:13', 'cb33c25f5c664058a111a9b876152317', '2017-08-13 17:59:12', 'cb33c25f5c664058a111a9b876152317');
INSERT INTO `wechat_menu` VALUES ('e0f24602472a47089b253f905548edcc', '礼包', '0', 'view', 'V10002', 'wechat/bag/goBag.jhtml', '', null, '1', '2', '', '2017-05-04 00:39:51', 'cb33c25f5c664058a111a9b876152317', '2017-05-20 08:39:43', 'cb33c25f5c664058a111a9b876152317');
