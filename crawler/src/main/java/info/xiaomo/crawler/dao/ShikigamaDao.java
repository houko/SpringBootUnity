package info.xiaomo.crawler.dao;

import info.xiaomo.crawler.model.ShikigamiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:55
 */
@Repository
public interface ShikigamaDao extends JpaRepository<ShikigamiModel, Long> {

    /**
     * 根据名字查式神
     *
     * @param name
     * @return
     */
    ShikigamiModel findByName(String name);
}
