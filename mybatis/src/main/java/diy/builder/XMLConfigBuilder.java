package diy.builder;

import diy.annotation.Select;
import diy.session.Configuration;
import diy.session.DataSource;
import diy.statement.MapperStatement;
import diy.util.ClassUtil;
import diy.util.PropertiesUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

// 负责解析配置文件 构建出Configuration对象
public class XMLConfigBuilder extends BaseBuilder {

    private String propertiesName;

    private PropertiesUtil propertiesUtil;

    public XMLConfigBuilder(String propertiesName) {
        this.propertiesName = propertiesName;
        this.configuration = new Configuration();
        propertiesUtil = new PropertiesUtil(this.propertiesName);
    }

    /**
     * 解析装配出Configuration对象
     */
    public Configuration parse() {
        // 1.解析装配DataSource
        parseDataSource();
        // 2.解析Mappers包名，注册每个Mapper接口类、每个Mapper接口SQL方法
        parseMappers();
        // 3.configuration装配成功
        return configuration;
    }


    private void parseDataSource() {
        // 1.解析配置文件
        String driver = propertiesUtil.readProperty("driver");
        String url = propertiesUtil.readProperty("url");
        String username = propertiesUtil.readProperty("username");
        String password = propertiesUtil.readProperty("password");
        // 2.创建配置源
        DataSource dataSource = new DataSource(driver, url, username, password);
        // 3.装配到configuration
        configuration.setDataSource(dataSource);
    }

    private void parseMappers() {
        // 1.获取mapper包名
        String mappersPackage = propertiesUtil.readProperty("mappersPackage");
        configuration.setMappersPackage(mappersPackage);

        // 2.注册Mapper接口类，注册Mapper注解Sql方法
        List<Class<?>> classSet = ClassUtil.getClassSet(mappersPackage);
        classSet.forEach(c -> {
            // 注册Mapper接口类型
            configuration.addMapper(c);
            // 注册Mapper接口SQL方法
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                Annotation annotation = declaredMethod.getAnnotation(Select.class);
                if (annotation != null){
                    // 返回值类型
                    Class<?> returnType = declaredMethod.getReturnType();

                    // 特殊处理 判断返回值类型是不是集合类，如果是，获取泛型参数作为返回值类型
                    if (Collection.class.isAssignableFrom(returnType)){
                        Type genericReturnType = declaredMethod.getGenericReturnType();
                        Type parameterizedType = ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];
                        returnType = (Class<?>) parameterizedType;
                    }
                    // 特殊处理


                    // 解析SQL方法 放入configuration
                    MapperStatement mapperStatement = new MapperStatement(c.getName() + "." + declaredMethod.getName(), annotation, returnType);
                    configuration.addMapperStatement(mapperStatement);
                }
            }
        });
    }
}
