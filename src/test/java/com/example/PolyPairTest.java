package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PolyPairTest {

    @Test
    public void testPolyPairToString() {
        Polygon p1 = new Polygon("Square", new Position(5f,5f), 10f);
        Polygon p2 = new Polygon("Rectangle", new Position(8f,8f), 4f, 2f);

        PolyPair pair = new PolyPair(p1, p2);
        String result = pair.toString();
        assertTrue(result.contains(p1.getName()));
        assertTrue(result.contains(p2.getName()));
        assertTrue(result.contains("("));
        assertTrue(result.contains(")"));
    }
}

