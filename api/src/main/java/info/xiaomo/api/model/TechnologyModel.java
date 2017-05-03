package info.xiaomo.api.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import info.xiaomo.core.base.BaseModel;
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
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 2016/11/3 14:27
 * Copyright(©) 2015 by xiaomo.
 **/

@Entity
@Table(name = "technology")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
// fast jackson
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// swagger ui
@ApiModel(value = "技术中心实体类")
public class TechnologyModel extends BaseModel {

    @ApiModelProperty(value = "技术名字")
    @Column(name = "Name")
    private String name;

    @ApiModelProperty(value = "url")
    @Column(name = "Url")
    private String url;

    @ApiModelProperty(value = "简介")
    @Column(name = "Summary")
    private String summary;

    @ApiModelProperty(value = "图片链接")
    @Column(name = "ImgUrl")
    private String imgUrl;

}
