package main;

import entity.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class TestMain {

    public static void main(String[] args) throws IOException {
        // 1.定义mybatis配置文件路径
        String resource = "mybatis-config.xml";
        // 2.获取配置文件的字符流
        Reader reader = Resources.getResourceAsReader(resource);
        // 3.构建SqlSession工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 4.生成一个SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5.获取mapper对象，操作数据库
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1L);
        System.out.println(user);
        User user2 = userMapper.getUserById(2L);
        System.out.println(user2);
    }
}
