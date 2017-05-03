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
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 16/4/2 13:04
 * Copyright(©) 2015 by xiaomo.
 */
public interface AdminUserService {

    AdminModel findAdminUserByUserName(String userName);

    AdminModel findAdminUserById(Long id);

    AdminModel addAdminUser(AdminModel model);

    AdminModel updateAdminUser(AdminModel model) throws UserNotFoundException;

    Page<AdminModel> getAdminUsers(int start, int pageSize);

    List<AdminModel> getAdminUsers();

    AdminModel deleteAdminUserById(Long id) throws UserNotFoundException;

    AdminModel forbidAdminUserById(Long id) throws UserNotFoundException;

}
