package info.xiaomo.javase.model;

import info.xiaomo.core.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 *
 * @author : xiaomo
 * github  : https://github.com/xiaomoinfo
 * email   : xiaomo@xiaomo.info
 * QQ      : 83387856
 * Date    : 2017/11/20 18:57
 * desc    :
 * Copyright(©) 2017 by xiaomo.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "question")
@Data
public class QuestionModel extends BaseModel implements Serializable {
    /**
     * 问题
     */
    private String question;
//    /**
//     * 选项
//     */
//    private List<String> choice;
//    /**
//     * 答案
//     */
//    private List<Integer> answer;
}
