package com.slanf.dbpool.meta;

import com.slanf.dbpool.enums.ConnectionState;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Song on 2017/6/1.
 * 采用阻塞队列实现数据库连接池
 */
public class MapDataSourcePool extends AbstractDataSourcePool{
    /**
     * 数据库连接池
     */
    private final ConcurrentHashMap<ConnectionEntry,Object> pool = new ConcurrentHashMap();
    /**
     * 可用连接数
     */
    private final AtomicLong activeNum = new AtomicLong(0);
    /**
     * 忙碌中连接数
     */
    private final AtomicLong runningNum = new AtomicLong(0);
    /**
     * 总连接数
     */
    private final AtomicLong managedNum = new AtomicLong(0);
    /**
     * 等待获取连接数
     */
    private final AtomicLong waitingNum = new AtomicLong(0);

    public MapDataSourcePool(DataSource dataSource) {
        super(dataSource);
        init();
    }

    public MapDataSourcePool(int corePoolSize,
                             int maxPoolSize,
                             long keepAliveTime,
                             TimeUnit unit,
                             DataSource dataSource,
                             BlockingQueue<ConnectionEntry> pool) {
        super(corePoolSize, maxPoolSize, keepAliveTime, unit, dataSource);
        init();
    }

    /**
     * 初始化连接池大小
     */
    private void init(){
        if(corePoolSize > maxPoolSize) throw new IllegalArgumentException("corePoolSize must not be bigger than maxPoolSize");
        int i = 0;
        while ((i++)<corePoolSize){
            pool.put(new ConnectionEntry(dataSource.getConnection(),ConnectionState.CREATED,dataSource),new Object());
            activeNum.incrementAndGet();
            managedNum.incrementAndGet();
        }
    }

    @Override
    public ConnectionEntry get() {
        if(activeNum.get()>0){
            return scanAndGetConnectionEntry();
        }else{
            return handleAndGetConnectionEntry();
        }
    }

    private ConnectionEntry scanAndGetConnectionEntry(){
        if(activeNum.decrementAndGet()>=0) {
            for (ConnectionEntry ce : pool.keySet()) {
                if (ce.getState().equals(ConnectionState.CREATED)) {
                    synchronized (ce) {
                        if(ce.getState().equals(ConnectionState.CREATED)) {
                            ce.setState(ConnectionState.RUNNING);
                            runningNum.incrementAndGet();
                            return ce;
                        }return scanAndGetConnectionEntry();
                    }
                }
            }
        }else activeNum.incrementAndGet();
        return createAndGetConnectionEntry();
    }

    private ConnectionEntry handleAndGetConnectionEntry(){
        synchronized (managedNum) {
            if (managedNum.get() < maxPoolSize) {
                return createAndGetConnectionEntry();
            }else{
                //TODO 等待获取连接
                waitingNum.incrementAndGet();
                return null;
            }
        }
    }

    private ConnectionEntry createAndGetConnectionEntry(){
        ConnectionEntry connectionEntry = new ConnectionEntry(dataSource.getConnection(),ConnectionState.RUNNING,dataSource);
        managedNum.incrementAndGet();
        runningNum.incrementAndGet();
        pool.put(connectionEntry,new Object());
        return connectionEntry;
    }


}
