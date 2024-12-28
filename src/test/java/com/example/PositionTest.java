package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PositionTest {
	@Test
	public void testPosition() {
		Position possition = new Position(1.5f, 2f);
		assertEquals(1.5f, possition.getX());
		assertEquals(2f, possition.getY());
	}

	@Test
	public void testPositionSet() {
		Position possition = new Position(1f, 2f);
		possition.setX(3f);
		possition.setY(4f);
		assertEquals(3f, possition.getX());
		assertEquals(4f, possition.getY());
	}

	@Test
	public void testPositionToString() {
		Position possition = new Position(1f, 2f);
		assertEquals("(1.0, 2.0)", possition.toString());
	}

	@Test
	public void testPositionEquals() {
		Position possition1 = new Position(1f, 2f);
		Position possition2 = new Position(1f, 2f);
		assertEquals(true, possition1.equals(possition2));
	}

	@Test
	public void testPositionNotEquals() {
		Position possition1 = new Position(1f, 2f);
		Position possition2 = new Position(3f, 4f);
		assertEquals(false, possition1.equals(possition2.getX(), possition2.getY()));
	}

	@Test
	public void testSetPosition() {
		Position possition = new Position(1f, 2f);
		possition.setPosition(3f, 4f);
		assertEquals(3f, possition.getX());
		assertEquals(4f, possition.getY());
	}

	@Test
	public void testPositionCopy() {
		Position possition = new Position(1f, 2f);
		Position possition2 = possition.copy();
		assertEquals(true, possition.equals(possition2));
	}

	@Test
	public void testSetPositionPosition() {
		Position possition = new Position(1f, 2f);
		Position possition2 = new Position(3f, 4f);
		possition.setPosition(possition2);
		assertEquals(3f, possition.getX());
		assertEquals(4f, possition.getY());
	}

	@Test
	public void testConstructorAndGetters() {
		Position p = new Position(10f, 20f);
		assertEquals(10f, p.getX());
		assertEquals(20f, p.getY());
	}

	@Test
	public void testSetters() {
		Position p = new Position(0f, 0f);
		p.setX(15f);
		p.setY(25f);
		assertEquals(15f, p.getX());
		assertEquals(25f, p.getY());
	}

	@Test
	public void testSetPositionXY() {
		Position p = new Position(0f, 0f);
		p.setPosition(5f, 6f);
		assertEquals(5f, p.getX());
		assertEquals(6f, p.getY());
	}

	@Test
	public void testSetPositionObject() {
		Position p1 = new Position(3f, 4f);
		Position p2 = new Position(0f, 0f);
		p2.setPosition(p1);
		assertEquals(3f, p2.getX());
		assertEquals(4f, p2.getY());
	}

	@Test
	public void testEqualsPosition() {
		Position p1 = new Position(1f, 2f);
		Position p2 = new Position(1f, 2f);
		Position p3 = new Position(2f, 3f);
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
	}

	@Test
	public void testEqualsXY() {
		Position p = new Position(1f, 2f);
		assertTrue(p.equals(1f, 2f));
		assertFalse(p.equals(2f, 1f));
	}

	@Test
	public void testCopy() {
		Position p1 = new Position(10f, 20f);
		Position p2 = p1.copy();
		assertNotSame(p1, p2);
		assertEquals(p1.getX(), p2.getX());
		assertEquals(p1.getY(), p2.getY());
	}

	@Test
	public void testToString() {
		Position p = new Position(7f, 8f);
		assertEquals("(7.0, 8.0)", p.toString());
	}
}
