package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

public class AfterMethodInterceptor implements MethodInterceptor {
    @Override
    public void invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        methodInvocation.process();
        System.out.println("后置通知<<<<<<<<<<<");
    }
}
