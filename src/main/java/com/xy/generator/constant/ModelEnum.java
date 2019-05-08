package com.xy.generator.constant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.xy.generator.entity.ModelConfig;

public enum ModelEnum {

    DAO("Dao", "dao.ftl", "Dao.java", "/src/main/java/"),

    ACT("Act", "controller.ftl", "Act.java", "/src/main/java/"),

    SERVICE("Service", "service.ftl", "Service.java", "/src/main/java/"),

    INTEGR("Integr", "integr.ftl", "Integr.java", "/src/main/java/"),

    MAPPER("Mapper", "mapper.ftl", "Dao.xml", "/src/main/resources/"),

    BO("BO", "/bean/bo.ftl", "BO.java", "/src/main/java/"),

    PO("", "/bean/po.ftl", ".java", "/src/main/java/"),

    VO("VO", "/bean/vo.ftl", "VO.java", "/src/main/java/");

    public final String name;
    public final String template;
    public final String prefix;
    public final String path;

    ModelEnum(String name, String template, String prefix, String path) {
        this.name = name;
        this.template = template;
        this.prefix = prefix;
        this.path = path;
    }

    public final static List<ModelEnum> AVAILIABLE = Stream.of(
            ModelEnum.DAO,
            ModelEnum.ACT,
            ModelEnum.SERVICE,
            ModelEnum.INTEGR,
            ModelEnum.MAPPER,
            ModelEnum.BO,
            ModelEnum.VO,
            ModelEnum.PO).collect(Collectors.toList());

    public String baseUrl(ModelConfig config) {
        if (StringUtils.equals(this.name(), DAO.name())) return config.getDaoUrl();
        if (StringUtils.equals(this.name(), ACT.name())) return config.getControllerUrl();
        if (StringUtils.equals(this.name(), SERVICE.name())) return config.getServiceUrl();
        if (StringUtils.equals(this.name(), INTEGR.name())) return config.getIntegrUrl();
        if (StringUtils.equals(this.name(), MAPPER.name())) return config.getMapperUrl();
        if (StringUtils.equals(this.name(), BO.name())) return config.getBoUrl();
        if (StringUtils.equals(this.name(), PO.name())) return config.getPoUrl();
        if (StringUtils.equals(this.name(), VO.name())) return config.getVoUrl();
        return StringUtils.EMPTY;
    }

}
