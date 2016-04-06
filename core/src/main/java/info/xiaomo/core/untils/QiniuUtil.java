package info.xiaomo.core.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 七牛云储存的帮助类
 *
 * @author l.cm
 */
public class QiniuUtil {

    public static final String IMG_TYPE = ".jpg|.jepg|.gif|.png|.bmp";
    public static final String ALL_TYPE = ".jpg|.jepg|.gif|.png|.bmp|.gz|.rar|.zip|.pdf|.txt|.swf|.wmv";

    /**
     * 获取文件类型
     *
     * @return String    返回类型
     */
    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
    }

    /**
     * 检查文件类型
     *
     * @return boolean    返回类型
     */
    public static boolean checkFileType(String fileName, boolean isImg) {
        String fileType = getFileType(fileName);
        if (isImg) {
            return !IMG_TYPE.contains(fileType.toLowerCase());
        } else {
            return !ALL_TYPE.contains(fileType.toLowerCase());
        }
    }
}
