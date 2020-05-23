package org.uvsq21400579.Shapes;

import org.uvsq21400579.Coordinates;
import org.uvsq21400579.Shape;

public class Triangle extends Shape {

  Coordinates first;
  Coordinates second;
  Coordinates third;

  public Triangle(String name, Coordinates first, Coordinates second, Coordinates third) {
    super(name);
    this.first = first;
    this.second = second;
    this.third = third;
  }

  @Override
  public void moveBy(int x, int y) {
    this.first.moveBy(x, y);
    this.second.moveBy(x, y);
    this.third.moveBy(x, y);
  }

  @Override
  public void display() {
    System.out.println(this.name + this.first.getCoordinates() + this.second.getCoordinates() + this.third.getCoordinates());
  }

  public Coordinates getFirst() {
    return first;
  }

  public Coordinates getSecond() {
    return second;
  }

  public Coordinates getThird() {
    return third;
  }
}
