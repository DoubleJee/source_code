package use.annotation.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import use.annotation.entity.SubEntity;
//此接口定义 提供了手动注册bean的能力，提供方法去自定义注册bean，方法入参有bean注册器，子类实现去自定义加载注册bean
public class SimpleImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    //beanDefinition 是spring对bean的描述 每个定义的bean 也就是beanDefinition
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println(importingClassMetadata);
        System.out.println(registry);
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(SubEntity.class);
        registry.registerBeanDefinition("importBeanSubEntity",rootBeanDefinition);
    }
}
