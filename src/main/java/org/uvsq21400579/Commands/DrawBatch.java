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
    List<Shape> list = this.drawingBoard.getShapeList();
    Batch batch = new Batch(this.name);
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < batchList.length ; j++) {
        if (list.get(i).getName().equals(batchList[j])) {
          batch.addShape(list.get(i));
          list.remove(i);
        }
      }
    }
    list.add(batch);
  }
}
