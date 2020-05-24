package org.uvsq21400579.Commands;

import org.uvsq21400579.Command;
import org.uvsq21400579.Shape;
import org.uvsq21400579.Shapes.Batch;

public class MoveBy implements Command {

  Shape shape;
  Batch batch;
  int x;
  int y;

  /**
   * Moves a shape by the specified amount x and y.
   * @param shape .
   * @param x Horizontal.
   * @param y Vertical.
   */
  public MoveBy(Shape shape, int x, int y) {
    this.shape = shape;
    this.x = x;
    this.y = y;
  }

  @Override
  public void execute() {
    if (this.shape != null) {
      this.shape.moveBy(this.x, this.y);
    } else {
      this.batch.moveBy(this.x, this.y);
    }
  }
}
