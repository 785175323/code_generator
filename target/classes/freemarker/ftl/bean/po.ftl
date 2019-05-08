package ${config.poUrl};

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import lombok.Data;
import ${config.boUrl}.${entityName}BO;

/**
* @author Generator
*/
@Data
public class ${entityName}  {

<#list props as ci>
	private ${ci.javaType} ${ci.property};
</#list>


    public static ${entityName} createNew(${entityName}BO param){
    
		${entityName} ${entityName?uncap_first} = new ${entityName}();
		BeanUtils.copyProperties(param, ${entityName?uncap_first});
		return ${entityName?uncap_first};
	}
}
	