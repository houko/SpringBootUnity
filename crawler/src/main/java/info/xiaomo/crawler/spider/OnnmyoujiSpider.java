package info.xiaomo.crawler.spider;

import info.xiaomo.core.untils.HttpUtil;
import info.xiaomo.crawler.model.ShikigamiModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:05
 * 阴阳师获取数据
 */
public class OnnmyoujiSpider {

    private static final String URL = "http://ng.d.cn/db/yinyangshi/index.html";


    /**
     * 获取数据并封装成model
     */
    public static List<ShikigamiModel> getShikigamiModel() {
        List<ShikigamiModel> list = new ArrayList<>();
        List<String> shikigamiDetailInfoUrl = getShikigamiDetailInfoUrl();
        for (String url : shikigamiDetailInfoUrl) {
            ShikigamiModel shikigamiModel = getShikigami(url);
            list.add(shikigamiModel);
        }
        return list;
    }


    /**
     * 获取式神详情页链接
     */
    private static List<String> getShikigamiDetailInfoUrl() {
        List<String> list = new ArrayList<>();
        String html = HttpUtil.get(URL);
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".heroList-2").get(0);
        Elements liElement = select.select("a");
        for (Element element : liElement) {
            String href = element.attr("href");
            list.add(href);
        }
        return list;
    }


    /**
     * 获取御魂信息详情页连接
     */
    private static List<String> getMitamaDetailInfoUrl() {
        List<String> list = new ArrayList<>();
        String html = HttpUtil.get(URL);
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".heroList-1").get(0);
        Elements liElement = select.select("a");
        for (Element element : liElement) {
            String href = element.attr("href");
            list.add(href);
        }
        return list;
    }


    /**
     * 获取式神信息
     */
    private static ShikigamiModel getShikigami(String url) {
        ShikigamiModel a = new ShikigamiModel();
        String html = HttpUtil.get(url);
        Document doc = Jsoup.parse(html);
        Elements selects = doc.select("table").get(1).select("tr").get(2).select("td");
        String seiyou = selects.get(0).text();
        String name = selects.get(1).text();
        String star = selects.get(2).text();
        String sex = selects.get(3).text();
        String level = selects.get(4).text();
        String getWay = selects.get(5).text();
        String image = doc.select("table").get(0).select("tr").get(0).select("img").attr("src");
        return new ShikigamiModel(name, image, seiyou, sex, star, getWay, level, "");


    }

}
