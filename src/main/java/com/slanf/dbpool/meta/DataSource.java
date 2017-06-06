package com.slanf.dbpool.meta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Song on 2017/5/17.
 */
public class DataSource {
    /**
     * the full address of target database
     */
    private String url;
    /**
     * the username of target database
     */
    private String user;
    /**
     * the password of target database
     */
    private String password;
    /**
     * the driver name of target database
     */
    private String driver;

    public DataSource(String url, String user, String password, String driver) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driver = driver;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            connection.setAutoCommit(false);
        }catch (ClassNotFoundException e){
            System.out.println("driver class "+driver+" not found");
            return null;
        }catch (SQLException e){
            System.out.println("database username or password error");
            return null;
        }
        return connection;
    }
}
