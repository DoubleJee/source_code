package use.annotation.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

//系统匹配规则
public class SystemCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //如果是win10系统满足，不是则不满足
        String property = context.getEnvironment().getProperty("os.name");
        if (property.contains("Windows 10")){
            //满足匹配
            return true;
        }
        //不满足
        return false;
    }
}
