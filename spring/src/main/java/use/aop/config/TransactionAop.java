package use.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

//@Aspect
//@Component
public class TransactionAop {

//    @Autowired
//    private TransactionUtil transactionUtil;
//
//    @Pointcut("execution (* use.aop.service..*.*(..))")
//    public void transactionAop(){
//
//    }
//
//    @Around("transactionAop()")
//    public void around(ProceedingJoinPoint joinPoint) {
//        TransactionStatus transactionStatus = transactionUtil.begin();
//        try {
//            joinPoint.proceed();
//            transactionUtil.commit(transactionStatus);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            transactionUtil.rollback(transactionStatus);
//        }
//    }
}
