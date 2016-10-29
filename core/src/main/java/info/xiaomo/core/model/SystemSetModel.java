package info.xiaomo.core.model;

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
 * @Date: 2016/4/517:17
 * @Description: 网站设置
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "systemSet")
public class SystemSetModel extends BaseModel implements Serializable {

    /**
     * 站点名字
     */
    @Column(name = "SiteName")
    private String siteName;

    /**
     * 站点图标
     */
    @Column(name = "Icon")
    private String icon;

    /**
     * footer 的开始年份
     */
    @Column(name = "FromYear")
    private int fromYear;

    /**
     * footer的结束年份
     */
    @Column(name = "ToYear")
    private int toYear;

    /**
     * 备案号
     */
    @Column(name = "BeianNumber")
    private String beianNumber;
    /**
     * 备案地址
     */
    @Column(name = "BeianUrl")
    private String beianUrl;


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public String getBeianUrl() {
        return beianUrl;
    }

    public void setBeianUrl(String beianUrl) {
        this.beianUrl = beianUrl;
    }

    public String getBeianNumber() {
        return beianNumber;
    }

    public void setBeianNumber(String beianNumber) {
        this.beianNumber = beianNumber;
    }
}
