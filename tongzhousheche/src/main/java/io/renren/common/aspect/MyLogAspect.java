package io.renren.common.aspect;

import com.tceasy.common.utils.id.IdBuilder;
import com.tceasy.common.utils.json.JsonUtil;
import com.tceasy.common.utils.string.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志切面
 */
@Aspect
@Service
@Order(9)
public class MyLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);

    private static Map<String, String[]> parameterNameCaches = new ConcurrentHashMap<String, String[]>();

    private static LocalVariableTableParameterNameDiscoverer parameterNameDiscovere = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("execution(public * io.renren.controller..*.*(..))")
    private void controller() {
    }

    @Around("controller()")
    public Object setMDC(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            MDC.put("requestId", IdBuilder.getID());
            Object result = point.proceed();
            return result;
        } finally {
            logger.info("接口耗时{}ms;接口名称:{}", System.currentTimeMillis() - startTime, getMethodName(point));
            MDC.clear();
        }
    }

    private String getMethodName(ProceedingJoinPoint point) {
        return point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
    }

    private void printResult(ProceedingJoinPoint point, Boolean printResult, Object result) {
        if (null == printResult) {
            return;
        }
        if (!printResult) {
            return;
        }
        Class returnType = ((MethodSignature) point.getSignature()).getReturnType();
        if (StringUtil.equals(returnType.getName(), "void")) {
            return;
        }
        logger.info("返回结果:{}", JsonUtil.objectToJson(result));
    }

    /**
     * 获取客户端参数
     *
     * @param point
     * @return
     * @throws NoSuchMethodException
     */
    private Map getParam(ProceedingJoinPoint point) throws NoSuchMethodException {
        String methodLongName = point.getSignature().toLongString();
        String[] parameterNames = parameterNameCaches.get(methodLongName);
        if (parameterNames == null) {
            Method method = getMethod(point);
            parameterNames = parameterNameDiscovere.getParameterNames(method);
            parameterNameCaches.put(methodLongName, parameterNames);
        }
        Object[] args = point.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (args.length == parameterNames.length) {
            for (int i = 0, len = args.length; i < len; i++) {
                params.put(parameterNames[i], args[i]);
            }
        }
        return params;
    }

    /**
     * 获取当前执行的方法
     *
     * @param point
     * @return
     */
    private Method getMethod(ProceedingJoinPoint point) {
        return ((MethodSignature) point.getSignature()).getMethod();
    }

}
