package info.xiaomo.core.service.website;

import info.xiaomo.core.model.website.WorksModel;
import org.springframework.data.domain.Page;

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
 * @Date: 2016/11/3 14:33
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/

public interface WorksService {

    List<WorksModel> findAll();

    Page<WorksModel> findAll(int start, int pageSize);

    WorksModel findById(Long id);

    WorksModel findByName(String name);

    WorksModel update(WorksModel model);

    WorksModel add(WorksModel model);

    void del(Long id);
}
