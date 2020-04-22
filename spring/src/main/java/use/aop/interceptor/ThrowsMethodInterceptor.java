package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

public class ThrowsMethodInterceptor implements MethodInterceptor {
    @Override
    public void invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        try {
            methodInvocation.process();
        }catch (Throwable throwable){
            System.out.println("异常通知");
        }
    }
}
