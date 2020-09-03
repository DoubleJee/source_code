package diy.session;

import diy.executor.Executor;
import diy.statement.MapperStatement;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

// Sql会话的默认实现
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }

    @Override
    public <T> T selectOne(String statement) {
        return selectOne(statement,null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectList(String statement) {
        return selectList(statement,null);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        MapperStatement mapperStatement = configuration.getMapperStatement(statement);
        if (mapperStatement == null){
            throw new RuntimeException("该Mapper方法没有映射SQL，无法执行");
        }
        return executor.query(mapperStatement,parameter);
    }
}
