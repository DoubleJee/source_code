package diy.session;

import diy.executor.SimpleExecutor;

// Sql会话工厂的默认实现，持有一个configuration，创建的会话都使用同一个configuration的数据源
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration,new SimpleExecutor(configuration));
    }
}
