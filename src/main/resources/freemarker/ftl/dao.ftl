package ${config.daoUrl};

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ${config.poUrl}.${entityName};
import ${config.boUrl}.${entityName}BO;

/**
* @author Generator
*/
public interface ${entityName}Dao {

	int insert(${entityName} ${entityName?uncap_first});
	
	${entityName} findBy${idParamName}(@Param("${idParamName?uncap_first}")${idType} ${idParamName?uncap_first});
	
    int update(${entityName} ${entityName?uncap_first});
	
	int delete(@Param("${idParamName?uncap_first}")${idType} ${idParamName?uncap_first});
	
	List<${entityName}> list(@Param("p") ${entityName}BO param);
	
	int count(@Param("p") ${entityName}BO param);

}
	