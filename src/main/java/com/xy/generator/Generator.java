package com.xy.generator;

import com.xy.generator.constant.ModelEnum;
import com.xy.generator.util.CodeUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.hutool.core.util.StrUtil.toCamelCase;
import static cn.hutool.core.util.StrUtil.upperFirst;

public class Generator {

    // 项目物理路径
    public static final String PATH = "C:\\java\\project\\xiaoyeshouyin\\NewMMC";
    // 需要生成代码的数据表
    public static final String TABLE = "operator";
    // 数据表对应的po 类名
    public static final String CLASSNAME = upperFirst(toCamelCase(TABLE));
    // 项目路径
    public static final String PACKAGE_URL = "com.xiaoyeyun.mms.rbacmms";
    // mapper xml 路径
    public final static String MAPPER_XML_PATH = "/mybatis.mapper.mms.rbacmms";
    // dao.list integr 是否分页
    public static final boolean IS_PAGE = false;

    // 数据库连接信息
    public static final String URL = "jdbc:mysql://rm-2ze866m3081q2x67vo.mysql.rds.aliyuncs.com:3306/?characterEncoding=utf8&amp;connectTimeout=6000&amp;allowMultiQueries=true";
    public static final String NAME = "xiaoyeyun";
    public static final String PASSWORD = "Cjd34folmc^mCLDS#";
    public static final String DATABASE = "mtest";

    public final static boolean IS_OVERRIDE = true;
    /**
     * 标注是否是瑞客来的项目
     */
    public final static boolean IS_RUIKELAI = false;

    /**
     * 需要生成的代码模块
     * 如不需要某个模块 只需代码中注释掉 在执行main 方法即可
     */
    public final static List<ModelEnum> GENERATOR_MODEL = Stream.of(
            ModelEnum.BO,
            ModelEnum.VO,
            ModelEnum.PO,
            ModelEnum.ACT,
            ModelEnum.INTEGR,
            ModelEnum.SERVICE,
            ModelEnum.DAO,
            ModelEnum.MAPPER
    ).collect(Collectors.toList());

    public static void main(String[] args) throws SQLException {
        CodeUtil.generator();
    }
}
