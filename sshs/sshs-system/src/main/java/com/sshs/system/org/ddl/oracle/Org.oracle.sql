-- Create table 系统管理-机构表

create table SYS_ORG
(
	ORG_ID	VARCHAR2(32),
	ORG_CODE	VARCHAR2(32),
	ORG_NAME	VARCHAR2(256),
	ORG_BRIEF	VARCHAR2(100),
	PARENT_ORG_CODE	VARCHAR2(32),
	ORG_CATEGORY	VARCHAR2(10),
	ORG_LEVEL	VARCHAR2(32),
	ORG_GRADE	VARCHAR2(32),
	PATH_NAME	VARCHAR2(100),
	STATUS	VARCHAR2(10),
	COUNTRY	VARCHAR2(10),
	EMAIL	VARCHAR2(100),
	OFFICE_ADDR	VARCHAR2(512),
	POST	VARCHAR2(100),
	TEL	VARCHAR2(100),
	CONTACT	VARCHAR2(100),
	ORG_FI_NO	VARCHAR2(60),
	ORG_PAY_NO	VARCHAR2(60),
	ORG_CREDIT_NO	VARCHAR2(60),
	ORG_PERMIT_NO	VARCHAR2(60),
	ORG_REG_NO	VARCHAR2(60),
	ORG_ID_NUMBER	VARCHAR2(60),
	ORG_TAX_NO	VARCHAR2(60),
	ORG_CREATE_DATE	DATE(7),
	ORG_START_DATE	VARCHAR2(10),
	ORG_END_DATE	VARCHAR2(10),
	ORG_HEADER	VARCHAR2(32),
	NODE_NO	VARCHAR2(32),
	HIGHER_MNG_ORG	VARCHAR2(1,000),
	IS_LEAF	VARCHAR2(10),
	BUSI_STATUS	VARCHAR2(10),
	AREA_CODE	VARCHAR2(10),
	ORDERS	NUMBER(22),
	OWN_LINE	VARCHAR2(10),
	VER_NO	NUMBER(22),
	APPLY_STATUS	VARCHAR2(60),
	OPER_TYPE	VARCHAR2(1),
	OPER_RELA_ID	VARCHAR2(60),
	APPLY_EMP_NO	VARCHAR2(60),
	APPLY_DATE	DATE(7),
	APPLY_REASON	VARCHAR2(4,000),
	PROCESS_INSTANCE_ID	VARCHAR2(32),
	REMARK	VARCHAR2(100),
	CRT_USER_CODE	VARCHAR2(32),
	CRT_ORG_CODE	VARCHAR2(32),
	CRT_DATE	DATE(7),
	UPD_USER_CODE	VARCHAR2(32),
	UPD_ORG_CODE	VARCHAR2(32),
	UPD_DATE	DATE(7),
);
-- Add comments to the table 
comment on table SYS_ORG
  is '系统管理-机构表';
-- Add comments to the columns 
comment on column SYS_ORG.ORG_ID
  is '机构ID';
comment on column SYS_ORG.ORG_CODE
  is '机构编码';
comment on column SYS_ORG.ORG_NAME
  is '机构名称';
comment on column SYS_ORG.ORG_BRIEF
  is '机构简称';
comment on column SYS_ORG.PARENT_ORG_CODE
  is '直接上级机构ID';
comment on column SYS_ORG.ORG_CATEGORY
  is '机构类别:01--机构,02--部门';
comment on column SYS_ORG.ORG_LEVEL
  is '机构层级（树形结构层级）';
comment on column SYS_ORG.ORG_GRADE
  is '机构级别：总行，分行，支行等';
comment on column SYS_ORG.PATH_NAME
  is '中文路径：用‘.’隔开各层级路径名称:.a.b.c.';
comment on column SYS_ORG.STATUS
  is '状态：01--启用,02--停用';
comment on column SYS_ORG.COUNTRY
  is '国家或地区:中国、中国香港、美国等,字典表数据';
comment on column SYS_ORG.EMAIL
  is '联系邮箱';
comment on column SYS_ORG.OFFICE_ADDR
  is '办公地址';
comment on column SYS_ORG.POST
  is '邮政编码';
comment on column SYS_ORG.TEL
  is '联系电话';
comment on column SYS_ORG.CONTACT
  is '联系人';
comment on column SYS_ORG.ORG_FI_NO
  is '金融机构编码（人行）';
comment on column SYS_ORG.ORG_PAY_NO
  is '支付行号（人行）';
comment on column SYS_ORG.ORG_CREDIT_NO
  is '机构信用代码（人行）';
comment on column SYS_ORG.ORG_PERMIT_NO
  is '金融许可证号码（银监）';
comment on column SYS_ORG.ORG_REG_NO
  is '营业执照注册号（工商）';
comment on column SYS_ORG.ORG_ID_NUMBER
  is '组织机构代码证号码';
comment on column SYS_ORG.ORG_TAX_NO
  is '税务登记证号码';
comment on column SYS_ORG.ORG_CREATE_DATE
  is '成立日期';
comment on column SYS_ORG.ORG_START_DATE
  is '营业开始时间';
comment on column SYS_ORG.ORG_END_DATE
  is '营业终止时间';
comment on column SYS_ORG.ORG_HEADER
  is '机构负责人';
comment on column SYS_ORG.NODE_NO
  is '网点号';
comment on column SYS_ORG.HIGHER_MNG_ORG
  is '代管机构：01,02,03';
comment on column SYS_ORG.IS_LEAF
  is '是否最末端';
comment on column SYS_ORG.BUSI_STATUS
  is '营业状态';
comment on column SYS_ORG.AREA_CODE
  is '行政区划代码';
comment on column SYS_ORG.ORDERS
  is '顺序号';
comment on column SYS_ORG.OWN_LINE
  is '所属条线';
comment on column SYS_ORG.VER_NO
  is '版本号';
comment on column SYS_ORG.APPLY_STATUS
  is '审批状态';
comment on column SYS_ORG.OPER_TYPE
  is '操作类型：合并，撤销，转移，修改';
comment on column SYS_ORG.OPER_RELA_ID
  is '关联主键：合并，撤销，转移，修改的关联ID';
comment on column SYS_ORG.APPLY_EMP_NO
  is '申请人';
comment on column SYS_ORG.APPLY_DATE
  is '申请日期';
comment on column SYS_ORG.APPLY_REASON
  is '申请原因';
comment on column SYS_ORG.PROCESS_INSTANCE_ID
  is '审批流ID';
comment on column SYS_ORG.REMARK
  is '备注';
comment on column SYS_ORG.CRT_USER_CODE
  is '创建人';
comment on column SYS_ORG.CRT_ORG_CODE
  is '创建机构';
comment on column SYS_ORG.CRT_DATE
  is '创建日期';
comment on column SYS_ORG.UPD_USER_CODE
  is '修改人';
comment on column SYS_ORG.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_ORG.UPD_DATE
  is '修改日期';
