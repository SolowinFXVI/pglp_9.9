package org.uvsq21400579;

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
    String input = null;
    Command command = null;
    String commandString = null;
    try {
      input = this.scanner.nextLine();
      input = input.replaceAll("\\s+", "");
      commandString = input.substring(0, input.indexOf("("));}
    catch (Exception e){
      System.out.println("Illegal argument");
    }
    try{
      if(commandString.matches("move") || commandString.matches("Move")){
        command = moveByCommand(input);
      }
      else if(commandString.matches("drawBatch") || commandString.matches("Batch") || commandString.matches("group") || commandString.matches("Group")){
        command = drawBatch(input);
      }
      else if(commandString.matches("delete") || commandString.matches("Delete")){
        command = delete(input);
      }
      else if(commandString.matches("save") || commandString.matches("Save")){
        command = saveCommand(input);
      }
      else if(commandString.matches("load") || commandString.matches("Load")){
        command = load(input);
      }
      else if(commandString.matches("quit")|| commandString.matches("Quit")){
        command = quit();
      }
      else if(input.contains("=")){
        command= drawShape(input);
      }
    }
    catch (Exception e){
      System.out.println("Please input correct syntax");
    }
    return command;
  }

  Command moveByCommand(String input){
    Command command = null;

    System.out.println("Move Detected");

    String shapeName = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    System.out.println("shapeName : "+ shapeName);
    int x = Integer.parseInt(input.substring(input.lastIndexOf("(")+1, input.lastIndexOf(",")));
    System.out.println("moveX : "+ x);
    int y = Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.indexOf(")")));
    System.out.println("moveY : "+ y);
    for(Shape shapes : drawingBoard.getShapeList()){
      if(shapes.getName().contentEquals(shapeName)){
        command = new MoveBy((Shape) shapes, x, y);
      }
    }
    return command;
  }

  Command saveCommand(String input){

    System.out.println("Save Detected");

    this.drawingBoard.name = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    return new Save(this.drawingBoard);
  }

  Command drawBatch(String input){

    System.out.println("Batch Detected");

    String groupName = input.substring(input.indexOf("(") + 1, input.indexOf(","));
    String[] groupMembers = input.substring(input.lastIndexOf("(") + 1, input.indexOf(")")).split(",");
    return new DrawBatch(this.drawingBoard, groupName, groupMembers);
  }

  Command delete(String input){

    System.out.println("delete Detected");

    String shapeName = input.substring(input.indexOf("(") + 1 , input.indexOf(")"));
    return new Delete(shapeName, this.drawingBoard);
  }

  Load load(String input){

    System.out.println("load Detected");

    String name = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    Load load = new Load(name, this);
    return load;
  }

  Command quit(){
    System.out.println("quit Detected");
    return new Quit();
  }

  public void setDrawingBoard(DrawingBoard drawingBoard) {
    this.drawingBoard = drawingBoard;
  }

  Command drawShape(String input){

    System.out.println("DrawShape Detected");

    Command command = null;
    String[] params = input.split("=");
    String shapeName = input.substring(0, input.indexOf("="));
    String shapeType = params[1].substring(0, params[1].indexOf("("));
    if(shapeType.matches("Circle") || shapeType.matches("circle")){
      command = drawCircle(input, shapeName);
    }
    else if(shapeType.matches("Square") || shapeType.matches("square")){
      command = drawSquare(input, shapeName);
    }
    else if(shapeType.matches("Rectangle") || shapeType.matches("rectangle")){
      command = drawRectangle(input, shapeName);
    }
    else if(shapeName.matches("Triangle") || shapeType.matches("triangle")){
      command = drawTriangle(input, shapeName);
    }
    return command;
  }

  private Command drawSquare(String input, String shapeName) {

    System.out.println("draw square Detected");

    String[] params = input.split(",");
    int x = Integer.parseInt(params[0].substring(params[0].lastIndexOf("(") + 1));
    int y = Integer.parseInt(params[1].substring(0, params[1].lastIndexOf(")")));
    int side = Integer.parseInt(params[2].substring(0, params[2].length() - 1));
    return new DrawSquare(this.drawingBoard, shapeName, new Coordinates(x, y), side);
  }

  private Command drawCircle(String input, String shapeName) {

    System.out.println("draw Circle Detected");

    String[] params = input.split(",");
    int x = Integer.parseInt(input.substring(input.lastIndexOf("(") + 1, input.indexOf(",")));
    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf(")")));
    int radius = Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.lastIndexOf(")")));
    return new DrawCircle(this.drawingBoard, shapeName, new Coordinates(x,y), radius);
  }

  private Command drawRectangle(String input, String shapeName) {//TODO

    System.out.println("draw Rectangle Detected");

    return null;
  }

  private Command drawTriangle(String input, String shapeName) { //TODO

    System.out.println("draw triangle Detected");

    return null;
  }

  public void displayDrawingBoard() {
    for(Shape shapes : drawingBoard.getShapeList()){
      shapes.display();
    }
  }
}
