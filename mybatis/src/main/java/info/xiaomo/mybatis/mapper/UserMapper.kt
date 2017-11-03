package info.xiaomo.mybatis.mapper


import info.xiaomo.mybatis.domain.User
import org.apache.ibatis.annotations.*

/**
 * @author : xiaomo
 */
@Mapper
interface UserMapper {

    @Results(Result(property = "name", column = "name"), Result(property = "age", column = "age"))
    /**
     * 根据名字查
     * @param name
     * @return user
     */
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    fun findByName(@Param("name") name: String): User

    /**
     * 插入
     *
     * @param name
     * @param age
     * @return
     */
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    fun insert(@Param("name") name: String, @Param("age") age: Int?): Int

    /**
     * 查所有
     * @return
     */
    @Select("SELECT * FROM USER WHERE 1=1")
    fun findAll(): List<User>

    /**
     * 更新
     *
     * @param user
     */
    @Update("UPDATE USER SET age=#{age} WHERE name=#{name}")
    fun update(user: User)

    /**
     * 删除
     *
     * @param id
     */
    @Delete("DELETE FROM USER WHERE id =#{id}")
    fun delete(id: Long?)

    /**
     * 添加
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO USER(name, age) VALUES(#{name}, #{age})")
    fun insertByUser(user: User): Int

    /**
     * 添加
     *
     * @param map
     * @return
     */
    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    fun insertByMap(map: Map<String, Any>): Int

}