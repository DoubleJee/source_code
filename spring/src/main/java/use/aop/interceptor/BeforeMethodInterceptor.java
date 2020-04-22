package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

public class BeforeMethodInterceptor implements MethodInterceptor {
    @Override
    public void invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("前置通知>>>>>>>>>>>");
        methodInvocation.process();
    }
}
