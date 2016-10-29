package info.xiaomo.core.model.website;

import info.xiaomo.core.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
 * @Date: 2016/4/1119:47
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "link")
public class LinkModel extends BaseModel implements Serializable{

    @Column(name = "Name")
    private String name;

    @Column(name = "Url")
    private String url;

    @Column(name = "Level")
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
