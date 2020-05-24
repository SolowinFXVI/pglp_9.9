package org.uvsq21400579.Shapes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DAO;

public class RectangleDAO extends DAO<Rectangle> {

  public RectangleDAO() {
  }

  @Override
  public Rectangle create(Rectangle object) {
    String insertRectangleString = "INSERT INTO RECTANGLE(NAME, FIRST_X, FIRST_Y, SECOND_X,"
        + " SECOND_Y) VALUES(?, ?, ?, ?, ?)";
    this.connect();
    try (
        PreparedStatement insertRectangle = this.connection.prepareStatement(insertRectangleString)
    ) {
      insertRectangle.setString(1, object.name);
      insertRectangle.setString(2, object.first.getX());
      insertRectangle.setString(3, object.first.getY());
      insertRectangle.setString(4, object.second.getX());
      insertRectangle.setString(5, object.second.getY());
      insertRectangle.executeUpdate();
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("Shape already exists ignoring");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Rectangle find(String key) {
    Rectangle rectangle = null;
    String findRectangleString = "SELECT * FROM RECTANGLE WHERE RECTANGLE.NAME = ?";
    this.connect();
    try (
        PreparedStatement findRectangle = this.connection.prepareStatement(findRectangleString)
    ) {
      findRectangle.setString(1, key);
      ResultSet resultSet = findRectangle.executeQuery();
      if (resultSet.next()) {
        rectangle = new Rectangle(resultSet.getString("NAME"),
            new Coordinates(resultSet.getInt("FIRST_X"), resultSet.getInt("FIRST_Y")),
            new Coordinates(resultSet.getInt("SECOND_X"), resultSet.getInt("SECOND_Y")));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return rectangle;
  }

  @Override
  public void delete(String key) {
    String deleteRectangleString = "DELETE FROM RECTANGLE WHERE RECTANGLE.NAME = ?";
    this.connect();
    try (
        PreparedStatement deleteRectangle = this.connection.prepareStatement(deleteRectangleString)
    ) {
      deleteRectangle.setString(1, key);
      deleteRectangle.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
  }
}
