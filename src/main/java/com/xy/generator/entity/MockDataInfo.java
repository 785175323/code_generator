package com.xy.generator.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.xy.generator.MockDataGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MockDataInfo {

    @Builder.Default
    private int count = 1;
    private Map<String, String> colValueMap;
    private List<Propertys> props;

    public void dataGenerator() throws SQLException {
        Connection con = null;
        PreparedStatement pstemt = null;
        List<String> columns = this.props.stream().filter(p -> !StringUtils.equals("PRI", p.getColumnKey()))
                .map(e -> e.getColumn()).collect(Collectors.toList());
        String sql = "insert into " + MockDataGenerator.DATABASE + "." + MockDataGenerator.TABLE + "("
                + StringUtils.join(columns, ",")
                + ")values"
                + StringUtils.join(toData(columns), ",") + ";";
        try {
            System.out.println(sql);
            con = DriverManager.getConnection(MockDataGenerator.URL, MockDataGenerator.NAME,
                    MockDataGenerator.PASSWORD);
            pstemt = con.prepareStatement(sql);
            pstemt.execute();
            System.out.println("执行成功");
        } finally {
            pstemt.close();
            con.close();
        }

    }

    public List<String> toData(List<String> columns) {

        List<String> values = new ArrayList<>();

        for (int i = 0; i < this.count; i++) {
            Map<String, String> map = new HashMap<>();
            this.props.stream().forEach(p -> {
                map.put(p.getColumn(), p.getValue());
            });

            values.add(
                    "(" +
                            StringUtils.join(columns.stream().map(e -> map.get(e)).collect(Collectors.toList()), ",")
                            + ")");
        }
        return values;
    }

    public void conDb() throws SQLException {
        Connection con = null;
        PreparedStatement pstemt = null;
        String sql = "select column_name,data_type,column_key from information_schema.columns where table_schema='"
                + MockDataGenerator.DATABASE + "' and table_name='" + MockDataGenerator.TABLE + "'";

        List<Propertys> columns = new ArrayList<Propertys>();
        try {
            con = DriverManager.getConnection(MockDataGenerator.URL, MockDataGenerator.NAME,
                    MockDataGenerator.PASSWORD);
            pstemt = con.prepareStatement(sql);
            ResultSet executeQuery = pstemt.executeQuery();
            while (executeQuery.next()) {
                columns.add(Propertys.createNew(MockDataGenerator.COLUMN_DEFAULT_VALUE, executeQuery.getString(1),
                        executeQuery.getString(2),
                        executeQuery.getString(3)));
            }
            this.props = columns;
        } finally {
            pstemt.close();
            con.close();
        }
    }

}
