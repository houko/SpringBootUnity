package info.xiaomo.core.model;

import info.xiaomo.core.model.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

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
 * @Date: 2016/4/517:17
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "tag")
public class TagModel extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
