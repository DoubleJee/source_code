package diy.adapter;

import diy.handler.HandlerMethod;
import diy.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestMappingHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        //调用真实请求方法
        Object retVal = handlerMethod.getMethod().invoke(handlerMethod.getTarget(), null);
        return new ModelAndView((String) retVal);
    }
}
