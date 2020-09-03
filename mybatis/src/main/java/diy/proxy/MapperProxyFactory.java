package diy.proxy;

import diy.session.SqlSession;

import java.lang.reflect.Proxy;

// Mapper代理工厂 用于创建Mapper代理
public class MapperProxyFactory<T> {

    // 要代理的接口
    private Class<T> methodInterface;

    public MapperProxyFactory(Class<T> methodInterface) {
        this.methodInterface = methodInterface;
    }

    // 创建代理对象，传入Mapper实际处理器
    protected T newInstance(MapperProxy mapperProxy) {
        return (T) Proxy.newProxyInstance(methodInterface.getClassLoader(), new Class[]{methodInterface}, mapperProxy);
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, methodInterface);
        return newInstance(mapperProxy);
    }

}
