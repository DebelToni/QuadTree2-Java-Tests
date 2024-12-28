//package com.example;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Circle extends Polygon {
//  private float radius;
//
//  public Circle(float radius) {
//    this.radius = radius;
//    List<Position> vertices = new ArrayList<>();
//    for (int i = 0; i < 360; i++) {
//      vertices.add(
//          new Position(
//              (float) (radius * Math.cos(i * Math.PI / 180)),
//              (float) (radius * Math.sin(i * Math.PI / 180))));
//    }
//    super(360, vertices);
//  }
//
//  public float getRadius() {
//    return radius;
//  }
//}
