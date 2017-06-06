package com.slanf.dbpool.proxy;

import com.slanf.dbpool.exception.DataSourceNotFoundException;
import com.slanf.dbpool.meta.AbstractDataSourcePool;
import com.slanf.dbpool.meta.ConnectionEntry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Song on 2017/6/5.
 * 数据库连接池代理
 * 1. 支持多数据源
 * 2. 维护事务提交
 * 3. 统计量
 */
public class DataSourcePoolProxy {
    /**
     * 获取数据连接的次数
     */
    private final AtomicLong getConnectionNum = new AtomicLong(0);
    /**
     * 多数据源，key为数据源名称，val为数据源
     */
    private final ConcurrentHashMap<String,AbstractDataSourcePool> pools = new ConcurrentHashMap<String, AbstractDataSourcePool>();

    /**
     * 获得对应数据源连接
     * @param dbname 数据源名称
     * @return
     * @throws DataSourceNotFoundException
     */
    public ConnectionEntry getConnectionEntry(String dbname) throws DataSourceNotFoundException{
        getConnectionNum.incrementAndGet();
        AbstractDataSourcePool pool = pools.get(dbname);
        if(null != pool)
            return pool.get();
        else throw new DataSourceNotFoundException("datasource with name "+dbname+" not found");
    }

    /**
     * 新增数据源
     * @param name 数据源名称
     * @param pool 数据源
     * @return
     */
    public AbstractDataSourcePool addNewPool(String name,AbstractDataSourcePool pool){
        return this.pools.put(name,pool);
    }
}
