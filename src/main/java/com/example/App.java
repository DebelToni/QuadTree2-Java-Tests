package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {

  /**
   * Opens the specified file and counts the number of lines.
   *
   * @param filePath Path to the input file.
   * @return Number of lines in the file.
   * @throws IOException If an I/O error occurs.
   */
  public int countLines(String filePath) throws IOException {
    int lines = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      while (reader.readLine() != null) {
        lines++;
      }
    }
    return lines;
  }

  public static void main(String[] args) {
    App app = new App();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the path to the input file: ");
    String filePath = scanner.nextLine();

    try {
      int lineCount = app.countLines(filePath);
      System.out.println("The file has " + lineCount + " lines.");
    } catch (IOException e) {
      System.err.println("An error occurred while processing the file:");
      e.printStackTrace();
    }
    scanner.close();
  }
}
