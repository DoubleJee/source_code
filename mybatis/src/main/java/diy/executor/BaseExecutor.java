package diy.executor;

import diy.session.Configuration;
import diy.statement.MapperStatement;
import diy.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 基本执行器 定义公共方法、缓存的默认实现
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;
    protected Map<Object, Object> cache = new HashMap<>();

    public BaseExecutor(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(MapperStatement ms, Object parameter) {
        // 查询缓存
        String cacheKey = createCacheKey(ms, parameter);
        List<E> list = (List<E>) cache.get(cacheKey);
        if (list == null) {
            // 查询数据库
            list = queryFromDatabase(ms, parameter, cacheKey);
        }
        return list;
    }

    protected <E> List<E> queryFromDatabase(MapperStatement ms, Object parameter, String cacheKey) {
        List<E> list = doQuery(ms, parameter);
        // 放入缓存
        if (list != null) {
            cache.put(cacheKey, list);
        }
        return list;
    }

    protected abstract <E> List<E> doQuery(MapperStatement ms, Object parameter);

    // 创建缓存key
    public String createCacheKey(MapperStatement ms, Object parameter) {
        boolean array = parameter != null && parameter.getClass().isArray();
        String paramStr = array ? StringUtil.getStringByArray((Object[]) parameter,":") : String.valueOf(parameter);
        return ms.getId() + ms.getSqlStatement() + paramStr + ms.getResultType();
    }
}
