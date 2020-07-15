package io.muzoo.ooc.webapp.basic.security;

import io.muzoo.ooc.webapp.basic.database.MySql;

import java.util.List;

public class UserService {

    private MySql mySql = new MySql();

    public void addUser(String username, String password, String email) {
        mySql.insert(username, password, email);
    }

    public void removeUser(String username) {
        mySql.delete(username);
    }

    public void editUser(String username, String password, String email) {
        mySql.update(username, password, email);
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
