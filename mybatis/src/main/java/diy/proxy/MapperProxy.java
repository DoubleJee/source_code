package diy.proxy;

import diy.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

// Mapper代理实际处理器 将Mapper的方法调用交于SqlSession对应方法执行
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
        // 如果查询结果是集合，走selectList
        if (Collection.class.isAssignableFrom(method.getReturnType())) {
            return sqlSession.selectList(methodInterface.getName() + "." + method.getName(),args);
        }
        // 单个调用selectOne
        return sqlSession.selectOne(methodInterface.getName() + "." + method.getName(),args);
    }
}
