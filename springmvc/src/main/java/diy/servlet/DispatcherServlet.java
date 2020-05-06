package diy.servlet;

import diy.adapter.HandlerAdapter;
import diy.adapter.RequestMappingHandlerAdapter;
import diy.config.DiySpringMVCConfig;
import diy.handler.HandlerMethod;
import diy.handler.RequestMappingHandlerMapping;
import diy.handler.HandlerExecutionChain;
import diy.view.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends FrameworkServlet {

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    private List<HandlerAdapter> handlerAdapters;

    private String servletPrefix;

    public DispatcherServlet(String servletPrefix) {
        this.servletPrefix = servletPrefix;
        //handler映射器
        this.requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        //适配器
        this.handlerAdapters = new ArrayList<>();
        handlerAdapters.add(new RequestMappingHandlerAdapter());
    }

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    protected void initStrategies() {
        requestMappingHandlerMapping.initHandlerMapping(DiySpringMVCConfig.class, servletPrefix);
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 1.获取handler（包装类）
        HandlerExecutionChain handler = getHandler(req);
        if (handler == null) {
            noHandlerFound(req, resp);
            return;
        }

        // 2.获取Handler适配器
        HandlerAdapter handlerAdapter = getHandlerAdapter(handler.getHandler());

        // 3.适配调用handler
        ModelAndView modelAndView = handlerAdapter.handle(req,resp,handler.getHandler());

        // 4.渲染视图
        render(modelAndView, req, resp);
    }

    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        HandlerMethod handler = requestMappingHandlerMapping.getHandler(uri);
        if (handler == null) {
            return null;
        }
        return new HandlerExecutionChain(handler);
    }

    protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
        for (HandlerAdapter handlerAdapter : handlerAdapters){
            if (handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        return null;
    }

    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendError(404);
    }

    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/view/" + mv.getViewName() + ".jsp").forward(request, response);
    }
}
