package com.example.demofx.DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {
    InputStream inputStream = DBConnector.class.getClassLoader().getResourceAsStream("SQLs.properties");
    public Properties prop = new Properties();
    public Connection connection = null;

    public Connection getConnection() throws IOException {

        prop.load(inputStream);
        //Ссылка для подключения к базе данных
        String url = prop.getProperty("db.url");

        //Имя пользователя и пароль для подключения к базе данных
        String username = prop.getProperty("db.username");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, "");

            if (connection != null) {
                System.out .println("Successfully connected to MySQL database ");
            }
        } catch (Exception ex) {
            System.out .println("An error occurred while connecting MySQL database");
            ex.printStackTrace();
        }
        return connection;
    }
}