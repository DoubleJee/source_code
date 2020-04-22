package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

//方法调用器
public interface MethodInvocation {

    //定义方法处理
    Object process() throws InvocationTargetException, IllegalAccessException;
}
