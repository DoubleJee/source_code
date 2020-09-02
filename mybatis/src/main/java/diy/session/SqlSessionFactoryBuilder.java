package diy.session;

import diy.builder.XMLConfigBuilder;

// Sql会话工厂构建器 负责根据配置文件构建出来sql会话工厂
public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(String propertiesName){
        // 解析配置文件并构建sql会话工厂
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(propertiesName);
        return build(xmlConfigBuilder.parse());
    }

    // 构建默认sql会话工厂
    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
