package diy.builder;

import diy.session.Configuration;
import diy.session.DataSource;
import diy.util.ClassUtil;
import diy.util.PropertiesUtil;

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
        // 2.解析Mappers包名，将每个Mapper接口类注册
        parseMappers();
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
        // 1.获取mapper包名并且装配
        String mappersPackage = propertiesUtil.readProperty("mappersPackage");
        configuration.setMappersPackage(mappersPackage);

        // 2.注册Mapper接口类
        List<Class<?>> classSet = ClassUtil.getClassSet(mappersPackage);
        classSet.forEach(c -> configuration.addMapper(c));
    }
}
