package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

//方法拦截器
public interface MethodInterceptor {

    //定义方法拦截处理 拦截此方法调用 MethodInvocation
    void invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException;
}
