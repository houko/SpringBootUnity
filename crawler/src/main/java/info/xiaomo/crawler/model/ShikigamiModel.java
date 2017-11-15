package info.xiaomo.crawler.model;

import info.xiaomo.core.base.BaseModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:09
 */

@Entity
@Table(name = "shikigame")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShikigamiModel extends BaseModel {

    /**
     * 名字
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 声优
     */
    private String seiyou;


    /**
     * 性别
     */
    private String sex;

    /**
     * 星级
     */
    private String star;

    /**
     * 获取方式
     */
    private String getWay;


    /**
     * N/R/SR/SSR
     */
    private String level;


    /**
     * 描述
     */
    private String des;


}
