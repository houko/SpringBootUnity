package info.xiaomo.mybatis.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : xiaomo
 */
@Data
@ToString(callSuper = false)
@NoArgsConstructor
public class User {

    private Long id;

    private String name;

    private Integer age;

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
