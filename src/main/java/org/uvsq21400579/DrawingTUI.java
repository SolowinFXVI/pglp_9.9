package org.uvsq21400579;

import java.security.InvalidParameterException;
import java.util.Scanner;
import org.uvsq21400579.Commands.Delete;
import org.uvsq21400579.Commands.DrawBatch;
import org.uvsq21400579.Commands.DrawCircle;
import org.uvsq21400579.Commands.DrawSquare;
import org.uvsq21400579.Commands.Load;
import org.uvsq21400579.Commands.MoveBy;
import org.uvsq21400579.Commands.Quit;
import org.uvsq21400579.Commands.Save;

public class DrawingTUI {

  Scanner scanner;
  DrawingBoard drawingBoard;
  DAO dao;

  public DrawingTUI() {
    this.scanner = new Scanner(System.in, "UTF-8");
    this.drawingBoard = new DrawingBoard("Main");
    this.dao = DAOFactory.getDrawingBoardDAO();
  }

  public Command nextCommand(){
    String input;
    Command command = null;
    input = this.scanner.nextLine();
    input = input.replaceAll("\\s+", "");
    String commandString = input.substring(0, input.indexOf("("));
    try{
      if(commandString.matches("move")){
        command = moveByCommand(input);
      }
      else if(commandString.matches("drawBatch")){
        command = drawBatch(input);
      }
      else if(commandString.matches("delete")){
        command = delete(input);
      }
      else if(commandString.matches("save")){
        command = saveCommand(input);
      }
      else if(commandString.matches("load")){
        command = load(input);
      }
      else if(commandString.matches("quit")){
        command = quit();
      }
      else if(input.contains("=")){
        command= drawShape(input);
      }
    }
    catch (Exception e){
      System.out.println("Veuillez entrer la bonne syntaxe");
    }
    return command;
  }

  Command moveByCommand(String input){
    Command command = null;
    String shapeName = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    int x = Integer.parseInt(input.substring(input.lastIndexOf("(")+1, input.lastIndexOf(",")));
    int y = Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.lastIndexOf(")")));
    for(Shape shapes : drawingBoard.getShapeList()){
      if(shapes.getName().contentEquals(shapeName)){
        command = new MoveBy((Shape) shapes, x, y);
      }
    }
    return command;
  }

  Command saveCommand(String input){
    this.drawingBoard.name = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    return new Save(this.drawingBoard);
  }

  Command drawBatch(String input){
    String groupName = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    String[] groupMembers = input.substring(input.lastIndexOf("(") + 1, input.indexOf(")")).split(",");
    return new DrawBatch(this.drawingBoard, groupName, groupMembers);
  }

  Command delete(String input){
    String shapeName = input.substring(input.indexOf("(") + 1 , input.indexOf(")"));
    return new Delete(shapeName, this.drawingBoard);
  }

  Load load(String input){
    String name = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    return new Load(name, this);
  }

  Command quit(){
    return new Quit();
  }

  public void setDrawingBoard(DrawingBoard drawingBoard) {
    this.drawingBoard = drawingBoard;
  }

  Command drawShape(String input){
    Command command = null;
    String[] params = input.split("=");
    String shapeName = input.substring(0, input.indexOf("="));
    String shapeType = params[1].substring(0, params[1].indexOf("("));
    if(shapeType.matches("Circle")){
      command = drawCircle(input, shapeName);
    }
    else if(shapeType.matches("Square")){
      command = drawSquare(input, shapeName);
    }
    else if(shapeType.matches("Rectangle")){
      command = drawRectangle(input, shapeName);
    }
    else if(shapeName.matches("Triangle")){
      command = drawTriangle(input, shapeName);
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
    String[] params = input.split(",");
    int x = Integer.parseInt(input.substring(input.lastIndexOf("(") + 1, input.indexOf(",")));
    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf(")")));
    int radius = Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.lastIndexOf(")")));
    return new DrawCircle(this.drawingBoard, shapeName, new Coordinates(x,y), radius);
  }

  private Command drawRectangle(String input, String shapeName) {//TODO
    return null;
  }

  private Command drawTriangle(String input, String shapeName) { //TODO
    return null;
  }
}
