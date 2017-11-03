package info.xiaomo.core.field;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info(83387856)
 * Date: 2016/1/8 13:16
 * Description: QQ登录常量类
 * Copyright(©) 2015 by xiaomo.
 */
public interface LoginField {
    //qq
    String QQOpenId = "openid";
    String QQNickname = "nickname";
    String QQPhotourl = "figureurl_qq_2";
    String QQGender = "gender";
    //sina
    String sinaNickName = "screen_name";
    String sinaOpenId = "id";
    String sinaHeadPhoto = "avatar_large";
    //github
    String githubNickName = "name";
    String githubOpenId = "id";
    String githubHeadPhoto = "avatar_url";
    String githubEmail = "email";
    //baidu
    String baiduNickName = "name";
    String baiduOpenId = "id";
    String baiduHeadPhoto = "avatar_url";
    String baiduGender = "";

    //osc
    String oscNickName = "name";
    String oscOpenId = "id";
    String oscHeadPhoto = "avatar";
    String oscEmail = "email";
    String oscGender = "gender";

    //renren
    String renrenNickName = "name";
    String renrenOpenId = "id";
    String renrenHeadPhoto = "url";
    String renrenEmail = "email";
    String renrenGender = "gender";
    String renrenPhoto = "avatar";

    String id = "id";
    String password = "password";
    String email = "email";
    String emailVerify = "emailVerify";          // EmailVerifyConst 是否验证
    String sex = "gender";                           //GenderType 性别
    String birthday = "birthday";                           //GenderType 性别
    String status = "status";                      // UserStatusType 帐号状态
    String lastLoginTime = "lastLoginTime";

    String type = "type";                          //LoginTypeConst 登录类型
    String contribution = "contribution";
    String authority = "authority";             // OauthType 权限类型
    String updateTime = "updateTime";
    String createTime = "createTime";
    String index = "/index.html";
}
