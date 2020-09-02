public class Core {


    /**
     * MyBatis：是一个基于jdbc的ORM框架（对象关系映射框架）
     */

    /**
     * MyBatis启动过程：
     * 1.Resources.getResourceAsReader() 获取配置文件字符流
     * 2.SqlSessionFactoryBuilder.build() 构建SqlSession工厂对象
     * 3.XMLConfigBuilder.parse() -> parseConfiguration() 创建configuration配置对象，解析XML配置文件字符流，填入configuration对象（解析出环境、数据源、事务、mapper等，装配成configuration对象）
     * 4.parseConfiguration() -> environmentsElement() 解析配置文件里声明的Environment、DataSource、Transaction元素
     * 5.parseConfiguration() -> mapperElement() 解析配置文件里mappers元素声明的所有mapper映射文件
     *
     * 6.XMLMapperBuilder.parse() 解析每个mapper文件字符流
     * 7.configurationElement() 解析配置mapper文件里声明的各个元素，有 解析mapper文件声明的每个sql语句，创建为MappedStatement对象，放入configuration的mappedStatements集合，解析mapper文件的cache声明，创建Cache对象，放入caches集合
     * 8.bindMapperForNamespace() 解析mapper文件，找到mapper对应的接口类型并与MapperProxyFactory映射，然后注册到configuration的mapperRegistry（方便给Mapper做代理），mapper文件资源路径放入configuration的loadedResource
     *
     * 9.configuration对象装配完毕
     * 10.new DefaultSqlSessionFactory(config) 构建出SqlSession工厂对象
     *
     */

    /**
     * MyBatis SqlSession创建过程：
     * 1.openSession() 获取会话对象
     * 2.openSessionFromDataSource() 根据环境配置获取数据源、事务
     * 3.newExecutor() 根据事务和数据源，创建默认的SimpleExecutor，如果开启了二级缓存则走CachingExecutor
     * CachingExecutor持有一个默认的SimpleExecutor作为委派，如果配置了二级缓存则走自己的缓存，否则交给委派去做（二级缓存查不到结果也会交给委派去做），委派查询了后，会放入到自己的二级缓存
     * SimpleExecutor基于BaseExecutor，BaseExecutor里有一级缓存PerpetualCache的代码实现，会根据查询 namespace + id、查询sql、查询参数等 生成CacheKey，去查询一级缓存，查到了直接返回数据
     * 如果查不到，会去查询数据库，然后将key和结果映射起来，放入一级缓存，一旦sqlSession有增删改操作会清理一级缓存
     *
     * 4.有了数据源、事务、执行器，然后new DefaultSqlSession()创建出SqlSession对象
     *
     *
     *
     * SqlSession是我们查询数据的主要对象 通过mapper xml文件里的namespace+id，找到对应的sql语句去查询，或者走缓存，不过SqlSession线程不安全
     * sqlSession的增删改操作，都会清理一级缓存和对应的二级缓存
     *
     */

    /**
     * MyBatis Mapper创建过程：
     * 1.SqlSession.getMapper() 通过configuration的mapperRegistry，检查Mapper接口是否已经登记注册过，并找到对应的MapperProxyFactory去创建MapperProxy代理，提供对Mapper接口的代理执行
     * 2.MapperProxy代理Mapper接口的所有方法，Mapper接口的方法调用都会走MapperProxy.invoke()
     * 3.cachedMapperMethod() 根据Mapper接口方法，找到对应的MapperMethod对象，MapperMethod类是代表Mapper接口方法与XML里对应的sql语句标签信息
     * 4.execute() 根据MapperMethod的 sql类型，返回类型 选择对应的SqlSession方法，再根据MapperMethod的namespace+id作为参数，然后去执行，实际还是交给SqlSession去查询的
     *
     *
     *  Mapper接口，通过代理，是帮我们方便的拼接namespace+id，和选择相应的sqlSession方法去执行
     *
     */


    /**
     * MyBatis 二级缓存过程：
     * 1.CachingExecutor.query() 执行器执行查询，传入key和sql语句、参数
     * 2.ms.getCache() 根据sql语句获取mapper文件里声明的缓存，Cache对象
     * 3.tcm.getObject() 通过缓存管理器，去此sql语句对应的Cache包装器里，获取key映射的值，有的话将结果返回，没有的话则交给委派去一级缓存拿 （Cache包装器会在首次调用的时候创建）
     * 4.tcm.putObject() 通过委派查询到结果后，放入此sql语句对应的Cache包装器的entriesToAddOnCommit容器，这个容器会存放数据，不会立马放入二级缓存（是个Map容器）
     * 5.tcm.commit() 缓存管理器提交所有的Cache包装器数据到二级缓存
     * 6.flushPendingEntries() Cache包装器将entriesToAddOnCommit容器里存放的数据全部放入到Cache里去 （调用Cache的putObject方法）
     * 7.reset() 清空Cache包装器entriesToAddOnCommit容器
     *
     * 8.CachingExecutor.update() 执行器执行增修删，传入key和sql语句、参数
     * 9.flushCacheIfRequired() 通过缓存管理器，得到此sql语句对应的Cache包装器（第2步操作，得到对应的包装器）去设置clearOnCommit提交时清理属性，然后将entriesToAddOnCommit容器清理空
     * 等到下次执行tcm.commit()的时候，每个Cache包装器会检查自己的clearOnCommit属性，如果需要清理，Cache包装器最后会调用所包装的Cache的clear()方法
     *
     *
     *
     * 每个XML声明的Cache会创建一个Cache实例对象，Cache的id为XML的namespace
     * sqlSession的增删改操作，会清理sql对应的二级缓存，二级缓存id为sql所在的namespace
     *
     *
     * 二级缓存回收策略
     *  LRU：最近最少使用策略，移除最长时间不被使用的对象。
     *  FIFO：先进先出策略，按对象进入缓存的顺序来移除它们
     *  SOFT：软引用策略，移除基于垃圾回收器状态和软引用规则的对象
     *  WEAK：弱引用策略，更积极的移除基于垃圾回收器状态和软引用规则的对象
     */


    /**
     * MyBatis整合Spring
     *
     * 每次请求都会OpenSession，创建一个独立的SqlSession
     * 每次请求完之后都会commit数据到二级缓存，或按需清理二级缓存，并且关闭SqlSession
     *
     */
}
