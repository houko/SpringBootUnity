package info.xiaomo.i18n.controller;

import info.xiaomo.core.base.Result;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * 用当前请求的地区去取对应文案。地区由 LocaleResolver 解析出来并放进 LocaleContextHolder,
 * 因此控制器里不需要把 Locale 当参数传来传去。
 *
 * @author : xiaomo
 */
@RestController
public class GreetingController {

    private final MessageSource messageSource;

    public GreetingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/greeting/{name}")
    public Result<String> greet(@PathVariable("name") String name) {
        Locale locale = LocaleContextHolder.getLocale();
        return new Result<>(messageSource.getMessage("greeting.welcome", new Object[]{name}, locale));
    }

}