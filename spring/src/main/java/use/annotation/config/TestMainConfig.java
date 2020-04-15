package use.annotation.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import use.annotation.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//等同于xml
@Configuration
//在此配置类把所有范围包内的bean，扫描并加入进来
@ComponentScan(value = "use.annotation",includeFilters = {@ComponentScan.Filter(classes = {Component.class,Service.class, Repository.class})},useDefaultFilters = false)

//等同于Bean，只不过bean id是要导入类的全限定类名
@Import({UserEntity.class,SimpleImportSelector.class,SimpleImportBeanDefinitionRegistrar.class,SimpleFactoryBean.class})
@EnableSuperPower
public class TestMainConfig {

    //等同于xml里的bean标签            PS：适合外部jar包，因为你没办法在别人jar上加入Component注解
    @Bean

    //Conditional接口 定义了匹配规则行为，由具体来实现，满足此匹配规则的注册bean
    @Conditional(SystemCondition.class)
    public UserEntity userEntity(){
        return new UserEntity();
    }

}
