package info.xiaomo.website.model.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import info.xiaomo.core.filter.CustomDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
 * @Date: 2016/4/1 20:37
 * @Copyright(©) 2015 by xiaomo.
 **/

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("基类")
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    @ApiModelProperty(value  = "id")
    private long id;

    @Version
    @Column(name = "Version")
    @ApiModelProperty(value  = "版本号")
    private long version;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateTime")
    @ApiModelProperty(value  = "创建时间")
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateTime")
    @ApiModelProperty(value  = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value  = "排序编辑")
    @Column(name = "Position")
    private int position = 1;
}
