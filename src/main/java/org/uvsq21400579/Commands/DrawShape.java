package org.uvsq21400579.Commands;

import org.uvsq21400579.Command;
import org.uvsq21400579.DrawingBoard;

public abstract class DrawShape implements Command {
  DrawingBoard drawingBoard;

  public DrawShape(DrawingBoard drawingBoard) {
    this.drawingBoard = drawingBoard;
  }


}
