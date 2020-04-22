package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

public class FinallyMethodInterceptor implements MethodInterceptor {
    @Override
    public void invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        try{
            methodInvocation.process();
        }finally{
            System.out.println("最终通知<<<<<<<<<<<");
        }
    }
}
