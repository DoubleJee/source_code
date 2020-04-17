package use.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("use.aop")
//开启AOP 作用导入了AOP启动配置类
@EnableAspectJAutoProxy
public class TestMainConfig {
}
