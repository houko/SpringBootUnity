package info.xiaomo.website.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.website.SystemSetModel;
import info.xiaomo.core.service.website.WebSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/5/6 14:21
 * @Description: 系统设置控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/webSet")
public class WebSetController extends BaseController {

    private final WebSetService service;

    @Autowired
    public WebSetController(WebSetService service) {
        this.service = service;
    }

    /**
     * 查找所有
     *
     * @return list
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<SystemSetModel> findAll() {
        return service.findAll();

    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public SystemSetModel add(
            @RequestParam(value = "siteName", defaultValue = "小莫的博客") String siteName,
            @RequestParam(value = "icon", defaultValue = "") String icon,
            @RequestParam(value = "fromYear", defaultValue = "2016") int fromYear,
            @RequestParam(value = "toYear", defaultValue = "2016") int toYear,
            @RequestParam(value = "beianNumber", defaultValue = "浙ICP备15009606号") String beianNumber,
            @RequestParam(value = "beianUrl", defaultValue = "http://www.miitbeian.gov.cn/") String beianUrl
    ) {
        List<SystemSetModel> all = service.findAll();
        if (all.size() > 1) {
            return null;
        }
        SystemSetModel model = new SystemSetModel();
        model.setSiteName(siteName);
        model.setIcon(icon);
        model.setFromYear(fromYear);
        model.setToYear(toYear);
        model.setBeianUrl(beianUrl);
        model.setBeianNumber(beianNumber);
        return service.add(model);
    }

    /**
     * 更新
     * @param id id
     * @param siteName siteName
     * @param icon icon
     * @param fromYear fromYear
     * @param toYear toYear
     * @param beianNumber beianNumber
     * @param beianUrl beianUrl
     * @return SystemSetModel
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public SystemSetModel update(
            @RequestParam(value = "id", defaultValue = "") Long id,
            @RequestParam(value = "siteName", defaultValue = "小莫的博客") String siteName,
            @RequestParam(value = "icon", defaultValue = "") String icon,
            @RequestParam(value = "fromYear", defaultValue = "2016") int fromYear,
            @RequestParam(value = "toYear", defaultValue = "2016") int toYear,
            @RequestParam(value = "beianNumber", defaultValue = "浙ICP备15009606号") String beianNumber,
            @RequestParam(value = "beianUrl", defaultValue = "http://www.miitbeian.gov.cn/") String beianUrl
    ) {
        List<SystemSetModel> all = service.findAll();
        if (all.size() > 1) {
            return null;
        }
        SystemSetModel model = new SystemSetModel();
        model.setId(id);
        model.setSiteName(siteName);
        model.setIcon(icon);
        model.setFromYear(fromYear);
        model.setToYear(toYear);
        model.setBeianUrl(beianUrl);
        model.setBeianNumber(beianNumber);
        return service.update(model);
    }
}
