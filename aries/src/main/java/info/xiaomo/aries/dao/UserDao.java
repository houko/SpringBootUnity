package info.xiaomo.aries.dao;

import info.xiaomo.aries.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:39
 */
@Repository
public interface UserDao extends JpaRepository<UserModel, Long> {

    UserModel findById(Long id);

    UserModel findByName(String name);

    boolean deleteByName(String name);

}
