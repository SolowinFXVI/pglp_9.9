package org.uvsq21400579;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO<T> {

  public Connection connection = null;

  public abstract T create(T object);

  public abstract T find(String key);

  public abstract void delete(String key);

  Statement statement = null;

  /**
   * Connect to the embedded database.
   */
  public void connect() {
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      connection = DriverManager.getConnection("jdbc:derby:drawingAppDB");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Disconnect from the database.
   */
  public void disconnect() {
    try {
      connection.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
