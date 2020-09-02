package diy.session;

// Sql会话工厂接口 定义：用于创建SqlSession
public interface SqlSessionFactory {

    SqlSession openSqlSession();

}
