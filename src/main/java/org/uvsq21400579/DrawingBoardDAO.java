package org.uvsq21400579;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.uvsq21400579.Shapes.Batch;
import org.uvsq21400579.Shapes.BatchDAO;
import org.uvsq21400579.Shapes.Circle;
import org.uvsq21400579.Shapes.CircleDAO;
import org.uvsq21400579.Shapes.Rectangle;
import org.uvsq21400579.Shapes.RectangleDAO;
import org.uvsq21400579.Shapes.Square;
import org.uvsq21400579.Shapes.SquareDAO;
import org.uvsq21400579.Shapes.Triangle;
import org.uvsq21400579.Shapes.TriangleDAO;

public class DrawingBoardDAO extends DAO<DrawingBoard> {

  @Override
  public DrawingBoard create(DrawingBoard object) {
    String insertDrawingBoardString = "INSERT INTO DRAWINGBOARD(DRAWINGBOARDNAME) VALUES(?)";
    String insertSquareDrawingMembersString = "INSERT INTO DRAWINGBOARDMEMBERS("
        + "DRAWINGBOARDMEMBERSNAME, SHAPE, SQUARESHAPENAME) VALUES(?, ?, ?)";
    String insertCircleDrawingMembersString = "INSERT INTO DRAWINGBOARDMEMBERS("
        + "DRAWINGBOARDMEMBERSNAME, SHAPE, CIRCLESHAPENAME) VALUES(?, ?, ?)";
    String insertTriangleDrawingMembersString = "INSERT INTO DRAWINGBOARDMEMBERS("
        + "DRAWINGBOARDMEMBERSNAME, SHAPE, TRIANGLESHAPENAME) VALUES(?, ?, ?)";
    String insertRectangleDrawingMembersString = "INSERT INTO DRAWINGBOARDMEMBERS("
        + "DRAWINGBOARDMEMBERSNAME, SHAPE, RECTANGLESHAPENAME) VALUES(?, ?, ?)";
    String insertBatchDrawingMembersString = "INSERT INTO DRAWINGBOARDMEMBERS("
        + "DRAWINGBOARDMEMBERSNAME, SHAPE, BATCHSHAPENAME) VALUES(?, ?, ?)";
    this.connect();
    try (
        PreparedStatement insertDrawingBoard =
            connection.prepareStatement(insertDrawingBoardString);
        PreparedStatement insertSquareDrawingMembers =
            connection.prepareStatement(insertSquareDrawingMembersString);
        PreparedStatement insertCircleDrawingMembers =
            connection.prepareStatement(insertCircleDrawingMembersString);
        PreparedStatement insertTriangleDrawingMembers =
            connection.prepareStatement(insertTriangleDrawingMembersString);
        PreparedStatement insertRectangleDrawingMembers =
            connection.prepareStatement(insertRectangleDrawingMembersString);
        PreparedStatement insertBatchDrawingMembers =
            connection.prepareStatement(insertBatchDrawingMembersString)
    ) {
      insertDrawingBoard.setString(1, object.name);
      insertDrawingBoard.executeUpdate();
      List<Shape> list = object.getShapeList();
      DAO dao;
      for (Shape shapes : list) {
        if (shapes instanceof Square) {
          try {
            dao = new SquareDAO();
            dao.create(shapes);
            insertSquareDrawingMembers.setString(1, object.name);
            insertSquareDrawingMembers.setString(2, "SQUARE");
            insertSquareDrawingMembers.setString(3, shapes.name);
          } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Shape already saved ignoring");
          }
        }
        if (shapes instanceof Circle) {
          try {
            dao = new CircleDAO();
            dao.create(shapes);
            insertCircleDrawingMembers.setString(1, object.name);
            insertCircleDrawingMembers.setString(2, "CIRCLE");
            insertCircleDrawingMembers.setString(3, shapes.name);
          } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Shape already saved ignoring");
          }
        }
        if (shapes instanceof Triangle) {
          try {
            dao = new TriangleDAO();
            dao.create(shapes);
            insertTriangleDrawingMembers.setString(1, object.name);
            insertTriangleDrawingMembers.setString(2, "TRIANGLE");
            insertTriangleDrawingMembers.setString(3, shapes.name);
          } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Shape already saved ignoring");
          }
        }
        if (shapes instanceof Rectangle) {
          try {
            dao = new RectangleDAO();
            dao.create(shapes);
            insertRectangleDrawingMembers.setString(1, object.name);
            insertRectangleDrawingMembers.setString(2, "RECTANGLE");
            insertRectangleDrawingMembers.setString(3, shapes.name);
          } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Shape already saved ignoring");
          }
        }
        if (shapes instanceof Batch) {
          try {
            dao = new BatchDAO();
            dao.create(shapes);
            insertBatchDrawingMembers.setString(1, object.name);
            insertBatchDrawingMembers.setString(2, "BATCH");
            insertBatchDrawingMembers.setString(3, shapes.name);
          } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Shape already saved ignoring");
          }
        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return object;
  }

  @Override
  public DrawingBoard find(String key) {
    DrawingBoard drawingBoard = null;
    DAO dao;
    String findDrawingBoardString = "SELECT * FROM DRAWINGBOARD DB"
        + " WHERE DB.DRAWINGBOARDNAME = ?";
    String findShapesString = "SELECT * FROM DRAWINGBOARDMEMBERS DBM"
        + " WHERE DBM.DRAWINGBOARDMEMBERSNAME = ?";
    this.connect();
    try (
        PreparedStatement findDrawingBoard =
            this.connection.prepareStatement(findDrawingBoardString);
        PreparedStatement findShapes = this.connection.prepareStatement(findShapesString)
    ) {
      findDrawingBoard.setString(1, key);
      findShapes.setString(1, key);
      ResultSet resultSet = findDrawingBoard.executeQuery();
      ResultSet resultSetShapes = findShapes.executeQuery();
      if (resultSet.next()) {
        drawingBoard = new DrawingBoard(resultSet.getString("DRAWINGBOARDNAME"));
      }
      while (resultSetShapes.next()) {
        if (resultSetShapes.getString("SHAPE").equals("SQUARE")) {
          dao = new SquareDAO();
          drawingBoard.addShape((Square) dao.find(resultSetShapes.getString("SQUARESHAPENAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("CIRCLE")) {
          dao = new CircleDAO();
          drawingBoard.addShape((Circle) dao.find(
              resultSetShapes.getString("CIRCLESHAPENAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("TRIANGLE")) {
          dao = new TriangleDAO();
          drawingBoard.addShape((Triangle) dao.find(
              resultSetShapes.getString("TRIANGLESHAPENAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("RECTANGLE")) {
          dao = new RectangleDAO();
          drawingBoard.addShape((Rectangle) dao.find(
              resultSetShapes.getString("RECTANGLESHAPENAME")));
        }
        if (resultSetShapes.getString("SHAPE").equals("BATCH")) {
          dao = new BatchDAO();
          drawingBoard.addShape((Batch) dao.find(
              resultSetShapes.getString("BATCHSHAPENAME")));
        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return drawingBoard;
  }

  @Override
  public void delete(String key) {
    String deleteDrawingBoardMembersString = "DELETE FROM DRAWINGBOARDMEMBERS DBM"
        + " WHERE DBM.DRAWINGBOARDMEMBERSNAME = ?";
    String deleteDrawingBoardString = "DELETE FROM DRAWINGBOARD DB WHERE DB.DRAWINGBOARDNAME = ? ";
    this.connect();
    try (
        PreparedStatement deleteDrawingBoardMembers =
            this.connection.prepareStatement(deleteDrawingBoardMembersString);
        PreparedStatement deleteDrawingBoard =
            this.connection.prepareStatement(deleteDrawingBoardString)
    ) {
      deleteDrawingBoardMembers.setString(1,key);
      deleteDrawingBoardMembers.executeUpdate();
      deleteDrawingBoard.setString(1,key);
      deleteDrawingBoard.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
  }
}
