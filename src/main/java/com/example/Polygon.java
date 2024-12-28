package com.example;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
  private List<Position> vertices = new ArrayList<>();
  private int size;
  private Position center;
  private float radius;
  private String type;
  private String name;
  private static int id = 0;

  public Polygon(List<Position> vertices) {
    this.size = vertices.size();
    for (int i = 0; i < size; i++) {
      this.vertices.add(new Position(vertices.get(i).getX(), vertices.get(i).getY()));
    }
    float x = 0;
    float y = 0;
    for (int i = 0; i < size; i++) {
      x += vertices.get(i).getX();
      y += vertices.get(i).getY();
    }
    this.center = new Position(x / size, y / size);
    this.radius = findDistanceToCenter(vertices.get(0));
    for (int i = 1; i < size; i++) {
      if (radius < findDistanceToCenter(vertices.get(i))) {
        radius = findDistanceToCenter(vertices.get(i));
      }
    }
    this.type = "Custom";
    this.name = this.type + id++;
  }

  // znam che mozeh da napravq otdelni klasove za otdelnite figuri ama taka e
  // dosta po-prosto imajki predvid nachina po kojto zadavam tochkite
  public Polygon(String type, Position center, float radius) {
    if (type.equals("Square")) {
      vertices.add(new Position(center.getX() - radius / 2, center.getY() - radius / 2));
      vertices.add(new Position(center.getX() + radius / 2, center.getY() - radius / 2));
      vertices.add(new Position(center.getX() + radius / 2, center.getY() + radius / 2));
      vertices.add(new Position(center.getX() - radius / 2, center.getY() + radius / 2));
      this.size = 4;
      this.center = new Position(center.getX(), center.getY());
      this.radius = radius/2;
      this.type = type;
      this.name = this.type + id++;
    } else if (type.equals("Circle")) {
      List<Position> vertices = new ArrayList<>();
      for (int i = 0; i < 360; i++) {
        vertices.add(
            new Position(
                (float) (radius * Math.cos(i * Math.PI / 180)),
                (float) (radius * Math.sin(i * Math.PI / 180))));
      }
      this.size = 360;
      for (int i = 0; i < size; i++) {
        this.vertices.add(new Position(vertices.get(i).getX(), vertices.get(i).getY()));
      }
      this.center = new Position(center.getX(), center.getY());
      this.radius = radius;
      this.type = type;
      this.name = this.type + id++;
    }
  }

  // ne e nuzno da slagam Type ama da si lichi poveche pri testvane kakvo
  // suzdavam

  public Polygon(String type, Position center, float side1, float side2) {
    this.type = "Rectangle";
    this.center = center;
    this.size = 4;
    this.radius = (float) Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2)) / 2;
    this.vertices.add(new Position(center.getX() - side1 / 2, center.getY() - side2 / 2));
    this.vertices.add(new Position(center.getX() + side1 / 2, center.getY() - side2 / 2));
    this.vertices.add(new Position(center.getX() + side1 / 2, center.getY() + side2 / 2));
    this.vertices.add(new Position(center.getX() - side1 / 2, center.getY() + side2 / 2));
    this.name = this.type + id++;
  }

  public Polygon(String type, Position center, float side1, float side2, float height) {
    this.type = "Trapezoid";
    this.center = center;
    this.size = 4;
    this.vertices.add(new Position(center.getX() - side1 / 2, center.getY() - height / 2));
    this.vertices.add(new Position(center.getX() + side1 / 2, center.getY() - height / 2));
    this.vertices.add(new Position(center.getX() - side1 / 2, center.getY() + height / 2));
    this.vertices.add(
        new Position(center.getX() - side1 / 2 + side2 / 2, center.getY() + height / 2));
    this.name = this.type + id++;
  }

  private float findDistanceToCenter(Position p) {
    return (float)
        Math.sqrt(Math.pow(p.getX() - center.getX(), 2) + Math.pow(p.getY() - center.getY(), 2));
  }

  public int getSize() {
    return size;
  }

  public Position getCenter() {
    return center;
  }

  public float getRadius() {
    return radius;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String posToString() {
    return center.toString();
  }

  public Position[] getVertices() {
    Position[] copy = new Position[size];
    for (int i = 0; i < size; i++) {
      copy[i] = new Position(vertices.get(i).getX(), vertices.get(i).getY());
    }
    return copy;
  }
}
