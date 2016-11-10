package info.xiaomo.website.model;


import info.xiaomo.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "作品实体类")
public class WorksModel extends BaseModel {

    @ApiModelProperty(value = "作品")
    @Column(name = "Name")
    private String name;

    @ApiModelProperty(value = "url")
    @Column(name = "Url")
    private String url;

    @ApiModelProperty(value = "简介")
    @Column(name = "Summary")
    private String summary;

    @ApiModelProperty(value = "完成时间(字符串自己编辑)")
    @Column(name = "CompleteTime")
    private String completeTime;

    @ApiModelProperty(value = "图片链接")
    @Column(name = "ImgUrl")
    private String imgUrl;
}
