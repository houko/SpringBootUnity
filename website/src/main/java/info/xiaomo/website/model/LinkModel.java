package info.xiaomo.website.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import info.xiaomo.website.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "link")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
// fast jackson
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// swagger ui
@ApiModel(value = "友情链接实体类")
public class LinkModel extends BaseModel implements Serializable {

    @ApiModelProperty(value = "友情链接的名字", required = true)
    @Column(name = "Name")
    private String name;

    @ApiModelProperty(value = "友情链接的URL", required = true)
    @Column(name = "Url")
    private String url;
}
