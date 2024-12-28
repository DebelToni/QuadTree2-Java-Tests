package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Usage: java -jar your-jar-file.jar <input-file>");
      //System.exit(1);
				return;
    }

    String inputFilePath = args[0];
    List<Polygon> polygons = new ArrayList<>();

    try {
      List<String> lines = Files.readAllLines(Path.of(inputFilePath));

      for (String line : lines) {
        if (line.isBlank()) {
          continue;
        }

        Polygon polygon = parsePolygon(line);
        polygons.add(polygon);
      }

      Polygon bounds = new Polygon("Square", new Position(0, 0), 2000);
      QuadTree quadTree = new QuadTree(bounds, null);

      for (Polygon poly : polygons) {
        quadTree.add(poly);
      }

      List<PolyPair> collisions = quadTree.getAllCollisions();

      System.out.println("Inserted " + polygons.size() + " polygons into the QuadTree.");
      System.out.println("Number of collisions found: " + collisions.size());

    } catch (IOException e) {
      System.err.println("Error reading file: " + inputFilePath);
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static Polygon parsePolygon(String input) {
    if (input == null || input.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid input: Empty line.");
    }

    String trimmedInput = input.trim();

    String[] tokens = trimmedInput.split("\\s+");

    if (tokens.length < 2) {
      throw new IllegalArgumentException("Invalid input: Not enough parameters.");
    }

    String shapeType = tokens[0].toLowerCase();

    float[] parameters = new float[tokens.length - 1];
    try {
      for (int i = 1; i < tokens.length; i++) {
        parameters[i - 1] = Float.parseFloat(tokens[i]);
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Invalid input: Cannot parse parameters as float numbers.");
    }

    switch (shapeType) {
      case "square":
        if (parameters.length != 3) {
          throw new IllegalArgumentException(
              "Invalid number of parameters for square. Expected 3, got " + parameters.length);
        }
        return new Polygon("Square", new Position(parameters[0], parameters[1]), parameters[2]);

      case "rectangle":
        if (parameters.length != 4) {
          throw new IllegalArgumentException(
              "Invalid number of parameters for rectangle. Expected 4, got " + parameters.length);
        }
        return new Polygon(
            "Rectangle", new Position(parameters[0], parameters[1]), parameters[2], parameters[3]);

      case "circle":
        if (parameters.length != 3) {
          throw new IllegalArgumentException(
              "Invalid number of parameters for circle. Expected 3, got " + parameters.length);
        }
        return new Polygon("Circle", new Position(parameters[0], parameters[1]), parameters[2]);
      case "trapezoid":
        if (parameters.length != 5) {
          throw new IllegalArgumentException(
              "Invalid number of parameters for trapezoid. Expected 4, got " + parameters.length);
        }
        return new Polygon(
            "Trapezoid",
            new Position(parameters[0], parameters[1]),
            parameters[2],
            parameters[3],
            parameters[4]);
      case "custom":
        if (parameters.length < 3) {
          throw new IllegalArgumentException(
              "Invalid number of parameters for "
                  + "custom. Expected at least 3, got "
                  + parameters.length);
        }
        List<Position> vertices = new ArrayList<>();
        for (int i = 0; i < parameters.length; i += 2) {
          vertices.add(new Position(parameters[i], parameters[i + 1]));
        }
        return new Polygon(vertices);

      default:
        throw new IllegalArgumentException("Invalid input: Unknown shape type.");
    }
  }
}

