package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

public class AroundMethodInterceptor implements MethodInterceptor {
    @Override
    public void invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("环绕前通知>>>>>>>>>>>");
        methodInvocation.process();
        System.out.println("环绕后通知>>>>>>>>>>>");
    }
}
