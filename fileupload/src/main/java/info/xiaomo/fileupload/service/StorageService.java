package info.xiaomo.fileupload.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 文件落盘与读取。
 *
 * <p>这里对文件名的处理是重点。客户端提供的文件名完全不可信, 直接用它拼路径会导致目录穿越
 * (形如 {@code a.b/../../etc/passwd} 的名字, 按"最后一个点之后"取扩展名会把整段路径带进来)。
 * 因此本类的做法是: 落盘时只用自己生成的 UUID 加一个经过白名单校验的扩展名, 读取时要求文件名
 * 必须完全匹配这个格式。污染数据根本没有机会进入路径表达式, 而不是等拼好路径再回头检查。
 *
 * @author : xiaomo
 */
@Service
public class StorageService {

    /**
     * 扩展名白名单: 一个点加 1-10 位字母或数字。不匹配就当作没有扩展名。
     */
    private static final Pattern SAFE_EXTENSION = Pattern.compile("\\.[A-Za-z0-9]{1,10}");

    /**
     * 存储文件名必须是 UUID 加可选的安全扩展名, 也就是 {@link #store} 生成的那种形状。
     */
    private static final Pattern STORED_NAME = Pattern.compile(
            "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}(\\.[A-Za-z0-9]{1,10})?");

    private final Path root;

    public StorageService(@Value("${app.upload.dir}") String uploadDir) {
        this.root = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new UncheckedIOException("无法创建上传目录: " + root, e);
        }
    }

    /**
     * 保存文件, 返回存储用的文件名。
     */
    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传的文件为空");
        }
        // 文件名完全由服务端生成, 客户端的原始文件名只贡献一个经过白名单校验的扩展名
        String stored = UUID.randomUUID() + safeExtensionOf(file.getOriginalFilename());
        Path target = resolveWithinRoot(stored);
        try (var in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("保存文件失败: " + stored, e);
        }
        return stored;
    }

    public Resource loadAsResource(String fileName) {
        // 只接受本服务自己生成过的文件名形状, 任何含有分隔符或 .. 的输入都无法通过
        if (fileName == null || !STORED_NAME.matcher(fileName).matches()) {
            throw new IllegalArgumentException("非法的文件名: " + fileName);
        }
        Path target = resolveWithinRoot(fileName);
        try {
            Resource resource = new UrlResource(target.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new IllegalArgumentException("文件不存在: " + fileName);
            }
            return resource;
        } catch (IOException e) {
            throw new UncheckedIOException("读取文件失败: " + fileName, e);
        }
    }

    /**
     * 把文件名解析到存储根目录下, 并确认解析结果确实落在根目录之内。
     *
     * <p>normalize 会把 .. 折叠掉, 随后的 startsWith 保证无论输入是什么, 最终路径都不会逃出 root。
     * 这是防目录穿越的标准写法, 也是静态分析工具能够识别的形式。
     */
    private Path resolveWithinRoot(String fileName) {
        Path target = root.resolve(fileName).normalize();
        if (!target.startsWith(root)) {
            throw new IllegalArgumentException("非法的文件名: " + fileName);
        }
        return target;
    }

    /**
     * 取出扩展名。先丢掉任何路径成分, 再要求剩下的部分匹配白名单, 否则一律返回空串。
     */
    private String safeExtensionOf(String originalName) {
        if (originalName == null) {
            return "";
        }
        // 去掉目录部分, 兼容 windows 客户端传来的反斜线
        String baseName = originalName.substring(
                Math.max(originalName.lastIndexOf('/'), originalName.lastIndexOf('\\')) + 1);
        int dot = baseName.lastIndexOf('.');
        if (dot < 0) {
            return "";
        }
        String extension = baseName.substring(dot);
        return SAFE_EXTENSION.matcher(extension).matches() ? extension : "";
    }

}
