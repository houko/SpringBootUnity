package info.xiaomo.crawler.service;

import info.xiaomo.crawler.model.ShikigamiModel;

import java.util.List;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:54
 */
public interface ShikigamaService {

    /**
     * 根据名字查式神
     *
     * @param name
     * @return
     */
    ShikigamiModel findByName(String name);

    /**
     * 保存
     *
     * @param model
     */
    void save(ShikigamiModel model);

    /**
     * 查所有
     *
     * @return
     */
    List<ShikigamiModel> findAll();

}
