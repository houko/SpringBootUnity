package info.xiaomo.aries.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import info.xiaomo.core.filter.CustomDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:41
 */

@MappedSuperclass
@Data
@ApiModel("基类")
public class BaseModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "名字")
    @Column(name = "Name")
    private String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateTime")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateTime")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
