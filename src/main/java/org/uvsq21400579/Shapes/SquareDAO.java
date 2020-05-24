package org.uvsq21400579.Shapes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DAO;

public class SquareDAO extends DAO<Square> {

  @Override
  public Square create(Square object) {
    String insertSquareString = "INSERT INTO SQUARE(NAME, FIRST_X, FIRST_Y, SIDE) VALUES(?, ?, ?, ?)";
    this.connect();
    try (
        PreparedStatement insertSquare = this.connection.prepareStatement(insertSquareString)
        ) {
      insertSquare.setString(1, object.name);
      insertSquare.setString(2, object.topLeft.getX());
      insertSquare.setString(3, object.topLeft.getY());
      insertSquare.setString(4, object.getSide());
      insertSquare.executeUpdate();
    }
    catch (SQLIntegrityConstraintViolationException e){
      System.out.println("Shape already exists ignoring");
    }
    catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Square find(String key) {
    Square square = null;
    String findSquareString = "SELECT * FROM SQUARE WHERE SQUARE.NAME= ?";
    this.connect();
    try(
        PreparedStatement findSquare = this.connection.prepareStatement(findSquareString)
        ) {
      findSquare.setString(1, key);
      ResultSet resultSet = findSquare.executeQuery();
      if (resultSet.next()) {
        square = new Square(resultSet.getString("NAME"), new Coordinates(
            resultSet.getInt("FIRST_X"),resultSet.getInt("FIRST_Y")),
            resultSet.getInt("SIDE"));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return square;
  }

  @Override
  public void delete(String key) {
    String deleteSquareString = "DELETE FROM SQUARE WHERE SQUARE.NAME = ?";
    this.connect();
    try(
        PreparedStatement deleteSquare = this.connection.prepareStatement(deleteSquareString)
        ){
        deleteSquare.setString(1, key);
        deleteSquare.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
  }
}
