package info.xiaomo.website.model;


import info.xiaomo.core.model.BaseModel;

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
 * @Date: 2016/11/3 14:15
 * @Description: 作品实体类
 * @Copyright(©) 2015 by xiaomo.
 **/

@Entity
@Table(name = "works")
public class WorksModel extends BaseModel {

    /**
     * 作品
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
     * 完成时间
     */
    @Column(name = "CompleteTime")
    private String completeTime;


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

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
