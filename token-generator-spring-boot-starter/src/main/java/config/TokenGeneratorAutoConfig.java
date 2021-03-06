package config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import property.TokenProperties;
import service.TokenService;

@Configuration
//启用读取配置属性，并将标记该类加入读取集合
@EnableConfigurationProperties(TokenProperties.class)
public class TokenGeneratorAutoConfig {

    @Bean
    public TokenService tokenService(){
        return new TokenService();
    }

}
