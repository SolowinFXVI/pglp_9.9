package org.uvsq21400579;

public class Coordinates {
  private int x;
  private int y;

  Coordinates(int x, int y){
    this.x = x;
    this.y =y;
  }

  public void moveBy(int x, int y){
    this.x = this.x + x;
    this.y = this.y + y;
  }

  public String getCoordinates(){
    return ("("+ this.x +"," + this.y +")");
  }
}
