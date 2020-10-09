package info.xiaomo.order.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.order.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xiaomo
 */
@RestController
@RequestMapping("/order")
@Api(value = "识别订单")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService) {
        this.service = orderService;
    }


    @RequestMapping(value = "forbid/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "封号", notes = "根据传入的id对修改对应帐号状态", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "后台用户唯一id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result forbid(@PathVariable("id") Long id) {
        return new Result<>(null);
    }
}

