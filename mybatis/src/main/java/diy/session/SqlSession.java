package diy.session;

import java.util.List;

// Sql会话，定义：提供用于操作数据库的统一接口
public interface SqlSession {

    // 获取Mapper，方便调用下面的定义的方法
    <T> T getMapper(Class<T> type);

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <E> List<E> selectList(String statement);

    <E> List<E> selectList(String statement, Object parameter);
}
