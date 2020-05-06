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
     * 4.SpringServletContainerInitializer的onStartup()调用所有WebApplicationInitializer子类的onStartup()，进行初始化注册DispatchServlet
     *
     * 5.DispatchServlet继承自 FrameworkServlet -> HttpServletBean -> HttpServlet
     *
     * 6.HttpServletBean的init()方法 -> initServletBean() -> FrameworkServlet的initServletBean()方法 -> onRefresh()
     * -> DispatcherServlet的onRefresh()方法 -> initStrategies()
     *
     * 7.initStrategies() 初始化DispatcherServlet的所有资源，（handler映射器、handler适配器、视图解析器等）
     */


    /**
     * SpringMVC 调用过程
     *
     * 1.DispatcherServlet 继承 FrameworkServlet 继承 HttpServlet
     * 2.HttpServlet的Service()方法 -> FrameworkServlet的doXXX()方法（可能是doGet doPost等）-> DispatcherServlet的doService()方法
     * 3.doDispatch()方法
     * 3.getHandler() 根据url找请求方法，如果找不到则返回404
     * 4.HandlerMethod是请求的具体方法，HandlerMapping存放url路径与请求方法映射
     * 5.getHandlerAdapter() 获取处理器的适配器 RequestMappingHandlerAdapter
     * 6.applyPreHandle() 执行目标拦截器前置方法 如果返回true继续往下走否则断开
     * 7.handle()执行实际请求目标方法返回ModelAndView
     * 8.applyPostHandle() 执行目标拦截器后置方法
     * 9.processDispatchResult() 渲染视图，（返回给客户端页面或者JSON数据）
     * 10.triggerAfterCompletion() 执行目标拦截器渲染完视图后置方法
     */

}
