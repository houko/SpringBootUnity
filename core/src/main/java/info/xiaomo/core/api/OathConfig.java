package info.xiaomo.core.api;

import java.io.IOException;
import java.util.Properties;

/**
 * Oauth 授权
 */
public class OathConfig {

    private static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/oauth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setProps(Properties prop) {
        props = prop;
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
