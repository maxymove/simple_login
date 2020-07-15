package io.muzoo.ooc.webapp.basic.database;

import io.muzoo.ooc.webapp.basic.security.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySql {

    enum TestTableColumns {
        id, TEXT;
    }

    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost/ooc_2019?"
            + "user=max&password=root";

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public MySql() {
        this.jdbcDriverStr = MYSQL_DRIVER;
        this.jdbcURL = MYSQL_URL;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from ooc_2019.users;");
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
        return users;
    }

    public void insert(String username, String password, String email, String firstName, String lastName) {
        try {
//            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            // the mysql insert statement
            String query = " insert into users (username, password, email, firstName, lastName)"
                    + " values (?, ?, ?, ?, ?)";
            // create the mysql insert preparedstatement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            // execute the preparedstatement
            preparedStatement.execute();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete(String username) {
        try {
            connection = DriverManager.getConnection(jdbcURL);
            String query = " delete from users where username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(String username, String password, String email, String firstname, String lastName) {
        try {
            connection = DriverManager.getConnection(jdbcURL);
            String query = " update users set password = ? where username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            query = " update users set email = ? where username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            query = " update users set firstName = ? where username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            query = " update users set lastName = ? where username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }
}
