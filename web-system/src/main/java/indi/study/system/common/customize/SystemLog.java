package indi.study.system.common.customize;
import org.springframework.stereotype.Component;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SystemLog {
}
