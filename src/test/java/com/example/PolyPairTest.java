package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PolyPairTest {

  @Test
  public void testPolyPairToStringType() {
    Polygon p1 = new Polygon("Square", new Position(5f, 5f), 10f);
    Polygon p2 = new Polygon("Rectangle", new Position(8f, 8f), 4f, 2f);

<<<<<<< HEAD
        PolyPair pair = new PolyPair(p1, p2);
        String result = pair.toString();
        assertTrue(result.contains(p1.getName()));
        assertTrue(result.contains(p2.getName()));
        assertTrue(result.contains("("));
        assertTrue(result.contains(")"));
    }
=======
    PolyPair pair = new PolyPair(p1, p2);
    String result = pair.toStringNames();
     assertTrue(result.contains(p2.getType()));
     assertTrue(result.contains(p2.getType()));
    assertTrue(result.contains("("));
    assertTrue(result.contains(")"));
  }

  @Test
  public void testPolyPairToStringNames() {
    Polygon p1 = new Polygon("Square", new Position(5f, 5f), 10f);
    Polygon p2 = new Polygon("Rectangle", new Position(8f, 8f), 4f, 2f);

    PolyPair pair = new PolyPair(p1, p2);
    String result = pair.toStringNames();
    assertTrue(result.contains(p1.getName()));
    // assertTrue(result.contains(p2.getType()));
    assertTrue(result.contains(p2.getName()));
    // assertTrue(result.contains(p2.getType()));
    assertTrue(result.contains("("));
    assertTrue(result.contains(")"));
  }

  @Test
  public void testGetFirst() {
    Polygon p1 = new Polygon("Square", new Position(5f, 5f), 10f);
    Polygon p2 = new Polygon("Rectangle", new Position(8f, 8f), 4f, 2f);
    PolyPair pair = new PolyPair(p1, p2);
    Polygon result = pair.getFirst();
    assertEquals(p1, result);
  }

  @Test
  public void testGetSecond() {
    Polygon p1 = new Polygon("Square", new Position(5f, 5f), 10f);
    Polygon p2 = new Polygon("Rectangle", new Position(8f, 8f), 4f, 2f);
    PolyPair pair = new PolyPair(p1, p2);
    Polygon result = pair.getSecond();
    assertEquals(p2, result);
  }
>>>>>>> 84ac445 (Finished pls work)
}
