package info.xiaomo.aries.dao;

import info.xiaomo.aries.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:39
 */
public interface UserDao extends JpaRepository<Long, UserModel> {

    UserModel findById(Long id);

    UserModel findByName(String name);

}
