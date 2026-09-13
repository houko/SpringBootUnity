package info.xiaomo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记一个方法需要被切面记录日志与耗时。业务代码里加一个注解即可, 具体逻辑统一写在切面中,
 * 这就是 AOP 把横切关注点从业务里剥离出来的价值。
 *
 * @author : xiaomo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

    /**
     * 日志里的别名, 留空则使用方法签名。
     */
    String value() default "";

}