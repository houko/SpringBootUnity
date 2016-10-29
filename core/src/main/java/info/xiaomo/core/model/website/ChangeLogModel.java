package info.xiaomo.core.model.website;

import info.xiaomo.core.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
 * @Date: 2016/4/517:17
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "changeLog")
public class ChangeLogModel extends BaseModel implements Serializable {

    @Column(name = "Name")
    private String name;

    @Column(name = "OnlineTime")
    private String onlineTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }
}
