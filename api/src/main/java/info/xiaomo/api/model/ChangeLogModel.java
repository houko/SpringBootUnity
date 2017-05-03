package info.xiaomo.api.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import info.xiaomo.core.base.BaseModel;
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
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 2016/4/517:17
 * Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "changeLog")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
// fast jackson
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// swagger ui
@ApiModel(value = "更新日志实体类")
public class ChangeLogModel extends BaseModel implements Serializable {

    @ApiModelProperty(value = "更新日志描述", required = true)
    @Column(name = "Name")
    private String name;

    @ApiModelProperty(value = "上线时间(字符串自己编辑)", required = true)
    @Column(name = "OnlineTime")
    private String onlineTime;
}
