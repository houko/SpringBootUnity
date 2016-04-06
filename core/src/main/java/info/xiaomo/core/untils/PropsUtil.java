package info.xiaomo.core.untils;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropsUtil {

    /**
     * 构造函数
     * 找到数据源，并用这个数据源创建连接
     */
    public PropsUtil() {

    }

    public String getPropsFilePath() {
        String filePath = this.getClass().getResource("/").getPath();
        filePath = filePath.substring(0, filePath.indexOf("classes") - 1) + "/destinations.properties";
        return filePath;
    }

    public InputStream getPropsIS() {
        return this.getClass().getResourceAsStream("/destinations.properties");
    }

    /**
     * 读取属性文件中的属性值
     */
    public String readSingleProps(String attr) {
        String retValue;
        Properties props = new Properties();
        try {
            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();

            retValue = props.getProperty(attr);
        } catch (Exception e) {
            return "";
        }
        return retValue;
    }

    /**
     * 读取属性文件中的属性值
     */
    public HashMap readAllProps() {
        HashMap h = new HashMap();
        Properties props = new Properties();

        try {
            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();
            Enumeration er = props.propertyNames();
            while (er.hasMoreElements()) {
                String paramName = (String) er.nextElement();
                h.put(paramName, props.getProperty(paramName));
            }
        } catch (Exception e) {
            return new HashMap();
        }
        return h;
    }
}
