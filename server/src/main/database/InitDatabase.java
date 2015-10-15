package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mihfilip on 15/10/2015.
 */
public class InitDatabase {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/faceit";
    private static final String DB_USER = "sqluser";
    private static final String DB_PASS = "sqluserpw";

    public static void main (String[] args) {
        InitDatabase init= new InitDatabase();
        init.createUserTable();
    }

    public void createUserTable() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE USER " +
                    "(id VARCHAR(255) not NULL, " +
                    " email VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " username VARCHAR(255), " +
                    " token VARCHAR(255), " +
                    " score INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException ignored){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

}
