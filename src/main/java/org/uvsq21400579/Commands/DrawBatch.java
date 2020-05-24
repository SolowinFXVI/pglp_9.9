package org.uvsq21400579.Commands;

import java.util.List;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shape;
import org.uvsq21400579.Shapes.Batch;

public class DrawBatch extends DrawShape{

  String name;
  String[] batchList;

  public DrawBatch(DrawingBoard drawingBoard, String name,
      String[] batchList) {
    super(drawingBoard);
    this.name = name;
    this.batchList = batchList;
  }

  @Override
  public void execute() {
    List<Shape> shapeList = this.drawingBoard.getShapeList();
    Batch batch = new Batch(this.name);
    for (int i = 0; i < shapeList.size(); i++) {
      for (String s : batchList) {
        if (shapeList.get(i).getName().equals(s)) {
          batch.addShape(shapeList.get(i));
          shapeList.remove(i);
        }
      }
    }
    shapeList.add(batch);
  }
}
