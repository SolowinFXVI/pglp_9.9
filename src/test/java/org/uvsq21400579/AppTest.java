package org.uvsq21400579;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uvsq21400579.Commands.DrawCircle;
import org.uvsq21400579.Commands.DrawRectangle;
import org.uvsq21400579.Commands.DrawSquare;
import org.uvsq21400579.Commands.DrawTriangle;
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


public class AppTest
{
  @BeforeClass
  public static void initializeDB(){
    String cleanupTableDrawingMembersString = "DROP TABLE DRAWINGBOARDMEMBERS";
    String cleanupTableDrawingString = "DROP TABLE DRAWINGBOARD";
    String cleanupTableSquareString = "DROP TABLE SQUARE";
    String cleanupTableCircleString = "DROP TABLE CIRCLE";
    String cleanupTableTriangleString = "DROP TABLE TRIANGLE";
    String cleanupTableRectangleString = "DROP TABLE RECTANGLE";
    String cleanupTableBatchMembers = "DROP TABLE BATCHMEMBERS";
    String cleanupTableBatch = "DROP TABLE BATCH";
    String createTableSquareString = "CREATE TABLE SQUARE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, SIDE INT)";
    String createTableCircleString = "CREATE TABLE CIRCLE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, RADIUS INT)";
    String createTableTriangleString = "CREATE TABLE TRIANGLE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, SECOND_X INT, SECOND_Y INT, THIRD_X INT, THIRD_Y INT)";
    String createTableRectangleString = "CREATE TABLE RECTANGLE(NAME VARCHAR(128) PRIMARY KEY NOT NULL, FIRST_X INT, FIRST_Y INT, SECOND_X INT, SECOND_Y INT)";
    String createTableBatchString = "CREATE TABLE BATCH(BATCHNAME VARCHAR(128) PRIMARY KEY NOT NULL)";
    String createTableBatchMembersString = "CREATE TABLE BATCHMEMBERS(BATCHNAME VARCHAR(128), NAME VARCHAR(128),"
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
    try {
      Class.forName(driver).newInstance();
      Connection connection = DriverManager
          .getConnection(protocol + "drawingAppDB;create=true");
      Statement statement = connection.createStatement();

//            statement.execute(cleanupTableDrawingMembersString);
//            statement.execute(cleanupTableDrawingString);
//            statement.execute(cleanupTableSquareString);
//            statement.execute(cleanupTableCircleString);
//            statement.execute(cleanupTableTriangleString);
//            statement.execute(cleanupTableRectangleString);
//            statement.execute(cleanupTableBatchMembers);
//            statement.execute(cleanupTableBatch);

      statement.execute(createTableSquareString);
      statement.execute(createTableCircleString);
      statement.execute(createTableTriangleString);
      statement.execute(createTableRectangleString);
      statement.execute(createTableBatchString);
      statement.execute(createTableBatchMembersString);
      statement.execute(createTableDrawing);
      statement.execute(createTableDrawingMembers);
      connection.close();
    }
    catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  @AfterClass
  public static void cleanupDB() throws SQLException {
    String cleanupTableDrawingMembersString = "DROP TABLE DRAWINGBOARDMEMBERS";
    String cleanupTableDrawingString = "DROP TABLE DRAWINGBOARD";
    String cleanupTableSquareString = "DROP TABLE SQUARE";
    String cleanupTableCircleString = "DROP TABLE CIRCLE";
    String cleanupTableTriangleString = "DROP TABLE TRIANGLE";
    String cleanupTableRectangleString = "DROP TABLE RECTANGLE";
    String cleanupTableBatchMembers = "DROP TABLE BATCHMEMBERS";
    String cleanupTableBatch = "DROP TABLE BATCH";

    Connection connection = DriverManager.getConnection("jdbc:derby:drawingAppDB");
    Statement statement = connection.createStatement();
    statement.execute(cleanupTableDrawingMembersString);
    statement.execute(cleanupTableDrawingString);
    statement.execute(cleanupTableSquareString);
    statement.execute(cleanupTableCircleString);
    statement.execute(cleanupTableTriangleString);
    statement.execute(cleanupTableRectangleString);
    statement.execute(cleanupTableBatchMembers);
    statement.execute(cleanupTableBatch);
    connection.close();
  }

  @Test
  public void testMoveBy(){
    CircleDAO dao = new CircleDAO();
    Circle shape = new Circle("cercle1", new Coordinates(1,1), 3);
    shape.moveBy(1, 1);
    assertEquals(Integer.parseInt(shape.getCenter().getX()), 2);
    assertEquals(Integer.parseInt(shape.getCenter().getY()), 2);
  }

  @Test
  public void negativeMoveBy(){
    CircleDAO dao = new CircleDAO();
    Circle shape = new Circle("cercle1", new Coordinates(1,1), 3);
    shape.moveBy(-1, -1);
    assertEquals(Integer.parseInt(shape.getCenter().getX()), 0);
    assertEquals(Integer.parseInt(shape.getCenter().getY()), 0);
  }

  @Test
  public void testCircleDAO(){
    CircleDAO dao = new CircleDAO();
    Circle shape = new Circle("cercle_test_1", new Coordinates(1,1), 3);
    dao.create(shape);
    Circle result = dao.find("cercle_test_1");
    dao.delete("cercle_test_1");
    assertEquals(result.name, shape.name);
    assertEquals(result.getCenter().getX(), shape.getCenter().getX());
    assertEquals(result.getCenter().getY(), shape.getCenter().getY());
    assertEquals(result.getRadius(), shape.getRadius());
  }

  @Test
  public void testSquareDAO(){
    SquareDAO dao = new SquareDAO();
    Square square = new Square("square_test_1", new Coordinates(2,2), 3);
    dao.create(square);
    Square result = dao.find("square_test_1");
    dao.delete("square_test_1");
    assertEquals(result.name, square.name);
    assertEquals(result.getTopLeft().getX(), square.getTopLeft().getX());
    assertEquals(result.getTopLeft().getY(), square.getTopLeft().getY());
    assertEquals(result.getSide(), square.getSide());
  }

  @Test
  public void testRectangleDAO(){
    RectangleDAO dao = new RectangleDAO();
    Rectangle rectangle = new Rectangle("rectangle_test_1", new Coordinates(1, 2), new Coordinates(3, 4));
    dao.create(rectangle);
    Rectangle result = dao.find("rectangle_test_1");
    dao.delete("rectangle_test_1");
    assertEquals(result.name, rectangle.name);
    assertEquals(result.getFirst().getX(), rectangle.getFirst().getX());
    assertEquals(result.getFirst().getY(), rectangle.getFirst().getY());
    assertEquals(result.getSecond().getX(), rectangle.getSecond().getX());
    assertEquals(result.getSecond().getY(), rectangle.getSecond().getY());
  }

  @Test
  public void testTriangleDAO(){
    TriangleDAO dao = new TriangleDAO();
    Triangle triangle = new Triangle("triangle1", new Coordinates(1, 2), new Coordinates(3, 4), new Coordinates(5, 6));
    dao.create(triangle);
    Triangle result = dao.find("triangle1");
    dao.delete("triangle1");
    assertEquals(result.name, triangle.name);
    assertEquals(result.getFirst().getX(), triangle.getFirst().getX());
    assertEquals(result.getFirst().getY(), triangle.getFirst().getY());
    assertEquals(result.getSecond().getX(), triangle.getSecond().getX());
    assertEquals(result.getSecond().getY(), triangle.getSecond().getY());
    assertEquals(result.getThird().getX(), triangle.getThird().getX());
    assertEquals(result.getThird().getY(), triangle.getThird().getY());
  }

  @Test
  public void testBatching(){
    BatchDAO dao = new BatchDAO();
    Batch batch = new Batch("test");
    Circle shape = new Circle("cercle1", new Coordinates(1,1), 3);
    Square square = new Square("square1", new Coordinates(2,2), 3);
    Rectangle rectangle = new Rectangle("rectangle1", new Coordinates(1, 2), new Coordinates(3, 4));
    Triangle triangle = new Triangle("triangle1", new Coordinates(1, 2), new Coordinates(3, 4), new Coordinates(5, 6));
    batch.addShape(shape);
    batch.addShape(square);
    batch.addShape(rectangle);
    batch.addShape(triangle);
    dao.create(batch);
    Batch result = dao.find("test");
    dao.delete("test");
    assertEquals(result.name, batch.name);
    result.display();
    batch.display();
    //TODO MORE THAN VISUAL PROOF
  }

  @Test
  public void testBatchMove(){
    BatchDAO dao = new BatchDAO();
    Batch batch = new Batch("test2");
    Circle shape = new Circle("cercle2", new Coordinates(1,1), 3);
    Square square = new Square("square2", new Coordinates(2,2), 3);
    Rectangle rectangle = new Rectangle("rectangle2", new Coordinates(1, 2), new Coordinates(3, 4));
    Triangle triangle = new Triangle("triangle2", new Coordinates(1, 2), new Coordinates(3, 4), new Coordinates(5, 6));
    batch.addShape(shape);
    batch.addShape(square);
    batch.addShape(rectangle);
    batch.addShape(triangle);
    dao.create(batch);
    Batch result = dao.find("test2");
    dao.delete("test2");
    assertEquals(result.name, batch.name);
    batch.moveBy(1,1);
    result.display();
    batch.display();
    //TODO MORE THAN VISUAL PROOF
  }

  @Test
  public void testBatchInBatch(){
    BatchDAO dao = new BatchDAO();
    Batch batch = new Batch("test2");
    Circle shape = new Circle("cercle2", new Coordinates(1,1), 3);
    Square square = new Square("square2", new Coordinates(2,2), 3);
    Rectangle rectangle = new Rectangle("rectangle2", new Coordinates(1, 2), new Coordinates(3, 4));
    Triangle triangle = new Triangle("triangle2", new Coordinates(1, 2), new Coordinates(3, 4), new Coordinates(5, 6));
    batch.addShape(shape);
    batch.addShape(square);
    batch.addShape(rectangle);
    batch.addShape(triangle);
    Batch batch2 = new Batch("test3");
    Square square2 = new Square("squareInBatch2", new Coordinates(3,3),3);
    batch2.addShape(square2);
    batch.addShape(batch2);
    dao.create(batch);
    Batch result = dao.find("test2");
    dao.delete("test2");
    assertEquals(result.name, batch.name);
    result.display();
    batch.display();
    //TODO MORE THAN VISUAL PROOF
  }

  @Test
  public void testCommands(){
    DrawingBoard testBoard = new DrawingBoard("testBoard");
    Command commandSquare = new DrawSquare( testBoard,"rectangle1", new Coordinates(1, 2), 1);
    commandSquare.execute();
    Command commandCircle = new DrawCircle(testBoard, "circle", new Coordinates(2, 3), 3);
    commandCircle.execute();
    Command commandTriangle = new DrawTriangle(testBoard, "triangle", new Coordinates(1, 2), new Coordinates(3,4), new Coordinates(5,6));
    commandTriangle.execute();
    Command commandRectangle = new DrawRectangle(testBoard, "rectangle", new Coordinates(1, 2), new Coordinates(5, 5));
    commandRectangle.execute();
  }

  @Test
  public void testDrawing(){
    DrawingBoardDAO drawingBoardDAO = new DrawingBoardDAO();
    DrawingBoard drawingBoard = new DrawingBoard("testBoard");
    Circle shape = new Circle("cercle2", new Coordinates(1,1), 3);
    Square square = new Square("square2", new Coordinates(2,2), 3);
    drawingBoard.addShape(shape);
    drawingBoard.addShape(square);
    drawingBoardDAO.create(drawingBoard);
    DrawingBoard result = drawingBoardDAO.find("testBoard");
    result.getShapeList();
    drawingBoardDAO.delete("testBoard");
  }
}
