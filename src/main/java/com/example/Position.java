package com.example;

public class Position {
  private float x;
  private float y;

  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setPosition(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void setPosition(Position p) {
    this.x = p.getX();
    this.y = p.getY();
  }

  public boolean equals(Position p) {
    return (this.x == p.getX() && this.y == p.getY());
  }

  public boolean equals(float x, float y) {
    return (this.x == x && this.y == y);
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public Position copy() {
    return new Position(x, y);
  }
}
