package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CollisionDetectorTest {
  @Test
  public void testCollisionDetector() {
    CollisionDetector collisionDetector = new CollisionDetector();
    List<Position> possitions = new ArrayList<Position>();
    possitions.add(new Position(1, 1));
    possitions.add(new Position(1, 2));
    possitions.add(new Position(3, 3));
    possitions.add(new Position(1, 1));

    Polygon polygon = new Polygon(4, possitions);

    List<Position> possitions2 = new ArrayList<Position>();
    possitions2.add(new Position(3, 1));
    possitions2.add(new Position(3, 2));
    possitions2.add(new Position(5, 3));
    possitions2.add(new Position(3, 1));

    Polygon polygon2 = new Polygon(4, possitions2);

    assertEquals(false, collisionDetector.polygonsIntersectOrContain(polygon, polygon2));
  }
}
