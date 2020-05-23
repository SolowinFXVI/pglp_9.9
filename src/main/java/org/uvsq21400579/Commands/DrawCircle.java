package org.uvsq21400579.Commands;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shapes.Circle;

public class DrawCircle extends DrawShape{

  String name;
  Coordinates coordinates;
  int radius;

  public DrawCircle(DrawingBoard drawingBoard, String name,
      Coordinates coordinates, int radius) {
    super(drawingBoard);
    this.name = name;
    this.coordinates = coordinates;
    this.radius = radius;
  }

  @Override
  public void execute() {
    super.drawingBoard.addShape(new Circle(name, coordinates, radius));
  }
}
