package database;

import util.bean.Office;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by mihfilip on 15/10/2015.
 */
public class OfficeDatabase extends Database {

    public void addOffice(String city, String country, String picture, String telephone, String picturemap, String coordinates) throws Exception {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("insert into  faceit.office(id, city, country, picture, telephone, picturemap, coordinates) values (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, country);
        preparedStatement.setString(3, picture);
        preparedStatement.setString(4, telephone);
        preparedStatement.setString(5, picturemap);
        preparedStatement.setString(6, coordinates);

        preparedStatement.executeUpdate();

        System.out.println("Added user " + city + ":" + country + " to database");

        close(conn, null);
    }

    public List<Office> getOfficesAll() throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.office");

        ResultSet rs = preparedStatement.executeQuery();

        List<Office> offices = new ArrayList<>();

        while (rs.next()) {
            Office o = new Office();
            String id = rs.getString("id");
            String city = rs.getString("city");
            String country = rs.getString("country");
            String picture = rs.getString("picture");
            String telephone = rs.getString("telephone");
            String picturemap = rs.getString("picturemap");
            String coordinates = rs.getString("coordinates");
            o.setId(id);
            o.setCity(city);
            o.setCountry(country);
            o.setPicture(picture);
            o.setTelephone(telephone);
            o.setPicturemap(picturemap);
            o.setCoordinates(coordinates);
            offices.add(o);
        }

        System.out.println("Retrieved" +  offices.size() + "offices");

        close(conn, null);

        return offices;
    }

    public List<Office> getOfficeForId(String id) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.office WHERE id = ?");
        preparedStatement.setString(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        List<Office> offices = new ArrayList<>();

        while (rs.next()) {
            Office o = new Office();
            String city = rs.getString("city");
            String country = rs.getString("country");
            String picture = rs.getString("picture");
            String telephone = rs.getString("telephone");
            String picturemap = rs.getString("picturemap");
            String coordinates = rs.getString("coordinates");
            o.setId(id);
            o.setCity(city);
            o.setCountry(country);
            o.setPicture(picture);
            o.setTelephone(telephone);
            o.setPicturemap(picturemap);
            o.setCoordinates(coordinates);
            offices.add(o);
        }

        System.out.println("Retrieved" +  offices.size() + "offices");

        close(conn, null);
        return offices;
    }

    public Map<String, Object> getOfficeForCityCountry(String city, String country) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.office WHERE city = ? AND country = ?");
        preparedStatement.setString(1, city);
        preparedStatement.setString(2, country);

        ResultSet rs = preparedStatement.executeQuery();

        Map<String, Object> employees = new HashMap<String, Object>();

        while (rs.next()) {
            String id = rs.getString("id");
            String picture = rs.getString("picture");
            String telephone = rs.getString("telephone");
            String picturemap = rs.getString("picturemap");
            String coordinates = rs.getString("coordinates");
            employees.put("id", id);
            employees.put("picture", picture);
            employees.put("telephone", telephone);
            employees.put("picturemap", picturemap);
            employees.put("coordinates", coordinates);
        }

        System.out.println("Retrieved" + employees.size() + "employees");

        close(conn, null);
        return employees;
    }

    public Office getOfficeForCity(String city) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.office WHERE city = ?");
        preparedStatement.setString(1, city);

        ResultSet rs = preparedStatement.executeQuery();

        Office o = new Office();

        while (rs.next()) {
            String id = rs.getString("id");
            String picture = rs.getString("picture");
            String telephone = rs.getString("telephone");
            String picturemap = rs.getString("picturemap");
            String coordinates = rs.getString("coordinates");
            o.setId(id);
            o.setPicture(picture);
            o.setPicturemap(picturemap);
            o.setTelephone(telephone);
            o.setCoordinates(coordinates);
            break;
        }

        close(conn, null);
        return o;
    }

    public String getOfficeIdForCityCountry(String city, String country) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT * FROM faceit.office WHERE city = ? AND country = ?");
        preparedStatement.setString(1, city);
        preparedStatement.setString(2, country);

        ResultSet rs = preparedStatement.executeQuery();
        String officeId = null;

        while (rs.next()) {
            officeId = rs.getString("id");
        }

        System.out.println("Retrieved " + officeId + " for " + city + ", " + country);

        close(conn, null);

        return officeId;
    }

}
