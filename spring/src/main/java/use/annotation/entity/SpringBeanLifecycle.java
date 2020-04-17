package use.annotation.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//SpringBean生命周期
@Component
public class SpringBeanLifecycle implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean, ApplicationContextAware {

    public SpringBeanLifecycle(){
        System.out.println("1.对象初始化");
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("2.对象beanNameAware：" + name);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3.对象beanFactory：" + beanFactory);
    }


    //4.bean初始化前置处理器

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4.前置处理器阶段，传入ApplicationContext，" + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5.bean 自定义初始化方法");
    }

    //6.bean初始化后置处理器

    //7.使用

    @Override
    public void destroy() throws Exception {
        System.out.println("8.bean销毁");
    }
}
