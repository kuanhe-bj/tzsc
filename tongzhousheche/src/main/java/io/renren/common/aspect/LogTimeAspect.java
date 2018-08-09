package io.renren.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhangyulong
 * @date 2018/5/4
 */
@Slf4j
@Component
@Aspect
public class LogTimeAspect {

    /**
     * controller 切入点
     */
    @Pointcut("execution( * io.renren.controller..*.*(..))")
    public void controller() {}

    /**
     * service 切入点
     */
    @Pointcut("execution( * io.renren.service..*.*(..))")
    public void service() {}

    /**
     * 统计方法耗时环绕通知
     * @param joinPoint
     */
    @Around("controller() || service()")
    public Object timeAround(ProceedingJoinPoint joinPoint) {
        long startTime;
        long E_time;
        Object obj;
        try {
            // 获取开始时间
            startTime = System.currentTimeMillis();
            // 获取返回结果集
            obj = joinPoint.proceed(joinPoint.getArgs());
            // 获取方法执行时间
            E_time = System.currentTimeMillis() - startTime;
        } catch (Throwable t) {
            // 当方法中报异常时，会抛出这里的异常，
            RuntimeException runtimeException= new RuntimeException(t.getMessage());
            runtimeException.setStackTrace(t.getStackTrace());
            throw runtimeException;
        }
        String classAndMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info("执行 " + classAndMethod + " 耗时为：" + E_time + "ms");
        return obj;
    }

}