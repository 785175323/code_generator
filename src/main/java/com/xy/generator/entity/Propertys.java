package com.xy.generator.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.xy.generator.util.TimeUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojianan
 *         数据表属性信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Propertys implements Serializable {

    private static final long serialVersionUID = 123124L;

    private String column;

    private String jdbcType;

    private String property;

    private String javaType;

    private String columnKey;

    private String mockValue;

    public static Propertys createNew(String column, String jdbcType, String columnKey) {

        Propertys props = new Propertys();
        props.column = column;
        props.jdbcType = jdbcType;
        if (jdbcType.equalsIgnoreCase("int")) {
            props.setJdbcType("Integer");
        } else if (jdbcType.equalsIgnoreCase("datetime")) {
            props.setJdbcType("TIMESTAMP");
        } else if (jdbcType.equalsIgnoreCase("enum")) {
            props.setJdbcType("VARCHAR");
        } else {
            props.setJdbcType(jdbcType);
        }
        props.columnKey = columnKey;
        props.property = colToProp(column);
        props.javaType = sqlTypeToJava(jdbcType);
        return props;

    }

    public static Propertys createNew(Map<String, String> valueMap, String column, String jdbcType, String columnKey) {

        Propertys props = new Propertys();
        props.column = column;
        props.jdbcType = jdbcType;
        if (jdbcType.equalsIgnoreCase("int")) {
            props.setJdbcType("Integer");
        } else if (jdbcType.equalsIgnoreCase("datetime")) {
            props.setJdbcType("TIMESTAMP");
        } else if (jdbcType.equalsIgnoreCase("enum")) {
            props.setJdbcType("VARCHAR");
        } else {
            props.setJdbcType(jdbcType);
        }

        props.mockValue = valueMap != null ? valueMap.get(column) : StringUtils.EMPTY;
        props.columnKey = columnKey;
        props.property = colToProp(column);
        props.javaType = sqlTypeToJava(jdbcType);
        return props;

    }

    static String colToProp(String field) {
        String[] fields = field.split("_");
        StringBuilder sbuilder = new StringBuilder(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            char[] cs = fields[i].toCharArray();
            cs[0] -= 32;
            sbuilder.append(String.valueOf(cs));
        }
        return sbuilder.toString();
    }

    static String sqlTypeToJava(String sqlType) {
        if (sqlType.equalsIgnoreCase("tinyint") || sqlType.equalsIgnoreCase("smallint")
                || sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("bit")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal")) {
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("enum")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")
                || sqlType.equalsIgnoreCase("timestamp")) {
            return "LocalDateTime";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "LocalDate";
        }
        return "123";
    }

    public String getValue() {

        if (StringUtils.isNoneBlank(this.mockValue)) {
            return StringUtils.equalsIgnoreCase("String", this.javaType)
                    || StringUtils.equalsIgnoreCase("LocalDate", this.javaType)
                    || StringUtils.equalsIgnoreCase("LocalDateTime", this.javaType) ? "'" + this.mockValue + "'"
                            : this.mockValue;
        }
        if (StringUtils.equalsIgnoreCase("bit", this.jdbcType))
            return String.valueOf(new java.util.Random().nextBoolean() ? 1 : 0);
        if (StringUtils.equalsIgnoreCase("String", this.javaType)) {
            return "'" + RandomStringUtils.randomAlphanumeric(5) + "'";
        }
        if (StringUtils.equalsIgnoreCase("LocalDate", this.javaType))
            return "'" + LocalDate.now().format(DateTimeFormatter.ofPattern(TimeUtils.PATTERN_YYYY_MM_DD)) + "'";
        if (StringUtils.equalsIgnoreCase("LocalDateTime", this.javaType))
            return "'"
                    + LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern(TimeUtils.PATTERN_YYYY_MM_DD_HH_MM_SS_DASH))
                    + "'";

        return String.valueOf(new Random().nextInt(4));
    }

}
