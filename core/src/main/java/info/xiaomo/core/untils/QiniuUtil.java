package info.xiaomo.core.untils;

import com.alibaba.fastjson.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 七牛云储存的帮助类
 *
 * @author l.cm
 * @site:www.dreamlu.net 2014年1月12日 下午11:07:36
 */
public class QiniuUtil {

    public static final String IMG_TYPE = ".jpg|.jepg|.gif|.png|.bmp";
    public static final String ALL_TYPE = ".jpg|.jepg|.gif|.png|.bmp|.gz|.rar|.zip|.pdf|.txt|.swf|.wmv";
    private static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    /**
     * 获取文件类型
     *
     * @param @param  fileName
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
    }

    /**
     * 检查文件类型
     *
     * @param @param  fileName
     * @param @param  isImg
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     */
    public static boolean checkFileType(String fileName, boolean isImg) {
        String fileType = getFileType(fileName);
        if (isImg) {
            return IMG_TYPE.indexOf(fileType.toLowerCase()) == -1;
        } else {
            return ALL_TYPE.indexOf(fileType.toLowerCase()) == -1;
        }
    }

    /**
     * 上传文件
     * @param file
     * @param fileName
     * @return
     * @throws JSONException
     * @throws AuthException
     */
//	private static String upload(String filePath, String newName) throws AuthException, JSONException {
//		// 统一水印图片
//		ImageUtil.pressText(Consts.DOMAIN_NAME, filePath);
//		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
//		PutPolicy putPolicy = new PutPolicy(Consts.QINIU_BUCKET);
//		String uptoken = putPolicy.token(mac);
//		PutExtra extra = new PutExtra();
//		PutRet ret = IoApi.putFile(uptoken, newName, filePath, extra);
//		logger.info(ret.toString());
//		String key = ret.getKey();
//		if (StrKit.isBlank(key)) {
//			throw new AuthException("用户信息获取失败！");
//		}
//		return ret.getKey();
//	}

    /**
     * 上传文件，为管理图片而抽出来了
     * @param filePath
     * @param fileName
     * @return
     * @throws AuthException
     * @throws JSONException
     */
//	public static String uploadFile(String filePath, String fileName) throws AuthException, JSONException {
//		String fileType = getFileType(fileName);
//		String newName = System.currentTimeMillis() + fileType;
//		return upload(filePath, newName);
//	}

    /**
     * 上传图片
     * @param file
     * @param fileName
     * @return
     * @throws JSONException
     * @throws AuthException
     */
//	public static String uploadImg(String filePath, String fileName) throws AuthException, JSONException {
//		String fileType = getFileType(fileName);
//		String newName = "img" + System.currentTimeMillis() + fileType;
//		return upload(filePath, newName);
//	}

    /**
     * 上传涂鸦
     * @param content
     * @param temPath
     * @return
     * @throws IOException
     * @throws AuthException
     * @throws JSONException
     */
//	public static Object uploadBase64(String content, String temPath) throws IOException, AuthException, JSONException {
//		byte[] bytes = Base64.decodeBase64(content);
//		for (int i = 0; i < bytes.length; ++i) {
//			if (bytes[i] < 0) {
//				bytes[i] += 256;
//			}
//		}
//		String newName = "img" +  System.currentTimeMillis() + ".jpg";
//		String filePath = temPath.concat(newName);
//		FileUtils.writeByteArrayToFile(new File(filePath), bytes);
//		return upload(filePath, newName);
//	}

//	public static String listObject(int count) {
//		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
//		RSFClient client = new RSFClient(mac);
//		ListPrefixRet list = client.listPrifix(Consts.QINIU_BUCKET, "img", "", count);
//		StringBuffer sb = new StringBuffer();
//		for (ListItem item : list.results) {
//			sb.append("/");
//			sb.append(item.key);
//			sb.append("ue_separate_ue");
//		}
//		String imgStr = sb.toString();
//		if (imgStr != "") {
//			imgStr = imgStr.substring(0, imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
//		}
//		return imgStr;
//	}
}
