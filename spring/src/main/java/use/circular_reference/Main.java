package use.circular_reference;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        OrderService orderService = annotationConfigApplicationContext.getBean("orderServiceImpl", OrderService.class);
        UserService userService = annotationConfigApplicationContext.getBean("userServiceImpl", UserService.class);
        orderService.getOrder();
        userService.getUser();
        System.out.println("完毕");
    }
}
