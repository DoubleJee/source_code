package use.annotation.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SubEntity implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化完成后执行的");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁要调用的");
    }
}
