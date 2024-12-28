package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class AppTest {

	@Test
	public void testParsePolygon_Square_Valid() {
		Polygon poly = App.parsePolygon("square 100 200 50");
		assertEquals("Square", poly.getType());
		assertNotNull(poly.getCenter());
		assertEquals(100, poly.getCenter().getX());
		assertEquals(200, poly.getCenter().getY());
	}

	@Test
	public void testParsePolygon_Rectangle_Valid() {
		Polygon poly = App.parsePolygon("rectangle 10 20 100 200");
		assertEquals("Rectangle", poly.getType());
		assertNotNull(poly.getCenter());
		assertEquals(10, poly.getCenter().getX(), 0.001);
		assertEquals(20, poly.getCenter().getY(), 0.001);
	}

	@Test
	public void testParsePolygon_Circle_Valid() {
		Polygon poly = App.parsePolygon("circle 5 5 10");
		assertEquals("Circle", poly.getType());
		assertNotNull(poly.getCenter());
		assertEquals(5, poly.getCenter().getX(), 0.001);
		assertEquals(5, poly.getCenter().getY(), 0.001);
	}

	@Test
	public void testParsePolygon_Trapezoid_Valid() {
		Polygon poly = App.parsePolygon("trapezoid 1 2 3 4 5");
		assertEquals("Trapezoid", poly.getType());
		assertNotNull(poly.getCenter());
		assertEquals(1, poly.getCenter().getX(), 0.001);
		assertEquals(2, poly.getCenter().getY(), 0.001);
	}

	@Test
	public void testParsePolygon_Custom_Valid() {
		Polygon poly = App.parsePolygon("custom 10 10 20 20 30 30");
		assertNotNull(poly.getVertices());
		assertEquals(3, poly.getSize());
	}

	@Test
	public void testParsePolygon_EmptyLine_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("  ");
				});
	}

	@Test
	public void testParsePolygon_NullInput_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon(null);
				});
	}

	@Test
	public void testParsePolygon_InsufficientParameters_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("square 100");
				});
	}

	@Test
	public void testParsePolygon_UnknownShapeType_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("octagon 1 2 3");
				});
	}

	@Test
	public void testParsePolygon_NonNumericParam_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("square 10 A 50");
				});
	}

	@Test
	public void testParsePolygon_WrongNumberOfParams_Square_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("square 1 2");
				});
	}

	@Test
	public void testParsePolygon_WrongNumberOfParams_Rectangle_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("rectangle 10 20 30");
				});
	}

	@Test
	public void testParsePolygon_WrongNumberOfParams_Circle_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("circle 10 20");
				});
	}

	@Test
	public void testParsePolygon_WrongNumberOfParams_Trapezoid_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("trapezoid 100 200 300");
				});
	}

	@Test
	public void testParsePolygon_Custom_TooFewParams_Throws() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App.parsePolygon("custom 10 10");
				});
	}

	@Test
	public void testParseNotEnoughParameters() {
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					App a = new App();
					a.parsePolygon("banan");
				});
	}

	@Test
	public void testMain_NoArguments() {
		PrintStream originalOut = System.out;
		PrintStream originalErr = System.err;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();

		try {
			System.setOut(new PrintStream(outContent));
			System.setErr(new PrintStream(errContent));
			App.main(new String[] {});
			String errOutput = errContent.toString();
			assertTrue(
					errOutput.contains("Usage: java -jar your-jar-file.jar <input-file>"),
					"Expected usage message on System.err");
		} finally {
			System.setOut(originalOut);
			System.setErr(originalErr);
		}
	}

	@Test
	public void testMain_InvalidFile() {
		PrintStream originalOut = System.out;
		PrintStream originalErr = System.err;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();

		try {
			System.setOut(new PrintStream(outContent));
			System.setErr(new PrintStream(errContent));
			App.main(new String[] { "no_such_file.txt" });
			String errOutput = errContent.toString();
			assertTrue(
					errOutput.contains("Error reading file: no_such_file.txt"),
					"Should complain about not finding the file");
		} finally {
			System.setOut(originalOut);
			System.setErr(originalErr);
		}
	}

	@Test
	public void testMain_ValidFile() throws Exception {
		PrintStream originalOut = System.out;
		PrintStream originalErr = System.err;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();

		Path tempFile = null;
		try {
			System.setOut(new PrintStream(outContent));
			System.setErr(new PrintStream(errContent));
			tempFile = Files.createTempFile("test_polygons", ".txt");
			String data = "" + "square 10 10 5\n" + "rectangle 0 0 100 50\n" + "\n" + "circle 100 100 20\n";
			Files.write(tempFile, data.getBytes());
			App.main(new String[] { tempFile.toString() });
			String consoleOut = outContent.toString();
			assertTrue(
					consoleOut.contains("Inserted 3 polygons into the QuadTree."),
					"Should mention 3 polygons inserted");
			assertTrue(
					consoleOut.contains("Number of collisions found:"),
					"Should print collisions result");
		} finally {
			System.setOut(originalOut);
			System.setErr(originalErr);
			if (tempFile != null) {
				try {
					Files.deleteIfExists(tempFile);
				} catch (IOException ignore) {
				}
			}
		}
	}
}
