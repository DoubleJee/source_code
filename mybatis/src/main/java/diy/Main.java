package diy;

import diy.entity.UserEntity;
import diy.mapper.UserMapper;
import diy.session.SqlSession;
import diy.session.SqlSessionFactory;
import diy.session.SqlSessionFactoryBuilder;

public class Main {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build("diy/mybatis_config.properties");
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.getUserById(1L);
        System.out.println(userEntity);
    }
}
