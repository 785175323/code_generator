package com.xy.generator.util;

import java.sql.SQLException;

import com.xy.generator.MockDataGenerator;
import com.xy.generator.entity.MockDataInfo;

public class MockDataUtil {

    public static void generator() throws SQLException {
        MockDataInfo info = MockDataInfo.builder().count(MockDataGenerator.DATA_COUNT)
                .colValueMap(MockDataGenerator.COLUMN_DEFAULT_VALUE).build();
        info.conDb();
        info.dataGenerator();
    }
}
