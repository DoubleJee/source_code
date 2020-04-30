package diy.config;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> classSet, ServletContext ctx) throws ServletException {
        //调用启动方法
        classSet.forEach(c -> {
            try {
                WebApplicationInitializer webApplicationInitializer = (WebApplicationInitializer) c.getDeclaredConstructor().newInstance();
                webApplicationInitializer.onStartup(ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
