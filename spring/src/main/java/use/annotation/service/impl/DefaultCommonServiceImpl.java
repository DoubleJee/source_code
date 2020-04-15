package use.annotation.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import use.annotation.service.CommonService;

@Component
//代表默认、主的意思，此处用代表 多个实现CommonService的时候，默认为CommonService的注入的实现bean
@Primary
public class DefaultCommonServiceImpl implements CommonService {
}
