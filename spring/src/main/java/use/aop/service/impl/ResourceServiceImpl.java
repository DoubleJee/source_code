package use.aop.service.impl;

import org.springframework.stereotype.Component;
import use.aop.service.ResourceService;
@Component
public class ResourceServiceImpl implements ResourceService {

    @Override
    public void getResource() {
        System.out.println("获取资源");
    }
}
