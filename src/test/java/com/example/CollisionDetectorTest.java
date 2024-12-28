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

		Polygon polygon = new Polygon(possitions);

		List<Position> possitions2 = new ArrayList<Position>();
		possitions2.add(new Position(3, 1));
		possitions2.add(new Position(3, 2));
		possitions2.add(new Position(5, 3));
		possitions2.add(new Position(3, 1));

		Polygon polygon2 = new Polygon(possitions2);

		assertEquals(false, collisionDetector.polygonsIntersectOrContain(polygon, polygon2));
	}

	@Test
	public void testCollisionDetectorLastSideCase() { // Super vazen test 
		CollisionDetector collisionDetector = new CollisionDetector();
		List<Position> possitions = new ArrayList<Position>();
		possitions.add(new Position(6, 0));
		possitions.add(new Position(0, 0));
		possitions.add(new Position(0, 6));
		possitions.add(new Position(6, 6));

		Polygon polygon = new Polygon(possitions);

		List<Position> possitions2 = new ArrayList<Position>();
		possitions2.add(new Position(5, 3));
		possitions2.add(new Position(10, 1));
		possitions2.add(new Position(10, 4));

		Polygon polygon2 = new Polygon(possitions2);

		assertEquals(true, collisionDetector.polygonsIntersectOrContain(polygon, polygon2));

	}

@Test
    public void testOrientation() {
        Position p1 = new Position(0f,0f);
        Position p2 = new Position(4f,4f);
        Position p3 = new Position(1f,2f);

        int orient = CollisionDetector.orientation(p1, p2, p3);
        assertEquals(-1, orient);
    }

    @Test
    public void testDoSegmentsIntersect() {
        Position p1 = new Position(0f, 0f);
        Position p2 = new Position(10f, 0f);
        Position p3 = new Position(5f, 1f);
        Position p4 = new Position(5f, -1f);

        assertEquals(true, CollisionDetector.doSegmentsIntersect(p1, p2, p3, p4));

        Position p5 = new Position(0f, 1f);
        Position p6 = new Position(10f, 1f);
        assertEquals(false, CollisionDetector.doSegmentsIntersect(p1, p2, p5, p6));
    }

    @Test
    public void testPointInPolygon() {
        Position[] square = new Position[]{
                new Position(0f,0f),
                new Position(10f,0f),
                new Position(10f,10f),
                new Position(0f,10f)
        };
        Position insidePoint = new Position(5f,5f);
        Position outsidePoint = new Position(15f,5f);

        assertEquals(true, CollisionDetector.pointInPolygon(insidePoint, square));
        assertEquals(false, CollisionDetector.pointInPolygon(outsidePoint, square));
    }

    @Test
    public void testPointInPolygonWithPolygonObject() {
        Polygon squarePoly = new Polygon("Square", new Position(5f,5f), 10f);
        Position inside = new Position(5f,5f);
        Position outside = new Position(15f,15f);

        assertEquals(true, CollisionDetector.pointInPolygon(inside, squarePoly));
        assertEquals(false, CollisionDetector.pointInPolygon(outside, squarePoly));
    }

    @Test
    public void testPolygonsIntersectOrContain() {
        Polygon square1 = new Polygon("Square", new Position(5f,5f), 10f);
        Polygon square2 = new Polygon("Square", new Position(8f,5f), 10f);

        assertEquals(true, CollisionDetector.polygonsIntersectOrContain(square1, square2));

        Polygon square3 = new Polygon("Square", new Position(100f,100f), 10f);
        assertEquals(false, CollisionDetector.polygonsIntersectOrContain(square1, square3));
    }

    @Test
    public void testPolygonIntersectsCircle() {
        Polygon squarePoly = new Polygon("Square", new Position(5f,5f), 10f);

        Position centerIn = new Position(5f,5f);
        double radius1 = 2.0;
        assertEquals(true, CollisionDetector.polygonIntersectsCircle(squarePoly, centerIn, radius1));

        Position centerOut = new Position(100f,100f);
        double radius2 = 1.0;
        assertEquals(false, CollisionDetector.polygonIntersectsCircle(squarePoly, centerOut, radius2));

        Position centerEdge = new Position(15f,5f);
        double radius3 = 5.0; 
        assertEquals(true, CollisionDetector.polygonIntersectsCircle(squarePoly, centerEdge, radius3));
    }

}
