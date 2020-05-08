package listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.IOException;
import java.util.Properties;

//此接口定义了 在SpringApplication启动运行期间的监听事件
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    public MySpringApplicationRunListener(SpringApplication springApplication, String[] args){
    }

    @Override
    public void starting() {
        System.out.println("spring开始加载......");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Properties properties = new Properties();
        try {
            // 读取配置文件
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("customize.properties"));

            // 转换成Spring属性源，读取名称为customize
            PropertySource propertySource = new PropertiesPropertySource("customize",properties);

            // 加入Spring属性源集合
            environment.getPropertySources().addLast(propertySource);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
