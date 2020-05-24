package org.uvsq21400579;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawingBoard {

  List<Shape> shapeList;
  String name;

  public DrawingBoard(String name) {
    this.name = name;
    this.shapeList = new ArrayList<>();
  }

  public List<Shape> getShapeListUnmodifiable(){
    return Collections.unmodifiableList(shapeList);
  }

  public List<Shape> getShapeList(){
    return this.shapeList;
  }

  public void setShapeList(List<Shape> shapeList) {
    this.shapeList = shapeList;
  }

  public String getName(){
    return this.name;
  }

  public void addShape(Shape shape){
    this.shapeList.add(shape);
  }

}
