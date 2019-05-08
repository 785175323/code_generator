package ${config.serviceUrl};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${config.poUrl}.${entityName};
import ${config.boUrl}.${entityName}BO;
import ${config.daoUrl}.${entityName}Dao;

/**
* @author Generator
*/
@Service
public class ${entityName}Service {
	
	@Autowired
    private ${entityName}Dao ${entityName?uncap_first}Dao;
    
	public int insert(${entityName} ${entityName?uncap_first}){
		return ${entityName?uncap_first}Dao.insert(${entityName?uncap_first});
	}
    
	public ${entityName} findBy${idParamName}(${idType} ${idParamName?uncap_first}){
		return ${entityName?uncap_first}Dao.findBy${idParamName}(${idParamName?uncap_first});
	}
	
	public List<${entityName}> list(${entityName}BO param){
		return ${entityName?uncap_first}Dao.list(param);
	}
	
	public int count(${entityName}BO param){
		return ${entityName?uncap_first}Dao.count(param);
	}
	
	
	public int delete(${idType} ${idParamName?uncap_first}){
		return ${entityName?uncap_first}Dao.delete(${idParamName?uncap_first});
	}
	
	public int update(${entityName} ${entityName?uncap_first}){
		return ${entityName?uncap_first}Dao.update(${entityName?uncap_first});
	}
}