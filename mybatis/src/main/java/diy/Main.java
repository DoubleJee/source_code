package diy;

import diy.entity.UserEntity;
import diy.mapper.UserMapper;
import diy.session.SqlSession;
import diy.session.SqlSessionFactory;
import diy.session.SqlSessionFactoryBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build("diy/mybatis_config.properties");
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.getUserById(1L);
        System.out.println(userEntity);
        UserEntity userEntity2 = userMapper.getUserById(2L);
        System.out.println(userEntity2);
        UserEntity userEntity3 = userMapper.getUserById(1L);
        System.out.println(userEntity3);
        List<UserEntity> userEntities = userMapper.selectAllUser();
        System.out.println(userEntities);
        List<UserEntity> userEntities2 = userMapper.selectAllUser();
        System.out.println(userEntities2);
        List<UserEntity> userEntityList = sqlSession.selectList("diy.mapper.UserMapper.selectAllUser");
        System.out.println(userEntityList);
    }
}
