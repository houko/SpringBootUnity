package info.xiaomo.application.service;


import info.xiaomo.application.model.ShikigamiModel;

import java.util.List;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @created : 2016/12/24 15:54
 */
public interface ShikigamaService {

    List<ShikigamiModel> findAll();

    ShikigamiModel findById(Long id);

    void del(Long id);

    void delByName(String name);

    ShikigamiModel findByName(String name);

    void save(ShikigamiModel model);

}
