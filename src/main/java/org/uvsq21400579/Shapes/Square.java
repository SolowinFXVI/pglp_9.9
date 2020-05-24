package org.uvsq21400579.Shapes;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.Shape;

public class Square extends Shape {
  Coordinates topLeft;

  int side;

  /**
   * A Square is defined by one Coordinate, top left in our case, and a side length.
   * @param name .
   * @param topLeft .
   * @param side .
   */
  public Square(String name, Coordinates topLeft, int side) {
    super(name);
    this.topLeft = topLeft;
    this.side = side;
  }

  public String getSide() {
    return String.valueOf(side);
  }

  @Override
  public void moveBy(int x, int y) {
    this.topLeft.moveBy(x,y);
  }

  @Override
  public void display() {
    System.out.println("Square : " + this.name + " (top-left :" + this.topLeft.getCoordinates()
        + ", side :" + side + ")");
  }

  public Coordinates getTopLeft() {
    return topLeft;
  }
}
