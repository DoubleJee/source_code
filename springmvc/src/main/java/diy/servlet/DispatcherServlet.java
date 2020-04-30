package diy.servlet;

import diy.config.DiySpringMVCConfig;
import diy.handler.HandlerMethod;
import diy.handler.RequestMappingHandlerMapping;
import diy.handler.HandlerExecutionChain;
import diy.view.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends FrameworkServlet {

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    private String servletPrefix;

    public DispatcherServlet(String servletPrefix) {
        this.servletPrefix = servletPrefix;
        this.requestMappingHandlerMapping = new RequestMappingHandlerMapping();
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

        // 1.获取handler
        HandlerExecutionChain handler = getHandler(req);
        if (handler == null) {
            noHandlerFound(req, resp);
            return;
        }

        // 2.调用handler
        ModelAndView modelAndView = handler.handle(req, resp);

        // 3.渲染视图
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

    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendError(404);
    }

    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/view/" + mv.getViewName() + ".jsp").forward(request, response);
    }
}
