package com.chenxianping.blog.component;

import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
@Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.chenxianping.blog.controller.*.*(..))")
    public void BindingResult() {
    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if(fieldError!=null){
                        return new ResultVO(ResStatus.VALIDATE_FAILED,fieldError.getDefaultMessage(),null);
                    }else{
                        return new ResultVO(ResStatus.VALIDATE_FAILED,"FAIL",null);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}