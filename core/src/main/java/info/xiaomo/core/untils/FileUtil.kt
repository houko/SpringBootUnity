package info.xiaomo.core.untils

import info.xiaomo.core.constant.FileConst
import info.xiaomo.core.constant.SymbolConst
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.experimental.and

/**
 * 此类中封装一些常用的文件操作。
 * 所有方法都是静态方法，不需要生成此类的实例，
 * 为避免生成此类的实例，构造方法被申明为private类型的。
 *
 * @author : xiaomo
 * @since 1.0
 */

object FileUtil {
    /**
     * Buffer size when reading from input stream.
     *
     * @since ostermillerutils 1.00.00
     */
    private val BUFFER_SIZE = 1024

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     * **目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。**
     *
     * @param file 需要修改最后访问时间的文件。
     * @since 1.0
     */
    fun touch(file: File?) {
        val currentTime = System.currentTimeMillis()
        if (file != null) {
            if (!file.exists()) {
                System.err.println("file not found:" + file.name)
                System.err.println("Create a new file:" + file.name)
                try {
                    if (file.createNewFile()) {
                        println("Succeeded!")
                    } else {
                        System.err.println("Create file failed!")
                    }
                } catch (e: IOException) {
                    System.err.println("Create file failed!")
                    e.printStackTrace()
                }

            }
        }
        val result = file!!.setLastModified(currentTime)
        if (!result) {
            System.err.println("touch failed: " + file.name)
        }
    }

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     * **目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。**
     *
     * @param fileName 需要修改最后访问时间的文件的文件名。
     * @since 1.0
     */
    fun touch(fileName: String) {
        val file = File(fileName)
        touch(file)
    }

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     * **目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。**
     *
     * @param files 需要修改最后访问时间的文件数组。
     * @since 1.0
     */
    fun touch(files: Array<File?>) {
        for (file in files) {
            touch(file)
        }
    }

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     * **目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。**
     *
     * @param fileNames 需要修改最后访问时间的文件名数组。
     * @since 1.0
     */
    @JvmStatic
    fun touch(fileNames: Array<String>) {
        val files = arrayOfNulls<File>(fileNames.size)
        for (i in fileNames.indices) {
            files[i] = File(fileNames[i])
        }
        touch(files)
    }

    /**
     * 判断指定的文件是否存在。
     *
     * @param fileName 要判断的文件的文件名
     * @return 存在时返回true，否则返回false。
     * @since 1.0
     */
    fun isFileExist(fileName: String): Boolean {
        return File(fileName).isFile
    }

    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录。
     * **注意：可能会在返回false的时候创建部分父目录。**
     *
     * @param file 要创建的目录
     * @return 完全创建成功时返回true，否则返回false。
     * @since 1.0
     */
    fun makeDirectory(file: File): Boolean {
        val parent = file.parentFile
        return parent != null && parent.mkdirs()
    }

    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录。
     * **注意：可能会在返回false的时候创建部分父目录。**
     *
     * @param fileName 要创建的目录的目录名
     * @return 完全创建成功时返回true，否则返回false。
     * @since 1.0
     */
    fun makeDirectory(fileName: String): Boolean {
        val file = File(fileName)
        return makeDirectory(file)
    }

    /**
     * 清空指定目录中的文件。
     * 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
     * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
     *
     * @param directory 要清空的目录
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false.
     * @since 1.0
     */
    fun emptyDirectory(directory: File): Boolean {
        var result = true
        val entries = directory.listFiles()
        for (entry in entries ?: arrayOfNulls(0)) {
            if (!entry.delete()) {
                result = false
            }
        }
        return result
    }

    /**
     * 清空指定目录中的文件。
     * 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
     * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
     *
     * @param directoryName 要清空的目录的目录名
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false。
     * @since 1.0
     */
    fun emptyDirectory(directoryName: String): Boolean {
        val dir = File(directoryName)
        return emptyDirectory(dir)
    }

    /**
     * 删除指定目录及其中的所有内容。
     *
     * @param dirName 要删除的目录的目录名
     * @return 删除成功时返回true，否则返回false。
     * @since 1.0
     */
    fun deleteDirectory(dirName: String): Boolean {
        return deleteDirectory(File(dirName))
    }

    /**
     * 删除指定目录及其中的所有内容。
     *
     * @param dir 要删除的目录
     * @return 删除成功时返回true，否则返回false。
     * @since 1.0
     */
    fun deleteDirectory(dir: File?): Boolean {
        if (dir == null || !dir.isDirectory) {
            throw IllegalArgumentException("Argument " + dir +
                    " is not a directory. ")
        }

        val entries = dir.listFiles()
        val sz = entries?.size ?: 0

        for (entry in entries ?: arrayOfNulls(0)) {
            if (entry.isDirectory) {
                if (!deleteDirectory(entry)) {
                    return false
                }
            } else {
                if (!entry.delete()) {
                    return false
                }
            }
        }

        return dir.delete()
    }


    /**
     * 列出目录中的所有内容，包括其子目录中的内容。
     *
     * @param file   要列出的目录
     * @param filter 过滤器
     * @return 目录内容的文件数组。
     * @since 1.0
     */
    fun listAll(file: File,
                filter: javax.swing.filechooser.FileFilter): Array<File?>? {
        val arrayList = ArrayList<File>()
        val files: Array<File?>
        if (!file.exists() || file.isFile) {
            return null
        }
        list(arrayList, file, filter)
        files = arrayOfNulls(arrayList.size)
        arrayList.toTypedArray()
        return files
    }


    /**
     * 返回文件的URL地址。
     *
     * @param file 文件
     * @return 文件对应的的URL地址
     * @throws MalformedURLException
     * @since 1.0
     */
    @Deprecated("在实现的时候没有注意到File类本身带一个toURL方法将文件路径转换为URL。\n" +
            "      请使用File.toURL方法。")
    @Throws(MalformedURLException::class)
    fun getURL(file: File): URL {
        val fileURL = "file:/" + file.absolutePath
        return URL(fileURL)
    }

    /**
     * 从文件路径得到文件名。
     *
     * @param filePath 文件的路径，可以是相对路径也可以是绝对路径
     * @return 对应的文件名
     * @since 1.0
     */
    fun getFileName(filePath: String): String {
        val file = File(filePath)
        return file.name
    }

    /**
     * 从文件名得到文件绝对路径。
     *
     * @param fileName 文件名
     * @return 对应的文件路径
     * @since 1.0
     */
    fun getFilePath(fileName: String): String {
        val file = File(fileName)
        return file.absolutePath
    }

    /**
     * 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
     * 其实就是将路径中的"\"全部换为"/"，因为在某些情况下我们转换为这种方式比较方便，
     * 某中程度上说"/"比"\"更适合作为路径分隔符，而且DOS/Windows也将它当作路径分隔符。
     *
     * @param filePath 转换前的路径
     * @return 转换后的路径
     * @since 1.0
     */
    fun toUNIXpath(filePath: String): String {
        return filePath.replace('\\', '/')
    }

    /**
     * 从文件名得到UNIX风格的文件绝对路径。
     *
     * @param fileName 文件名
     * @return 对应的UNIX风格的文件路径
     * @see .toUNIXpath
     * @since 1.0
     */
    fun getUNIXfilePath(fileName: String): String {
        val file = File(fileName)
        return toUNIXpath(file.absolutePath)
    }

    /**
     * 得到文件的类型。
     * 实际上就是得到文件名中最后一个“.”后面的部分。
     *
     * @param fileName 文件名
     * @return 文件名中的类型部分
     * @since 1.0
     */
    fun getFileType(fileName: String): String {
        val point = fileName.lastIndexOf('.')
        val length = fileName.length
        return if (point == -1 || point == length - 1) {
            ""
        } else {
            fileName.substring(point + 1, length)
        }
    }

    /**
     * 得到文件的类型。
     * 实际上就是得到文件名中最后一个“.”后面的部分。
     *
     * @param file 文件
     * @return 文件名中的类型部分
     * @since 1.0
     */
    fun getFileType(file: File): String {
        return getFileType(file.name)
    }

    /**
     * 得到文件的名字部分。
     * 实际上就是路径中的最后一个路径分隔符后的部分。
     *
     * @param fileName 文件名
     * @return 文件名中的名字部分
     * @since 1.0
     */
    fun getNamePart(fileName: String): String {
        val point = getPathLsatIndex(fileName)
        val length = fileName.length
        if (point == -1) {
            return fileName
        } else if (point == length - 1) {
            val secondPoint = getPathLsatIndex(fileName, point - 1)
            return if (secondPoint == -1) {
                if (length == 1) {
                    fileName
                } else {
                    fileName.substring(0, point)
                }
            } else {
                fileName.substring(secondPoint + 1, point)
            }
        } else {
            return fileName.substring(point + 1)
        }
    }

    /**
     * 得到文件名中的父路径部分。
     * 对两种路径分隔符都有效。
     * 不存在时返回""。
     * 如果文件名是以路径分隔符结尾的则不考虑该分隔符，例如"/path/"返回""。
     *
     * @param fileName 文件名
     * @return 父路径，不存在或者已经是父目录时返回""
     * @since 1.0
     */
    fun getPathPart(fileName: String): String {
        val point = getPathLsatIndex(fileName)
        val length = fileName.length
        if (point == -1) {
            return ""
        } else if (point == length - 1) {
            val secondPoint = getPathLsatIndex(fileName, point - 1)
            return if (secondPoint == -1) {
                ""
            } else {
                fileName.substring(0, secondPoint)
            }
        } else {
            return fileName.substring(0, point)
        }
    }

    /**
     * 得到路径分隔符在文件路径中首次出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中首次出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    fun getPathIndex(fileName: String): Int {
        var point = fileName.indexOf('/')
        if (point == -1) {
            point = fileName.indexOf('\\')
        }
        return point
    }

    /**
     * 得到路径分隔符在文件路径中指定位置后首次出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的位置
     * @return 路径分隔符在路径中指定位置后首次出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    fun getPathIndex(fileName: String, fromIndex: Int): Int {
        var point = fileName.indexOf('/', fromIndex)
        if (point == -1) {
            point = fileName.indexOf('\\', fromIndex)
        }
        return point
    }

    /**
     * 得到路径分隔符在文件路径中最后出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    fun getPathLsatIndex(fileName: String): Int {
        var point = fileName.lastIndexOf('/')
        if (point == -1) {
            point = fileName.lastIndexOf('\\')
        }
        return point
    }

    /**
     * 得到路径分隔符在文件路径中指定位置前最后出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的位置
     * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    fun getPathLsatIndex(fileName: String, fromIndex: Int): Int {
        var point = fileName.lastIndexOf('/', fromIndex)
        if (point == -1) {
            point = fileName.lastIndexOf('\\', fromIndex)
        }
        return point
    }

    /**
     * 将文件名中的类型部分去掉。
     *
     * @param filename 文件名
     * @return 去掉类型部分的结果
     * @since 1.0
     */
    fun trimType(filename: String): String {
        val index = filename.lastIndexOf(".")
        return if (index != -1) {
            filename.substring(0, index)
        } else {
            filename
        }
    }

    /**
     * 得到相对路径。
     * 文件名不是目录名的子节点时返回文件名。
     *
     * @param pathName 目录名
     * @param fileName 文件名
     * @return 得到文件名相对于目录名的相对路径，目录下不存在该文件时返回文件名
     * @since 1.0
     */
    fun getSubpath(pathName: String, fileName: String): String {
        val index = fileName.indexOf(pathName)
        return if (index != -1) {
            fileName.substring(index + pathName.length + 1)
        } else {
            fileName
        }
    }

    /**
     * 检查给定目录的存在性
     * 保证指定的路径可用，如果指定的路径不存在，那么建立该路径，可以为多级路径
     *
     * @param path
     * @return 真假值
     * @since 1.0
     */
    fun pathValidate(path: String): Boolean {
        val arraypath = path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var tmppath = ""
        for (anArraypath in arraypath) {
            tmppath += "/" + anArraypath
            val d = File(tmppath.substring(1))
            //检查Sub目录是否存在
            if (!d.exists()) {
                println(tmppath.substring(1))
                if (!d.mkdir()) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * 根据内容生成文件
     *
     * @param path          要生成文件的绝对路径，
     * @param modulecontent 文件的内容。
     * @return 真假值
     * @since 1.0
     */
    @Throws(IOException::class)
    fun genModuleTpl(path: String, modulecontent: String): Boolean {
        var path = path

        path = getUNIXfilePath(path)
        val patharray = path.split("\\/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var modulepath = ""
        for (i in 0 until patharray.size - 1) {
            modulepath += "/" + patharray[i]
        }
        val d = File(modulepath.substring(1))
        if (!d.exists()) {
            if (!pathValidate(modulepath.substring(1))) {
                return false
            }
        }
        //建立FileWriter对象，并实例化fw
        val fw = FileWriter(path)
        //将字符串写入文件
        fw.write(modulecontent)
        fw.close()
        return true
    }

    /**
     * 获取图片文件的扩展名（发布系统专用）
     *
     * @param picPath 为图片名称加上前面的路径不包括扩展名
     * @return 图片的扩展名
     * @since 1.0
     */
    fun getPicExtendName(picPath: String): String {
        var picPath = picPath
        picPath = getUNIXfilePath(picPath)
        var picExtend = ""
        val gif = ".gif"
        if (isFileExist(picPath + gif)) {
            picExtend = gif
        }
        val jpeg = ".jpeg"
        if (isFileExist(picPath + jpeg)) {
            picExtend = jpeg
        }
        val jpg = ".jpg"
        if (isFileExist(picPath + jpg)) {
            picExtend = jpg
        }
        val png = ".png"
        if (isFileExist(picPath + png)) {
            picExtend = png
        }
        //返回图片扩展名
        return picExtend
    }

    @Throws(Exception::class)
    fun copyFile(`in`: File, out: File): Boolean {
        try {
            val fis = FileInputStream(`in`)
            val fos = FileOutputStream(out)
            val buf = ByteArray(1024)
            var i = 0
            while ((fis.read(buf)) != -1) {
                fos.write(buf, 0, i)
            }
            fis.close()
            fos.close()
            return true
        } catch (ie: IOException) {
            ie.printStackTrace()
            return false
        }

    }

    @Throws(Exception::class)
    fun copyFile(infile: String, outfile: String): Boolean {
        try {
            val `in` = File(infile)
            val out = File(outfile)
            return copyFile(`in`, out)
        } catch (ie: IOException) {
            ie.printStackTrace()
            return false
        }

    }

    /**
     * Copy the data from the input stream to the output stream.
     *
     * @param in  data source
     * @param out data destination
     * @throws IOException in an input or output error occurs
     * @since orientals 1.00.00
     */
    @Throws(IOException::class)
    private fun copy(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(BUFFER_SIZE)
        val read = 0
        while ((`in`.read(buffer)) != -1) {
            out.write(buffer, 0, read)
        }
    }

    /**
     * 将目录中的内容添加到列表。
     *
     * @param list   文件列表
     * @param filter 过滤器
     * @param file   目录
     */
    private fun list(list: MutableList<File>, file: File,
                     filter: javax.swing.filechooser.FileFilter) {
        if (filter.accept(file)) {
            list.add(file)
            if (file.isFile) {
                return
            }
        }
        if (file.isDirectory) {
            val files = file.listFiles()
            for (file1 in files ?: arrayOfNulls(0)) {
                list(list, file1, filter)
            }
        }

    }


    /**
     * 文件上传
     *
     * @param file  file
     * @param email email
     * @return fileUrl
     */
    fun upload(file: MultipartFile?, email: String): String {
        var savePath = ""
        var filename = ""
        if (file != null && !file.isEmpty) {
            // 获取图片的文件名
            val fileName = file.originalFilename
            // 重新定义图片名字
            filename = FileUtil.getNewFileName(fileName, email)
            //上传服务器上 新文件路径
            val os = System.getProperty("os.name").toLowerCase()
            try {
                // 判断服务器上 文件夹是否存在
                val newFile = File(savePath)
                if (!newFile.exists()) {
                    val result = newFile.mkdirs()
                    println(result)
                }
                savePath = savePath + filename
                val out = FileOutputStream(savePath)
                // 写入文件
                out.write(file.bytes)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return filename
    }


    @Throws(Exception::class)
    fun readStream(inStream: InputStream): ByteArray {
        val outsStream = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var len = -1
        while ((inStream.read(buffer)) != -1) {
            outsStream.write(buffer, 0, len)
        }
        outsStream.close()
        inStream.close()
        return outsStream.toByteArray()
    }

    @Throws(IOException::class)
    fun readFileImage(file: File): ByteArray {
        val bufferedInputStream = BufferedInputStream(
                FileInputStream(file))
        val len = bufferedInputStream.available()
        var bytes: ByteArray? = ByteArray(len)
        val r = bufferedInputStream.read(bytes!!)
        if (len != r) {
            bytes = null
            throw IOException("读取文件不正确")
        }
        bufferedInputStream.close()
        return bytes
    }

    @Throws(IOException::class)
    fun readFileImage(filename: String): ByteArray {
        val bufferedInputStream = BufferedInputStream(
                FileInputStream(filename))
        val len = bufferedInputStream.available()
        var bytes: ByteArray? = ByteArray(len)
        val r = bufferedInputStream.read(bytes!!)
        if (len != r) {
            bytes = null
            throw IOException("读取文件不正确")
        }
        bufferedInputStream.close()
        return bytes
    }


    /**
     * 读取返回的信息
     *
     * @param in
     * @return
     */
    private fun getData(`in`: InputStream): String {
        val result = ""
        val sb = StringBuilder()
        val br = BufferedReader(InputStreamReader(`in`))
        var line = ""
        try {
            while ((br.readLine()) != null) {
                // result = result + line;
                sb.append(line)
            }
            br.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                br.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return sb.toString()
    }


    private fun bytesToHexString(src: ByteArray?): String? {
        val stringBuilder = StringBuilder()
        if (src == null || src.size <= 0) {
            return null
        }
        for (aSrc in src) {
            val v = aSrc and 0xFF.toByte()
            val hv = Integer.toHexString(v.toInt())
            if (hv.length < 2) {
                stringBuilder.append(0)
            }
            stringBuilder.append(hv)
        }
        return stringBuilder.toString()
    }


    /**
     * 获取文件内容
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun getFile(filePath: String): String? {
        val b = ByteArray(28)
        var inputStream: InputStream? = null
        try {
            inputStream = FileInputStream(filePath)
            inputStream.read(b, 0, 28)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return bytesToHexString(b)
    }

    /**
     * @param filePath filePath
     * @return FileConst
     * @throws IOException
     */
    @Throws(IOException::class)
    fun getType(filePath: String): FileConst? {
        var fileHead = getFile(filePath)
        if (fileHead == null || fileHead.length == 0) {
            return null
        }
        fileHead = fileHead.toUpperCase()
        val fileConsts = FileConst.values()
        for (type in fileConsts) {
            if (fileHead.startsWith(type.value)) {
                return type
            }
        }
        return null
    }

    fun getNewFileName(fileName: String, email: String): String {
        val fileType = FileUtil.getFileType(fileName)
        val newName = email.split(SymbolConst.AT.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        return (TimeUtil.getDateNow(TimeUtil.DATE_FORMAT_STRING) + SymbolConst.HENGXIAN + newName + SymbolConst.DIAN + fileType).toLowerCase()
    }

    fun isImage(imageName: String): Boolean {
        val fileType = FileUtil.getFileType(imageName)
        return !("bmp" == fileType || "BMP" == fileType
                || "jpg" == fileType || "JPG" == fileType
                || "jpeg" == fileType || "JPEG" == fileType
                || "git" == fileType || "GIF" == fileType
                || "png" == fileType || "PNG" == fileType)
    }

}
