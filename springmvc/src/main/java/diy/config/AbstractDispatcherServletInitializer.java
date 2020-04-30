package diy.config;

import diy.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AbstractDispatcherServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        String servletPrefix = "/diy";
        // 注册DispatcherServlet
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("diyDispatcherServlet", new DispatcherServlet(servletPrefix));
        // 添加路径映射
        dispatcherServlet.addMapping(servletPrefix + "/*");
        // 设置优先级
        dispatcherServlet.setLoadOnStartup(-1);
    }
}
