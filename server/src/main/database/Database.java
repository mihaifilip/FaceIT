package database;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;


/**
 * Created by mihfilip on 15/10/2015.
 */
public class Database {

    protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mysql://localhost/faceit";
    protected static final String DB_USER = "sqluser";
    protected static final String DB_PASS = "sqluserpw";


    // You need to close the resultSet
    protected void close(Connection connect, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }


}
