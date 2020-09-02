package diy.proxy;

import diy.entity.UserEntity;
import diy.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// Mapper代理实际处理器
public class MapperProxy<T> implements InvocationHandler {

    private SqlSession sqlSession;

    private Class<T> methodInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> methodInterface) {
        this.sqlSession = sqlSession;
        this.methodInterface = methodInterface;
    }

    // 处理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("love");
        userEntity.setAge(13);
        return userEntity;
    }
}
