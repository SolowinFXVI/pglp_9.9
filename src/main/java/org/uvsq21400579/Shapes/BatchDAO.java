package org.uvsq21400579.Shapes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.uvsq21400579.DAO;
import org.uvsq21400579.Shape;

public class BatchDAO extends DAO<Batch> {

  @Override
  public Batch create(Batch object) {
    String insertBatchString = "INSERT INTO BATCH(BATCHNAME) VALUES (?)";
    String insertCircleString = "INSERT INTO BATCHCIRCLE(BATCHNAME, NAME) VALUES (?, ?)";
    String insertSquareString = "INSERT INTO BATCHSQUARE(BATCHNAME, NAME) VALUES (?, ?)";
    String insertTriangleString = "INSERT INTO BATCHTRIANGLE(BATCHNAME, NAME) VALUES (?, ?)";
    String insertRectangleString = "INSERT INTO BATCHRECTANGLE(BATCHNAME, NAME) VALUES (?, ?)";
    String insertBatchceptionString = "INSERT INTO BATCHCEPTION(BATCHNAME, NAME) VALUES (?, ?)";
    this.connect();
    try(
        PreparedStatement insertBatch = this.connection.prepareStatement(insertBatchString);
        PreparedStatement insertCircle = this.connection.prepareStatement(insertCircleString);
        PreparedStatement insertSquare = this.connection.prepareStatement(insertSquareString);
        PreparedStatement insertRectangle = this.connection.prepareStatement(insertRectangleString);
        PreparedStatement insertTriangle = this.connection.prepareStatement(insertTriangleString);
        PreparedStatement insertBatchception = this.connection.prepareStatement(insertBatchceptionString)
        ) {
      insertBatch.setString(1, object.name);
      insertBatch.executeUpdate();
      List<Shape> list = object.getShapesList();
      DAO dao;
      for(Shape shapes :list ){
        if(shapes instanceof Square){
          dao = new SquareDAO();
          dao.create((Square) shapes);
          insertSquare.setString(1, object.name);
          insertSquare.setString(2, shapes.name);
          insertSquare.executeUpdate();
        }
        else if(shapes instanceof Circle){
          dao = new CircleDAO();
          dao.create((Circle) shapes);
          insertCircle.setString(1, object.name);
          insertCircle.setString(2, shapes.name);
          insertCircle.executeUpdate();
        }
        else if(shapes instanceof Triangle){
          dao = new TriangleDAO();
          dao.create((Triangle) shapes);
          insertTriangle.setString(1, object.name);
          insertTriangle.setString(2, shapes.name);
          insertTriangle.executeUpdate();
        }
        else if(shapes instanceof  Rectangle){
          dao = new RectangleDAO();
          dao.create((Rectangle) shapes);
          insertRectangle.setString(1, object.name);
          insertRectangle.setString(2, shapes.name);
          insertRectangle.executeUpdate();
        }
        else if(shapes instanceof Batch){
          dao = new BatchDAO();
          dao.create((Batch) shapes);
          insertBatchception.setString(1, object.name);
          insertBatchception.setString(2, shapes.name);
          insertBatchception.executeUpdate();
        }
      }


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
    String findBatchString = "SELECT * FROM BATCH WHERE BATCH.NAME = ?";
    String findSquareString = "SELECT * FORM BATCHSQUARE WHERE BATCHSQUARE.NAME = ?";
    String findCircleString = "SELECT * FORM BATCHCIRCLE WHERE BATCHCIRCLE.NAME = ?";
    String findTriangleString = "SELECT * FORM BATCHTRIANGLE WHERE BATCHTRIANGLE.NAME = ?";
    String findRectangleString = "SELECT * FORM BATCHRECTANGLE WHERE BATCHRECTANGLE.NAME = ?";
    String findBatchceptionString = "SELECT * FORM BATCHCEPTION WHERE BATCHCEPTION.NAME = ?";
    this.connect();
    try(
        PreparedStatement findBatch = this.connection.prepareStatement(findBatchString);
        PreparedStatement findSquare = this.connection.prepareStatement(findSquareString);
        PreparedStatement findCircle = this.connection.prepareStatement(findCircleString);
        PreparedStatement findTriangle = this.connection.prepareStatement(findTriangleString);
        PreparedStatement findRectangle = this.connection.prepareStatement(findRectangleString);
        PreparedStatement findBatchception = this.connection.prepareStatement(findBatchceptionString);
        ) {
      findBatch.setString(1, key);
      findSquare.setString(1,key);
      findCircle.setString(1,key);
      findTriangle.setString(1,key);
      findRectangle.setString(1,key);
      findBatchception.setString(1,key);
      ResultSet resultSet = findBatch.executeQuery();
      ResultSet resultSetSquare = findSquare.executeQuery();
      ResultSet resultSetCircle = findCircle.executeQuery();
      ResultSet resultSetTriangle = findTriangle.executeQuery();
      ResultSet resultSetRectangle = findRectangle.executeQuery();
      ResultSet resultSetBatchception = findBatchception.executeQuery();
      if(resultSet.next()){
        batch = new Batch(resultSet.getString("BATCHNAME"));
      }
      while (resultSetSquare.next()){
        dao = new SquareDAO();
        batch.addShape((Square) dao.find(resultSetSquare.getString("NAME")));
      }
      while (resultSetCircle.next()){
        dao = new CircleDAO();
        batch.addShape((Circle) dao.find(resultSetCircle.getString("NAME")));
      }
      while (resultSetTriangle.next()){
        dao = new TriangleDAO();
        batch.addShape((Triangle) dao.find(resultSetTriangle.getString("NAME")));
      }
      while (resultSetRectangle.next()){
        dao = new RectangleDAO();
        batch.addShape((Rectangle) dao.find(resultSetRectangle.getString("NAME")));
      }
      while (resultSetBatchception.next()){
        dao = new BatchDAO();
        batch.addShape((Batch) dao.find(resultSetBatchception.getString("NAME")));
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    this.disconnect();
    return batch;
  }

  @Override
  public void delete(String key) {
      String deleteString = "DELETE FROM BATCH WHERE BATCH.BATCHNAME = ?";
      this.connect();
      try(
          PreparedStatement delete = this.connection.prepareStatement(deleteString);
          ) {
        delete.setString(1, key);
        delete.executeUpdate();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      this.disconnect();
  }
}
