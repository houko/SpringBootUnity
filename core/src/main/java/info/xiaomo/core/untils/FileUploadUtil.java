package info.xiaomo.core.untils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUploadUtil {

    public final static String separator = "/";
    public final static String split = "_";
    //设置最多只允许在内存中存储的数据,单位:字节，这个参数不要设置太大
    private static final int sizeThreshold = 4096;
    //设置允许用户上传文件大小,单位:字节
    //共10M
    private static long sizeMax = 10485760;
    protected final Log log = LogFactory.getLog(getClass());
    //当上传文件超过限制时设定的临时文件位置，注意是绝对路径
    private String tempPath = null;
    //文件上传目标目录，注意是绝对路径
    private String dstPath = null;
    //新文件名称，不设置时默认为原文件名
    private String newFileName = null;
    //获取的上传请求
    private HttpServletRequest fileuploadReq = null;
    //图片文件序号
    private int picSeqNo = 1;
    private boolean isSmallPic = false;

    public FileUploadUtil() {
    }

    public FileUploadUtil(String tempPath, String destinationPath) {
        this.tempPath = tempPath;
        this.dstPath = destinationPath;
    }

    public FileUploadUtil(String tempPath, String destinationPath, HttpServletRequest fileuploadRequest) {
        this.tempPath = tempPath;
        this.dstPath = destinationPath;
        this.fileuploadReq = fileuploadRequest;
    }

    /**
     * 获得当前的文件路径（通过当前日期生成）
     */
    public static String getNowFilePath(String basePath) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String pathName = formater.format(new Date());
        File dir = new File(basePath + separator + pathName);
        if (!dir.exists())
            dir.mkdir();
        return pathName;
    }

    public static String getNewFileName(String oldFileName) {
        oldFileName = oldFileName.replaceAll("'", "").replaceAll("\"", "");
        Calendar date = Calendar.getInstance();
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);
        if (oldFileName.length() > 30)
            oldFileName = oldFileName.substring(oldFileName.length() - 30);
        return (Integer.toString(hour * 3600 + minute * 60 + second))
                + split + oldFileName;
    }

    public static String getThumbFileName(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos >= 0)
            return fileName.substring(0, pos) + "s" + fileName.substring(pos);
        else
            return fileName + "s";
    }

    /**
     * 文件上载
     *
     * @return true —— success; false —— fail.
     */
    public boolean Upload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        try {

            //如果没有上传目的目录，则创建它
            FileUtil.makeDirectory(dstPath + "/ddd");
            if (!FileUtil.makeDirectory(dstPath + "/ddd")) {
                throw new IOException("Create destination Directory Error.");
            }
            //如果没有临时目录，则创建它
            FileUtil.makeDirectory(tempPath + "/ddd");
            if (!FileUtil.makeDirectory(tempPath + "/ddd")) {
                throw new IOException("Create Temp Directory Error.");
            }
            //上传项目只要足够小，就应该保留在内存里。
            //较大的项目应该被写在硬盘的临时文件上。
            //非常大的上传请求应该避免。
            //限制项目在内存中所占的空间，限制最大的上传请求，并且设定临时文件的位置。

            //设置最多只允许在内存中存储的数据,单位:字节
            factory.setSizeThreshold(sizeThreshold);
            factory.setRepository(new File(tempPath));

            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置允许用户上传文件大小,单位:字节
            upload.setSizeMax(sizeMax);

            List fileItems = upload.parseRequest(fileuploadReq);
            Iterator it = fileItems.iterator();

            //  正则匹配，过滤路径取文件名
            String regExp = ".+\\\\(.+)$";

            //  过滤掉的文件类型
            String[] errorType = {".exe", ".com", ".cgi", ".asp", ".php", ".jsp"};
            Pattern p = Pattern.compile(regExp);
            while (it.hasNext()) {
                System.out.println("newFileName:" + newFileName);
                FileItem item = (FileItem) it.next();
                //忽略其他不是文件域的所有表单信息
                if (!item.isFormField()) {
                    String name = item.getName();
                    System.out.println("++++=====" + name);
                    long size = item.getSize();
                    //有多个文件域时，只上传有文件的
                    if ((name == null || name.equals("")) && size == 0)
                        continue;
                    Matcher m = p.matcher(name);
                    boolean result = m.find();
                    if (result) {
                        for (String anErrorType : errorType) {
                            if (m.group(1).endsWith(anErrorType)) {
                                throw new IOException(name + ": Wrong File Type");
                            }
                        }
                        String ext = "." + FileUtil.getFileType(name);
                        try {
                            //保存上传的文件到指定的目录
                            //在下文中上传文件至数据库时，将对这里改写
                            //没有指定新文件名时以原文件名来命名
                            if (newFileName == null || newFileName.trim().equals("")) {
                                item.write(new File(dstPath + "/" + m.group(1)));
                            } else {
                                String uploadFilename = "";
                                if (isSmallPic) {
                                    uploadFilename = dstPath + "/" + newFileName + "_" + picSeqNo + "_small" + ext;
                                } else {
                                    uploadFilename = dstPath + "/" + newFileName + "_" + picSeqNo + ext;
                                }
                                //生成所有未生成的目录
                                System.out.println("++++=====" + uploadFilename);
                                FileUtil.makeDirectory(uploadFilename);
                                item.write(new File(uploadFilename));
                            }
                            picSeqNo++;
                        } catch (Exception e) {
                            throw new IOException(e.getMessage());
                        }
                    } else {
                        throw new IOException("fail to upload");
                    }
                }
            }
        } catch (IOException | FileUploadException e) {
            System.out.println(e);
        }
        return true;
    }

    /**
     * 从路径中获取单独文件名
     */
    public String GetFileName(String filepath) {
        String returnStr = "*.*";
        int length = filepath.trim().length();

        filepath = filepath.replace('\\', '/');
        if (length > 0) {
            int i = filepath.lastIndexOf("/");
            if (i >= 0) {
                filepath = filepath.substring(i + 1);
                returnStr = filepath;
            }
        }
        return returnStr;
    }

    /**
     * 设置临时存贮目录
     */
    public void setTmpPath(String tmppath) {
        this.tempPath = tmppath;
    }

    /**
     * 设置目标目录
     */
    public void setDstPath(String dstpath) {
        this.dstPath = dstpath;
    }

    /**
     * 设置最大上传文件字节数，不设置时默认10M
     */
    public void setFileMaxSize(long maxsize) {
        sizeMax = maxsize;
    }

    /**
     * 设置Http 请求参数，通过这个能数来获取文件信息
     */
    public void setHttpReq(HttpServletRequest httpreq) {
        this.fileuploadReq = httpreq;
    }

    /**
     * 设置Http 请求参数，通过这个能数来获取文件信息
     */
    public void setNewFileName(String filename) {
        this.newFileName = filename;
    }

    /**
     * 设置此上传文件是否是缩略图文件，这个参数主要用于缩略图命名
     */
    public void setIsSmalPic(boolean isSmallPic) {
        this.isSmallPic = isSmallPic;
    }

    /**
     * 设置Http 请求参数，通过这个能数来获取文件信息
     */
    public void setPicSeqNo(int seqNo) {
        this.picSeqNo = seqNo;
    }

    /**
     * This method checks if the given file exists on disk. If it does it's ignored because
     * that means that the file is allready cached on the server. If not we dump
     * the text on it.
     */
    public void dumpAttributeToFile(String attributeValue, String fileName, String filePath) throws Exception {
        File outputFile = new File(filePath + separator + fileName);
        PrintWriter pw = new PrintWriter(new FileWriter(outputFile));
        pw.println(attributeValue);
        pw.close();
    }

    /**
     * 保存文件
     * This method checks if the given file exists on disk. If it does it's ignored because
     * that means that the file is allready cached on the server. If not we take out the stream from the
     * digitalAsset-object and dumps it.
     */
    public void dumpAsset(File file, String fileName, String filePath) throws Exception {
        long timer = System.currentTimeMillis();

        File outputFile = new File(filePath + separator + fileName);
        if (outputFile.exists()) {
            log.info("The file allready exists so we don't need to dump it again..");
            return;
        }

        FileOutputStream fis = new FileOutputStream(outputFile);
        BufferedOutputStream bos = new BufferedOutputStream(fis);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        int character;
        while ((character = bis.read()) != -1) {
            bos.write(character);
        }
        bos.flush();

        bis.close();
        fis.close();
        bos.close();
        log.info("Time for dumping file " + fileName + ":" + (System.currentTimeMillis() - timer));
    }

    /**
     * 保存缩略图
     */

    public void dumpAssetThumbnail(File file, String fileName, String thumbnailFile, String filePath, int width, int height, int quality) throws Exception {
        long timer = System.currentTimeMillis();
        log.info("fileName:" + fileName);
        log.info("thumbnailFile:" + thumbnailFile);

        File outputFile = new File(filePath + separator + thumbnailFile);
        if (outputFile.exists()) {
            log.info("The file allready exists so we don't need to dump it again..");
            return;
        }

        transform(filePath + separator + fileName, filePath + separator + thumbnailFile, width, height, quality);

        log.info("Time for dumping file " + fileName + ":" + (System.currentTimeMillis() - timer));
    }

    /**
     * This method removes all images in the digitalAsset directory which belongs to a certain digital asset.
     */
    public void deleteDigitalAssets(String filePath, String filePrefix) throws Exception {
        try {
            File assetDirectory = new File(filePath);
            File[] files = assetDirectory.listFiles(new FilenameFilterImpl(filePrefix));
            for (File file : files) {
                log.info("Deleting file " + file.getPath());
                file.delete();
            }
        } catch (Exception e) {
            log.error("Could not delete the assets for the digitalAsset " + filePrefix + ":" + e.getMessage(), e);
        }
    }

    public void transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight, int quality) throws Exception {
        Image image = javax.imageio.ImageIO.read(new File(originalFile));

        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }

        if (imageWidth < thumbWidth && imageHeight < thumbHeight) {
            thumbWidth = imageWidth;
            thumbHeight = imageHeight;
        } else if (imageWidth < thumbWidth)
            thumbWidth = imageWidth;
        else if (imageHeight < thumbHeight)
            thumbHeight = imageHeight;

        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
        javax.imageio.ImageIO.write(thumbImage, "JPG", new File(thumbnailFile));
    }

    private class FilenameFilterImpl implements FilenameFilter {
        private String filter = ".";

        public FilenameFilterImpl(String aFilter) {
            filter = aFilter;
        }

        public boolean accept(File dir, String name) {
            return name.startsWith(filter);
        }
    }

}
