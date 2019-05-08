<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.daoUrl}.${entityName}Dao">
	<resultMap id="BaseResultMap" type="${config.poUrl}.${entityName}">
	<#list props as ci>
		<#if (ci.columnKey)=="PRI">
		<id column="${ci.column}" jdbcType="${ci.jdbcType?upper_case}" property="${ci.property}" />
		<#else>
		<result column="${ci.column}" jdbcType="${ci.jdbcType?upper_case}" property="${ci.property}" />		
		</#if>
	</#list>
	</resultMap>
	
	<sql id="Base_Column_List">
		<#list props as ci>
			${ci.column}<#if ci_has_next>,</#if>
		</#list>
	</sql>
	
	<select id="findBy${idParamName}" resultMap="BaseResultMap">
		select
		<include refid="Base_Column_List" />
		from ${table}
		where ${idColumn} = ${r'#{'}${idParamName?uncap_first}}
	</select>
	
	<select id="list" resultMap="BaseResultMap">
		select
		<include refid="Base_Column_List" />
		from ${table}
		<#if isPage>
		<where>
			<if test="p.q != null and p.q != ''">
				and name like CONCAT('%',${r'#{'}p.q},'%')
			</if>
		</where>
		</#if>
		order by create_time desc
		<#if isPage>
		limit ${r'#{'}p.pnum} offset  ${r'#{'}p.startNum}
		</#if>
	</select>
	
	<select id="count" resultType="int">
		select
		count(1)
		from ${table}
		<#if isPage>
		<where>
			<if test="p.q != null and p.q != ''">
				and name like CONCAT('%',${r'#{'}p.q},'%')
			</if>
		</where>
		</#if>
	</select>
		
	<delete id="delete">
		delete from ${table}
		where ${idColumn} = ${r'#{'}${idParamName?uncap_first}}
	</delete>
	
	<insert id="insert" parameterType="${config.poUrl}.${entityName}">
		<#if idType=="int">
		<selectKey keyProperty="id" order="AFTER" resultType="int">
			SELECT
			LAST_INSERT_ID()
		</selectKey>
		<#else>
		<selectKey keyProperty="id" order="AFTER" resultType="java.lang.${idType}">
			SELECT
			LAST_INSERT_ID()
		</selectKey>
		</#if>
		insert into ${table}(
			<#list props as ci>
				${ci.column}<#if ci_has_next>,</#if>
			</#list>	
		)VALUES(
			<#list props as ci>
				${r'#{'}${ci.property}}<#if ci_has_next>,</#if>
			</#list>	
		)
	</insert>
	
	
	<update id="update" parameterType="${config.poUrl}.${entityName}">
		update ${table}
		<set>
			<#list props as ci>
				${ci.column} = ${r'#{'}${ci.property}}<#if ci_has_next>,</#if>
			</#list>
		</set>
		where ${idColumn} = ${r'#{'}${idParamName?uncap_first}}
	</update>
	
	
</mapper>