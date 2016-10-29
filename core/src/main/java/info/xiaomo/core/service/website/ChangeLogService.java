package info.xiaomo.core.service.website;

import info.xiaomo.core.model.website.ChangeLogModel;
import org.springframework.data.domain.Page;

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
 * @Date: 2016/4/1119:49
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
public interface ChangeLogService {

    ChangeLogModel findById(Long id);

    ChangeLogModel findByName(String name);

    Page<ChangeLogModel> findAll(int start ,int pageSize);

    ChangeLogModel add(ChangeLogModel model);

    ChangeLogModel update(ChangeLogModel model);

    ChangeLogModel delete(Long id);
}
