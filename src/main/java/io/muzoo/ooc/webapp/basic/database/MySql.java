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
                User user = new User(resultSet.getString(2), resultSet.getString(3));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
        return users;
    }

    public void insertUser(String username, String password) {
        try {
//            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            // the mysql insert statement
            String query = " insert into users (username, password)"
                    + " values (?, ?)";
            // create the mysql insert preparedstatement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
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

//    public void readData() throws Exception {
//        try {
//            Class.forName(jdbcDriverStr);
//            connection = DriverManager.getConnection(jdbcURL);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select * from ooc_test.test_table;");
//            getResultSet(resultSet);
//            preparedStatement = connection.prepareStatement("insert into ooc_test.test_table values (default,?)");
//            preparedStatement.setString(1, "insert test from java");
//            preparedStatement.executeUpdate();
//        } finally {
//            close();
//        }
//    }

//    private void getResultSet(ResultSet resultSet) throws Exception {
//        while (resultSet.next()) {
//            Integer id = resultSet.getInt(TestTableColumns.id.toString());
//            String text = resultSet.getString(TestTableColumns.TEXT.toString());
//            System.out.println("id: " + id);
//            System.out.println("text: " + text);
//        }
//    }

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
