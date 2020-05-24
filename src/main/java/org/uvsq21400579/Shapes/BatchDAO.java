package org.uvsq21400579.Shapes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.uvsq21400579.DAO;
import org.uvsq21400579.Shape;

public class BatchDAO extends DAO<Batch> {

  @Override
  public Batch create(Batch object) {
    String insertBatchString = "INSERT INTO BATCH(BATCHNAME) VALUES (?)";
    String insertCircleString = "INSERT INTO BATCHMEMBERS(BATCHNAME, NAME, SHAPE, FIRST_X, FIRST_Y,"
        + " RADIUS) VALUES (?, ?, ?, ?, ?, ?)";
    String insertSquareString = "INSERT INTO BATCHMEMBERS(BATCHNAME, NAME, SHAPE, FIRST_X, FIRST_Y,"
        + " SIDE) VALUES (?, ?, ?, ?, ?, ?)";
    String insertTriangleString = "INSERT INTO BATCHMEMBERS(BATCHNAME, NAME, SHAPE, FIRST_X,"
        + " FIRST_Y, SECOND_X, SECOND_Y, THIRD_X, THIRD_Y) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String insertRectangleString = "INSERT INTO BATCHMEMBERS(BATCHNAME, NAME, SHAPE, FIRST_X, "
        + "FIRST_Y, SECOND_X, SECOND_Y) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String insertBatchceptionString = "INSERT INTO BATCHMEMBERS(BATCHNAME, NAME, SHAPE) "
        + "VALUES (?, ?, ?)";
    this.connect();
    try (
        PreparedStatement insertBatch = this.connection.prepareStatement(insertBatchString);
        PreparedStatement insertCircle = this.connection.prepareStatement(insertCircleString);
        PreparedStatement insertSquare = this.connection.prepareStatement(insertSquareString);
        PreparedStatement insertRectangle = this.connection.prepareStatement(insertRectangleString);
        PreparedStatement insertTriangle = this.connection.prepareStatement(insertTriangleString);
        PreparedStatement insertBatchception =
            this.connection.prepareStatement(insertBatchceptionString)
    ) {
      insertBatch.setString(1, object.name);
      insertBatch.executeUpdate();
      List<Shape> list = object.getShapesList();
      DAO dao;
      for (Shape shapes :list) {
        if (shapes instanceof Square) {
          dao = new SquareDAO();
          dao.create(shapes);
          insertSquare.setString(1, object.name);
          insertSquare.setString(2, shapes.name);
          insertSquare.setString(3, "SQUARE");
          insertSquare.setString(4, ((Square) shapes).topLeft.getX());
          insertSquare.setString(5,((Square) shapes).topLeft.getY());
          insertSquare.setString(6, String.valueOf(((Square) shapes).side));
          insertSquare.executeUpdate();
        } else if (shapes instanceof Circle) {
          dao = new CircleDAO();
          dao.create(shapes);
          insertCircle.setString(1, object.name);
          insertCircle.setString(2, shapes.name);
          insertCircle.setString(3, "CIRCLE");
          insertCircle.setString(4, ((Circle) shapes).getCenter().getX());
          insertCircle.setString(5, ((Circle) shapes).getCenter().getY());
          insertCircle.setString(6, ((Circle) shapes).getRadius());
          insertCircle.executeUpdate();
        } else if (shapes instanceof Triangle) {
          dao = new TriangleDAO();
          dao.create(shapes);
          insertTriangle.setString(1, object.name);
          insertTriangle.setString(2, shapes.name);
          insertTriangle.setString(3, "TRIANGLE");
          insertTriangle.setString(4, ((Triangle) shapes).first.getX());
          insertTriangle.setString(5, ((Triangle) shapes).first.getY());
          insertTriangle.setString(6, ((Triangle) shapes).second.getX());
          insertTriangle.setString(7, ((Triangle) shapes).second.getY());
          insertTriangle.setString(8, ((Triangle) shapes).third.getX());
          insertTriangle.setString(9, ((Triangle) shapes).third.getY());
          insertTriangle.executeUpdate();
        } else if (shapes instanceof  Rectangle) {
          dao = new RectangleDAO();
          dao.create(shapes);
          insertRectangle.setString(1, object.name);
          insertRectangle.setString(2, shapes.name);
          insertRectangle.setString(3, "RECTANGLE");
          insertRectangle.setString(4, ((Rectangle) shapes).first.getX());
          insertRectangle.setString(5, ((Rectangle) shapes).first.getY());
          insertRectangle.setString(6, ((Rectangle) shapes).second.getX());
          insertRectangle.setString(7, ((Rectangle) shapes).second.getY());
          insertRectangle.executeUpdate();
        } else if (shapes instanceof Batch) {
          dao = new BatchDAO();
          dao.create(shapes);
          insertBatchception.setString(1, object.name);
          insertBatchception.setString(2, shapes.name);
          insertBatchception.setString(3,"BATCH");
          insertBatchception.executeUpdate();
        }
      }
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("Shape already exists ignoring");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return object;
  }

  @Override
  public Batch find(String key) {
    Batch batch = null;
    DAO dao;
    String findBatchString = "SELECT * FROM BATCH WHERE BATCH.BATCHNAME = ?";
    String findShapesString = "SELECT * FROM BATCHMEMBERS WHERE BATCHMEMBERS.BATCHNAME = ?";
    this.connect();
    try (
        PreparedStatement findBatch = this.connection.prepareStatement(findBatchString);
        PreparedStatement findShapes = this.connection.prepareStatement(findShapesString)
    ) {
      findBatch.setString(1, key);
      findShapes.setString(1,key);
      ResultSet resultSet = findBatch.executeQuery();
      ResultSet resultSetShapes = findShapes.executeQuery();
      if (resultSet.next()) {
        batch = new Batch(resultSet.getString("BATCHNAME"));
      }
      while (resultSetShapes.next()) {
        if (resultSetShapes.getString("SHAPE").equals("SQUARE")) {
          dao = new SquareDAO();
          batch.addShape((Square) dao.find(resultSetShapes.getString("NAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("CIRCLE")) {
          dao = new CircleDAO();
          batch.addShape((Circle) dao.find(resultSetShapes.getString("NAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("TRIANGLE")) {
          dao = new TriangleDAO();
          batch.addShape((Triangle) dao.find(resultSetShapes.getString("NAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("RECTANGLE")) {
          dao = new RectangleDAO();
          batch.addShape((Rectangle) dao.find(resultSetShapes.getString("NAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("BATCH")) {
          dao = new BatchDAO();
          batch.addShape((Batch) dao.find(resultSetShapes.getString("NAME")));
        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return batch;
  }

  @Override
  public void delete(String key) {
    String deleteBatchMemberString = "DELETE FROM BATCHMEMBERS WHERE BATCHMEMBERS.BATCHNAME = ?";
    String deleteString = "DELETE FROM BATCH WHERE BATCH.BATCHNAME = ?";
    this.connect();
    try (
        PreparedStatement deleteMembers = this.connection.prepareStatement(deleteBatchMemberString);
        PreparedStatement delete = this.connection.prepareStatement(deleteString)
    ) {
      deleteMembers.setString(1,key);
      deleteMembers.executeUpdate();
      delete.setString(1, key);
      delete.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
  }
}
