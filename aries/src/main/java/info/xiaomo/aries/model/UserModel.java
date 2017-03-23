package info.xiaomo.aries.model;

import info.xiaomo.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:40
 */
@Entity
@Table(name = "user")
@ApiModel(value = "用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseModel {

    private String school;

    private String password;

    private String salt;

}
