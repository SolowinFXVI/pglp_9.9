package org.uvsq21400579;

import java.util.ArrayList;
import java.util.List;

public class DrawingBoard {

  List<Shape> shapeList;
  String name;

  public DrawingBoard(String name) {
    this.name = name;
    this.shapeList = new ArrayList<>();
  }

  public List<Shape> getShapeList() {
    return this.shapeList;
  }

  public String getName() {
    return this.name;
  }

  public void addShape(Shape shape) {
    this.shapeList.add(shape);
  }

}
