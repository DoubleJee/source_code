package use.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import use.aop.config.TestMainConfig;
import use.aop.service.ResourceService;
import use.aop.service.UserService;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestMainConfig.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.get();
        ResourceService resourceService = applicationContext.getBean("resourceServiceImpl", ResourceService.class);
        resourceService.getResource();



    }
}
