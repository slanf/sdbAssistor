package com.slanf.orm;

import com.slanf.dbpool.enums.ConnectionState;
import com.slanf.dbpool.exception.DataSourceNotFoundException;
import com.slanf.dbpool.meta.ConnectionEntry;
import com.slanf.dbpool.proxy.DataSourcePoolProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Song on 2017/6/8.
 * 用于解析执行Sql语句
 */
public class SqlResolver {
    /**
     * SQL语句
     */
    private String sql;

    public Object executeSQL(String sql,String dbname) throws DataSourceNotFoundException,SQLException{
        ConnectionEntry connectionEntry = DataSourcePoolProxy.getConnectionEntry(dbname);
        connectionEntry.setState(ConnectionState.RUNNING);
        Connection connection = connectionEntry.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
        connection.commit();
        connectionEntry.setState(ConnectionState.CREATED);
        return null;
    }
}
