package use.annotation.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//单例模式默认是饿汉加载，多例原型模式默认是懒汉加载
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//懒加载
@Lazy(false)
public class UserService {
    {
        System.out.println("UserService对象初始化");
    }
}
