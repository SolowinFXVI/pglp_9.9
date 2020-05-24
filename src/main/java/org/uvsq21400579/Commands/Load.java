package org.uvsq21400579.Commands;

import org.uvsq21400579.Command;
import org.uvsq21400579.DAOFactory;
import org.uvsq21400579.DrawingTUI;

public class Load implements Command {

  DrawingTUI drawingTUI;
  String name;

  public Load(String name, DrawingTUI drawingTUI) {
    this.drawingTUI = drawingTUI;
    this.name = name;
  }

  @Override
  public void execute() {
    this.drawingTUI.setDrawingBoard(DAOFactory.getDrawingBoardDAO().find(this.name));
  }
}
