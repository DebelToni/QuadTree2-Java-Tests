package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolygonTest {

	//private Polygon customPoly;
	//private Polygon squarePoly;
	//private Polygon rectPoly;
	//private Polygon trapPoly;

	//@BeforeEach
	//public void initEach() {
	//	System.out.println("Setting up Polygon objects");
	//	List<Position> triangleVertices = new ArrayList<>();
	//	triangleVertices.add(new Position(0f, 0f));
	//	triangleVertices.add(new Position(10f, 0f));
	//	triangleVertices.add(new Position(5f, 10f));
	//	this.customPoly = new Polygon(triangleVertices);
	//
	//	this.squarePoly = new Polygon("Square", new Position(5f, 5f), 10f);
	//
	//	this.rectPoly = new Polygon("Rectangle", new Position(5f, 5f), 10f, 4f);
	//
	//	this.trapPoly = new Polygon("Trapezoid", new Position(5f, 5f), 8f, 2f, 6f);
	//}
		
		// mrazq toq tup maven ne mi vika tupiq beforeeach - probvah vsichko

@Test
    public void testCustomPolygonConstruction() {
        List<Position> triangleVertices = new ArrayList<>();
        triangleVertices.add(new Position(0f, 0f));
        triangleVertices.add(new Position(10f, 0f));
        triangleVertices.add(new Position(5f, 10f));
        Polygon customPoly = new Polygon(triangleVertices);

        assertEquals(3, customPoly.getSize());
        assertEquals(5f, customPoly.getCenter().getX());
        assertEquals(10f / 3f, customPoly.getCenter().getY());
        assertNotNull(customPoly.getName());
        assertEquals("Custom", customPoly.getType());
    }

    @Test
    public void testSquareConstruction() {
        List<Position> triangleVertices = new ArrayList<>();
        triangleVertices.add(new Position(0f, 0f));
        triangleVertices.add(new Position(10f, 0f));
        triangleVertices.add(new Position(5f, 10f));
        Polygon customPoly = new Polygon(triangleVertices);

        Polygon squarePoly = new Polygon("Square", new Position(5f, 5f), 10f);

        Polygon rectPoly = new Polygon("Rectangle", new Position(5f, 5f), 10f, 4f);

        Polygon trapPoly = new Polygon("Trapezoid", new Position(5f, 5f), 8f, 2f, 6f);

        assertEquals(4, squarePoly.getSize());
        assertEquals("Square", squarePoly.getType());
        Position[] verts = squarePoly.getVertices();
        assertEquals(5f, squarePoly.getRadius());
        for (Position v : verts) {
            float dx = Math.abs(v.getX() - 5f);
            float dy = Math.abs(v.getY() - 5f);
            assertTrue(dx == 5f || dy == 5f, "Vertex not on expected radius");
        }
    }

    @Test
    public void testRectangleConstruction() {
        List<Position> triangleVertices = new ArrayList<>();
        triangleVertices.add(new Position(0f, 0f));
        triangleVertices.add(new Position(10f, 0f));
        triangleVertices.add(new Position(5f, 10f));
        Polygon customPoly = new Polygon(triangleVertices);

        Polygon squarePoly = new Polygon("Square", new Position(5f, 5f), 10f);

        Polygon rectPoly = new Polygon("Rectangle", new Position(5f, 5f), 10f, 4f);

        Polygon trapPoly = new Polygon("Trapezoid", new Position(5f, 5f), 8f, 2f, 6f);

        assertEquals(4, rectPoly.getSize());
        assertEquals("Rectangle", rectPoly.getType());
        assertEquals(Math.sqrt(Math.pow(10f, 2) + Math.pow(4f, 2)) / 2, rectPoly.getRadius(), 0.0001);

        Position[] vertices = rectPoly.getVertices();
        assertEquals(4, vertices.length);
        assertEquals(0f, vertices[0].getX(), 0.0001);
        assertEquals(3f, vertices[0].getY(), 0.0001);
    }

    @Test
    public void testTrapezoidConstruction() {
        List<Position> triangleVertices = new ArrayList<>();
        triangleVertices.add(new Position(0f, 0f));
        triangleVertices.add(new Position(10f, 0f));
        triangleVertices.add(new Position(5f, 10f));
        Polygon customPoly = new Polygon(triangleVertices);

        Polygon squarePoly = new Polygon("Square", new Position(5f, 5f), 10f);

        Polygon rectPoly = new Polygon("Rectangle", new Position(5f, 5f), 10f, 4f);

        Polygon trapPoly = new Polygon("Trapezoid", new Position(5f, 5f), 8f, 2f, 6f);

        assertEquals(4, trapPoly.getSize());
        assertEquals("Trapezoid", trapPoly.getType());
        Position[] vertices = trapPoly.getVertices();
        assertEquals(4, vertices.length);
        assertNotNull(vertices[0]);
    }

    @Test
    public void testGetVertices() {
        List<Position> triangleVertices = new ArrayList<>();
        triangleVertices.add(new Position(0f, 0f));
        triangleVertices.add(new Position(10f, 0f));
        triangleVertices.add(new Position(5f, 10f));
        Polygon customPoly = new Polygon(triangleVertices);

        Polygon squarePoly = new Polygon("Square", new Position(5f, 5f), 10f);

        Polygon rectPoly = new Polygon("Rectangle", new Position(5f, 5f), 10f, 4f);

        Polygon trapPoly = new Polygon("Trapezoid", new Position(5f, 5f), 8f, 2f, 6f);

        Position[] customVerts = customPoly.getVertices();
        assertEquals(3, customVerts.length);

        assertEquals(0f, customVerts[0].getX());
        assertEquals(10f, customVerts[1].getX());
        assertEquals(5f, customVerts[2].getX());
    }

    @Test
    public void testPosToString() {
        System.out.println("Inside testPosToString");
        List<Position> triangleVertices = new ArrayList<>();
        triangleVertices.add(new Position(0f, 0f));
        triangleVertices.add(new Position(10f, 0f));
        triangleVertices.add(new Position(5f, 10f));
        Polygon customPoly = new Polygon(triangleVertices);

        Polygon squarePoly = new Polygon("Square", new Position(5f, 5f), 10f);

        Polygon rectPoly = new Polygon("Rectangle", new Position(5f, 5f), 10f, 4f);

        Polygon trapPoly = new Polygon("Trapezoid", new Position(5f, 5f), 8f, 2f, 6f);
        String centerString = customPoly.posToString();
        assertTrue(centerString.contains("("), "String does not contain '('");
        assertTrue(centerString.contains(")"), "String does not contain ')'");
    }

    @Test
    public void testPolygon() {
        System.out.println("Inside testPolygon");
        List<Position> possitions = new ArrayList<>();
        possitions.add(new Position(1, 1));
        possitions.add(new Position(1, 2));
        Polygon polygon = new Polygon(possitions);
        assertEquals(2, polygon.getSize());
    }
}
