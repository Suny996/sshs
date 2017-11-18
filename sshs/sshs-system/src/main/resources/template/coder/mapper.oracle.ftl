<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${coder.packageName}.${coder.className}Dao">
	
	<!--表名 -->
	<sql id="tableName">
		${coder.tableName}
	</sql>
	
	<!-- 字段 -->
	<sql id="Field">
		<#list coder.fields as field>
			<#if field_has_next>
				${field.columnName},
			<#else>	
				${field.columnName}
			</#if>
		</#list>
	</sql>
	
	<!-- 字段值 -->
	<sql id="FieldValue">
		<#list coder.fields as field>
			<#if field_has_next>
				${r"#{"}${field.propertyName}${r"}"},
			<#else>
				${r"#{"}${field.propertyName}${r"}"}
			</#if>	
		</#list>
	</sql>
	 
    <!-- 分页查询${coder.title}-查询列信息 -->
	<select id="findForPageList" parameterType="Page"
		resultType="${coder.className}">
		SELECT  
		<include refid="Field"></include>
		FROM 
		<include refid="tableName"></include>
		WHERE 1=1
		<#list coder.fields as field>
			<if test="variables.${field.propertyName} != null and variables.${field.propertyName} != ''">
				AND ${field.columnName} = ${r"#{"}variables.${field.propertyName}${r"}"}
			</if>	
		</#list>
	</select>
</mapper>