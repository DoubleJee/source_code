public class Core {

    /**
     *  Servlet 默认单例，多个请求线程情况下操作共享变量不安全，
     */

    /**
     *
     * ServletContainerInitializer 可以在servlet容器启动的时候加载一些第三方相关依赖初始化操作（添加过滤器、监听器、Servlet）
     * SpringBoot、SpringMVC实现没有web.xml启动
     *
     * Filter过滤器属于Servlet标准自带的，拦截Web请求
     *
     * 拦截器属于SpringMVC，不仅可以拦截Web请求，还可以拦截普通方法
     *
     * 执行顺序：过滤器 > 拦截器，拦截器封装的方法比过滤器的使用起来简单
     *
     *
     */

    /**
     *
     * SpringMVC启动过程
     *
     * 1.Spring-Web依赖jar包 里META-INF.services.javax.servlet.ServletContainerInitializer文件里定义了org.springframework.web.SpringServletContainerInitializer初始化器
     *
     * 2.Servlet容器的时候加载SpringServletContainerInitializer
     *
     * 3.此类有注解@HandlesTypes(WebApplicationInitializer.class)，启动方法onStartup()能拿到所有实现WebApplicationInitializer的类
     *
     * 4.SpringServletContainerInitializer的onStartup()调用所有WebApplicationInitializer子类的onStartup()，进行初始化
     *
     *
     */


}
