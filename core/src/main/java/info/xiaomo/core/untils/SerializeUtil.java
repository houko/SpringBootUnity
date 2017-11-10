package info.xiaomo.core.untils;

import java.io.*;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 15/9/6 16:05
 * Description: 序列化通用方法
 * Copyright(©) 2015 by xiaomo.
 */
public class SerializeUtil {

    /**
     * 序列化
     */
    public static String serialize(Object object) {
        if (object == null) {
            return null;
        }
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     */
    public static Object unserialize(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("utf-8");
        ByteArrayInputStream bais;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}