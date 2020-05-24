package org.uvsq21400579.Shapes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DAO;

public class CircleDAO extends DAO<Circle> {

  @Override
  public Circle create(Circle object) {
    String insertCircleString = "INSERT INTO CIRCLE(NAME, FIRST_X, FIRST_Y, RADIUS) VALUES(?, ?, ?, ?)";
    this.connect();
    try (
        PreparedStatement insertCircle = this.connection.prepareStatement(insertCircleString)
        ) {
      insertCircle.setString(1, object.name);
      insertCircle.setString(2, object.center.getX());
      insertCircle.setString(3, object.center.getY());
      insertCircle.setString(4, object.getRadius());
      insertCircle.executeUpdate();
    } catch (SQLIntegrityConstraintViolationException e){
      System.out.println("Shape already exists ignoring");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Circle find(String key) {
    Circle circle = null;
    String findCircleString = "SELECT * FROM CIRCLE WHERE CIRCLE.NAME = ?";
    this.connect();
    try (
        PreparedStatement findCircle = this.connection.prepareStatement(findCircleString)
        ) {
      findCircle.setString(1, key);
      ResultSet resultSet = findCircle.executeQuery();
      if(resultSet.next()){
        circle = new Circle( resultSet.getString("NAME"),
            new Coordinates(resultSet.getInt("FIRST_X"), resultSet.getInt("FIRST_Y")),
            resultSet.getInt("RADIUS"));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return circle;
  }

  @Override
  public void delete(String key) {
    String deleteCircleString = "DELETE FROM CIRCLE WHERE CIRCLE.NAME = ?";
    this.connect();
    try (
        PreparedStatement deleteCircle = this.connection.prepareStatement(deleteCircleString)
        ) {
        deleteCircle.setString(1, key);
        deleteCircle.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
  }
}
