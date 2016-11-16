package info.xiaomo.website.model;


import info.xiaomo.website.model.base.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/11/3 14:15
 * @Copyright(©) 2015 by xiaomo.
 **/

// hibernate
@Entity
@Table(name = "works")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class WorksModel extends BaseModel {

    @Column(name = "Name")
    private String name;

    @Column(name = "Url")
    private String url;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "CompleteTime")
    private String completeTime;

    @Column(name = "ImgUrl")
    private String imgUrl;
}
