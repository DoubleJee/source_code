package diy.handler;

import java.lang.reflect.Method;

//处理方法
public class HandlerMethod {

    private Object target;

    private Method method;

    public HandlerMethod(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
