package org.uvsq21400579.Commands;

import com.sun.xml.internal.fastinfoset.util.StringArray;
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
    int index = 0;
    List<Shape> list = this.drawingBoard.getShapeList();
    Batch batch = new Batch(this.name);
    for (Shape shape : list) {
      index += 1;
      for (String name : batchList) {
        if (name.matches(shape.getName())) {
          batch.addShape(shape);
          list.remove(index);
        }
      }
    }
    list.add(batch);
  }
}
