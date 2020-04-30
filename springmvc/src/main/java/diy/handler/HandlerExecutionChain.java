package diy.handler;

import diy.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

//处理器包装类，存放处理器和对应的拦截器链
public class HandlerExecutionChain {

    //处理器
    private HandlerMethod handlerMethod;


    //拦截器集合 先不实现

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public void setHandlerMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        //调用真实请求方法
        Object retVal = handlerMethod.getMethod().invoke(handlerMethod.getTarget(), null);
        return new ModelAndView((String) retVal);
    }
}
