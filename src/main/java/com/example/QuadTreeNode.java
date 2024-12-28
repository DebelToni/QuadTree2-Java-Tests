package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuadTreeNode {
	private final int MAX_POLYS_IN_BRANCH = 10;
	// private final float MAX_WIDTH = 1000; // just use the bounds
	private String type;
	private List<Polygon> polygons;
	private QuadTreeNode[] quads;
	private int polysInBranch;
	private Polygon bounds;
	private List<Polygon>[] subPolys;
	List<PolyPair> pairs;

	public QuadTreeNode(Polygon bounds, List<Polygon> polygons) {
		this.type = "BRANCH";
		this.polygons = new ArrayList<Polygon>();
		this.quads = new QuadTreeNode[4];
		this.polysInBranch = 0;
		this.bounds = bounds;

		this.polygons = new ArrayList<Polygon>(polygons); 
		this.polysInBranch = polygons.size();

		if (polygons.size() <= MAX_POLYS_IN_BRANCH) {
			this.type = "LEAF";
		} else {
			splitPolys();
			createQuads();
		}
	}

	public void addPolygon(Polygon p) {
		if ("BRANCH".equals(this.type)) {
			int quad = determineQuad(p);
			quads[quad].addPolygon(p);
		} else {
			if (polysInBranch < MAX_POLYS_IN_BRANCH) {
				polygons.add(p);
				polysInBranch++;
			} else {
				splitPolys();
				createQuads();
				int quad = determineQuad(p);
				quads[quad].addPolygon(p);
			}
		}
	}

	private void createQuads() {
		quads[0] = new QuadTreeNode(
				new Polygon(
						"SQUARE",
						new Position(
								bounds.getCenter().getX() - bounds.getRadius() / 2,
								bounds.getCenter().getY() - bounds.getRadius() / 2),
						bounds.getRadius() / 2),
				subPolys[0]);
		quads[1] = new QuadTreeNode(
				new Polygon(
						"SQUARE",
						new Position(
								bounds.getCenter().getX() + bounds.getRadius() / 2,
								bounds.getCenter().getY() - bounds.getRadius() / 2),
						bounds.getRadius() / 2),
				subPolys[1]);
		quads[2] = new QuadTreeNode(
				new Polygon(
						"SQUARE",
						new Position(
								bounds.getCenter().getX() - bounds.getRadius() / 2,
								bounds.getCenter().getY() + bounds.getRadius() / 2),
						bounds.getRadius() / 2),
				subPolys[2]);
		quads[3] = new QuadTreeNode(
				new Polygon(
						"SQUARE",
						new Position(
								bounds.getCenter().getX() + bounds.getRadius() / 2,
								bounds.getCenter().getY() + bounds.getRadius() / 2),
						bounds.getRadius() / 2),
				subPolys[3]);
	}

	private void splitPolys() {
		this.subPolys = new ArrayList[4];

		for (int i = 0; i < 4; i++) {
			this.subPolys[i] = new ArrayList<>();
		}

		for (int i = 0; i < this.polygons.size(); i++) {
				System.out.println("Polygon: " + this.polygons.get(i).getName());
			int quadrant = determineQuad(this.polygons.get(i));
						System.out.println("Quadrant: " + quadrant);
			subPolys[quadrant].add(this.polygons.get(i));
		}
	}

	private int determineQuad(Polygon p) {
		int quad = -1;
		double xMid = bounds.getCenter().getX();
		double yMid = bounds.getCenter().getY();
		if (p.getCenter().getX() < xMid) {
			if (p.getCenter().getY() < yMid) {
				quad = 0;
			} else {
				quad = 2;
			}
		} else {
			if (p.getCenter().getY() < yMid) {
				quad = 1;
			} else {
				quad = 3;
			}
		}
		return quad;
	}

	private Polygon getBounds() {
		return bounds;
	}

	private Boolean areCollided(Polygon p1, Polygon p2) {
		CollisionDetector cd = new CollisionDetector();
		if (cd.polygonsIntersectOrContain(p1, p2)) {
			return true;
		}
		return false;
	}

	private void giveColliding(QuadTreeNode current, Polygon p, QuadTreeNode root) {
		if (!areCollided(current.getBounds(), root.getBounds())) {
			return;
		}
		if ("LEAF".equals(current.type)) {
			for (int i = 0; i < root.polysInBranch; i++) {
				if (areCollided(p, root.polygons.get(i))
						&& p.getName() != root.polygons.get(i).getName()) {
					pairs.add(new PolyPair(p, root.polygons.get(i)));
				}
			}
		} else {
			giveColliding(current, p, root.quads[0]);
			giveColliding(current, p, root.quads[1]);
			giveColliding(current, p, root.quads[2]);
			giveColliding(current, p, root.quads[3]);
		}
	}

	private void currentEl(QuadTreeNode current, QuadTreeNode root) {
		if (current == null) {
			return;
		}
		if ("LEAF".equals(current.type)) {
			for (int i = 0; i < this.polysInBranch; i++) {
				this.giveColliding(current, current.polygons.get(i), root);
			}
		} else {
			for (int i = 0; i < 4; i++) {
				currentEl(current.quads[i], root);
			}
		}
	}

	public List<PolyPair> checkCollisions() {
		pairs = new ArrayList<PolyPair>();
		currentEl(this, this);
		Set<PolyPair> set = new HashSet<>(pairs);
		pairs = new ArrayList<>(set);
		return pairs;
	}
}
