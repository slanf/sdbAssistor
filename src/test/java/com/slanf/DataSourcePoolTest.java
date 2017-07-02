package com.slanf;

import com.slanf.dbpool.meta.AbstractDataSourcePool;
import com.slanf.dbpool.meta.DataSource;
import com.slanf.dbpool.meta.MapDataSourcePool;
import org.junit.Test;

/**
 * Created by Song on 2017/6/5.
 */
public class DataSourcePoolTest {
    @Test
    public void testDataSourcePool(){
        AbstractDataSourcePool dsp = new MapDataSourcePool(new DataSource("jdbc:mysql://slanf.cn:3306/test", "root","admin","com.mysql.jdbc.Driver"));
        dsp.get();
    }

    @Test
    public void testClassLoader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader1 = DataSourcePoolTest.class.getClassLoader();
        ClassLoader classLoader2 = DataSource.class.getClassLoader();
        System.out.println(classLoader==classLoader2);
    }
}
