package info.xiaomo.website.view;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/11/21 11:12
 * Copyright(©) 2015 by xiaomo.
 **/

public enum TechnologyView {
    /**
     * login
     */
    LOGIN("login"),
    REGISTER("register"),
    REGISTER_INFO("info"),
    INDEX("/web/index");

    private String name;

    TechnologyView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
