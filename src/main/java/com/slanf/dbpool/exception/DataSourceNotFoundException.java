package com.slanf.dbpool.exception;

/**
 * Created by Song on 2017/6/5.
 * 数据源未找到
 */
public class DataSourceNotFoundException extends Exception {
    public DataSourceNotFoundException() {
    }

    public DataSourceNotFoundException(String message) {
        super(message);
    }

    public DataSourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
