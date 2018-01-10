-- Create table 系统管理-用户基本信息表

create table SYS_USER
(
	USER_ID	VARCHAR2(32),
	USER_CODE	VARCHAR2(32),
	USER_NAME	VARCHAR2(32),
	USER_NAME_EN	VARCHAR2(32),
	USER_NAME_PY	VARCHAR2(50),
	USER_NAME_FR	VARCHAR2(36),
	ORG_CODE	VARCHAR2(36),
	POST_NO	VARCHAR2(32),
	USER_SEX	VARCHAR2(2),
	NATIONALITY	VARCHAR2(3),
	ETHNIC	VARCHAR2(50),
	NATIVE_PLACE	VARCHAR2(100),
	BIRTH_PLACE	VARCHAR2(50),
	BIRTH_DATE	DATE(7),
	ID_DECIMAL	VARCHAR2(20),
	PHOTO_ID	VARCHAR2(100),
	JOIN_DATE	DATE(7),
	STATUS	VARCHAR2(10),
	TELLER_ID	VARCHAR2(32),
	RESIDENTIAL_ADDRESS	VARCHAR2(1,000),
	MOBILE_PHONE	VARCHAR2(32),
	REMARK	VARCHAR2(250),
	PASSWORD	VARCHAR2(32),
	SALT	VARCHAR2(32),
	ONLINE_STATUS	VARCHAR2(10),
	IP_ADDR	VARCHAR2(60),
	USER_THEME	VARCHAR2(32),
	PD_COUNT	VARCHAR2(32),
	PD_MODTIME	VARCHAR2(32),
	PD_LOCKTIME	VARCHAR2(32),
	LAST_SIGNON_TIME	DATE(7),
	LAST_SIGNOUT_TIME	DATE(7),
	LEGAL_ORG	VARCHAR2(50),
	CRT_USER_CODE	VARCHAR2(32),
	CRT_ORG_CODE	VARCHAR2(32),
	CRT_DATE	DATE(7),
	UPD_USER_CODE	VARCHAR2(32),
	UPD_ORG_CODE	VARCHAR2(32),
	UPD_DATE	DATE(7),
);
-- Add comments to the table 
comment on table SYS_USER
  is '系统管理-用户基本信息表';
-- Add comments to the columns 
comment on column SYS_USER.USER_ID
  is '主键';
comment on column SYS_USER.USER_CODE
  is '用户编号';
comment on column SYS_USER.USER_NAME
  is '姓名';
comment on column SYS_USER.USER_NAME_EN
  is '英文名';
comment on column SYS_USER.USER_NAME_PY
  is '拼音码';
comment on column SYS_USER.USER_NAME_FR
  is '曾用名';
comment on column SYS_USER.ORG_CODE
  is '机构编号:到具体的部门、支行和二级中心';
comment on column SYS_USER.POST_NO
  is '岗位';
comment on column SYS_USER.USER_SEX
  is '性别:1－男性，2－女性，9－未说明性别';
comment on column SYS_USER.NATIONALITY
  is '国籍';
comment on column SYS_USER.ETHNIC
  is '民族:01=汉族';
comment on column SYS_USER.NATIVE_PLACE
  is '籍贯';
comment on column SYS_USER.BIRTH_PLACE
  is '出生地';
comment on column SYS_USER.BIRTH_DATE
  is '出生日期';
comment on column SYS_USER.ID_DECIMAL
  is '身份证号码';
comment on column SYS_USER.PHOTO_ID
  is '证件照';
comment on column SYS_USER.JOIN_DATE
  is '入职日期';
comment on column SYS_USER.STATUS
  is '用户状态:0-无效，1-有效';
comment on column SYS_USER.TELLER_ID
  is '柜员编号';
comment on column SYS_USER.RESIDENTIAL_ADDRESS
  is '现居住地址';
comment on column SYS_USER.MOBILE_PHONE
  is '手机';
comment on column SYS_USER.REMARK
  is '备注';
comment on column SYS_USER.PASSWORD
  is '密码';
comment on column SYS_USER.SALT
  is '盐（密码）';
comment on column SYS_USER.ONLINE_STATUS
  is '在线状态:在线、离线、锁定';
comment on column SYS_USER.IP_ADDR
  is 'IP地址';
comment on column SYS_USER.USER_THEME
  is '用户皮肤';
comment on column SYS_USER.PD_COUNT
  is '密码输入次数';
comment on column SYS_USER.PD_MODTIME
  is '密码修改时间';
comment on column SYS_USER.PD_LOCKTIME
  is '密码锁定时间';
comment on column SYS_USER.LAST_SIGNON_TIME
  is '最后一次登陆时间';
comment on column SYS_USER.LAST_SIGNOUT_TIME
  is '最后一次退出时间';
comment on column SYS_USER.LEGAL_ORG
  is '法人机构编号';
comment on column SYS_USER.CRT_USER_CODE
  is '创建人';
comment on column SYS_USER.CRT_ORG_CODE
  is '创建机构';
comment on column SYS_USER.CRT_DATE
  is '创建日期';
comment on column SYS_USER.UPD_USER_CODE
  is '修改人';
comment on column SYS_USER.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_USER.UPD_DATE
  is '修改日期';
