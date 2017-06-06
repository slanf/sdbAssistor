package com.slanf.dbpool.meta;

import com.slanf.dbpool.enums.ConnectionState;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * Created by Song on 2017/5/23.
 * 数据库连接池抽象父类
 */
public abstract class AbstractDataSourcePool {
    /**
     * 最小线程池大小
     */
    protected final int corePoolSize;
    /**
     * 最大线程池大小
     */
    protected final int maxPoolSize;
    /**
     * 连接最大存活时间
     */
    protected final long keepAliveTime;
    /**
     * 时间单位
     */
    protected final TimeUnit unit;
    /**
     * 数据源
     */
    protected final DataSource dataSource;

    /**
     * 获得数据库连接
     * @return
     */
    public abstract ConnectionEntry get();

    public AbstractDataSourcePool(DataSource dataSource){
        this.corePoolSize= 1;
        this.maxPoolSize = 1;
        this.keepAliveTime = -1;
        this.unit = TimeUnit.SECONDS;
        this.dataSource = dataSource;
    }

    public AbstractDataSourcePool(int corePoolSize,
                                  int maxPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  DataSource dataSource){
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.dataSource = dataSource;
    }
}
