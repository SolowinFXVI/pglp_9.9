package org.uvsq21400579;

public class Coordinates {
  private int x;
  private int y;

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void moveBy(int x, int y) {
    this.x = this.x + x;
    this.y = this.y + y;
  }

  public String getX() {
    return String.valueOf(x);
  }

  public String getY() {
    return String.valueOf(y);
  }

  public String getCoordinates() {
    return ("(" + this.x + "," + this.y + ")");
  }
}
