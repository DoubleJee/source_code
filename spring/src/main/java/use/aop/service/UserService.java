package use.aop.service;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public void get(){
        System.out.println("获取会员");
    }
}
