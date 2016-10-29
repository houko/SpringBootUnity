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
 * @Date: 2016/4/1 17:36
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "user")
public class UserModel extends BaseModel implements Serializable{

    /**
     * 登录用户
     */
    @Column(name = "Email")
    private String email;

    @Column(name = "NickName")
    private String nickName;

    @Column(name = "Password")
    private String password;

    /**
     * 盐值
     */
    @Column(name = "Salt")
    private String salt;

    @Column(name = "ValidateCode")
    private String validateCode;//激活码

    @Column(name = "Gender")
    private int gender;

    @Column(name = "Phone")
    private Long phone;

    @Column(name = "ImgUrl")
    private String imgUrl;

    @Column(name = "Address")
    private String address;

    public String getNickName() {
        return nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
