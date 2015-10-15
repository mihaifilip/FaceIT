package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mihfilip on 15/10/2015.
 */
public class UserDatabase extends Database {

    public Map<String, Object> getUserByEmail(String email) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        preparedStatement = conn.prepareStatement("SELECT * from faceit.user where email=?;");
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Map<String, Object> user = new HashMap<String, Object>();
            user.put("email", resultSet.getString("email"));
            user.put("password", resultSet.getString("password"));
            user.put("token", resultSet.getString("token"));
            user.put("username", resultSet.getString("username"));
            user.put("score", resultSet.getInt("score"));
            close(conn, resultSet);
            return user;
        }
        close(conn, resultSet);
        return null;
    }

    public void addUser(String username, String email, String password, String token) throws Exception {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("insert into  faceit.user(id, email, password, username, token, score) values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, username);
        preparedStatement.setString(5, token);
        preparedStatement.setInt(6, 0);

        preparedStatement.executeUpdate();

        System.out.println("Added user " + email + " to database");

        close(conn, null);
    }

    public void updateUserToken(String email, String token) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("UPDATE faceit.user SET token = ? WHERE email = ?");
        preparedStatement.setString(1, token);
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();

        close(conn, null);
    }

    public void updateUserScore(String email, int score) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        preparedStatement = conn.prepareStatement("UPDATE faceit.user SET score = ? WHERE email = ?");
        preparedStatement.setInt(1, score);
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();

        close(conn, null);
    }

}
