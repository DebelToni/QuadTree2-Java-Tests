package com.example;

public class PolyPair {
  private final Polygon first;
  private final Polygon second;

  public PolyPair(Polygon first, Polygon second) {
    this.first = first;
    this.second = second;
  }

<<<<<<< HEAD
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
=======
	public String toStringNames() {
		return
		 this.first.getName()
				+ " "
				+ this.first.posToString()
				+ " - "
				+ this.second.getName()
				+ " "
				+ this.second.posToString();
	}


	public String toStringTypes() {
		return
		// this.first.getName()
		this.first.getType()
				+ " "
				+ this.first.posToString()
				+ " - "
				// + this.second.getName()
				+ this.second.getType()
				+ " "
				+ this.second.posToString();
	}

	public Polygon getFirst() {
		return first;
	}

	public Polygon getSecond() {
		return second;
	}
>>>>>>> 84ac445 (Finished pls work)
}

// TODO: clear identical collision pairs
