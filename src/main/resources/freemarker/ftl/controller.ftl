package ${config.controllerUrl};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
import ${config.integrUrl}.${entityName}Integr;
import ${config.boUrl}.${entityName}BO;
<#if isRuikelai>
import com.ruikelai.commons.web.validation.group.Create;
import com.ruikelai.commons.web.validation.group.ID;
import com.ruikelai.commons.web.validation.group.Update;
</#if>

/**
* @author Generator
*/
@RestController
public class ${entityName}Act {
	
	@Autowired
	public ${entityName}Integr ${entityName?uncap_first}Integr;
	
	
	@PostMapping(value = "/${entityName?uncap_first}/create")
    public RespWriter create(@RequestBody @Validated({ Create.class }) ${entityName}BO param) {
        return ${entityName?uncap_first}Integr.create(param);
    }
    
    @PostMapping(value = "/${entityName?uncap_first}/list")
    public <#if isPage>PageRespWriter<#else>RespWriter</#if> list(@RequestBody ${entityName}BO param) {
        return ${entityName?uncap_first}Integr.list(param);
    }
    
    @PostMapping(value = "/${entityName?uncap_first}/detail")
    public RespWriter detail(@RequestBody @Validated({ ID.class }) ${entityName}BO param) {
        return ${entityName?uncap_first}Integr.detail(param);
    }

    @PostMapping(value = "/${entityName?uncap_first}/delete")
    public RespWriter delete(@RequestBody @Validated({ ID.class }) ${entityName}BO param) {
         return ${entityName?uncap_first}Integr.delete(param);
    }


    @PostMapping(value = "/${entityName?uncap_first}/update")
    public RespWriter update(@RequestBody @Validated({ Update.class }) ${entityName}BO param) {
        return ${entityName?uncap_first}Integr.update(param);
    }
	
}