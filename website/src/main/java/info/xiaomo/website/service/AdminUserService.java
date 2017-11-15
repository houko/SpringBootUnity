package info.xiaomo.website.service;

import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.website.model.AdminModel;
import org.springframework.data.domain.Page;

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
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/2 13:04
 * Copyright(©) 2015 by xiaomo.
 */
public interface AdminUserService {

    /**
     * 根据用户名查用户
     *
     * @param userName
     * @return
     */
    AdminModel findAdminUserByUserName(String userName);

    /**
     * 根据id查用户
     *
     * @param id
     * @return
     */
    AdminModel findAdminUserById(Long id);

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    AdminModel addAdminUser(AdminModel model);

    /**
     * 更新用户
     *
     * @param model
     * @return
     * @throws UserNotFoundException
     */
    AdminModel updateAdminUser(AdminModel model) throws UserNotFoundException;

    /**
     * 获取分页
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<AdminModel> getAdminUsers(int start, int pageSize);

    /**
     * 查所有
     *
     * @return
     */
    List<AdminModel> getAdminUsers();

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    AdminModel deleteAdminUserById(Long id) throws UserNotFoundException;

    /**
     * 禁
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    AdminModel forbidAdminUserById(Long id) throws UserNotFoundException;

}
