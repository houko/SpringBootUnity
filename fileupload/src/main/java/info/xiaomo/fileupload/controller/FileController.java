package info.xiaomo.fileupload.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.fileupload.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 单文件 / 多文件上传与下载。上传大小限制见 config/application.properties 的 spring.servlet.multipart.*。
 *
 * @author : xiaomo
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private final StorageService storage;

    public FileController(StorageService storage) {
        this.storage = storage;
    }

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        return new Result<>(storage.store(file));
    }

    @PostMapping("/upload/batch")
    public Result<List<String>> uploadBatch(@RequestParam("files") MultipartFile[] files) {
        return new Result<>(Arrays.stream(files).map(storage::store).toList());
    }

    /**
     * 下载。用 attachment 让浏览器走下载而不是内联展示。
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        Resource resource = storage.loadAsResource(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleBadRequest(IllegalArgumentException e) {
        return new Result<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
    }

}
