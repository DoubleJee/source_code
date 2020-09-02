package diy.session;


import diy.proxy.MapperProxyFactory;
import org.apache.ibatis.binding.BindingException;

import java.util.HashMap;
import java.util.Map;

// 配置类，负责记录运行时所需要的所有配置
public class Configuration {

    // 数据源
    private DataSource dataSource;

    // Mapper包名
    private String mappersPackage;

    // 注册的Mapper类
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public Configuration() {
    }

    public Configuration(DataSource dataSource, String mappersPackage) {
        this.dataSource = dataSource;
        this.mappersPackage = mappersPackage;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getMappersPackage() {
        return mappersPackage;
    }

    public void setMappersPackage(String mappersPackage) {
        this.mappersPackage = mappersPackage;
    }

    public <T> void addMapper(Class<T> type) {
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataSource=" + dataSource +
                ", mappersPackage='" + mappersPackage + '\'' +
                '}';
    }
}
