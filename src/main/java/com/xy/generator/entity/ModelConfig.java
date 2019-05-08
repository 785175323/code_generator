package com.xy.generator.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.xy.generator.Generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojianan
 *         生成模块配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 520144624612381275L;

    private String poUrl;
    private String voUrl;
    private String boUrl;

    private String daoUrl;
    private String mapperUrl;
    private String serviceUrl;
    private String controllerUrl;
    private String integrUrl;

    public static ModelConfig createNew(String baseUrl) {
        return ModelConfig.builder()
                .poUrl(baseUrl + ".bean.po")
                .boUrl(baseUrl + ".bean.bo")
                .voUrl(baseUrl + ".bean.vo")
                .daoUrl(baseUrl + ".dao")
                .mapperUrl(Generator.MAPPER_XML_PATH + "/" + StringUtils.uncapitalize(
                        StringUtils.right(baseUrl, baseUrl.length() - StringUtils.lastIndexOf(baseUrl, ".") - 1)))
                .serviceUrl(baseUrl + ".service")
                .integrUrl(baseUrl + ".integr")
                .controllerUrl(baseUrl + ".act").build();

    }

}
