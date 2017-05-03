package info.xiaomo.crawler.service;

import info.xiaomo.crawler.model.ShikigamiModel;

import java.util.List;

/**
 * author 小莫 (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:54
 */
public interface ShikigamaService {

    ShikigamiModel findByName(String name);

    void save(ShikigamiModel model);

    List<ShikigamiModel> findAll();

}
