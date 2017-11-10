package info.xiaomo.crawler.service.impl;

import com.alibaba.fastjson.JSON;
import info.xiaomo.crawler.dao.ShikigamaDao;
import info.xiaomo.crawler.model.ShikigamiModel;
import info.xiaomo.crawler.service.ShikigamaService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
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

    @Override
    public List<ShikigamiModel> findAll() {
        return dao.findAll();
    }
}
