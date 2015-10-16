package database;

import util.bean.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by mihfilip on 15/10/2015.
 */
public class EmployeeDatabase extends Database {

    public void addEmployee(String name, String email, String picture, String officeid, String department) throws Exception {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("insert into  faceit.employee(id, name, email, picture, officeid, department) values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, picture);
        preparedStatement.setString(5, officeid);
        preparedStatement.setString(6, department);

        preparedStatement.executeUpdate();

        System.out.println("Added employee " + name + " to database");

        close(conn, null);
    }

    public Map<String, Object> getEmployeeForEmail(String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.employee WHERE email = ?");
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();

        Map<String, Object> employees = new HashMap<String, Object>();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String picture = rs.getString("picture");
            String officeid = rs.getString("officeid");
            String department = rs.getString("department");
            employees.put("id", id);
            employees.put("name", name);
            employees.put("picture", picture);
            employees.put("officeid", officeid);
            employees.put("department", department);
        }

        System.out.println("Retrieved" + employees.size() + "employees");

        close(conn, null);
        return employees;
    }

    public List<Employee> getEmployeeForId(String id) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.employee WHERE id = ?");
        preparedStatement.setString(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        List<Employee> employees = new ArrayList<>();

        while (rs.next()) {
            Employee e = new Employee();
            String email = rs.getString("email");
            String name = rs.getString("name");
            String picture = rs.getString("picture");
            String officeid = rs.getString("officeid");
            String department = rs.getString("department");
            e.setId(id);
            e.setEmail(email);
            e.setName(name);
            e.setPicture(picture);
            e.setOfficeid(officeid);
            e.setDepartment(department);
            employees.add(e);
        }

        System.out.println("Retrieved" + employees.size() + "employees");

        close(conn, null);
        return employees;
    }

    public void updateEmployeePicture(String picture, String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


        preparedStatement = conn.prepareStatement("UPDATE faceit.employee SET picture = ? WHERE email = ?");
        preparedStatement.setString(1, picture);
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();

        close(conn, null);
    }

    public void updateEmployeeDepartment(String department, String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


        preparedStatement = conn.prepareStatement("UPDATE faceit.employee SET picture = ? WHERE email = ?");
        preparedStatement.setString(1, department);
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();

        close(conn, null);
    }

    public void updateEmployeeOffice(String officeid, String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


        preparedStatement = conn.prepareStatement("UPDATE faceit.employee SET officeid = ? WHERE email = ?");
        preparedStatement.setString(1, officeid);
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();

        close(conn, null);
    }

    public Integer getEmployeeNoForOffice(String officeid) throws Exception {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT COUNT(*) AS total FROM faceit.employee WHERE officeid = ?");
        preparedStatement.setString(1, officeid);

        ResultSet rs = preparedStatement.executeQuery();
        Integer count = null;

        while (rs.next()) {
            count = rs.getInt("total");
        }

        System.out.println("Retrieved total of " + count);

        close(conn, null);
        return count;
    }

    public Map<String, Object> getRandomPicturesForOfficeId(String city, String country) throws Exception {

        //get officeId first
        String officeid = new OfficeDatabase().getOfficeIdForCityCountry(city, country);
        Integer count = getEmployeeNoForOffice(officeid);

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        count = count / 6 + 1;
        String limitQuery = "SELECT * FROM faceit.employee WHERE officeid = ? ORDER BY RAND() LIMIT " + count;
        preparedStatement = conn.prepareStatement(limitQuery);
        preparedStatement.setString(1, officeid);

        ResultSet rs = preparedStatement.executeQuery();

        Map<String, Object> employees = new HashMap<String, Object>();

        while (rs.next()) {
            String name = rs.getString("name");
            String picture = rs.getString("picture");
            employees.put(name, picture);
        }

        System.out.println("Retrieved" + employees.size() + "employees");

        close(conn, null);
        return employees;
    }

}
