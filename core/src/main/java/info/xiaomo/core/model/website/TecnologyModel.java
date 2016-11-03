package info.xiaomo.core.model.website;

import info.xiaomo.core.model.base.BaseModel;

import javax.persistence.Column;
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
 * @Date: 2016/11/3 14:27
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/

@Entity
@Table(name = "technology")
public class TecnologyModel extends BaseModel {

    /**
     * 技术名字
     */
    @Column(name = "Name")
    private String name;

    /**
     * url
     */
    @Column(name = "Url")
    private String url;

    /**
     * 简介
     */
    @Column(name = "Summary")
    private String summary;

    /**
     * 图片链接
     */
    @Column(name = "ImgUrl")
    private String imgUrl;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
