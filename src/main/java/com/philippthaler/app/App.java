package com.philippthaler.app;

import com.philippthaler.app.utils.Warehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {

  public static final int COLUMN_INIT=10, ROW_INIT = 10;

  public static void main(String[] args) {
    Connection connection = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "root");
    connectionProps.put("password", "password");

    try {
      connection=DriverManager.getConnection("jdbc:mariadb://localhost:3306/",connectionProps);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("Connected to database");


  }
}
