package info.xiaomo.core.untils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/26 13:25
 */
@Slf4j
public class DownUtil {

    public static void download(String urlString) throws Exception {
        File file = new File(urlString);
        String filename = file.getName();
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }


    /**
     * 下载图片
     *
     * @param urlString url
     * @param filePath  存储路径  D:\MIR\config\data\
     */
    public static void download(String urlString, String filePath) throws Exception {
        File file = new File(urlString);
        String filename = file.getName();
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流

        File output = new File(filePath);
        if (!output.exists()) {
            boolean res = output.mkdir();
            if (res) {
                log.debug("{} 目录创建成功", filePath);
            }
        }

        OutputStream os = new FileOutputStream(filePath + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}
