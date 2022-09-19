package indi.study.system.common.customize;
import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * 拦截器使用
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AuthAnnotation {
}
