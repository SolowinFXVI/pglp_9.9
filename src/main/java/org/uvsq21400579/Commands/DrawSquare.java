package org.uvsq21400579.Commands;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shapes.Square;

public class DrawSquare extends DrawShape {
  String name;
  Coordinates coordinates;
  int side;

  public DrawSquare(DrawingBoard drawingBoard, String name, Coordinates coordinates, int side) {
    super(drawingBoard);
    this.name = name;
    this.coordinates = coordinates;
    this.side = side;
  }

  @Override
  public void execute() {
    super.drawingBoard.addShape(new Square(this.name, this.coordinates, this.side));
  }
}
