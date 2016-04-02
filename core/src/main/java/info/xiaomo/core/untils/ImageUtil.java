package info.xiaomo.core.untils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 图片处理
 *
 * @author l.cm
 * @site:www.dreamlu.net 2014年3月20日 下午9:54:30
 */
public final class ImageUtil {

    /**
     * 文字水印
     *
     * @param pressText
     * @param targetImg
     */
    public static void pressText(String pressText, String targetImg) {
        try {
            File img = new File(targetImg);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(Color.black);
            g.setFont(new Font("黑体", 10, 20));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
            g.drawString(pressText, 10, 20);
            g.dispose();
            ImageIO.write(image, "jpg", img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}