package use.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SuperPowerConfig {

    {
        System.out.println("超能力开启");
    }

    @Bean
    public Long powerLevel(){
        return 999L;
    }
}
