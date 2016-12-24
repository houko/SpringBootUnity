package info.xiaomo.application.dao;

import info.xiaomo.application.model.ShikigamiModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @created : 2016/12/24 15:55
 */
public interface ShikigamaDao extends JpaRepository<ShikigamiModel, Long> {

    ShikigamiModel findByName(String name);
}
