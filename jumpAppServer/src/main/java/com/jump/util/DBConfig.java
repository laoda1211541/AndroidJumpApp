package com.jump.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:19
 * @Version 1.0
 */

/**
 * 配置类
 * @author 18443
 */

public class DBConfig {
    private static Properties p = null;
    static {
        try {
            p = new Properties();
            // 加载配置文件
            p.load(new FileInputStream("target/classes/db.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取键对应的值
     */
    public static String getValue(String key) {
        return p.get(key).toString();
    }
}
