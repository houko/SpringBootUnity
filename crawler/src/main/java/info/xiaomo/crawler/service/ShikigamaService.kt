package info.xiaomo.crawler.service

import info.xiaomo.crawler.model.ShikigamiModel

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:54
 */
interface ShikigamaService {

    /**
     * 根据名字查式神
     *
     * @param name
     * @return
     */
    fun findByName(name: String): ShikigamiModel?

    /**
     * 保存
     *
     * @param model
     */
    fun save(model: ShikigamiModel)

    /**
     * 查所有
     *
     * @return
     */
    fun findAll(): List<ShikigamiModel>

}
