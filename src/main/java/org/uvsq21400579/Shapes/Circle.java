package org.uvsq21400579.Shapes;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.Shape;

public class Circle extends Shape {

  Coordinates center;
  int radius;

  public Circle(String name, Coordinates center, int radius) {
    super(name);
    this.center = center;
    this.radius =radius;
  }

  @Override
  public void moveBy(int x, int y) {
    this.center.moveBy(x,y);
  }

  @Override
  public void display() {
    System.out.println(this.name + this.center.getCoordinates() + radius);
  }

  public String getRadius() {
    return String.valueOf(this.radius);
  }
}
