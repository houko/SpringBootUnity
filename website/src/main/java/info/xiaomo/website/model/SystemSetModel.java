package info.xiaomo.website.model;


import info.xiaomo.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemSetModel extends BaseModel implements Serializable {

    /**
     * 站点名字
     */
    @Column(name = "SiteName")
    private String siteName ="小莫-fighting";

    /**
     * 站点图标
     */
    @Column(name = "Icon")
    private String icon ="";

    /**
     * footer 的开始年份
     */
    @Column(name = "FromYear")
    private int fromYear = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * footer的结束年份
     */
    @Column(name = "ToYear")
    private int toYear = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * 备案号
     */
    @Column(name = "BeianNumber")
    private String beianNumber = "浙ICP备15009606号";
    /**
     * 备案地址
     */
    @Column(name = "BeianUrl")
    private String beianUrl = "http://www.miitbeian.gov.cn/";

}
