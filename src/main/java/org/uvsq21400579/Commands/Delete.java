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
  public void execute() { //TODO BROKEN
    List<Shape> list = this.drawingBoard.getShapeList();
    for(int i = 0; i < list.size(); i++){
      if(list.get(i).getName().equals(name)){
        list.remove(i);
      }
    }
  }
}
