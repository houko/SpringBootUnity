package info.xiaomo.application.controller;

import info.xiaomo.application.model.ShikigamiModel;
import info.xiaomo.application.service.ShikigamaService;
import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @created : 2016/12/24 16:23
 */
@RestController
@RequestMapping("/shikigami")
public class ShikigamaController extends BaseController {

    private final ShikigamaService shikigamaService;


    @Autowired
    public ShikigamaController(ShikigamaService shikigamaService) {
        this.shikigamaService = shikigamaService;
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result<List> findAll() {
        List<ShikigamiModel> all = shikigamaService.findAll();
        if (all == null || all.size() == 0) {
            return new Result<>(Err.NULL_DATA.getResultCode(), "无数据", null);
        }
        return new Result<>(all);
    }


}
