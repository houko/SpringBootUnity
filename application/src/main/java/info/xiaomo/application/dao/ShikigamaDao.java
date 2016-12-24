package info.xiaomo.application.dao;

import info.xiaomo.application.model.ShikigamiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @created : 2016/12/24 15:55
 */
@Repository
public interface ShikigamaDao extends JpaRepository<ShikigamiModel, Long> {

    ShikigamiModel findByName(String name);

    void deleteByName(String name);
}
