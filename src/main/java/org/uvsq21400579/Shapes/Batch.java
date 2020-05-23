package org.uvsq21400579.Shapes;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;
import org.uvsq21400579.Shape;

public class Batch  extends Shape {

  List<Shape> shapesList;

  public Batch(String name) {
    super(name);
    shapesList = new ArrayList<>();
  }

  public void addShape(Shape shape){
    this.shapesList.add(shape);
  }

  @Override
  public void moveBy(int x, int y) {
    for (Shape shape : shapesList){
      shape.moveBy(x,y);
    }
  }

  @Override
  public void display() {
    System.out.println("\n"+this.name);
    for(Shape shape : shapesList){
      shape.display();
    }
    System.out.println("//" + this.name + "\n");
  }
}
