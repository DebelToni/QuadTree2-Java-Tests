package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PolygonTest {
	@Test
	public void testPolygon() {
		List<Position> possitions = new ArrayList<Position>();
		possitions.add(new Position(1, 1));
		possitions.add(new Position(1, 2));
		Polygon polygon = new Polygon(2, possitions);
		assertEquals(2, polygon.getSize());
		return;
	}
}
