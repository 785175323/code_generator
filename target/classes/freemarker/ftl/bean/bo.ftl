package ${config.boUrl};

import java.time.LocalDateTime;
<#if isPage>
	<#if isRuikelai>
import com.ruikelai.mc.web.req.PageReqParam;
	<#else>
import com.xiaoyeyun.mms.core.web.req.PageReqParam;
	</#if>
<#else>
	<#if isRuikelai>
import com.ruikelai.mc.web.req.ReqParam;
	<#else>
import com.xiaoyeyun.mms.core.web.req.ReqParam;
	</#if>
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author Generator
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entityName}BO  extends <#if isPage>PageReqParam<#else>ReqParam</#if>{

<#list props as ci>
	private ${ci.javaType} ${ci.property};
</#list>
}
	