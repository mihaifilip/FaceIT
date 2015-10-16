package database;

import util.bean.Employee;
import util.bean.Office;

import java.io.File;
import java.io.FilenameFilter;
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
        init.createOfficeTable();
        init.createAchievementsTable();
        init.createDiscoveredUsersTable();
        init.createEmployeeTable();

        //add offices
        init.addOffices();


        //add employees to DB -> employee picture should be in the path resources/employees/{office}/{name}.jpg
        //only run this command once
        //init.addEmployees();
    }

    public void addEmployees() {
        Connection conn = null;
        Statement stmt = null;
        OfficeDatabase od = new OfficeDatabase();
        EmployeeDatabase ed = new EmployeeDatabase();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.createStatement();

            File file = new File("resources/employees");
            String[] directories = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File current, String name) {
                    return new File(current, name).isDirectory();
                }
            });

            for (int i = 0 ; i < directories.length ; i++) {
                File folder = new File("resources/employees/" + directories[i]);
                File[] listOfFiles = folder.listFiles();

                for (int j = 0; j < listOfFiles.length; j++) {
                    if (listOfFiles[j].isFile()) {
                        System.out.println("File " + listOfFiles[j].getName());
                        Office o = od.getOfficeForCity(directories[i]);
                        String username = listOfFiles[j].getName().split("\\.")[0].toLowerCase();
                        String firstName = username.split(" ")[0];
                        String lastName = username.split(" ")[1];
                        String email = firstName + "." + lastName + "@misys.com";
                        ed.addEmployee(username.toUpperCase(), email, listOfFiles[j].getName(), o.getId(), null);
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOffices() {
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

            String statement = "insert into office values('2', 'Bucharest', 'Romania', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('3', 'Dubai', 'UAE', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('4', 'Frankfurt', 'Germany', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('5', 'London', 'UK', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('6', 'Manila', 'Philippines', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('7', 'Miskolc', 'Hungary', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('8', 'New York', 'USA', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('9', 'Paris', 'France', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('10', 'Richardson', 'USA', null, null, null, null)";
            stmt.executeUpdate(statement);

            statement = "insert into office values('11', 'Singapore', 'Singapore', null, null, null, null)";
            stmt.executeUpdate(statement);

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

    private void createOfficeTable() {
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

            String sql = "CREATE TABLE OFFICE " +
                    "(id VARCHAR(255) not NULL, " +
                    " city VARCHAR(255), " +
                    " country VARCHAR(255), " +
                    " picture VARCHAR(255), " +
                    " telephone VARCHAR(255), " +
                    " picturemap VARCHAR(255), " +
                    " coordinates VARCHAR(255), " +
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

    private void createEmployeeTable() {
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

            String sql = "CREATE TABLE EMPLOYEE " +
                    "(id VARCHAR(255) not NULL, " +
                    " name VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " picture VARCHAR(255), " +
                    " officeid VARCHAR(255), " +
                    " department VARCHAR(255), " +
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

    private void createDiscoveredUsersTable() {
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

            String sql = "CREATE TABLE DISCOVEREDUSERS " +
                    "(id VARCHAR(255) not NULL, " +
                    " email VARCHAR(255), " +
                    " employeeid VARCHAR(255), " +
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

    private void createAchievementsTable() {
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

            String sql = "CREATE TABLE ACHIEVEMENTS " +
                    "(id VARCHAR(255) not NULL, " +
                    " email VARCHAR(255), " +
                    " achievement VARCHAR(255), " +
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
