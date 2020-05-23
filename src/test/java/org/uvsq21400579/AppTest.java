package org.uvsq21400579;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uvsq21400579.Commands.Delete;
import org.uvsq21400579.Commands.DrawCircle;
import org.uvsq21400579.Commands.DrawRectangle;
import org.uvsq21400579.Commands.DrawSquare;
import org.uvsq21400579.Commands.DrawTriangle;
import org.uvsq21400579.Commands.Quit;
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

    }

    @AfterClass
    public static void cleanupDB(){

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
        Circle shape = new Circle("cercle1", new Coordinates(1,1), 3);
        dao.create(shape);
        Circle result = dao.find("cercle1");
        dao.delete("cercle1");
        assertEquals(result.name, shape.name);
        assertEquals(result.getCenter(), shape.getCenter());
        assertEquals(result.getRadius(), shape.getRadius());
    }

    @Test
    public void testSquareDAO(){
        SquareDAO dao = new SquareDAO();
        Square square = new Square("square1", new Coordinates(2,2), 3);
        dao.create(square);
        Square result = dao.find("square1");
        dao.delete("square1");
        assertEquals(result.name, square.name);
        assertEquals(result.getTopLeft(), square.getTopLeft());
        assertEquals(result.getSide(), square.getSide());
    }

    @Test
    public void testRectangleDAO(){
        RectangleDAO dao = new RectangleDAO();
        Rectangle rectangle = new Rectangle("rectangle1", new Coordinates(1, 2), new Coordinates(3, 4));
        dao.create(rectangle);
        Rectangle result = dao.find("rectangle1");
        dao.delete("rectangle1");
        assertEquals(result.name, rectangle.name);
        assertEquals(result.getFirst(), rectangle.getFirst());
        assertEquals(result.getSecond(), rectangle.getSecond());
    }

    @Test
    public void testTriangleDAO(){
        TriangleDAO dao = new TriangleDAO();
        Triangle triangle = new Triangle("triangle1", new Coordinates(1, 2), new Coordinates(3, 4), new Coordinates(5, 6));
        dao.create(triangle);
        Triangle result = dao.find("triangle1");
        dao.delete("triangle1");
        assertEquals(result.name, triangle.name);
        assertEquals(result.getFirst(), triangle.getFirst());
        assertEquals(result.getSecond(), triangle.getSecond());
        assertEquals(result.getThird(), triangle.getThird());
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
        assertEquals(result.getShapesList(), batch.getShapesList());
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
}
