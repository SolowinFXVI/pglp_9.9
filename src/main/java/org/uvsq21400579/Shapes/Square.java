package org.uvsq21400579.Shapes;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.Shape;

public class Square extends Shape {
  Coordinates topLeft;
  int side;

  public Square(String name, Coordinates topLeft, int side) {
    super(name);
    this.topLeft = topLeft;
    this.side = side;
  }


  @Override
  public void moveBy(int x, int y) {
    this.topLeft.moveBy(x,y);
  }

  @Override
  public void display() {
    System.out.println(this.name + this.topLeft.getCoordinates() + this.side);
  }
}
