package info.xiaomo.mybatis.mapper;


import info.xiaomo.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author : xiaomo
 */
@Mapper
public interface UserMapper {

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })

    /**
     * 根据名字查
     * @param name
     * @return user
     */
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    /**
     * 插入
     *
     * @param name
     * @param age
     * @return
     */
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    /**
     * 查所有
     *
     * @return
     */
    @Select("SELECT * FROM USER WHERE 1=1")
    List<User> findAll();

    /**
     * 更新
     *
     * @param user
     */
    @Update("UPDATE USER SET age=#{age} WHERE name=#{name}")
    void update(User user);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("DELETE FROM USER WHERE id =#{id}")
    void delete(Long id);

    /**
     * 添加
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO USER(name, age) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    /**
     * 添加
     *
     * @param map
     * @return
     */
    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

}