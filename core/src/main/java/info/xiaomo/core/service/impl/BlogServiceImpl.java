package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.BlogDao;
import info.xiaomo.core.model.BlogModel;
import info.xiaomo.core.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Objects;


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
 * @Date: 2016/4/517:27
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao dao;

    @Override
    public BlogModel findBlogById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Page<BlogModel> findAll(Pageable pageable) {
        Page<BlogModel> blogModels = dao.findAll(pageable);
        for (Iterator<BlogModel> it = blogModels.iterator(); it.hasNext(); ) {
            if (it.next().getStatus() == 1) {//去掉己删除的博客
                it.remove();
            }
        }
        return blogModels;
    }

    @Override
    public BlogModel addBlog(BlogModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public BlogModel updateBlog(BlogModel model) {
        BlogModel updateBlog = dao.findOne(model.getId());
        if (updateBlog == null) {
            return null;
        }
        if (!Objects.equals(model.getAuthor(), updateBlog.getAuthor())) {
            updateBlog.setAuthor(model.getAuthor());
        }
        if (Objects.equals(model.getContent(), updateBlog.getContent())) {
            updateBlog.setContent(model.getContent());
        }
        if (Objects.equals(model.getSummary(), updateBlog.getSummary())) {
            updateBlog.setSummary(model.getSummary());
        }
        if (Objects.equals(model.getTitle(), updateBlog.getTitle())) {
            updateBlog.setTitle(model.getTitle());
        }
        return dao.save(model);
    }

    @Override
    public BlogModel deleteBlogById(Long id) {
        BlogModel blogModel = dao.findOne(id);
        if (blogModel != null) {
            blogModel.setStatus(1);
            dao.save(blogModel);
            return blogModel;
        }
        return null;
    }

    @Override
    public BlogModel findBlogByTitle(String title) {
        return dao.findByTitle(title);
    }
}
