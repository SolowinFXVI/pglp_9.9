package org.uvsq21400579.Commands;

import org.uvsq21400579.Command;
import org.uvsq21400579.DAOFactory;

public class DeleteDrawingBoard implements Command {

  String name;

  public DeleteDrawingBoard(String name) {
    this.name = name;
  }


  @Override
  public void execute() {
    DAOFactory.getDrawingBoardDAO().delete(name);
  }
}
