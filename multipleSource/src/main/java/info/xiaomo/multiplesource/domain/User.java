package info.xiaomo.multiplesource.domain;


/**
 * @author : xiaomo
 */

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
