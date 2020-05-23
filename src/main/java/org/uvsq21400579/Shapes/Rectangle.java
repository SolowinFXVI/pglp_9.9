package org.uvsq21400579.Shapes;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.Shape;

public class Rectangle extends Shape {
  Coordinates first;
  Coordinates second;

  public Rectangle(String name, Coordinates first, Coordinates second) {
    super(name);
    this.first = first;
    this.second = second;
  }

  @Override
  public void moveBy(int x, int y) {
    this.first.moveBy(x, y);
    this.second.moveBy(x, y);
  }

  @Override
  public void display() {
    System.out.println(this.name + this.first.getCoordinates() + this.second.getCoordinates());
  }
}
