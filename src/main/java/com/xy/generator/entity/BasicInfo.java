package com.xy.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cn.hutool.core.util.StrUtil.toCamelCase;
import static cn.hutool.core.util.StrUtil.upperFirst;
import static com.xy.generator.Generator.*;

/**
 * @author zhaojianan
 * 基本配置信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicInfo implements Serializable {
    private static final long serialVersionUID = 123123L;

    private String dbUrl;

    private String dbName;

    private String dbPassword;

    private String database;

    private String table;

    private String entityName;

    private List<Propertys> props;

    private ModelConfig config;

    private String idType;

    private String idParamName;

    private String idColumn;

    private Boolean isPage;

    private Boolean isRuikelai = false;

    public static BasicInfo create() throws SQLException {
        BasicInfo info = BasicInfo.builder().dbName(NAME).dbUrl(URL).dbPassword(PASSWORD)
                                  .database(DATABASE).table(TABLE).isPage(IS_PAGE)
                                  .isRuikelai(IS_RUIKELAI)
                                  .build();
        return info.conDb(PACKAGE_URL, CLASSNAME);
    }

    public BasicInfo conDb(String baseUrl, String entityName) throws SQLException {

        Connection con = null;
        PreparedStatement pstemt = null;
        String sql = "select column_name,data_type,column_key from information_schema.columns where table_schema='"
                + this.getDatabase() + "' and table_name='" + this.table + "'";

        List<Propertys> columns = new ArrayList<>();
        try {
            con = DriverManager.getConnection(this.dbUrl, this.dbName, this.dbPassword);
            pstemt = con.prepareStatement(sql);
            ResultSet executeQuery = pstemt.executeQuery();
            while (executeQuery.next()) {
                columns.add(Propertys.createNew(executeQuery.getString(1), executeQuery.getString(2),
                        executeQuery.getString(3)));
            }
            this.props = columns;
            Optional<Propertys> op = columns.stream().filter(c -> StringUtils.equals(c.getColumnKey(), "PRI"))
                                            .findAny();
            this.idType = op != null ? op.get().getJavaType() : null;
            this.idParamName = op != null ? upperFirst(toCamelCase(op.get().getColumn())) : null;
            this.idColumn = op != null ? op.get().getColumn() : null;
            this.setConfig(ModelConfig.createNew(baseUrl));
            this.entityName = entityName;
            return this;
        } catch (Exception e) {
            throw new RuntimeException("自动生成实体类错误：" + e.getMessage());
        } finally {
            pstemt.close();
            con.close();
        }
    }

}
