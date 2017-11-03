package info.xiaomo.website.model;


import info.xiaomo.core.base.BaseModel;
import lombok.*;

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
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 2016/4/517:17
 * Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "systemSet")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SystemSetModel extends BaseModel implements Serializable {

    @Column(name = "SiteName")
    private String siteName = "小莫-fighting";

    @Column(name = "Icon")
    private String icon = "";

    @Column(name = "FromYear")
    private int fromYear = Calendar.getInstance().get(Calendar.YEAR);

    @Column(name = "ToYear")
    private int toYear = Calendar.getInstance().get(Calendar.YEAR);

    @Column(name = "BeianNumber")
    private String beianNumber = "浙ICP备15009606号";

    @Column(name = "BeianUrl")
    private String beianUrl = "http://www.miitbeian.gov.cn/";

}
