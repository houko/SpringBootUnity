package info.xiaomo.application.service.impl;

import com.alibaba.fastjson.JSON;
import info.xiaomo.application.dao.ShikigamaDao;
import info.xiaomo.application.model.ShikigamiModel;
import info.xiaomo.application.service.ShikigamaService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @created : 2016/12/24 15:54
 */
@Service
public class ShikigamaServiceImpl implements ShikigamaService {


    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShikigamaServiceImpl.class);

    private final ShikigamaDao dao;


    @Autowired
    public ShikigamaServiceImpl(ShikigamaDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ShikigamiModel> findAll() {
        return dao.findAll();
    }

    @Override
    public ShikigamiModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void del(Long id) {
        dao.delete(id);
    }

    @Override
    public void delByName(String name) {
        dao.deleteByName(name);
    }

    @Override
    public ShikigamiModel findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void save(ShikigamiModel model) {
        ShikigamiModel shikigamiModel = dao.findByName(model.getName());
        if (shikigamiModel == null) {
            dao.save(model);
            LOGGER.debug("插入数据:{}", JSON.toJSONString(model));
        }
    }
}
