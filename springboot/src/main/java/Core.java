public class Core {

    /**
     * SpringBoot:
     * 1.能够实现帮助开发者快速的整合第三方框架、组件（Spring、Mybatis）。原理：封装maven依赖进行整合和自定义Starter
     * 2.完全去除XML配置，采用注解形式。原理：基于Spring体系原生注解方式实现的包装
     * 3.内嵌Tomcat服务器。原理：Java语言创建tomcat服务器，然后将本地class文件交给tomcat加载。（使用的Apache基金会的嵌入Tomcat jar包）
     *
     *
     *  Starter就是SpringBoot对第三方框架组件的描述（命名方式、抽象定义）
     *
     *  spring-boot-starter-xxx 官方格式
     *  xxx-spring-boot-starter 个人格式
     */


    /**
     * 自定义springboot-starter：
     * 1. 生成/resources/META-INF/spring.factories文件
     * 2. org.springframework.boot.autoconfigure.EnableAutoConfiguration=你自己的配置类
     * 3. Springboot会默认加载你的配置类，不需要指定显示扫包
     */

    /**
     * 核心注解：
     * SpringBootApplication注解包装了@SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
     *
     * SpringBootConfiguration注解包装了@Configuration
     * EnableAutoConfiguration注解使用了Spring的导入选择器实现了自动配置，
     * 拿到/META-INF/spring.factories文件里的org.springframework.boot.autoconfigure.EnableAutoConfiguration映射的类名称集合，放入导入选择器
     *
     *
     * org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
     * 帮我们创建内置Tomcat
     *
     * org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
     * 自动配置创建DispatcherServlet
     */

    /**
     * SpringBoot启动流程：
     * 1.创建SpringApplication对象
     * 2.deduceWebApplicationType() 推断应用类型WebApplicationType，根据当前classpath下有没有加载相应的类来推断
     * WebApplicationType NONE：不会嵌入WEB服务器。SERVLET：基于servlet的WEB应用，嵌入Servlet WEB服务器。REACTIVE：基于响应式的WEB应用，嵌入响应式WEB服务器
     * 3.setInitializers() 设置初始化器集合 -> getSpringFactoriesInstances(ApplicationContextInitializer.class) 读取所有依赖Jar里的/META-INF/spring.factories文件配置的指定类集合（ApplicationContextInitializer类对应的映射类），并实例化它们然后返回。
     * 4.setListeners() 设置监听者集合 -> getSpringFactoriesInstances(ApplicationListener.class) 同上，得到监听者对象集合，并设置到监听者集合
     * 5.deduceMainApplicationClass() 推断启动主类
     *
     *
     * 6.调用run()方法
     * 7.new StopWatch().start()，记录项目的启动时间
     * 8.getRunListeners() 读取spring.factories，获取启动监听器实例集合，
     * 9.listeners.starting() 循环调用启动监听器的starting方法
     * 10.prepareEnvironment() 循环调用启动监听器的environmentPrepared环境准备方法，（读取配置文件加载到SpringBoot配置源中，application.yml、xml、yaml、properties）
     */
}
