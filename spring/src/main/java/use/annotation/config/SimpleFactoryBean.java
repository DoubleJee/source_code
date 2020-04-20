package use.annotation.config;

import org.springframework.beans.factory.FactoryBean;
import use.annotation.entity.UserEntity;


//此接口定义一个bean的创建过程，实现类可以创建bean的时候完成种种细节，去自定义创建bean，适合复杂bean的创建
public class SimpleFactoryBean implements FactoryBean<UserEntity> {
    @Override
    public UserEntity getObject() throws Exception {
        return new UserEntity();
    }

    @Override
    public Class<?> getObjectType() {
        return UserEntity.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
