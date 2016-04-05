package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.BlogDao;
import info.xiaomo.core.model.BlogModel;
import info.xiaomo.core.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
        return dao.findAll(pageable);
    }

    @Override
    public BlogModel addBlog(BlogModel model) {
        return dao.save(model);
    }

    @Override
    public BlogModel updateBlog(BlogModel model) {
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
