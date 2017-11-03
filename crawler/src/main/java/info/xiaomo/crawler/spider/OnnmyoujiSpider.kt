package info.xiaomo.crawler.spider

import info.xiaomo.core.untils.HttpUtil
import info.xiaomo.crawler.model.ShikigamiModel
import org.jsoup.Jsoup
import java.util.*

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:05
 * 阴阳师获取数据
 */
object OnnmyoujiSpider {

    private val URL = "http://ng.d.cn/db/yinyangshi/index.html"


    /**
     * 获取数据并封装成model
     */
    val shikigamiModel: List<ShikigamiModel>
        get() {
            val list = ArrayList<ShikigamiModel>()
            val shikigamiDetailInfoUrl = shikigamiDetailInfoUrl
            for (url in shikigamiDetailInfoUrl) {
                val shikigamiModel = getShikigami(url)
                list.add(shikigamiModel)
            }
            return list
        }


    /**
     * 获取式神详情页链接
     */
    private val shikigamiDetailInfoUrl: List<String>
        get() {
            val list = ArrayList<String>()
            val html = HttpUtil.get(URL)
            val doc = Jsoup.parse(html)
            val select = doc.select(".heroList-2")[0]
            val liElement = select.select("a")
            for (element in liElement) {
                val href = element.attr("href")
                list.add(href)
            }
            return list
        }


    /**
     * 获取御魂信息详情页连接
     */
    private val mitamaDetailInfoUrl: List<String>
        get() {
            val html = HttpUtil.get(URL)
            val doc = Jsoup.parse(html)
            val select = doc.select(".heroList-1")[0]
            val liElement = select.select("a")
            return liElement.map { it.attr("href") }
        }


    /**
     * 获取式神信息
     */
    private fun getShikigami(url: String): ShikigamiModel {
        val a = ShikigamiModel()
        val html = HttpUtil.get(url)
        val doc = Jsoup.parse(html)
        val selects = doc.select("table")[1].select("tr")[2].select("td")
        val seiyou = selects[0].text()
        val name = selects[1].text()
        val star = selects[2].text()
        val sex = selects[3].text()
        val level = selects[4].text()
        val getWay = selects[5].text()
        val image = doc.select("table")[0].select("tr")[0].select("img").attr("src")
        a.seiyou = seiyou
        a.name = name
        a.star = star
        a.sex = sex
        a.level = level
        a.getWay = getWay
        a.image = image
        return a


    }

}
