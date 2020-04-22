package use.circular_reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    public void getOrder(){
        System.out.println("获取订单");
    }
}
