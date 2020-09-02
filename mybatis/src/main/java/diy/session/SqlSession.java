package diy.session;

// Sql会话，定义：用于操作数据库
public interface SqlSession {

    // 获取Mapper
    <T> T getMapper(Class<T> type);
}
