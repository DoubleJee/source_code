package servlet.config;

import servlet.config.handles_type.MyHandlerType;
import servlet.LoginHttpServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

//此接口定义了，在Servlet容器初始化的时候要执行的方法

//实现或继承了此注解标记的类（不包含标记的），在容器启动的时候，都会传入到onStartup()方法的Set<Class>集合
@HandlesTypes(MyHandlerType.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    //可以在servlet容器启动的时候加载一些第三方相关依赖初始化工作，在扩展的启动方法里手动添加servlet、监听器、过滤器等
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        c.forEach(nc -> {
            try {
                MyHandlerType myHandlerType = (MyHandlerType) nc.getDeclaredConstructor().newInstance();
                myHandlerType.handle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //手动添加Servlet
        ServletRegistration.Dynamic loginHttpServlet = ctx.addServlet("loginHttpServlet", new LoginHttpServlet());
        loginHttpServlet.addMapping("/my_servlet/login");
    }


    //必须配置在META-INF.services.javax.servlet.ServletContainerInitializer，写入此类的全限定类名，Servlet容器启动的时候会加载此初始化类
}
