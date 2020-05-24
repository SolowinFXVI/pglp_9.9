package org.uvsq21400579;

import java.util.Scanner;
import org.uvsq21400579.Commands.Delete;
import org.uvsq21400579.Commands.DrawBatch;
import org.uvsq21400579.Commands.DrawCircle;
import org.uvsq21400579.Commands.DrawRectangle;
import org.uvsq21400579.Commands.DrawSquare;
import org.uvsq21400579.Commands.DrawTriangle;
import org.uvsq21400579.Commands.Load;
import org.uvsq21400579.Commands.MoveBy;
import org.uvsq21400579.Commands.Quit;
import org.uvsq21400579.Commands.Save;

public class DrawingTUI {

  Scanner scanner;
  DrawingBoard drawingBoard;
  DAO dao;

  /**
   * DrawingTUI constructor.
   */
  public DrawingTUI() {
    this.scanner = new Scanner(System.in, "UTF-8");
    this.drawingBoard = new DrawingBoard("Main");
    this.dao = DAOFactory.getDrawingBoardDAO();
  }

  /**
   * Command pattern nextCommand.
   * @return Command.
   */
  public Command nextCommand() {
    String input = null;
    Command command = null;
    String commandString = null;
    try {
      input = this.scanner.nextLine();
      input = input.replaceAll("\\s+", "");
      commandString = input.substring(0, input.indexOf("("));
    } catch (Exception e) {
      System.out.println("Illegal argument");
    }
    try {
      if (commandString.matches("move") || commandString.matches("Move")) {
        command = moveByCommand(input);
      } else if (commandString.matches("drawBatch") || commandString.matches("Batch")
          || commandString.matches("group") || commandString.matches("Group")) {
        command = drawBatch(input);
      } else if (commandString.matches("delete") || commandString.matches("Delete")) {
        command = delete(input);
      } else if (commandString.matches("save") || commandString.matches("Save")) {
        command = saveCommand(input);
      } else if (commandString.matches("load") || commandString.matches("Load")) {
        command = load(input);
      } else if (commandString.matches("quit") || commandString.matches("Quit")) {
        command = quit();
      } else if (input.contains("=")) {
        command = drawShape(input);
      }
    } catch (IllegalStateException e) {
      System.out.println("Please input correct syntax");
    }
    return command;
  }

  Command moveByCommand(String input) {
    Command command = null;
    String shapeName = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    int x = Integer.parseInt(input.substring(input.lastIndexOf("(") + 1, input.lastIndexOf(",")));
    int y = Integer.parseInt(input.substring(input.lastIndexOf(",") + 1, input.indexOf(")")));
    for (Shape shapes : drawingBoard.getShapeList()) {
      if (shapes.getName().contentEquals(shapeName)) {
        command = new MoveBy(shapes, x, y);
      }
    }
    return command;
  }

  Command saveCommand(String input) {
    this.drawingBoard.name = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    return new Save(this.drawingBoard);
  }

  Command drawBatch(String input) {
    String groupName = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    String[] groupMembers = input.substring(
        input.lastIndexOf("(") + 1, input.indexOf(")")).split(",");
    return new DrawBatch(this.drawingBoard, groupName, groupMembers);
  }

  Command delete(String input) {
    String shapeName = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    return new Delete(shapeName, this.drawingBoard);
  }

  Load load(String input) {
    String name = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    return new Load(name, this);
  }

  Command quit() {
    return new Quit();
  }

  public void setDrawingBoard(DrawingBoard drawingBoard) {
    this.drawingBoard = drawingBoard;
  }

  Command drawShape(String input) {
    Command command = null;
    String[] params = input.split("=");
    String shapeName = input.substring(0, input.indexOf("="));
    String shapeType = params[1].substring(0, params[1].indexOf("("));
    if (shapeType.matches("Circle") || shapeType.matches("circle")) {
      command = drawCircle(input, shapeName);
    } else if (shapeType.matches("Square") || shapeType.matches("square")) {
      command = drawSquare(input, shapeName);
    } else if (shapeType.matches("Rectangle") || shapeType.matches("rectangle")) {
      command = drawRectangle(input, shapeName);
    } else if (shapeType.matches("Triangle") || shapeType.matches("triangle")) {
      command = drawTriangle(input, shapeName);
    } else {
      System.out.println("Invalid Expression");
    }
    return command;
  }

  private Command drawSquare(String input, String shapeName) {
    String[] params = input.split(",");
    int x = Integer.parseInt(params[0].substring(params[0].lastIndexOf("(") + 1));
    int y = Integer.parseInt(params[1].substring(0, params[1].lastIndexOf(")")));
    int side = Integer.parseInt(params[2].substring(0, params[2].length() - 1));
    return new DrawSquare(this.drawingBoard, shapeName, new Coordinates(x, y), side);
  }

  private Command drawCircle(String input, String shapeName) {
    int x = Integer.parseInt(input.substring(input.lastIndexOf("(") + 1, input.indexOf(",")));
    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf(")")));
    int radius = Integer.parseInt(input.substring(
        input.lastIndexOf(",") + 1, input.lastIndexOf(")")));
    return new DrawCircle(this.drawingBoard, shapeName, new Coordinates(x,y), radius);
  }

  private Command drawRectangle(String input, String shapeName) { //TODO
    System.out.println("Rectangle detected");
    String[] params = input.split(",");
    int first_x = Integer.parseInt(params[0].substring(params[0].lastIndexOf("(") + 1));
    System.out.println("first x : " + first_x);
    int first_y = Integer.parseInt(params[1].substring(0,params[1].lastIndexOf(")")));
    System.out.println("first y : " + first_y);
    int second_x = Integer.parseInt(params[2].substring(params[2].lastIndexOf("(") + 1));
    System.out.println("second x : " + second_x);
    int second_y = Integer.parseInt(params[3].substring(0,params[3].lastIndexOf(")") - 1));
    System.out.println("second y : " + second_y);
    return new DrawRectangle(this.drawingBoard, shapeName,
        new Coordinates(first_x, first_y), new Coordinates(second_x, second_y));
  }

  private Command drawTriangle(String input, String shapeName) { //TODO
    System.out.println("Triangle detected");
    String[] params = input.split(",");
    int first_x = Integer.parseInt(params[0].substring(params[0].lastIndexOf("(") + 1));
    int first_y = Integer.parseInt(params[1].substring(0,params[1].lastIndexOf(")")));
    int second_x = Integer.parseInt(params[2].substring(params[2].lastIndexOf("(") + 1));
    int second_y = Integer.parseInt(params[3].substring(0,params[3].lastIndexOf(")")));
    int third_x = Integer.parseInt(params[4].substring(params[4].lastIndexOf("(") + 1));
    int third_y = Integer.parseInt(params[5].substring(0,params[5].lastIndexOf(")") - 1));
    return new DrawTriangle(this.drawingBoard, shapeName,
        new Coordinates(first_x, first_y), new Coordinates(second_x, second_y),
        new Coordinates(third_x, third_y));
  }

  /**
   * Displays all the current elements on the drawingBoard.
   */
  public void displayDrawingBoard() {
    for (Shape shapes : drawingBoard.getShapeList()) {
      shapes.display();
    }
  }
}
