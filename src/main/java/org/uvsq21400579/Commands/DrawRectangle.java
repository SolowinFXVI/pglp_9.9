package org.uvsq21400579.Commands;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shapes.Rectangle;

public class DrawRectangle extends DrawShape{

  String name;
  Coordinates first;
  Coordinates second;

  public DrawRectangle(DrawingBoard drawingBoard, String name,
      Coordinates first, Coordinates second) {
    super(drawingBoard);
    this.name = name;
    this.first = first;
    this.second = second;
  }

  @Override
  public void execute() {
    super.drawingBoard.addShape(new Rectangle(name, first, second));
  }
}
