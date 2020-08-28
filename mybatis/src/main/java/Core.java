public class Core {


    /**
     * MyBatis：是一个基于jdbc的ORM框架（对象关系映射框架）
     */

    /**
     * MyBatis启动过程：
     * 1.Resources.getResourceAsReader() 获取配置文件字符流
     * 2.SqlSessionFactoryBuilder.build() 根据配置文件字符流，构建SqlSession工厂对象
     * 3.XMLConfigBuilder.parse() -> parseConfiguration() 创建configuration配置对象，解析配置文件内容，填入configuration对象（解析出环境、数据源、事务、mapper等，装配成configuration对象）
     * 4.parseConfiguration() -> environmentsElement() 解析配置文件里的Environment、DataSource、Transaction属性
     * 5.parseConfiguration() -> mapperElement() 解析配置文件里声明的所有mapper映射文件
     * 6.XMLMapperBuilder.parse() 解析每个mapper文件字符流
     * 6.configurationElement() 解析mapper文件声明的每个sql语句，放入configuration的mappedStatements集合等
     * 6.bindMapperForNamespace() 解析mapper文件，找到mapper对应的接口类、mapper文件资源路径，放入configuration对象里的对应容器
     * 7.configuration对象装配完毕
     * 8.new DefaultSqlSessionFactory(config) 构建出SqlSession工厂对象
     *
     */
}
