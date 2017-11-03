package info.xiaomo.core.untils

import java.io.File
import java.io.FileOutputStream
import java.net.URL

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/26 13:25
 */
object DownUtil {

    @Throws(Exception::class)
    fun download(urlString: String) {
        val file = File(urlString)
        val filename = file.name
        // 构造URL
        val url = URL(urlString)
        // 打开连接
        val con = url.openConnection()
        // 输入流
        val `is` = con.getInputStream()
        // 1K的数据缓冲
        val bs = ByteArray(1024)
        // 读取到的数据长度
        val len = 0
        // 输出的文件流
        val os = FileOutputStream(filename)
        // 开始读取
        while ((`is`.read(bs)) != -1) {
            os.write(bs, 0, len)
        }
        // 完毕，关闭所有链接
        os.close()
        `is`.close()
    }


    /**
     * 下载图片
     *
     * @param urlString url
     * @param filePath  存储路径  D:\MIR\config\data\
     */
    @Throws(Exception::class)
    fun download(urlString: String, filePath: String) {
        val file = File(urlString)
        val filename = file.name
        // 构造URL
        val url = URL(urlString)
        // 打开连接
        val con = url.openConnection()
        // 输入流
        val `is` = con.getInputStream()
        // 1K的数据缓冲
        val bs = ByteArray(1024)
        // 读取到的数据长度
        var len = 0
        // 输出的文件流

        val output = File(filePath)
        if (!output.exists()) {
            val res = output.mkdir()
            if (res) {
                println("目录创建成功" + filePath)
            }
        }

        val os = FileOutputStream(filePath + filename)
        // 开始读取
        while ((`is`.read(bs)) != -1) {
            os.write(bs, 0, len)
        }
        // 完毕，关闭所有链接
        os.close()
        `is`.close()
    }

}
