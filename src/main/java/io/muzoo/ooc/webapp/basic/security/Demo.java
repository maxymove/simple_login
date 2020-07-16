package io.muzoo.ooc.webapp.basic.security;

public class Demo {

    public static void main(String[] args) {
        MySql mySql = new MySql();
        mySql.insert("admin", "root", null, null, null);
    }
}
