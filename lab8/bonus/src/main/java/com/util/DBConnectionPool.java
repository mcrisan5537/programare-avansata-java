package com.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {

    private DBConnectionPool() {
    }

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@172.16.41.129:1521:orclDB");
        dataSource.setUsername("STUDENT");
        dataSource.setPassword("STUDENT");
        dataSource.setInitialSize(25);
        dataSource.setMinIdle(25);
        dataSource.setMaxIdle(50);
        dataSource.setMaxTotal(50);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
