package use.annotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


//此接口提供定义返回数组，通过选择的全限定类名数组，去注册bean
public class SimpleImportSelector implements ImportSelector {

    //指定全限定类名注册bean
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"use.annotation.entity.SubEntity"};
    }
}
