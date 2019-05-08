package ${config.voUrl};

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import ${config.poUrl}.${entityName};
import lombok.Data;

/**
* @author Generator
*/
@Data
public class ${entityName}VO  {

<#list props as ci>
	private ${ci.javaType} ${ci.property};
</#list>

	public static ${entityName}VO createNew(${entityName} ${entityName?uncap_first}){
		${entityName}VO vo = new ${entityName}VO();
		BeanUtils.copyProperties(${entityName?uncap_first}, vo);
		return vo;
	}
}
	