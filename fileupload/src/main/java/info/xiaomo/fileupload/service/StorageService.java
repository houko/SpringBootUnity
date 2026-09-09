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

/**
 * 文件落盘与读取。
 *
 * @author : xiaomo
 */
@Service
public class StorageService {

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
     *
     * <p>用随机文件名而不是客户端传来的原始文件名, 原因有二: 原始文件名可能包含 ../ 之类的路径穿越片段;
     * 多个用户上传同名文件时也会互相覆盖。
     */
    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传的文件为空");
        }
        String stored = UUID.randomUUID() + extensionOf(file.getOriginalFilename());
        Path target = root.resolve(stored).normalize();
        // 双保险: 确认落点确实在 root 之下
        if (!target.getParent().equals(root)) {
            throw new IllegalArgumentException("非法的文件名");
        }
        try (var in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("保存文件失败: " + stored, e);
        }
        return stored;
    }

    public Resource loadAsResource(String fileName) {
        Path target = root.resolve(fileName).normalize();
        if (!target.getParent().equals(root)) {
            throw new IllegalArgumentException("非法的文件名");
        }
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

    private String extensionOf(String originalName) {
        if (originalName == null) {
            return "";
        }
        int dot = originalName.lastIndexOf('.');
        return dot >= 0 ? originalName.substring(dot) : "";
    }

}
