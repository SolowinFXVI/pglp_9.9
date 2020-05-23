package org.uvsq21400579.Commands;

import org.uvsq21400579.Command;
import org.uvsq21400579.DrawingBoard;

public class Save implements Command {

  private DrawingBoard drawingBoard;

  public Save(DrawingBoard drawingBoard) {
    this.drawingBoard = drawingBoard;
  }

  @Override
  public void execute() {

  }
}
