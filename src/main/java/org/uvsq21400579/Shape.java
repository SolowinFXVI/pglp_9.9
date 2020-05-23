package org.uvsq21400579;

public abstract class Shape {

  public final String name;

  public Shape(String name){
    this.name = name;
  }

  /**
   * Move a shape by x or y amount from its current position
   * @param x
   * @param y
   */
  public abstract void moveBy(int x, int y);

  public abstract void display();

  public String getName(){
    return name;
  }



}
