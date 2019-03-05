package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//目标、使用范围
@Target(value = {ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
//生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    //带默认参数
    int id() default 0;
    String name() default "";
    //不带默认参数
    int age();
}
