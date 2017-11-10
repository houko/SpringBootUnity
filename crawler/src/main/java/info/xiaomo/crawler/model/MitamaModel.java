package info.xiaomo.crawler.model;


import info.xiaomo.core.base.BaseModel;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 18:06
 * 御魂模型
 */
public class MitamaModel extends BaseModel {

    /**
     * 名字
     * 火灵
     */
    private String name;

    /**
     * icon
     * http://uus-ng.img.d.cn/snapshot/201610/999/image/388/388/hd/20161012151646257.jpeg
     */
    private String image;

    /**
     * 两件套效果
     * 效果命中+15%
     */
    private String effect2;

    /**
     * 四件套效果
     * 初次出场时获得额外3点鬼火（新回目战斗开始也会被计为初次出场）
     */
    private String effect4;

    /**
     * 关键字
     */
    private String keyworld;

    /**
     * 推荐式神
     * 青行灯
     */
    private String suggest;


    /**
     * 获取方式
     * 神秘商店、周末御魂
     */
    private String getWay;


}
