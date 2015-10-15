package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mihfilip on 15/10/2015.
 */
public class DiscoveredUsersDatabase extends Database {

    public void addDiscovery(String email, String employeeid) throws Exception {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("insert into  faceit.discoveredusers(email, employeeid) values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, employeeid);

        preparedStatement.executeUpdate();

        System.out.println("Added discovery " + employeeid + " to database");

        close(conn, null);
    }

    public List<String> getDiscoveriesForUser(String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("SELECT employeeid FROM faceit.discoveredusers WHERE email = ?");
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();

        List<String> employeeIds = new ArrayList<String>();

        while (rs.next()) {
            String employeeid = rs.getString("employeeid");
            employeeIds.add(employeeid);
        }

        System.out.println("Retrieved" +  employeeIds.size() + "employees");

        close(conn, null);
        return employeeIds;
    }

    public void deleteDiscoveriesForUser(String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("DELETE faceit.discoveredusers WHERE email = ?");
        preparedStatement.setString(1, email);

        preparedStatement.executeUpdate();

        close(conn, null);
    }

}
