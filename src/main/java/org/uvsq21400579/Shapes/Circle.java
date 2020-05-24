package org.uvsq21400579.Shapes;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.Shape;

public class Circle extends Shape {

  Coordinates center;
  int radius;

  /**
   * A circle is defined by a Name, a Center and a Radius.
   * @param name .
   * @param center .
   * @param radius .
   */
  public Circle(String name, Coordinates center, int radius) {
    super(name);
    this.center = center;
    this.radius = radius;
  }

  @Override
  public void moveBy(int x, int y) {
    this.center.moveBy(x,y);
  }

  public Coordinates getCenter() {
    return center;
  }

  @Override
  public void display() {
    System.out.println("Circle : " + this.name + " (center :" + this.center.getCoordinates()
        + ", radius :" + radius + ")");
  }

  public String getRadius() {
    return String.valueOf(this.radius);
  }
}
