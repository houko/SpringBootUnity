package info.xiaomo.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.untils.Md5Util;
import info.xiaomo.core.untils.RandomUtil;
import info.xiaomo.website.model.AdminModel;
import info.xiaomo.website.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/2 12:47
 * Description: 后台用户控制器
 * Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/adminUser")
@Tag(name = "后台用户相关api", description = "后台用户相关api")
public class AdminUserController extends BaseController {

    private final AdminUserService service;

    @Autowired
    public AdminUserController(AdminUserService service) {
        this.service = service;
    }

    /**
     * 后台账户登录
     *
     * @return Result
     */
    @RequestMapping(value = "login/{userName}/{password}", method = RequestMethod.POST)
    @Operation(summary = "获取用户信息", description = "根据用户帐号和密码登录后台")
    @Parameters({
            @Parameter(name = "userName", description = "用户名", required = true, in = ParameterIn.PATH),
            @Parameter(name = "password", description = "用户名", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result<AdminModel> login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
        if (!Md5Util.encode(password, adminModel.getSalt()).equals(adminModel.getPassword())) {
            return new Result<>(CodeConst.AUTH_FAILED.getResultCode(), CodeConst.AUTH_FAILED.getMessage());
        }
        return new Result<>(adminModel);
    }


    /**
     * 添加用户
     *
     * @return Result
     */
    @Operation(summary = "添加后台用户", description = "传一个管理员用户模型过来然后保存到数据库")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result add(@RequestBody AdminModel model) {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel != null) {
            return new Result<>(CodeConst.ADMIN_USER_REPEAT.getResultCode(), CodeConst.ADMIN_USER_REPEAT.getMessage());
        }
        String salt = RandomUtil.createSalt();
        model.setSalt(salt);
        model.setPassword(Md5Util.encode(model.getPassword(), salt));
        AdminModel saveModel = service.addAdminUser(model);
        return new Result<>(saveModel);
    }

    /**
     * 根据id查找
     *
     * @param id id
     * @return Result
     */
    @Operation(summary = "查找用户", description = "根据传来的id查找用户并返回")
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @Parameters({
            @Parameter(name = "id", description = "后台用户唯一id", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result findUserById(@PathVariable("id") Long id) {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(adminModel);
    }

    /**
     * 查找所有(不带分页)
     *
     * @return result
     */
    @Override
    public Result<List> findAll() {
        return null;
    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    /**
     * 根据id查看模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 根据名字查找
     *
     * @param userName userName
     * @return Result
     */
    @Override
    @Operation(summary = "查找用户", description = "根据传来的用户名查找用户并返回")
    @RequestMapping(value = "findByName/{userName}", method = RequestMethod.GET)
    @Parameters({
            @Parameter(name = "userName", description = "用户名", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result findByName(@PathVariable("userName") String userName) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(adminModel);
    }

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
    }

    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @Operation(summary = "修改用户密码", description = "传来模型验证并修改密码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result changePassword(@RequestBody AdminModel model) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        String salt = RandomUtil.createSalt();
        adminModel.setSalt(salt);
        adminModel.setPassword(Md5Util.encode(model.getPassword(), salt));
        service.updateAdminUser(adminModel);
        return new Result<>(adminModel);
    }


    /**
     * 返回所有
     *
     * @return 不分页
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @Operation(summary = "返回所有用户信息", description = "不分页")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result getAll() {
        List<AdminModel> pages = service.getAdminUsers();
        if (pages == null || pages.size() <= 0) {
            return new Result<>(pages);
        }
        return new Result<>(pages);
    }

    /**
     * 根据id删除数据
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @Operation(summary = "删除用户", description = "根据传入的id删除对应的用户")
    @Parameters({
            @Parameter(name = "id", description = "用户唯一id", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        service.deleteAdminUserById(id);
        return new Result<>(adminModel);
    }

    /**
     * 更新
     *
     * @param userName userName
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update/{userName}", method = RequestMethod.POST)
    @Operation(summary = "更新用户信息", description = "根据传入的模型更新用户信息")
    @Parameters({
            @Parameter(name = "userName", description = "用户名", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result update(@PathVariable("userName") String userName) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        adminModel.setUserName(userName);
        service.updateAdminUser(adminModel);
        return new Result<>(adminModel);
    }

    /**
     * 封号
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "forbid/{id}", method = RequestMethod.GET)
    @Operation(summary = "封号", description = "根据传入的id对修改对应帐号状态")
    @Parameters({
            @Parameter(name = "id", description = "后台用户唯一id", required = true, in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "No Name Provided"),
    })
    public Result forbid(@PathVariable("id") Long id) throws UserNotFoundException {
        AdminModel model = service.findAdminUserById(id);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        model = service.forbidAdminUserById(id);
        return new Result<>(model);
    }
}

