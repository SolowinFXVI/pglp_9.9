package org.uvsq21400579.Commands;

import java.util.List;
import org.uvsq21400579.Command;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shape;

public class Delete implements Command {

  String name;
  DrawingBoard drawingBoard;

  public Delete(String name, DrawingBoard drawingBoard) {
    this.name = name;
    this.drawingBoard = drawingBoard;
  }

  @Override
  public void execute() {
    int index = 0;
    List<Shape> list = this.drawingBoard.getShapeList();
    for(Shape shapes : list){
      index++;
      if(shapes.getName().equals(name)){
        list.remove(index);
      }
    }
  }
}
