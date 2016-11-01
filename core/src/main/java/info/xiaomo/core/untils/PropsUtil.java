package info.xiaomo.core.untils;

import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropsUtil {

    /**
     * 构造函数
     * 找到数据源，并用这个数据源创建连接
     */
    private PropsUtil() {

    }

    public static Properties getProperties(String url) {
        Properties properties = null;
        try {
            InputStream fs = new FileInputStream(url);
            properties = new Properties();
            properties.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Map<String,Object> getSystem(){
        Map<String, Object> map = new HashMap<>();
        Properties properties = System.getProperties();
        map.put("javaVersion", properties.getProperty("java.version"));//运行时环境版本
        map.put("javaVendor", properties.getProperty("java.vendor"));//Java 运行时环境供应商
        map.put("javaVendorUrl", properties.getProperty("java.vendor.url"));// Java 供应商的 URL
        map.put("javaHome", properties.getProperty("java.home"));//Java 安装目录
        map.put("javaClassVersion", properties.getProperty("java.class.version"));//  Java 类格式版本号
        map.put("osName", properties.getProperty("os.name"));//  操作系统的名称
        map.put("osVersion", properties.getProperty("os.version"));//  操作系统的版本
        map.put("userName", properties.getProperty("user.name"));//  用户的账户名称
        map.put("useRHome", properties.getProperty("user.home"));//  用户的主目录
        map.put("userDir", properties.getProperty("user.dir"));//  用户的当前工作目录
        return map;
    }


    public static void main(String[] args) {
        Properties properties = getProperties("E:\\oscchina\\xiaomo-blog-java\\web\\src\\main\\resources\\config\\application.properties");
        System.out.println(JSON.toJSONString(properties));
    }

}
