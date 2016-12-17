package info.xiaomo.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import info.xiaomo.application.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @created : 2016/12/17 15:08
 */
@Entity
@Table(name = "link")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "友链实体类")
public class LinkModel extends BaseModel {

    private String name;

    private String url;

    private int position;

}
