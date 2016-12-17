package info.xiaomo.app.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import info.xiaomo.app.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/4/517:17
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "systemSet")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
// fast jackson
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// swagger ui
@ApiModel(value = "系统设置实体类")
public class SystemSetModel extends BaseModel implements Serializable {

    @ApiModelProperty(value = "站点名字", required = false)
    @Column(name = "SiteName")
    private String siteName = "小莫-fighting";

    @ApiModelProperty(value = "站点图标")
    @Column(name = "Icon")
    private String icon = "";

    @ApiModelProperty(value = "footer的开始年份")
    @Column(name = "FromYear")
    private int fromYear = Calendar.getInstance().get(Calendar.YEAR);

    @ApiModelProperty(value = "footer的结束年份")
    @Column(name = "ToYear")
    private int toYear = Calendar.getInstance().get(Calendar.YEAR);

    @ApiModelProperty(value = "备案号")
    @Column(name = "BeianNumber")
    private String beianNumber = "浙ICP备15009606号";

    @ApiModelProperty(value = "备案地址")
    @Column(name = "BeianUrl")
    private String beianUrl = "http://www.miitbeian.gov.cn/";

}
