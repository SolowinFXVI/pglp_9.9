package org.uvsq21400579;

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

public class DAOFactory {

  public static DAO<Square> getSquareDAO() {
    return new SquareDAO();
  }

  public static DAO<Circle> getCircleDAO() {
    return new CircleDAO();
  }

  public static DAO<Triangle> getTriangleDAO() {
    return new TriangleDAO();
  }

  public static DAO<Rectangle> getRectangleDAO() {
    return new RectangleDAO();
  }

  public static DAO<Batch> getBatchDAO() {
    return new BatchDAO();
  }

  public static DAO<DrawingBoard> getDrawingBoardDAO() {
    return new DrawingBoardDAO();
  }

}
