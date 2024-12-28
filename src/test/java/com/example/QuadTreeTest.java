package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
public class QuadTreeTest {
  @Test
  public void testsomethingAsdTest() {
    Polygon boundingRect = new Polygon("Square", new Position(0, 0), 2000);

    List<Polygon> initialPolygons = new ArrayList<>();
    initialPolygons.add(new Polygon("Square", new Position(100, 100), 50));
    initialPolygons.add(new Polygon("Circle", new Position(200, 150), 40));

    QuadTree qt = new QuadTree(boundingRect, initialPolygons);

    qt.add(new Polygon("Square", new Position(300, 200), 60));

    List<PolyPair> collisions = qt.getAllCollisions();
    for (PolyPair pair : collisions) {
      System.out.println(pair);
    }
  }

  @Test
  public void testCollidingInSubTrees() {
    Polygon boundingRegion = new Polygon("Square", new Position(0, 0), 2000);

    List<Polygon> initialPolygons = new ArrayList<>();
    initialPolygons.add(new Polygon("Square", new Position(10, 10), 5));
    initialPolygons.add(new Polygon("Square", new Position(20, 20), 5));
    initialPolygons.add(new Polygon("Square", new Position(30, 30), 5));
    initialPolygons.add(new Polygon("Square", new Position(40, 40), 10));
    initialPolygons.add(new Polygon("Square", new Position(50, 50), 10));
    initialPolygons.add(new Polygon("Square", new Position(60, 60), 5));
    initialPolygons.add(new Polygon("Square", new Position(70, 70), 5));
    initialPolygons.add(new Polygon("Square", new Position(80, 80), 5));
    initialPolygons.add(new Polygon("Square", new Position(90, 90), 5));
    initialPolygons.add(new Polygon("Square", new Position(100, 100), 5));
    initialPolygons.add(new Polygon("Square", new Position(110, 110), 5));

    QuadTree quad = new QuadTree(boundingRegion, initialPolygons);

    List<PolyPair> collisions = quad.getAllCollisions();

    assertEquals(1, collisions.size());
  }

  @Test
  public void testCollisionsInSubTrees() {
    Polygon bounds = new Polygon("Square", new Position(0, 0), 2000);

    List<Polygon> initialPolygons = new ArrayList<>();
    initialPolygons.add(new Polygon("Square", new Position(10, 10), 5));
    initialPolygons.add(new Polygon("Square", new Position(20, 20), 5));
    initialPolygons.add(new Polygon("Square", new Position(30, 30), 5));
    initialPolygons.add(new Polygon("Square", new Position(40, 40), 10));
    initialPolygons.add(new Polygon("Square", new Position(50, 50), 10));
    initialPolygons.add(new Polygon("Square", new Position(60, 60), 5));
    initialPolygons.add(new Polygon("Square", new Position(70, 70), 5));
    initialPolygons.add(new Polygon("Square", new Position(80, 80), 5));
    initialPolygons.add(new Polygon("Square", new Position(90, 90), 5));
    initialPolygons.add(new Polygon("Square", new Position(100, 100), 5));
    initialPolygons.add(new Polygon("Square", new Position(110, 110), 5));

    QuadTree quad = new QuadTree(bounds, initialPolygons);

    List<PolyPair> collisions = quad.getAllCollisions();

    assertEquals("Square (40.0, 40.0) - Square (50.0, 50.0)", collisions.get(0).toStringTypes());
  }

  public void testLargeTreeSplit() {
    Polygon bounds = new Polygon("Square", new Position(0f, 0f), 2000f);
    QuadTree quadTreee = new QuadTree(bounds, null);
    for (int i = 0; i < 100; i++) {
      quadTreee.add(new Polygon("Square", new Position(-900f + i * 9f, -900f + i * 9f), 10f));
    }
    assertTrue(quadTreee.getAllCollisions().size() >= 99);
    System.out.println(quadTreee.getAllCollisions().size());
  }

  @Test
  public void testIfCollisionsRepeat() {
    Polygon bounds = new Polygon("Square", new Position(0, 0), 2000);

    List<Polygon> initialPolygons = new ArrayList<>();
    initialPolygons.add(new Polygon("Square", new Position(-10, -10), 20));
    initialPolygons.add(new Polygon("Square", new Position(10, 10), 20));

    QuadTree quad = new QuadTree(bounds, initialPolygons);

    for (int i = 0; i < 8; i++) {
      quad.add(new Polygon("Square", new Position(-500 + i * 10, -500 + i * 10), 5));
      quad.add(new Polygon("Square", new Position(500 + i * 10, 500 + i * 10), 5));
    }

    List<PolyPair> collisions = quad.getAllCollisions();

    for (PolyPair pair : collisions) {
      System.out.println(pair);
    }
    assertEquals(1, collisions.size());
  }

  @Test
  public void testAddingOutOfDounds() {
    Polygon bounds = new Polygon("Square", new Position(0, 0), 2000);
    QuadTree quad = new QuadTree(bounds, null);
    quad.add(new Polygon("Square", new Position(2000, 2000), 10));
    assertEquals(0, quad.getAllCollisions().size());
  }

  @Test
  public void testOuterLeafs() {
    Polygon bounds = new Polygon("Square", new Position(0, 0), 2000);
    QuadTree quad = new QuadTree(bounds, null);
    quad.add(new Polygon("Square", new Position(-100, -100), 10));
    quad.add(new Polygon("Square", new Position(-100, -100), 10)); //
    quad.add(new Polygon("Square", new Position(100, 100), 10));
    quad.add(new Polygon("Square", new Position(100, 100), 10)); //
    quad.add(new Polygon("Square", new Position(-100, 100), 10));
    quad.add(new Polygon("Square", new Position(-100, 100), 10)); //
    quad.add(new Polygon("Square", new Position(100, -100), 10));
    quad.add(new Polygon("Square", new Position(100, -100), 10)); //

    quad.add(new Polygon("Square", new Position(750, 750), 10));
    quad.add(new Polygon("Square", new Position(750, 750), 10)); //

    quad.add(new Polygon("Square", new Position(250, 750), 10));
    quad.add(new Polygon("Square", new Position(250, 750), 10)); //

    quad.add(new Polygon("Square", new Position(750, 250), 10));
    quad.add(new Polygon("Square", new Position(750, 250), 10)); //

    quad.add(new Polygon("Square", new Position(780, 250), 10));
    quad.add(new Polygon("Square", new Position(780, 250), 10)); //

    assertEquals(8, quad.getAllCollisions().size());
  }
}
