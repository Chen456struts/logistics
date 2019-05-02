drop database c_logistics;
create database c_logistics DEFAULT  charset utf8;
use c_logistics;
#1管理员表
create table admin(
a_id int primary key auto_increment comment '编号',
a_account varchar(100)    comment '账号',
a_password varchar(100)    comment '密码',
a_phone varchar(100)    comment '手机号码',
a_rank varchar(50)    comment '权限',
remarks varchar(256) comment '备注'
);
INSERT INTO `admin` VALUES ('1', 'admin', '123456','18776203778', '高级管理员', '1000');
INSERT INTO `admin` VALUES ('2', 'mydb', '123456','18776202228',  '普通管理员', '500');
INSERT INTO `admin` VALUES ('3', 'tom', '123456','4565645665',  '普通管理员', '1000');
#2财务信息表
create table finance_info(
f_id int primary key auto_increment  comment '编号',
befor_money decimal(10,2) comment '交易前余额',
after_money decimal(10,2) comment '交易后余额',
f_money decimal(10,2) comment '交易余额',
f_type varchar(100) comment '交易类型',
f_name_type varchar(100) comment '交易人类型',
f_ids int comment '交易人id',
f_name varchar(100) comment '交易人名字',
f_date datetime default(now()) comment '交易时间'
);
INSERT INTO `finance_info` VALUES ('1','1000','500','500','充值','司机','001','admin','20190109');
INSERT INTO `finance_info` VALUES ('2','1000','500','500','充值','司机','002','mydb','20190109');
INSERT INTO `finance_info` VALUES ('3','1000','500','500','充值','货主','003','moonLu','20190109');
INSERT INTO `finance_info` VALUES ('4','1000','500','500','充值','货主','004','bonZhang','20190109');
INSERT INTO `finance_info` VALUES ('5','1000','500','500','支付','货主','005','davidJiang','20190109');
INSERT INTO `finance_info` VALUES ('6','1000','500','500','支付','货主','006','jamesHou','20190109');
INSERT INTO `finance_info` VALUES ('7','1000','500','500','支付','货主','007','helenguan','20190109');
INSERT INTO `finance_info` VALUES ('8','1000','500','500','提现','货主','008','lenaKong','20190109');
INSERT INTO `finance_info` VALUES ('9','1000','500','500','提现','货主','009','Juneyang','20190109');
INSERT INTO `finance_info` VALUES ('10','1000','500','500','提现','司机','010','ally','20190109');
#3员工表
create table employee_information(
e_no int primary key auto_increment comment '员工编号',
e_name varchar(100) comment '员工姓名',
e_sex varchar(10) comment '性别',
e_birthhday date comment '出生日期',
e_phone varchar(50) comment '手机号码' ,
e_password varchar(256) comment '密码',
firm_name varchar(100) comment '公司名称',
remarks varchar(256) comment '备注'
);
INSERT INTO `employee_information`(e_name,e_sex,e_birthhday,e_phone,e_password,firm_name,remarks) VALUES
 ('ZhangSan', '男', '1996-01-03', '234324454', '123', 'ZS', '12300'),
 ('LiSi', '女', '1996-01-23', '234344555', '123', 'LS', '12000'),
 ('WangWu', '男', '1996-01-15', '123433433', '123', 'WW', '20000'),
 ('ZhaoLiu', '女', '1996-07-20', '324455464', '123', 'ZL', '20000'),
 ('SunQi', '男', '1996-11-03', '234565656', '123', 'SQ', '12000'),
 ('WangXiaoer','男','1997-01-20','234778288','456','WX','10000'),
 ('ZhouJielun','男','1996-02-12','375866478','123','ZJl','15000'),
 ('ZhanYishan','男','1992-01-23','347885655','456','ZYs','12500'),
 ('LiuYifei','女','1992-08-17','134878767','123','LYf','15000'),
 ('ZhangFengyi','男','1970-03-15','234635567','123','ZFy','13000'),
 ('XuSong','男','1990-08-10','764876786','123','XS','18000'),
 ('ChenChong','女','1996-08-25','768768377','456','CC','25000'),
 ('WangSulong','男','1992-08-09','686868789','789','WSl','30000'),
 ('DengZiqi','女','1988-02-10','987872782','789','DZq','50000'),
 ('ZhaoFangjing','女','1992-02-17','767523866','456','ZFj','27000'),
 ('YangZi','女','1992-03-27','623825837','456','YZ','20000'),
 ('LinJunjie','男','1990-11-23','686328682','789','LJj','19000'),
 ('GaoShijie','男','1978-12-10','973823971','123','GSj','23000'),
 ('GuoYongwei','男','1999-01-02','634826482','123','GYw','12000');
#4货主
create table shipper_information(
s_id int primary key auto_increment comment '货主编号',
s_name varchar(100) comment '货主姓名',
s_sex varchar(10) comment '货主性别',
s_phone varchar(50) comment '手机号码' ,
s_password varchar(100) comment '密码',
s_uuid varchar(100) comment '身份证号码',
s_balance decimal(10,2) comment '余额',
register_date datetime default(now()) comment '注册时间'
);
INSERT INTO `shipper_information`(s_name,s_sex,s_phone,s_password,s_uuid,s_balance) VALUES
 ('as', '男',  '1234345546', '123', '001', '0'),
( 'asd', '女',  '3455567776', '123', '002', '0'),
('adsa', '男',  '545667567', '123', '003', '100'),
('qe', '男',  '2347846888', '123', '004', '100'),
( 'tw', '女',  '7384584678', '123', '005', '1'),
( 'ewr', '女',  '3474678478', '123', '006', '5'),
( 'res','女', '235564565', '456', '007', '007'),
( 'dis','男', '475862477', '123', '008' ,'233'),
( 'vuess','女', '757365573' ,'123', '009', '235'),
( 'csse', '男', '887849748', '123', '010', '145'),
( 'kim', '男', '457578678', '456', '011', '290'),
( 'Dali', '女', '877438692','234', '012', '237'),
( 'tyh', '男', '344534345', '345', '013', '013'),
( 'oppo', '女', '987926399', '123', '014', '709'),
( 'Xin', '男', '787634768', '123', '015', '987');

#5司机
create table driver_information(
d_id int primary key auto_increment comment '编号',
d_name varchar(100) comment '姓名',
d_sex varchar(10) comment '性别',
d_uuid varchar(100) comment '身份证号码',
d_phone varchar(50) comment '手机号码' ,
d_password varchar(50) comment '密码',
card varchar(1000) comment '油卡',
d_balance decimal(10,2) comment '余额',
register_date datetime default(now())  comment '注册时间'
);
INSERT INTO `driver_information`(d_name,d_sex,d_uuid,d_phone,d_password,card,d_balance) VALUES
 ('admin', '男', '001', '1234345546', '123', '001', '0.00'),
( 'mydb', '女', '002', '3455567776', '123', '002', '0.00'),
('tom', '男', '003', '545667567', '123', '003', '1.00'),
('jack', '男', '004', '2347846888', '123', '004', '1.00'),
( 'tony', '女', '005', '7384584678', '123', '005', '0.00'),
( 'jenny', '女', '006', '3474678478', '123', '006', '1.00'),
( 'Chenchong', '男', '007', '433247828', '123', '007', '1.00'),
( 'LiuSongfeng', '男', '008', '657525467', '456', '008', '1.00'),
( 'ShiFuming', '男', '009', '084837938', '123', '009', '1.00'),
( 'GuoYongwei', '男', '010', '897246899', '123', '010', '0.00'),
( 'DuHaixin', '男', '011', '234342442', '456', '011', '0.00'),
( 'YangHanxiang', '男', '012', '788747282', '123', '012', '1.00'),
( 'ZhangShilong', '男' ,'013', '976836826', '123', '013', '0.00'),
( 'Liujiamei', '男', '014', '908798398', '456', '014', '1.00'),
( 'YanJiali', '女', '015', '782638863', '123', '015', '1.00');
#6车辆类型
create table vehicle_type(
t_id int primary key auto_increment comment '编号',
v_name varchar(50)  comment '类型名称',
v_load varchar(50) comment '载重',
v_zgk varchar(50) comment '长宽高',
v_s  varchar(50) comment '载货体积'
);
INSERT INTO `vehicle_type`(v_name,v_load,v_zgk,v_s) VALUES
 ( '小型车', '5', '5X4X3', '500'),
 ( '中型车', '15', '321', '1000'),
 ( '大型车', '20', '321', '1500');

#7车辆信息表
create table vehicle(
v_id int primary key auto_increment comment '编号',
license varchar(50)  comment '车牌号',
v_type varchar(50) comment '品牌',
v_color varchar(50) comment '车颜色',
t_type int comment '车辆类型',
d_id int comment '司机编号',
remarks varchar(256) comment '备注'
);
INSERT INTO `vehicle`(license,v_type,v_color,t_type,d_id,remarks) VALUES
 ( '0102u5', '法拉第', 'green', '1', '1', null),
 ( '0103u5', '宝马', 'green', '1', '2', null),
 ( '0104u5', '宝马', 'green', '1', '3', null),
 ( '0105u5', '奥迪', 'green', '1', '4', null),
 ( '0107d5', '奔驰', 'green', '1', '12', null);

#8公告表
create table notice(
infro_id int primary key auto_increment comment '编号',
n_content varchar(200) comment '内容',
direction varchar(100)  comment '标题'
);
insert into notice(n_content,direction)values('司机内容','司机');
insert into notice(n_content,direction)values('货主内容','货主');
insert into notice(n_content,direction)values('管理员内容','管理员');
#9平台收取费用表
create table collection_fee(
c_id int primary key auto_increment comment '编号',
c_date varchar(200) comment '时间/分钟',
mileage varchar(100) comment '里程/分钟',
c_starting varchar(100) comment '起步价',
remarks varchar(100) comment '备注',
c_type varchar(100) comment '类型'
);
INSERT INTO `collection_fee`(c_date,mileage,c_starting,remarks,c_type) VALUES 
( '0.7', '1.2', '12', '', '白天'),
( '0.8', '1.3', '12', '', '晚上'),
( '0.9', '1.4', '12', '', '周末'),
( '0.8', '1.2', '12', '', '节日假');
#10货主收藏信息表
create table s_collect(
c_id int primary key auto_increment comment '编号',
phone varchar(200) comment '司机电话',
name varchar(100) comment '司机名字',
s_id int comment '收藏人id'
);
INSERT INTO `s_collect`(phone,name,s_id) VALUES 
 ( '1231331385', 'ZhangSan', '1'),
 ( '1257751385', 'ZhangSan', '2'),
 ( '1236655385', 'ZhangSan', '3'),
 ( '6541531385', 'ZhangSan', '4'),
 ( '1231334512', 'LiSi', '5'),
 ( '1237891385', 'ZhangSan', '6'),
 ( '1237831385', 'WangWu', '7'),
 ( '1231331385', 'ZhangSan', '8'),
 ( '1467531385', 'ZhangSan', '9'),
 ( '1575457385', 'ZhangSan', '1');
#11订单表
create table order_information(
o_id varchar(50) primary key comment '订单编号',
o_type int comment '车辆类型ID',
contacts varchar(50) comment '联系人',
start_date datetime comment '预约时间',
end_date datetime comment '完成时间',
price decimal(10,2) comment '订单价格',
shipping_address varchar(100) comment '发货地址',
receiving_address varchar(100) comment '收货地址',
s_id int comment '司机id',
d_id int comment '货主id',
o_state varchar(50) comment '订单状态',
o_remarks varchar(256) comment '备注'
);
INSERT INTO `order_information`(o_id,o_type,contacts,start_date,end_date,price,shipping_address,receiving_address,s_id,d_id,o_state,o_remarks) VALUES
 (  '1213123213','1', 'ZhouJielun', '2019-01-23', null, '200', '郑州市丰产路文化路', '湖北黄冈', '1', '2', '待运输',''),
 (  '1123123','2', 'ZhouJielun', '2019-01-23', null, '200', '郑州市丰产路文化路', '湖北黄冈', '2', '1', '运输中',''),
 (  '11232131312','3', 'ZhouJielun', '2019-01-23', '2019-01-26', '200', '郑州市丰产路文化路', '湖北黄冈', '3', '3', '已完成',''),
 ( '3123123121', '4', 'ZhouJielun', '2019-01-23', null, '200', '郑州市丰产路文化路', '湖北黄冈', '4', '2', '待运输', ''),
 ( '3213213211', '5', 'ZhouJielun', '2019-01-23', null, '200', '郑州市丰产路文化路', '湖北黄冈', '5', '2', '待运输', '');
#12评价信息表
create table evaluation_information(
e_id int primary key auto_increment comment 'id',
o_id varchar(50) comment '订单编号',
d_id int comment '司机id',
d_name  varchar(50) comment '货主名字',
s_id int comment '货主id',
s_name  varchar(50) comment '司机名字',
fraction varchar(50) comment '评价分数',
e_content varchar(256) comment '评价内容'
);
INSERT INTO `evaluation_information`(d_id,o_id ,d_name,s_id,s_name,fraction,e_content) VALUES
 ('1', '1213123213','adsa', '3' ,'tom', 5, '速度快,服务态度很好,棒棒哒'),
 ('2', '1123123','kim', '6' ,'jenny', 5, '速度快,服务态度很好,棒棒哒'),
 ('3','11232131312', 'res', '11' ,'DuHaixin', 4, '速度快,服务态度很好,棒棒哒'),
 ('4','3123123121', 'oppo', '14' ,'Liujiamei', 4, '速度快,服务态度很好,棒棒哒'),
 ('5','1123123', 'xin', '9' ,'ShiFuming', 5, '速度快,服务态度很好,棒棒哒'),
 ('6',  '3213213211','xin','3' ,'tom', 4.5, '速度快,服务态度很好,棒棒哒'),
 ('7', '3212133212', 'xin','3' ,'tom', 5, '速度快,服务态度很好,棒棒哒');
#13短信验证
create table VerificationCode(
id int auto_increment primary key,
yzm int comment '验证码',
v_type varchar(10) comment '验证类型',
v_date datetime default now()  comment '时间',
v_phone varchar(11) comment '手机号码'
);

