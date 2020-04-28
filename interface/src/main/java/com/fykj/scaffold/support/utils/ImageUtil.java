package com.fykj.scaffold.support.utils;

import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.support.conns.Cons;
import constants.Mark;
import exception.BusinessException;
import org.springframework.util.Assert;
import result.ResultCode;
import utils.StringUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 图片压缩处理工具类
 *
 * @author zhangzhi
 * @date 2019/10/24
 */
public class ImageUtil {

    /**
     * 按照宽度或者高度进行等比例压缩
     *
     * @param fileName 文件名（包含路径）
     * @param w        目标宽度（超过原宽度时不压缩）
     * @param h        目标高度（超过原高度时不压缩）
     */
    public static String resizeFix(String fileName, int w, int h) {
        if (w == 0) {
            w = Integer.MAX_VALUE;
        }
        if (h == 0) {
            h = Integer.MAX_VALUE;
        }
        return resizeFix(fileName, null, w, h);
    }

    /**
     * 按照宽度或者高度进行等比例压缩
     *
     * @param fileName 文件名（包含路径）
     * @param target   处理后保存目标路径 （包含名称，为null时按默认规则生成新文件名）
     * @param w        目标宽度（超过原宽度时不压缩）
     * @param h        目标高度（超过原高度时不压缩）
     */
    public static String resizeFix(String fileName, String target, int w, int h) {
        // 构造Image对象
        Image img = validateImage(fileName);
        // 得到源图宽
        int width = img.getWidth(null);
        //得到源图长
        int height = img.getHeight(null);
        if (((double) width) / height > ((double) w) / h) {
            h = getHeight(width, height, w);
        } else {
            w = getWidth(width, height, h);
        }
        return resize(fileName, target, w, h);
    }

    /**
     * 强制压缩图片到固定的大小
     *
     * @param path 文件名（包含路径）
     * @param w    目标宽度（超过原宽度时不压缩）
     * @param h    目标高度（超过原高度时不压缩）
     */
    public static String resize(String path, int w, int h) {
        return resize(path, null, w, h);
    }

    /**
     * 强制压缩图片到固定的大小
     *
     * @param path   文件名（包含路径）
     * @param target 处理后保存目标路径 （包含名称，为null时按原路径保存-覆盖原文件）
     * @param w      目标宽度（超过原宽度时不压缩）
     * @param h      目标高度（超过原高度时不压缩）
     */
    public static String resize(String path, String target, int w, int h) {
        if (StringUtil.isEmpty(target)) {
            target = generateFileName(path, w, h);
        }

        if (fileExists(target)) {
            return target;
        }

        Image img = validateImage(path);
        // 得到源图宽
        w = Math.min(w, img.getWidth(null));
        h = Math.min(h, img.getHeight(null));
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // 绘制缩小后的图
        image.getGraphics().drawImage(img, 0, 0, w, h, null);
        File targetFile = new File(target);
        File parent = targetFile.getParentFile();
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new BusinessException(ResultCode.FAIL, "创建文件夹失败" + parent.getName());
            }
        }
        String ext = getExt(target);
        try {
            ImageIO.write(image, ext, targetFile);
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "Image write error.", e);
        }
        return target;
    }

    /**
     * 获取文件后缀名
     *
     * @param filePath 文件路径
     * @return 文件后缀名（不带点）
     */
    public static String getExt(String filePath) {
        Assert.notNull(filePath, "无法获取文件名");
        int i = filePath.lastIndexOf(Mark.DOT_CHAR);
        if (i < 0) {
            throw new BusinessException(ResultCode.FAIL, "无法获取文件后缀");
        }
        return filePath.substring(i + 1);
    }

    /**
     * 根据后缀名判断是否是图片
     *
     * @param fileName 文件名
     * @return 是:true/否:false
     */
    public static boolean isImage(String fileName) {
        String ext = getExt(fileName);
        String typeCode = SystemUtil.getBean(IDictService.class).findFileTypeByValue(ext);
        return StringUtil.equals(typeCode, Cons.FILE_TYPE_IMAGE);
    }

    /**
     * 验证图片路径并转换成{@link Image} 对象
     *
     * @param filePath 文件路径
     * @return {@link Image} 对象
     */
    private static Image validateImage(String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            throw new BusinessException(ResultCode.FAIL, "FileName must not be empty.");
        }
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "读取图片失败", e);
        }
    }

    /**
     * 生成文件名称（包含路径）
     *
     * @param path 原始文件路径（包含名称）
     * @param w    新文件宽度
     * @param h    新文件高度
     * @return 新文件名称（含文件路径）
     */
    private static String generateFileName(String path, int w, int h) {
        String ext = getExt(path);
        return path.substring(0, path.lastIndexOf(Mark.DOT_CHAR)) + "-w-" + w + "-h-" + h + Mark.DOT + ext;
    }

    /**
     * 根据原图比例和新图高度获取新图宽度
     *
     * @param imageWidth  原图宽度
     * @param imageHeight 原图高度
     * @param h           新图高度
     * @return 新图宽度
     */
    private static int getWidth(int imageWidth, int imageHeight, int h) {
        BigDecimal proportion = BigDecimal.valueOf(imageWidth)
                .divide(BigDecimal.valueOf(imageHeight), 5, BigDecimal.ROUND_HALF_EVEN);
        return BigDecimal.valueOf(h).multiply(proportion).intValue();
    }

    /**
     * 根据原图比例和新图宽度获取新图高度
     *
     * @param imageWidth  原图宽度
     * @param imageHeight 原图高度
     * @param w           新图宽度
     * @return 新图高度
     */
    private static int getHeight(int imageWidth, int imageHeight, int w) {
        BigDecimal proportion = BigDecimal.valueOf(imageWidth)
                .divide(BigDecimal.valueOf(imageHeight), 5, BigDecimal.ROUND_HALF_EVEN);
        return BigDecimal.valueOf(w).divide(proportion, BigDecimal.ROUND_HALF_EVEN).intValue();

    }

    /**
     * 判断文件是否存在
     *
     * @param fileName 文件名称（含路径）
     * @return 存在:true/不存在:false
     */
    private static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }
}
