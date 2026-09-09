package info.xiaomo.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import info.xiaomo.core.base.Result;
import info.xiaomo.order.service.OrderService;
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
@Tag(name = "识别订单")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService) {
        this.service = orderService;
    }


    @RequestMapping(value = "forbid/{id}", method = RequestMethod.GET)
    @Operation(summary = "封号", description = "根据传入的id对修改对应帐号状态")
    @Parameters({
            @Parameter(name = "id", description = "后台用户唯一id", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result forbid(@PathVariable("id") Long id) {
        return new Result<>(null);
    }
}

