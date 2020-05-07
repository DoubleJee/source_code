package property;

import org.springframework.boot.context.properties.ConfigurationProperties;

// 此注解定义一个读取外部配置文件的类
// 属性按照驼峰解析，redisHost = redis-host， redisPwd = redis-pwd
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    private String redisHost;

    private String redisPwd;

    private String defaultToken;

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getRedisPwd() {
        return redisPwd;
    }

    public void setRedisPwd(String redisPwd) {
        this.redisPwd = redisPwd;
    }

    public String getDefaultToken() {
        return defaultToken;
    }

    public void setDefaultToken(String defaultToken) {
        this.defaultToken = defaultToken;
    }
}
