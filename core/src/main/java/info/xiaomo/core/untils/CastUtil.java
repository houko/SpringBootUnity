package info.xiaomo.core.untils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

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
 * Date: 2016/11/22 14:55
 * Copyright(©) 2015 by xiaomo.
 **/

public class CastUtil {
    protected static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();
    protected static ObjectOutputStream oos;

    public CastUtil() {
    }

    public static int toInteger(Object str) {
        return str == null ? 0 : str instanceof Number ? ((Number) str).intValue() : toInteger(str.toString());
    }

    public static double toDouble(Object number) {
        if (number == null) {
            return 0.0D;
        } else if (number instanceof Number) {
            return ((Number) number).doubleValue();
        } else if (number instanceof String) {
            String str = (String) number;
            return isNumeric(str) > 0 ? Double.valueOf(str) : 0.0D;
        } else {
            return 0.0D;
        }
    }

    public static long toLong(Object number) {
        if (number == null) {
            return 0L;
        } else if (number instanceof Number) {
            return ((Number) number).longValue();
        } else if (number instanceof String) {
            String str = (String) number;
            int isNumber = isNumeric(str);
            return isNumber == 1 ? Long.parseLong(str) : (isNumber == 2 ? Double.valueOf(str).longValue() : 0L);
        } else {
            return 0L;
        }
    }

    public static int toInteger(String str) {
        if (str == null) {
            return 0;
        } else {
            str = str.trim();
            if (str.length() == 0) {
                return 0;
            } else {
                int i = isNumeric(str);
                return i == 1 ? Integer.parseInt(str) : (i == 2 ? Double.valueOf(str).intValue() : 0);
            }
        }
    }

    public static int isNumeric(String str) {
        if (str == null) {
            return 0;
        } else {
            boolean isdouble = false;
            boolean hasE = false;
            int i = str.length();

            while (true) {
                while (true) {
                    char c;
                    do {
                        --i;
                        if (i < 0) {
                            if (isdouble) {
                                return 2;
                            }

                            return 1;
                        }

                        c = str.charAt(i);
                    } while (i == 0 && c == 45);

                    if (c == 46) {
                        if (isdouble) {
                            return 0;
                        }

                        isdouble = true;
                    } else if (c != 69 && c != 101) {
                        if (!Character.isDigit(str.charAt(i))) {
                            return 0;
                        }
                    } else {
                        if (hasE) {
                            return 0;
                        }

                        hasE = true;
                    }
                }
            }
        }
    }

    public static HashMap copyMap(HashMap map) {
        HashMap<Object, Object> newmap = new HashMap<>(10);

        for (Object key : map.keySet()) {
            newmap.put(key, map.get(key));
        }

        return newmap;
    }

    public static void destroy(Hashtable map) {
        for (Iterator it = map.keySet().iterator(); it.hasNext(); it.remove()) {
            Object key = it.next();
            Object value = map.get(key);
            if (value instanceof HashMap) {
                HashMap valueMap = (HashMap) value;
                destroy(valueMap);
            }
        }

    }

    public static void destroy(HashMap map) {
        for (Iterator keyit = map.keySet().iterator(); keyit.hasNext(); keyit.remove()) {
            Object key = keyit.next();
            Object value = map.get(key);
            if (value instanceof HashMap) {
                HashMap valueMap = (HashMap) value;
                destroy(valueMap);
            }
        }

    }

    public static String objectToString(Object obj) {
        if (obj.getClass().equals(String.class)) {
            return obj.toString();
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            try {
                ObjectOutputStream e = new ObjectOutputStream(out);
                e.writeObject(obj);
                byte[] bytes = out.toByteArray();
                return new String(bytes, "ISO-8859-1");
            } catch (IOException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static Object stringToObject(String string) {
        try {
            byte[] e = string.getBytes("ISO-8859-1");
            ByteArrayInputStream in = new ByteArrayInputStream(e);
            ObjectInputStream ois = new ObjectInputStream(in);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static int bytesToInt(byte[] bytes) {
        byte length = 4;
        int intValue = 0;

        for (int i = length - 1; i >= 0; --i) {
            int offset = i * 8;
            intValue |= (bytes[i] & 255) << offset;
        }

        return intValue;
    }

    public static Object bytesToObject(byte[] bytes) {
        try {
            ByteArrayInputStream e = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(e);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public static byte[] objectToBytes(Object obj) throws IOException {
        OUT.reset();

        byte[] var2;
        try {
            if (oos == null) {
                oos = new ObjectOutputStream(OUT);
            } else {
                oos.reset();
            }

            oos.writeObject(obj);
            var2 = OUT.toByteArray();
        } finally {
            OUT.close();
        }

        return var2;
    }

    public static byte[] stringToBytes(String str) {
        StringBuffer sb = new StringBuffer(str);
        char c = sb.charAt(0);
        ByteBuffer buffer = ByteBuffer.allocate(sb.length() * 2);
        int index = 0;

        while (index < sb.length()) {
            buffer.putChar(sb.charAt(index++));
        }

        return buffer.array();
    }

    public static String bytesToString(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        StringBuffer sb = new StringBuffer();

        while (buffer.hasRemaining()) {
            sb.append(buffer.getChar());
        }

        return sb.toString();
    }

    public static long combineInt2Long(int low, int high) {
        return (long) low & 4294967295L | (long) high << 32 & -4294967296L;
    }

    public static int[] separateLong2int(Long val) {
        return new int[]{(int) (4294967295L & val), (int) ((-4294967296L & val) >> 32)};
    }

    public static int getLongLowInt(Long val) {
        return val == null ? 0 : (int) (4294967295L & val);
    }

    public static int getLongHighInt(Long val) {
        return val == null ? 0 : (int) ((-4294967296L & val) >> 32);
    }

    public static boolean isIntInList(int i, int[] list) {
        for (int aList : list) {
            if (aList == i) {
                return true;
            }
        }

        return false;
    }

    public static int[] stringToInts(String str, String regex) {
        String[] arr = str.split(regex);
        int length = arr.length;
        int[] ret = new int[length];

        for (int i = 0; i < length; ++i) {
            ret[i] = toInteger(arr[i]);
        }

        return ret;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src != null && src.length > 0) {
            for (byte aSrc : src) {
                int v = aSrc & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !"".equals(hexString)) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for (int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    public static double strToDouble(String str) {
        if (str != null && !str.isEmpty()) {
            int len = str.length();
            int p = str.indexOf(37);
            return p == len - 1 ? Double.valueOf(str.substring(0, len - 1)) / 100.0D : (p > -1 ? 0.0D : ("true".equals(str) ? 1.0D : toDouble(str)));
        } else {
            return 0.0D;
        }
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String cacheString(int i) {
        return String.valueOf(i);
    }

    public static void main(String[] args) {
        System.out.println(toInteger("2.147483647E9"));
    }

}
