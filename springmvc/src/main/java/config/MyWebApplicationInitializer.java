package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//该类会在servlet容器启动时调用，具体见org.springframework.web.SpringServletContainerInitializer
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    //当我们的WebAPP应用启动时，初始化Spring、SpringMVC。
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 初始化SpringMVC上下文环境
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // 注册配置文件
        applicationContext.register(SpringMVCConfig.class);

        // 添加DispatcherServlet
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext));
        // 添加路径映射
        dispatcherServlet.addMapping("/");
        // 设置优先级
        dispatcherServlet.setLoadOnStartup(1); //设置最高优先级，最早加载


    }
}
