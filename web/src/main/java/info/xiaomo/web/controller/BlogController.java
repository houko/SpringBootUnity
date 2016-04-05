package info.xiaomo.web.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.BlogModel;
import info.xiaomo.core.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

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
 * @Date: 2016/4/517:16
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/api/web/blog")
public class BlogController extends BaseController {

    @Autowired
    private BlogService service;

    /**
     * findById
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "findById/", method = RequestMethod.GET)
    public HashMap<String, Object> findById(@RequestParam Long id) {
        BlogModel model = service.findBlogById(id);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        model.setVote(model.getVote() + 1);
        service.updateBlog(model);
        result.put("blog", model);
        return result;
    }

    /**
     * 分页查询
     *
     * @param start    start
     * @param pageSize pageSize
     * @return result
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public HashMap<String, Object> findAll(@RequestParam("start") int start, @RequestParam("pageSize") int pageSize) {
        Page<BlogModel> models = service.findAll(new PageRequest(start, pageSize));
        result.put(code, success);
        result.put("blogs", models);
        return result;
    }

    /**
     * 增加博客
     *
     * @param title   title
     * @param summary summary
     * @param content content
     * @param tagId   tagId
     * @return result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public HashMap<String, Object> add(
            @RequestParam String title,
            @RequestParam String nickName,
            @RequestParam String summary,
            @RequestParam String content,
            @RequestParam Long tagId
    ) {
        BlogModel blogModel = service.findBlogByTitle(title);
        if (blogModel != null) {
            result.put(code, repeat);
            return result;
        }
        blogModel = new BlogModel();
        blogModel.setTitle(title);
        blogModel.setContent(content);
        blogModel.setSummary(summary);
        blogModel.setAuthor(nickName);
        blogModel.setStatus(0);
        blogModel.setUpdateTime(new Date());
        blogModel.setTagId(tagId);
        blogModel = service.addBlog(blogModel);
        result.put(code, success);
        result.put("blog", blogModel);
        return result;
    }


    /**
     * 修改博客
     *
     * @param title    title
     * @param nickName nickName
     * @param summary  summary
     * @param content  content
     * @param tagId    tagId
     * @return result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, Object> update(
            @RequestParam String title,
            @RequestParam String nickName,
            @RequestParam String summary,
            @RequestParam String content,
            @RequestParam Long tagId
    ) {
        BlogModel blogModel = service.findBlogByTitle(title);
        if (blogModel == null) {
            result.put(code, notFound);
            return result;
        }
        blogModel.setTitle(title);
        blogModel.setStatus(0);
        blogModel.setContent(content);
        blogModel.setAuthor(nickName);
        blogModel.setSummary(summary);
        blogModel.setTagId(tagId);
        blogModel = service.updateBlog(blogModel);
        result.put(code, success);
        result.put("blog", blogModel);
        return result;
    }


}
