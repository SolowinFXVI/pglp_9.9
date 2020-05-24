package org.uvsq21400579.Commands;

import org.uvsq21400579.Command;
import org.uvsq21400579.DAOFactory;
import org.uvsq21400579.DrawingBoard;

public class Save implements Command {

  private final DrawingBoard drawingBoard;

  public Save(DrawingBoard drawingBoard) {
    this.drawingBoard = drawingBoard;
  }

  @Override
  public void execute() {
    DAOFactory.getDrawingBoardDAO().create(this.drawingBoard);
  }
}
