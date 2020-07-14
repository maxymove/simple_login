package io.muzoo.ooc.webapp.basic.security;

import io.muzoo.ooc.webapp.basic.database.MySql;

import java.util.List;

public class UserService {

    private MySql mySql = new MySql();

    public void addUser(String username, String password) {
        mySql.insertUser(username, password);
    }

    public List<User> getUsers() {
        return mySql.getUsers();
    }

    public User getUser(String username) {
        for (User user : getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        for (User user : getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkIfUserExists(String username) {
        return getUser(username) != null;
    }
}
