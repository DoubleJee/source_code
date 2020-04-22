package use.circular_reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private OrderService orderService;

    public void getUser(){
        System.out.println("获取用户");
    }
}
