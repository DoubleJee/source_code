package diy.adapter;


import diy.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 处理器适配器类    定义接口完成对不同Handler类型调用方式的适配
public interface HandlerAdapter {

    // 是否支持
    boolean supports(Object handler);

    //适配处理
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
