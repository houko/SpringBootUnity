package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.BlogModel;
import info.xiaomo.core.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * @Date: 2016/4/1117:40
 * @Description: 后台博客管理
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/admin/blog")
public class BlogController extends BaseController {

    @Autowired
    private BlogService service;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     */
    @RequestMapping("findById")
    public HashMap<String, Object> findById(@RequestParam Long id) {
        result = new HashMap<>();
        BlogModel blogModel = service.findBlogById(id);
        if (blogModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        blogModel.setVote(blogModel.getVote() + 1);
        service.updateBlog(blogModel);
        result.put(blog, blogModel);
        return result;
    }

    /**
     * findByName
     *
     * @return result
     */
    @RequestMapping(value = "findByTitle", method = RequestMethod.GET)
    public HashMap<String, Object> findByTitle(@RequestParam String title) {
        result = new HashMap<>();
        BlogModel model = service.findBlogByTitle(title);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        model.setVote(model.getVote() + 1);
        service.updateBlog(model);
        result.put(blog, model);
        return result;
    }

    /**
     * 分页查询博客
     *
     * @param start
     * @param pageSize
     * @return
     */
    @RequestMapping("findAll")
    public HashMap<String, Object> findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        result = new HashMap<>();
        Page<BlogModel> all = service.findAll(start, pageSize);
        result.put(code, success);
        result.put(blogs, all);
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
            @RequestParam(name = "blogType",defaultValue = "1") int blogType,
            @RequestParam Long tagIds[]
    ) {
        result = new HashMap<>();
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
        blogModel.setBlogType(blogType);
        blogModel.setUpdateTime(new Date());
        blogModel.setTagIds(tagIds);
        blogModel = service.addBlog(blogModel);
        result.put(code, success);
        result.put(blog, blogModel);
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
            @RequestParam int blogType,
            @RequestParam Long tagIds[]
    ) {
        result = new HashMap<>();
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
        blogModel.setTagIds(tagIds);
        blogModel.setBlogType(blogType);
        blogModel = service.updateBlog(blogModel);
        result.put(code, success);
        result.put(blog, blogModel);
        return result;
    }


    /**
     * 删除博客
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "deleteBlogById", method = RequestMethod.GET)
    public HashMap<String, Object> deleteBlogById(@RequestParam Long id) {
        result = new HashMap<>();
        BlogModel blogModel = service.findBlogById(id);
        if (blogModel == null) {
            result.put(code, notFound);
            return result;
        }
        blogModel = service.deleteBlogById(id);
        result.put(code, success);
        result.put(blog, blogModel);
        return result;
    }


}
