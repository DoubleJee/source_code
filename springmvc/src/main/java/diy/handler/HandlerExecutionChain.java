package diy.handler;

//处理器包装类，存放处理器和对应的拦截器链
public class HandlerExecutionChain {

    //处理器
    private Object handler;

    //拦截器集合 先不实现

    public HandlerExecutionChain(Object handler) {
        this.handler = handler;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }
}
