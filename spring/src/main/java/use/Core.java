package use;

public class Core {


    // BeanDefinition 是spring对bean的描述 每个定义的bean 也就是BeanDefinition

    /**
     *
     * Spring bean生命周期阶段
     *
     *
     * 1.初始化bean对象
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
     *
     *
     *
     *  初始化bean对象 -> bean对象属性赋值 -> bean初始化 -> bean使用 -> bean销毁
     *
     */
}
