package info.xiaomo.core.untils

import com.alibaba.fastjson.JSON
import org.dom4j.*
import org.dom4j.io.OutputFormat
import org.dom4j.io.SAXReader
import org.dom4j.io.XMLWriter

import java.io.*
import java.net.URL

/**
 * @author : xiaomo
 */
object XmlUtil {
    /**
     * 解析url xml文档
     *
     * @param url
     */
    @Throws(DocumentException::class)
    fun parse(url: URL): Document {
        val reader = SAXReader()
        return reader.read(url)
    }

    /**
     * 遍历解析文档
     */
    fun treeWalk(document: Document) {
        treeWalk(document.rootElement)
    }

    /**
     * 遍历解析元素
     */
    fun treeWalk(element: Element) {
        var i = 0
        val size = element.nodeCount()
        while (i < size) {
            val node = element.node(i)
            if (node is Element) {
                treeWalk(node)
            }
            i++

        }
    }

    /**
     * 解析文件，获得根元素
     */
    @Throws(Exception::class)
    fun parse(xmlPath: String, encoding: String): Element {
        //文件是否存在
        val file = File(xmlPath)
        if (!file.exists()) {
            throw Exception("找不到xml文件：" + xmlPath)
        }

        //解析
        val reader = SAXReader(false)
        val doc = reader.read(FileInputStream(file), encoding)
        return doc.rootElement
    }

    /**
     * 保存文档
     *
     * @throws Exception
     */
    @Throws(Exception::class)
    fun save(doc: Document, xmlPath: String, encoding: String) {
        val format = OutputFormat.createPrettyPrint()
        format.encoding = encoding
        val writer = XMLWriter(OutputStreamWriter(FileOutputStream(xmlPath), encoding), format)
        writer.write(doc)
        writer.flush()
        writer.close()
    }

    /**
     * 修改xml某节点的值
     *
     * @param inputXml      原xml文件
     * @param nodes         要修改的节点
     * @param attributeName 属性名称
     * @param value         新值
     * @param outXml        输出文件路径及文件名 如果输出文件为null，则默认为原xml文件
     */
    fun modifyDocument(inputXml: File, nodes: String, attributeName: String, value: String, outXml: String?) {
        try {
            val saxReader = SAXReader()
            val document = saxReader.read(inputXml)
            val list = document.selectNodes(nodes)
            for (aList in list) {
                val attribute = aList as Attribute
                if (attribute.name == attributeName) {
                    attribute.value = value
                }
            }
            val output: XMLWriter
            //指定输出文件
            if (outXml != null) {
                output = XMLWriter(FileWriter(File(outXml)))
            } else { //输出文件为原文件
                output = XMLWriter(FileWriter(inputXml))
            }
            output.write(document)
            output.close()
        } catch (e: DocumentException) {
            println(e.message)
        } catch (e: IOException) {
            println(e.message)
        }

    }

    /**
     * xml转换为字符串
     *
     * @param doc
     * @param encoding
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun toString(doc: Document, encoding: String): String {
        val format = OutputFormat.createPrettyPrint()
        format.encoding = encoding
        val byteOS = ByteArrayOutputStream()
        val writer = XMLWriter(OutputStreamWriter(byteOS, encoding), format)
        writer.write(doc)
        writer.flush()
        writer.close()
        return byteOS.toString(encoding)
    }

    /**
     * 字符串转换为Document
     *
     * @param text
     * @return
     * @throws DocumentException
     */
    @Throws(DocumentException::class)
    fun str2Document(text: String): Document {
        return DocumentHelper.parseText(text)
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val parse = parse("E:\\thinkpage_cities.xls", "UTF8")
        println(JSON.toJSONString(parse))
    }
}
