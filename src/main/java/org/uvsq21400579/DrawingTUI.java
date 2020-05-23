package org.uvsq21400579;

import java.util.Scanner;

public class DrawingTUI {

  Scanner scanner;
  DrawingBoard drawingBoard;
  DAO dao;

  public DrawingTUI() {
    this.scanner = new Scanner(System.in, "UTF-8");
    this.drawingBoard = new DrawingBoard("Main");
    this.dao = DAOFactory.getDrawingBoardDAO();
  }
}
