package use.annotation.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import use.annotation.entity.UserEntity;

public class MyApplicationContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UserEntity userEntity = applicationContext.getBean("userEntity", UserEntity.class);
        System.out.println("bean增强器：" + userEntity);
    }
}
