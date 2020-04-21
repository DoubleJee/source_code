package use.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAop {

    @Pointcut("execution (* use.aop.service..*.*(..))")
    public void logAop(){

    }

    @Before("logAop()")
    public void doBefore(){
        System.out.println("前置通知");
    }

    @After("logAop()")
    public void doAfter(){
        System.out.println("后置通知");
    }

    @Around("logAop()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("哈哈环绕前");
        joinPoint.proceed();
        System.out.println("哈哈环绕后");

    }

}
