package com.records.demo.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 切点
     */

    @Pointcut("execution(* com.records..demo.*(..))")
    public void pointcut (){

    }

    /**
     * 通知
     */
    @Before("pointcut()")
        public void beforeAdvice(JoinPoint joinPoint) {
        /**
         *
         */
        System.out.println("beforeAdvice...");
    }
}
