package com.xy.generator;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.xy.generator.util.MockDataUtil;

public class MockDataGenerator {

    // 需要生成代码的数据表
    public static final String TABLE = "xy_operate_log";

    // 数据库连接信息
    public static final String URL = "jdbc:mysql://rm-2ze866m3081q2x67vo.mysql.rds.aliyuncs.com:3306/?characterEncoding=utf8&amp;connectTimeout=6000&amp;allowMultiQueries=true";
    public static final String NAME = "xiaoyeyun";
    public static final String PASSWORD = "Cjd34folmc^mCLDS#";
    public static final String DATABASE = "mms";

    public static final int DATA_COUNT = 10;
    @SuppressWarnings("serial")
    public static final Map<String, String> COLUMN_DEFAULT_VALUE = new HashMap<String, String>() {
        {
            put("store_id", "1975");
            put("business_line", "WD_LINE");
        }
    };

    public static void main(String[] args) throws SQLException {
        MockDataUtil.generator();
    }
}
