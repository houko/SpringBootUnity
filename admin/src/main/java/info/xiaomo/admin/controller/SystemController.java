package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
 * @Date: 2016/4/2520:57
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/admin/system")
public class SystemController extends BaseController {

    @RequestMapping("getSystem")
    public HashMap<String, Object> getSystem() {
        Map<String, Object> map = new HashMap<>();
        Properties properties = System.getProperties();
        map.put("java.version", properties.getProperty("java.version"));//运行时环境版本
        map.put("java.vendor", properties.getProperty("java.vendor"));//Java 运行时环境供应商
        map.put("java.vendor.url", properties.getProperty("java.vendor.url"));// Java 供应商的 URL
        map.put("java.home", properties.getProperty("java.home"));//Java 安装目录
        map.put("java.vm.name", properties.getProperty("java.version"));// Java 运行时环境规范版本
        map.put("java.class.version", properties.getProperty("java.class.version"));//  Java 类格式版本号
        map.put("os.name", properties.getProperty("os.name"));//  操作系统的名称
        map.put("os.version", properties.getProperty("os.version"));//  操作系统的版本
        map.put("user.name", properties.getProperty("user.name"));//  用户的账户名称
        map.put("user.home", properties.getProperty("user.home"));//  用户的主目录
        map.put("user.dir", properties.getProperty("user.dir"));//  用户的当前工作目录
        result.put(systems, map);
        result.put(code, success);
        return result;
    }
}
