package com.app;

import com.util.DBConnectionPool;
import com.util.Database;

import java.sql.*;
import java.util.List;

public class SQLOperation implements Runnable {

    Connection connection;
    boolean useConnectionPool;

    public SQLOperation(boolean useConnectionPool) throws SQLException {
        this.useConnectionPool = useConnectionPool;
        if(useConnectionPool) {
            connection = DBConnectionPool.getConnection();
        } else {
            // simulate multiple distinct clients by creating a connection each time the database is queried
            connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.41.129:1521:orclDB", "STUDENT", "STUDENT");
        }
    }

    @Override
    public void run() {

        try (Statement statement = connection.createStatement()) {
            // scenario where connection is established for a very simple query

            // disadvantage when creating a dedicated connection
            // the time it takes to establish the connection is greater than the time is takes to execute the query

            // advantage when using connection pools
            // no need to establish a connection, just pick one from pool
            statement.execute("SELECT * FROM artists WHERE id = 0");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // if useConnectionPool == true, return connection back to pool
            // else end client connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
