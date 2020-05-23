package org.uvsq21400579.Commands;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import java.util.List;
import org.uvsq21400579.DrawingBoard;
import org.uvsq21400579.Shape;
import org.uvsq21400579.Shapes.Batch;

public class DrawBatch extends DrawShape{

  String name;
  StringArray batchList;

  public DrawBatch(DrawingBoard drawingBoard, String name,
      StringArray batchList) {
    super(drawingBoard);
    this.name = name;
    this.batchList = batchList;
  }

  @Override
  public void execute() {
    List<Shape> list = this.drawingBoard.getShapeList();
    Batch batch = new Batch(this.name);
    for (Shape shape : list) {
      for (String name : batchList.getArray()) {
        if (name.matches(shape.getName())) {
          batch.addShape(shape);
          list.remove(name);
        }
      }
    }
    list.add(batch);
  }
}
