package indi.study.system.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 前置通知（@Before）：在目标方法调用之前调用通知
 * 后置通知（@After）：在目标方法完成之后调用通知
 * 环绕通知（@Around）：在被通知的方法调用之前和调用之后执行自定义的方法
 * 返回通知（@AfterReturning）：在目标方法成功执行之后调用通知
 * 异常通知（@AfterThrowing）：在目标方法抛出异常之后调用通知
 * */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    /**
     * 常用类型
     * execution execution(* indi.study.system.controller.*.*(..))
     * @annotation @annotation(indi.study.system.common.customize.SystemLog)
     * @bean
     * */
    // 切点
    @Pointcut("@annotation(indi.study.system.common.customize.SystemLog)")
    public void pointcut() {}

    /**
     * 切点绑定具体执行方式
     * 前置、后置、环绕
     * */
    // 后置通知
    @AfterReturning("pointcut()")
    public void doAfterReturn() {
        log.info("在标注了@SystemLog的方法执行完成后执行... ...");
    }

    // 前置通知
    @Before("pointcut()")
    public void doBefore(ProceedingJoinPoint joinPoint) throws Exception {
        log.info("在标注了@SystemLog的方法执行之前执行... ...");
    }

    // 环绕通知
    @Around("pointcut()")
    public void doAround() {
        log.info("在标注了@SystemLog的方法执行多次... ...");
    }
}
