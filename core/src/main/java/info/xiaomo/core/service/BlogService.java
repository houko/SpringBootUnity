package info.xiaomo.core.service;

import info.xiaomo.core.model.BlogModel;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

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
 * @Description: blog
 * @Copyright(©) 2015 by xiaomo.
 **/
public interface BlogService {

    BlogModel findBlogById(Long id);

    Page<BlogModel> findAll(Pageable pageable);

    BlogModel addBlog(BlogModel model);

    BlogModel updateBlog(BlogModel model);

    BlogModel deleteBlogById(Long id);

    BlogModel findBlogByTitle(String title);


}
