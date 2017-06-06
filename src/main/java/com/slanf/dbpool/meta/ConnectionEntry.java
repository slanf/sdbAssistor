package com.slanf.dbpool.meta;

import com.slanf.dbpool.enums.ConnectionState;

import java.sql.Connection;

/**
 * Created by Song on 2017/6/5.
 */
public final class ConnectionEntry{
    private Connection connection;
    private ConnectionState state;
    private DataSource dataSource;

    public ConnectionEntry(Connection conn, ConnectionState state, DataSource dataSource) {
        this.connection = conn;
        this.state = state;
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ConnectionState getState() {
        return state;
    }

    public void setState(ConnectionState state) {
        this.state = state;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
