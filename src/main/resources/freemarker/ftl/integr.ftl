package ${config.integrUrl};

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<#if isRuikelai>
import com.ruikelai.commons.web.resp.RespWriter;
<#else>
import com.xiaoyeyun.commons.web.resp.RespWriter;
</#if>
<#if isPage>
	<#if isRuikelai>
import com.ruikelai.commons.web.resp.PageRespWriter;
	<#else>
import com.xiaoyeyun.commons.web.resp.PageRespWriter;
	</#if>
</#if>
import org.apache.commons.collections.CollectionUtils;

import ${config.poUrl}.${entityName};
import ${config.voUrl}.${entityName}VO;
import ${config.boUrl}.${entityName}BO;
import ${config.serviceUrl}.${entityName}Service;
import org.springframework.transaction.annotation.Transactional;
<#if isRuikelai>
import com.ruikelai.commons.dict.PromptDict;
import com.ruikelai.commons.util.Assert;
</#if>

/**
* @author Generator
*/
@Service
public class ${entityName}Integr  {

	@Autowired
	public ${entityName}Service ${entityName?uncap_first}Service;

	public RespWriter detail(${entityName}BO param){
		RespWriter writer = new RespWriter();

		${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.findBy${idParamName}(param.get${idParamName}());
		Assert.notNull(${entityName?uncap_first},PromptDict.DETAIL_ERROR);

		writer.setDetail(${entityName}VO.createNew(${entityName?uncap_first}));
		return writer;
	}


	public <#if isPage>PageRespWriter<#else>RespWriter</#if> list(${entityName}BO param){
		<#if isPage>PageRespWriter<#else>RespWriter</#if> writer = new <#if isPage>PageRespWriter<#else>RespWriter</#if>();

		List<${entityName}> list = ${entityName?uncap_first}Service.list(param);
		if(CollectionUtils.isEmpty(list)){
			return writer;
		}

		List<${entityName}VO> rows = new ArrayList<>();
		list.stream().forEach(${entityName?uncap_first}->rows.add(${entityName}VO.createNew(${entityName?uncap_first})));

        writer.setRows(rows);
        <#if isPage>
        writer.setPageParams(${entityName?uncap_first}Service.count(param), param.getPage());
        </#if>

		return writer;
	}

	public RespWriter delete(${entityName}BO param){
		Assert.isValid(${entityName?uncap_first}Service.delete(param.get${idParamName}()),PromptDict.UPDATE_ERROR);
		return new RespWriter();
	}

	@Transactional
	public RespWriter create(${entityName}BO param){
		${entityName?uncap_first}Service.insert(${entityName}.createNew(param));
		 return new RespWriter();
	}

	public RespWriter update(${entityName}BO param){
		Assert.isValid(${entityName?uncap_first}Service.update(${entityName}.createNew(param)),PromptDict.UPDATE_ERROR);
		return new RespWriter();
	}
}