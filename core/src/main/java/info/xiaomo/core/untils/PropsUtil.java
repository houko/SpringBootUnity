package info.xiaomo.core.untils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : xiaomo
 */
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

}
