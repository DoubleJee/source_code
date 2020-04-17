package use.aop.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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


}
