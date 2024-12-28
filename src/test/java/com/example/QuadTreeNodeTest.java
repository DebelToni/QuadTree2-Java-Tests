package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class QuadTreeNodeTest {

  @Test
  public void testLeafConstruction() {
    System.out.println("Inside testLeafConstruction");

    Polygon bounds = new Polygon("Square", new Position(0f,0f),2000f);

    List<Polygon> initialPolys = new ArrayList<>();
    initialPolys.add(new Polygon("Square", new Position(20f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(20f, 80f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 80f), 10f));

    QuadTreeNode quadTree = new QuadTreeNode(bounds, initialPolys);

    List<PolyPair> collisions = quadTree.checkCollisions();
    assertTrue(
        collisions.isEmpty(), "Expected no collisions in leaf node with non-overlapping polygons.");
  }

  @Test
  public void testAddPolygonWithinLeafLimit() {
    System.out.println("Inside testAddPolygonWithinLeafLimit");

    Polygon bounds = new Polygon("Square", new Position(0f,0f),2000f);

    List<Polygon> initialPolys = new ArrayList<>();
    initialPolys.add(new Polygon("Square", new Position(20f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(20f, 80f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 80f), 10f));

    QuadTreeNode quadTree = new QuadTreeNode(bounds, initialPolys);

    Polygon extra = new Polygon("Square", new Position(30f, 30f), 10f);
    quadTree.addPolygon(extra);

    List<PolyPair> collisions = quadTree.checkCollisions();
    assertFalse(
        collisions.isEmpty(),
        "Expected collisions after adding overlapping polygons " + "within leaf limit.");
  }

  @Test
  public void testAddPolygonCausingSplit() {
    System.out.println("Inside testAddPolygonCausingSplit");

    Polygon bounds = new Polygon("Square", new Position(0f,0f),2000f);

    List<Polygon> initialPolys = new ArrayList<>();
    initialPolys.add(new Polygon("Square", new Position(20f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(20f, 80f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 80f), 10f));

    QuadTreeNode quadTree = new QuadTreeNode(bounds, initialPolys);

    for (int i = 0; i < 7; i++) {
      quadTree.addPolygon(new Polygon("Square", new Position(20f + i * 2, 20f), 2f));
    }

    List<PolyPair> collisions = quadTree.checkCollisions();
    assertNotNull(collisions, "Expected collisions list to be non-null after split.");
  }

  @Test
  public void testCheckCollisionsNoCollisions() {
    System.out.println("Inside testCheckCollisionsNoCollisions");

    Polygon bounds = new Polygon("Square", new Position(0f,0f),2000f);

    List<Polygon> initialPolys = new ArrayList<>();
    initialPolys.add(new Polygon("Square", new Position(20f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(20f, 80f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 80f), 10f));

    QuadTreeNode quadTree = new QuadTreeNode(bounds, initialPolys);

    List<PolyPair> collisions = quadTree.checkCollisions();
    assertTrue(collisions.isEmpty(), "Expected no collisions with non-overlapping polygons.");
  }

  @Test
  public void testCheckCollisionsSomeCollisions() {
    System.out.println("Inside testCheckCollisionsSomeCollisions");

    Polygon bounds = new Polygon("Square", new Position(0f,0f),2000f);

    List<Polygon> initialPolys = new ArrayList<>();
    initialPolys.add(new Polygon("Square", new Position(20f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 20f), 10f));
    initialPolys.add(new Polygon("Square", new Position(20f, 80f), 10f));
    initialPolys.add(new Polygon("Square", new Position(80f, 80f), 10f));

    QuadTreeNode quadTree = new QuadTreeNode(bounds, initialPolys);

    Polygon overlap1 = new Polygon("Square", new Position(25f, 25f), 10f);
    Polygon overlap2 = new Polygon("Square", new Position(28f, 28f), 10f);
    quadTree.addPolygon(overlap1);
    quadTree.addPolygon(overlap2);

    List<PolyPair> collisions = quadTree.checkCollisions();
    assertFalse(collisions.isEmpty(), "Expected collisions after adding overlapping polygons.");
  }
}
