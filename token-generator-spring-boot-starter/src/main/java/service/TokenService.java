package service;

import org.springframework.beans.factory.annotation.Autowired;
import property.TokenProperties;

public class TokenService {

    @Autowired
    private TokenProperties tokenProperties;

    public String generateToken(){
        if (tokenProperties.getRedisHost() == null|| tokenProperties.getRedisPwd() == null){
            return tokenProperties.getDefaultToken();
        }
        return tokenProperties.getRedisHost() + "," + tokenProperties.getRedisPwd();
    }

}
