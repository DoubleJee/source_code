package use.annotation.main;

import use.annotation.config.EnableSuperPower;
import use.annotation.config.SuperPowerConfig;
import use.annotation.config.TestMainConfig;
import use.annotation.entity.UserEntity;
import use.annotation.mapper.UserMapper;
import use.annotation.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestMain {

    public static void main(String[] args) {
        //获取指定注解方式配置类的应用上下文
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestMainConfig.class);
        System.out.println("启动配置类应用上下文加载完毕.... ");


        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String s : beanDefinitionNames){
            System.out.println("beanId：[" + s + "]");
        }


        UserEntity userEntity = annotationConfigApplicationContext.getBean("userEntity", UserEntity.class);
        System.out.println(userEntity);

        UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);

        UserMapper userMapper = annotationConfigApplicationContext.getBean("userMapper", UserMapper.class);
        System.out.println(userMapper);

        UserEntity userEntityImport = annotationConfigApplicationContext.getBean("use.annotation.entity.UserEntity", UserEntity.class);
        System.out.println(userEntityImport);

        Long powerLevel = annotationConfigApplicationContext.getBean("powerLevel", Long.class);
        System.out.println(powerLevel);


    }
}
