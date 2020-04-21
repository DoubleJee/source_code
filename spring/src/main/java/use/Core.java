package use;

public class Core {


    // BeanDefinition 是spring对bean的描述 每个定义的bean 也就是BeanDefinition

    /**
     *
     * Spring bean生命周期阶段
     *
     *
     * 1.实例化bean对象
     *
     * 2.设置bean对象属性（依赖注入）
     *
     *
     *
     * 3.检查是否Aware依赖类型（BeanNameAware，BeanFactoryAware） 执行对应的Aware方法
     *
     * 4.bean自定义初始化方法前置处理器 （某处理器实现类，检查是否ApplicationContextAware依赖类型，执行对应的Aware方法，可以让bean得到ApplicationContext）
     *
     * 5.执行bean自定义初始化init方法
     *
     * 6.bean自定义初始化方法后置处理器
     *
     *
     *
     * 7.使用
     *
     * 8.销毁
     *
     *
     *
     *  3、4、5、6属于bean初始化阶段
     *
     *  实例化bean对象 -> bean对象属性赋值 -> bean初始化 -> bean使用 -> bean销毁
     *
     */


    /**
     *
     *
     * Spring Bean 详细创建过程
     *
     * 1. 进入到刷新的方法refresh();
     * 2. 进入finishBeanFactoryInitialization()方法初始化所有单例对象
     * 3. 进入preInstantiateSingletons()方法初始化所有的单例对象（非懒加载的）
     * 4. getBean() —> doGetBean() 先查询该对象是否有被初始化过，没有的话就创建并注册入IOC容器中
     * 5. createBean() -> doCreateBean() 创建bean对象 判断对象如果是为单例的情况下调用该方法
     * 6. createBeanInstance() 使用反射机制创建bean对象
     * 6. populateBean() 给bean对象set属性值
     * 7. initializeBean() 执行bean初始化阶段方法
     * 8. invokeAwareMethods() 判断bean类是否是Aware类型依赖，如果是执行对应的Aware方法
     * 9. applyBeanPostProcessorsBeforeInitialization() bean初始化前置处理（在初始化方法之前执行处理，增强）
     * 10. invokeInitMethods() 调用bean初始化方法（自定义init方法）
     * 11. applyBeanPostProcessorsAfterInitialization() bean初始化后置处理（在初始化方法之后执行处理，增强）
     * 12. 正常使用bean对象
     * 13. 销毁bean
     */


    /**
     * Spring AOP 过程
     * 1. @EnableAspectJAutoProxy 开启AOP
     * 2. @Import(AspectJAutoProxyRegistrar.class)
     * 3. AspectJAutoProxyRegistrar实现了ImportBeanDefinitionRegistrar接口，手动注册 切面类
     * 4. 手动注册AnnotationAwareAspectJAutoProxyCreator到ioc容器中，beanId：org.springframework.aop.config.internalAutoProxyCreator
     *
     * 5. AnnotationAwareAspectJAutoProxyCreator 继承的 AbstractAutoProxyCreator 实现了BeanPostProcessor
     * AbstractAutoProxyCreator的postProcessAfterInitialization的后置处理方法
     *
     * 6. postProcessAfterInitialization()，bean初始化执行后置处理方法
     * 7. wrapIfNecessary()，判断bean是否在切入点范围内，如果是创建代理对象
     * 8. createAopProxy()，判断如果被代理的类是否实现了接口，使用JDK动态代理，否则使用CGLIB动态代理
     * 9. 创建JdkDynamicAopProxy 或者 ObjenesisCglibAopProxy
     * 10. 当调用目标方法的时候就会执行到 JdkDynamicAopProxy的invoke()方法
     * 11. 底层使用集合存放AOP通知，使用责任链模式设计模式进行依次循环调用。
     *
     */


}
