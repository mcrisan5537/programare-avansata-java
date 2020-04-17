package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;

    private Database() {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.41.129:1521:orclDB", "STUDENT", "STUDENT");
        }
        return connection;
    }

}
