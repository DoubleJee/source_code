package cache;

import org.apache.ibatis.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {

    // namespace+id = id
    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Map<Object,Object> cacheMap = new HashMap<>();

    public RedisCache(String id){
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("放入缓存");
        cacheMap.put(key,value);
    }

    @Override
    public Object getObject(Object key) {
        return cacheMap.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cacheMap.remove(key);
    }

    @Override
    public void clear() {
        cacheMap.clear();
    }

    @Override
    public int getSize() {
        return cacheMap.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
