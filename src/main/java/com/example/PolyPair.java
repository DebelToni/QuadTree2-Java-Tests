package com.example;

public class PolyPair {
  private final Polygon first;
  private final Polygon second;

  public PolyPair(Polygon first, Polygon second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String toString() {
    return this.first.getName()
        + " "
        + this.first.posToString()
        + " - "
        + this.second.getName()
        + " "
        + this.first.posToString();
  }
}

// TODO: clear identical collision pairs
