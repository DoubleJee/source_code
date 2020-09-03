package diy.executor;

import diy.statement.MapperStatement;

import java.util.List;

// 执行器 定义真正用来操作数据 （数据库、缓存）
public interface Executor {

    <E> List<E> query(MapperStatement ms, Object parameter);
}
