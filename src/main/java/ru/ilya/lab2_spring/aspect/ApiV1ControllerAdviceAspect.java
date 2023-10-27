package ru.ilya.lab2_spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiV1ControllerAdviceAspect {
    private final Logger logger = LoggerFactory.getLogger(ApiV1ControllerAdviceAspect.class);

    @AfterThrowing(pointcut = "execution(* ru.ilya.lab2_spring.controller.v1..*.*(..))", throwing = "ex")
    public void logControllerAdviceErrors(JoinPoint joinPoint, Exception ex) {
        logger.error("Exception in method: " + joinPoint.getSignature().toShortString() + ". Exception message: " + ex.getMessage(), ex);
    }
}
