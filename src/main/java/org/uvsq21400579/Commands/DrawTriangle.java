package org.uvsq21400579.Commands;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shapes.Triangle;

public class DrawTriangle extends DrawShape{
  String name;
  Coordinates first;
  Coordinates second;
  Coordinates third;

  public DrawTriangle(DrawingBoard drawingBoard, String name, Coordinates first,
      Coordinates second, Coordinates third) {
    super(drawingBoard);
    this.name = name;
    this.first = first;
    this.second = second;
    this.third = third;
  }

  @Override
  public void execute() {
    super.drawingBoard.addShape(new Triangle(name, first, second, third));
  }
}
