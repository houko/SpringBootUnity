package info.xiaomo.website.model;


import info.xiaomo.core.base.BaseModel;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/517:17
 * Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "changeLog")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLogModel extends BaseModel implements Serializable {

    @Column(name = "Name")
    private String name;

    @Column(name = "OnlineTime")
    private String onlineTime;
}
