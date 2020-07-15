package io.muzoo.ooc.webapp.basic.database;

import io.muzoo.ooc.webapp.basic.security.User;

public class MySqlTest {

    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost/ooc_test?"
            + "user=ooc&password=oocpass";

    public static void main(String[] args) throws Exception {
        MySql mySQL = new MySql();
//        mySQL.insert("roor", "root");
//        mySQL.update("mai", "1234", "mam@mma.com", "Su", "Siri");
//        mySQL.update("max", "12345", "max@ooc.com");
//        mySQL.delete("cha");
        for (User user : mySQL.getUsers()) {
            System.out.printf("username: %s password: %s\n", user.getUsername(), user.getPassword());
        }
    }
}
