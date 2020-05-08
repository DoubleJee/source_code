package main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

//包装了SpringBootConfiguration、EnableAutoConfiguration、ComponentScan等注解
//标明是一个配置类，并且开启了自动配置、扫包
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                System.out.println("应用启动");
            }
        });

        // 返回当前应用上下文
        ConfigurableApplicationContext applicationContext = springApplication.run(args);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanName：" + beanDefinitionName);
        }
    }

    @Value("${info.text}")
    private String text;

    @Bean
    public String testBean(){
        System.out.println(text);
        return null;
    }
}
