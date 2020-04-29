package config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 此接口定义了处理拦截器，在具体的目标方法（请求方法）执行之前做拦截增强
public class TokenInterceptor implements HandlerInterceptor {


    // 请求方法拦截前置操作
    // 返回true则继续往下执行拦截器或目标方法（请求方法），返回false则默认以为处理结束，不会往下继续执行拦截器或目标方法（请求方法）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            response.setStatus(401);
            response.getWriter().write("no authorized access");
            return false;
        }
        return true;
    }

    // 请求方法拦截后置操作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("<<<<<<<目标方法后置操作>>>>>>>>>");
    }

    // 视图渲染完后置操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("<<<<<<<完成视图后置操作>>>>>>>>>");
    }
}
