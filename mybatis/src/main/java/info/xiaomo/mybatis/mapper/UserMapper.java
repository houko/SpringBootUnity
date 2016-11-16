package info.xiaomo.mybatis.mapper;


import info.xiaomo.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Select("SELECT * FROM USER WHERE 1=1")
    List<User> findAll();


    @Update("UPDATE USER SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM USER WHERE id =#{id}")
    void delete(Long id);

    @Insert("INSERT INTO USER(name, age) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

}