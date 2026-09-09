package info.xiaomo.validation.handler;

import info.xiaomo.core.base.Result;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理。把散落在各处的 try-catch 收敛到一处, 保证接口在出错时也返回统一的 Result 结构,
 * 而不是把 Spring 默认的错误页或堆栈抛给调用方。
 *
 * @author : xiaomo
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Valid 校验请求体失败。把每个字段的错误信息收集成 字段名 -> 提示 的映射返回。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Map<String, String>> handleInvalidBody(MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (var error : e.getBindingResult().getFieldErrors()) {
            // 同一字段有多个约束时保留第一条, 避免提示信息互相覆盖
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        return new Result<>(HttpStatus.BAD_REQUEST.value(), "参数校验失败", errors);
    }

    /**
     * @Validated 校验方法参数(路径参数 / 查询参数)失败。
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleInvalidParam(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(v -> v.getMessage())
                .collect(Collectors.joining("; "));
        return new Result<>(HttpStatus.BAD_REQUEST.value(), "参数校验失败", message);
    }

    /**
     * 兜底。日志里保留完整堆栈, 但不把内部细节返回给调用方。
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleOthers(Exception e) {
        LOGGER.error("未处理的异常", e);
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误", null);
    }

}
