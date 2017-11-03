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
    String Q_Q_OPEN_ID = "openid";
    String Q_Q_NICKNAME = "nickname";
    String Q_Q_PHOTOURL = "figureurl_qq_2";
    String Q_Q_GENDER = "gender";
    //sina
    String SINA_NICK_NAME = "screen_name";
    String SINA_OPEN_ID = "id";
    String SINA_HEAD_PHOTO = "avatar_large";
    //github
    String GITHUB_NICK_NAME = "name";
    String GITHUB_OPEN_ID = "id";
    String GITHUB_HEAD_PHOTO = "avatar_url";
    String GITHUB_EMAIL = "email";
    //baidu
    String BAIDU_NICK_NAME = "name";
    String BAIDU_OPEN_ID = "id";
    String BAIDU_HEAD_PHOTO = "avatar_url";
    String BAIDU_GENDER = "";

    //osc
    String OSC_NICK_NAME = "name";
    String OSC_OPEN_ID = "id";
    String OSC_HEAD_PHOTO = "avatar";
    String OSC_EMAIL = "email";
    String OSC_GENDER = "gender";

    //renren
    String RENREN_NICK_NAME = "name";
    String RENREN_OPEN_ID = "id";
    String RENREN_HEAD_PHOTO = "url";
    String RENREN_EMAIL = "email";
    String RENREN_GENDER = "gender";
    String RENREN_PHOTO = "avatar";

    String ID = "id";
    String PASSWORD = "password";
    String EMAIL = "email";
    String EMAILVERIFY = "emailVerify";          // EmailVerifyConst 是否验证
    String SEX = "gender";                           //GenderType 性别
    String BIRTHDAY = "birthday";                           //GenderType 性别
    String STATUS = "status";                      // UserStatusType 帐号状态
    String LAST_LOGIN_TIME = "lastLoginTime";

    String TYPE = "type";                          //LoginTypeConst 登录类型
    String CONTRIBUTION = "contribution";
    String AUTHORITY = "authority";             // OauthType 权限类型
    String UPDATETIME = "updateTime";
    String CREATETIME = "createTime";
    String INDEX = "/index.html";
}
