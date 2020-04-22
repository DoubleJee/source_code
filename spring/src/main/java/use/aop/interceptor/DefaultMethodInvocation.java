package use.aop.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

//方法调用切入点
public class DefaultMethodInvocation implements MethodInvocation {

    private List<MethodInterceptor> methodInterceptorList;

    private int index;

    private Object target;

    private Method method;

    private Object[] args;

    public DefaultMethodInvocation(List<MethodInterceptor> methodInterceptorList,Object target,Method method,Object[] args){
        this.methodInterceptorList = methodInterceptorList;
        this.target = target;
        this.method = method;
        this.args = args;
    }


    @Override
    public Object process() throws InvocationTargetException, IllegalAccessException {
        //递归调用链
        if (index == methodInterceptorList.size()){
            return method.invoke(target,args);
        }
        methodInterceptorList.get(index++).invoke(this);
        return null;
    }
}
