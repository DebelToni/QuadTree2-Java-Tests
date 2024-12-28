package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
