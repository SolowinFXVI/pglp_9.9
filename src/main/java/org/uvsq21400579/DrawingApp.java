package org.uvsq21400579;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DrawingApp {
  private final DrawingTUI drawingTUI;
  private Command command;
  private Connection connection;

  DrawingApp(){
    String createTableSquareString = "CREATE TABLE SQUARE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, SIDE INT)";
    String createTableCircleString = "CREATE TABLE CIRCLE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, RADIUS INT)";
    String createTableTriangleString = "CREATE TABLE TRIANGLE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, SECOND_X INT, SECOND_Y INT, THIRD_X INT, THIRD_Y INT)";
    String createTableRectangleString = "CREATE TABLE RECTANGLE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, SECOND_X INT, SECOND_Y INT)";
    String createTableBatch = "CREATE TABLE BATCH(BATCHNAME VARCHAR(128) PRIMARY KEY NOT NULL)";
    String createTableBatchMembers = "CREATE TABLE BATCHMEMBERS(BATCHNAME VARCHAR(128), NAME VARCHAR(128),"
        + " SHAPE VARCHAR (128), PRIMARY KEY (BATCHNAME, NAME, SHAPE)"
        + ", FIRST_X INT, FIRST_Y INT, SECOND_X INT, SECOND_Y INT,"
        + " THIRD_X INT, THIRD_Y INT, SIDE INT, RADIUS INT, FOREIGN KEY (BATCHNAME) REFERENCES BATCH(BATCHNAME))";
    String createTableDrawing = "CREATE TABLE DRAWINGBOARD(DRAWINGBOARDNAME VARCHAR (128) PRIMARY KEY)";
    String createTableDrawingMembers = "CREATE TABLE DRAWINGBOARDMEMBERS(DRAWINGBOARDMEMBERSNAME VARCHAR(128), FOREIGN KEY (DRAWINGBOARDMEMBERSNAME) REFERENCES DRAWINGBOARD(DRAWINGBOARDNAME),"
        + " SHAPE VARCHAR(128), SQUARESHAPENAME VARCHAR(128), FOREIGN KEY (SQUARESHAPENAME) REFERENCES SQUARE(NAME),"
        + "CIRCLESHAPENAME VARCHAR(128), FOREIGN KEY (CIRCLESHAPENAME) REFERENCES CIRCLE(NAME),"
        + "TRIANGLESHAPENAME VARCHAR(128), FOREIGN KEY (TRIANGLESHAPENAME) REFERENCES TRIANGLE(NAME),"
        + "RECTANGLESHAPENAME VARCHAR(128), FOREIGN KEY (RECTANGLESHAPENAME) REFERENCES RECTANGLE(NAME),"
        + "BATCHSHAPENAME VARCHAR(128), FOREIGN KEY (BATCHSHAPENAME) REFERENCES BATCH(BATCHNAME),"
        + "PRIMARY KEY (DRAWINGBOARDMEMBERSNAME, SHAPE)"
        + ")";
    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    String protocol = "jdbc:derby:";
    try{
      Class.forName(driver).newInstance();
      connection = DriverManager.getConnection(protocol + "drawingAppDB;create=true");
      Statement statement = connection.createStatement();
      statement.execute(createTableSquareString);
      statement.execute(createTableCircleString);
      statement.execute(createTableTriangleString);
      statement.execute(createTableRectangleString);
      statement.execute(createTableBatch);
      statement.execute(createTableBatchMembers);
      statement.execute(createTableDrawing);
      statement.execute(createTableDrawingMembers);
    }
    catch (SQLException | ClassNotFoundException e){
      System.out.println("Existing database found");
      try {
        connection.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    } catch (IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }
    this.drawingTUI = new DrawingTUI();
  }

  public void run(){
    while(true) {
      command = this.drawingTUI.nextCommand();
      try {
        command.execute();
      } catch (NullPointerException e) {
        System.out.println("####");//TODO
      }
      this.drawingTUI.displayDrawingBoard();
    }
  }

}
