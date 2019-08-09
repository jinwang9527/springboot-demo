package com.firststringboot.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.firststringboot.controller.*..*(..))")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object beforeMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object object;
        try {
            logger.warn("TransactionAspect aop开始启动目标方法================>" + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "类的" + proceedingJoinPoint.getSignature().getName() + "方法");
            object = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            logger.warn("TransactionAspect 项目中================>" + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "类的" + proceedingJoinPoint.getSignature().getName() + "方法报错");
            logger.error(throwable.getMessage(), throwable);
            return throwable.getMessage();
        }
        return object;
    }

}
