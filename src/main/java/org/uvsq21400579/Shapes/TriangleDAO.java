package org.uvsq21400579.Shapes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DAO;

public class TriangleDAO extends DAO<Triangle> {

  @Override
  public Triangle create(Triangle object) {
    String insertTriangleString = "INSERT INTO TRIANGLE(NAME, FIRST_X, FIRST_Y , SECOND_X, SECOND_Y, THIRD_X, THIRD_Y) VALUES(?, ?, ?, ?, ?, ?, ?)";
    this.connect();
    try(
        PreparedStatement insertTriangle = this.connection.prepareStatement(insertTriangleString)
        ){
      insertTriangle.setString(1, object.name);
      insertTriangle.setString(2, object.first.getX());
      insertTriangle.setString(3, object.first.getY());
      insertTriangle.setString(4, object.second.getX());
      insertTriangle.setString(5, object.second.getY());
      insertTriangle.setString(6, object.third.getX());
      insertTriangle.setString(7, object.third.getY());
      insertTriangle.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Triangle find(String key) {
    Triangle triangle = null;
    String findTriangleString = "SELECT * FROM TRIANGLE WHERE TRIANGLE.NAME = ?";
    this.connect();
    try(
        PreparedStatement findTriangle = this.connection.prepareStatement(findTriangleString)
        ) {
      findTriangle.setString(1, key);
      ResultSet resultSet = findTriangle.executeQuery();
      if (resultSet.next()) {
        triangle = new Triangle(resultSet.getString("NAME"),
            new Coordinates(resultSet.getInt("FIRST_X"), resultSet.getInt("FIRST_Y")),
            new Coordinates(resultSet.getInt("SECOND_X"), resultSet.getInt("SECOND_Y")),
           new Coordinates(resultSet.getInt("THIRD_X"), resultSet.getInt("THIRD_Y"))
            );
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return triangle;
  }

  @Override
  public void delete(String key) {
    String deleteTriangleString = "DELETE FROM TRIANGLE WHERE TRIANGLE.NAME = ?";
    this.connect();
    try(
        PreparedStatement deleteTriangle = this.connection.prepareStatement(deleteTriangleString)
    ){
      deleteTriangle.setString(1, key);
      deleteTriangle.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
  }
}

