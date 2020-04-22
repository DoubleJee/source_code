package use.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import use.aop.config.TestMainConfig;
import use.aop.interceptor.*;
import use.aop.service.ResourceService;
import use.aop.service.UserService;
import use.aop.service.impl.ResourceServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestMainConfig.class);
//        UserService userService = applicationContext.getBean("userService", UserService.class);
//        userService.get();
//        ResourceService resourceService = applicationContext.getBean("resourceServiceImpl", ResourceService.class);
//        resourceService.getResource();



        MethodInterceptor finallyMethodInterceptor = new FinallyMethodInterceptor();
        MethodInterceptor throwsMethodInterceptor = new ThrowsMethodInterceptor();
        MethodInterceptor beforeMethodInterceptor = new BeforeMethodInterceptor();
        MethodInterceptor aroundMethodInterceptor = new AroundMethodInterceptor();
        MethodInterceptor afterMethodInterceptor = new AfterMethodInterceptor();
        List<MethodInterceptor> methodInterceptorList = new ArrayList<>();
        methodInterceptorList.add(finallyMethodInterceptor);
        methodInterceptorList.add(throwsMethodInterceptor);
        methodInterceptorList.add(afterMethodInterceptor);
        methodInterceptorList.add(aroundMethodInterceptor);
        methodInterceptorList.add(beforeMethodInterceptor);
        ResourceServiceImpl resourceServiceImpl = new ResourceServiceImpl();
        ResourceService resourceServiceAOP = (ResourceService)Proxy.newProxyInstance(ResourceServiceImpl.class.getClassLoader(), ResourceServiceImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return new DefaultMethodInvocation(methodInterceptorList,resourceServiceImpl,method,args).process();
            }
        });
        resourceServiceAOP.getResource();
        // 代理+责任链实现AOP各种通知
    }
}
