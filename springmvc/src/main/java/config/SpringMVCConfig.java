package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//标明是个配置类 等同于xml
@Configuration
//扫包范围
@ComponentScan("controller")
//开启SpringMVC
@EnableWebMvc
//WebMvcConfigurer 定义是一个webmvc配置器类，具有配置能力，可以在初始化时注册一些Web组件
public class SpringMVCConfig implements WebMvcConfigurer {

    //视图解析器
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
    }

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，配置拦截规则（所有）
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**");
    }
}
