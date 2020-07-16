package io.muzoo.ooc.webapp.basic.security;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySql {

    private final String jdbcDriverStr = "com.mysql.jdbc.Driver";
    private final String jdbcURL = "jdbc:mysql://localhost/ooc_2019?"
            + "user=max&password=root";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public MySql() {
        try {
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
//            String query = "CREATE TABLE IF NOT EXISTS " + "users"
//                    + "  (id           int(11) primary key auto_increment,"
//                    + "   username            varchar(255) not null ,"
//                    + "   password          varchar(255) not null ,"
//                    + "   email           varchar(255),"
//                    + "   firstName           varchar(255),"
//                    + "   lastName     varchar(255)";
            statement.execute("create table if not exists users (id int(11) primary key auto_increment, username varchar(255) not null, password varchar(255), email varchar(255), firstname varchar(255), lastname varchar(255))");
//            String hashpw = BCrypt.hashpw("root", BCrypt.gensalt());
            resultSet = statement.executeQuery("select * from ooc_2019.users;");
            if (!resultSet.next()) {
                insert("admin", "root", null, null, null);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }

    public User getUser(String username) {
        for (User user : getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
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
            String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
            preparedStatement.setString(2, hashpw);
//            preparedStatement.setString(2, password);
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
            String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
            preparedStatement.setString(1, hashpw);
//            preparedStatement.setString(1, password);
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
