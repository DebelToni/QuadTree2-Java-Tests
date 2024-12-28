package com.example;

public class CollisionDetector {
	public static int orientation(Position p1, Position p2, Position p3) {
		double cross = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
				- (p3.getY() - p2.getY()) * (p2.getX() - p1.getX());

		if (cross > 0) {
			return +1; // lqvo
		} else if (cross < 0) {
			return -1; // dqsno
		} else {
			return 0; // kolinearno (chasovete po mopr vse pak se okazaha polezni, za
			// da razbera kakvo tochno e pravil 4 godishniq mi kodec
		}
	}

	public static boolean doSegmentsIntersect(Position p1, Position p2, Position p3, Position p4) {
		int o1 = orientation(p1, p2, p3);
		int o2 = orientation(p1, p2, p4);
		int o3 = orientation(p3, p4, p1);
		int o4 = orientation(p3, p4, p2);

		if (o1 != o2 && o3 != o4) {
			return true;
		}

		// TODO: Add collinearen colision check
		return false;
	}

	public static boolean pointInPolygon(Position p, Position[] vertices) {
		int countIntersections = 0;
		int n = vertices.length;

		for (int i = 0; i < n; i++) {
			Position v1 = vertices[i];
			Position v2 = vertices[(i + 1) % n];
			boolean isBetweenY = (v1.getY() > p.getY()) != (v2.getY() > p.getY());
			if (isBetweenY) {
				double xIntersect = v1.getX() + (p.getY() - v1.getY()) * (v2.getX() - v1.getX())
						/ (v2.getY() - v1.getY());
				if (xIntersect > p.getX()) {
					countIntersections++;
				}
			}
		}

		return (countIntersections % 2 == 1);
	}

	public static boolean pointInPolygon(Position p, Polygon polygon) {
		return pointInPolygon(p, polygon.getVertices());
	}

	public static boolean polygonsIntersectOrContain(Position[] A, Position[] B) {
		int sizeA = A.length;
		int sizeB = B.length;

		for (int i = 0; i < sizeA; i++) {
			Position a1 = A[i];
			Position a2 = A[(i + 1) % sizeA];

			for (int j = 0; j < sizeB; j++) {
				Position b1 = B[j];
				Position b2 = B[(j + 1) % sizeB];

				if (doSegmentsIntersect(a1, a2, b1, b2)) {
					return true;
				}
			}
		}

		if (pointInPolygon(A[0], B)) {
			return true;
		}
		if (pointInPolygon(B[0], A)) {
			return true; // B v A
		}

		return false;
	}

	public static boolean polygonsIntersectOrContain(Polygon polyA, Polygon polyB) {
		Position[] A = polyA.getVertices();
		Position[] B = polyB.getVertices();
		return polygonsIntersectOrContain(A, B);
	}

	public static boolean polygonIntersectsCircle(Position[] vertices, Position c, double r) {
		int n = vertices.length;

		if (pointInPolygon(c, vertices)) {
			return true;
		}

		for (Position vertex : vertices) {
			double dx = vertex.getX() - c.getX();
			double dy = vertex.getY() - c.getY();
			if (dx * dx + dy * dy <= r * r) {
				return true;
			}
		}

		for (int i = 0; i < n; i++) {
			Position v1 = vertices[i];
			Position v2 = vertices[(i + 1) % n];

			if (lineSegmentIntersectsCircle(v1, v2, c, r)) {
				return true;
			}
		}

		return false;
	}

	public static boolean polygonIntersectsCircle(Polygon polygon, Position c, double r) {
		return polygonIntersectsCircle(polygon.getVertices(), c, r);
	}

	private static boolean lineSegmentIntersectsCircle(
			Position p1, Position p2, Position c, double r) {
		double dx = p2.getX() - p1.getX();
		double dy = p2.getY() - p1.getY();

		double fx = c.getX() - p1.getX();
		double fy = c.getY() - p1.getY();

		double segLenSq = dx * dx + dy * dy;
		if (segLenSq < 1e-12) {
			// sustata tochka
			double distSq = fx * fx + fy * fy;
			return distSq <= r * r;
		}

		double t = (fx * dx + fy * dy) / segLenSq;
		if (t < 0)
			t = 0;
		if (t > 1)
			t = 1;

		double closestX = p1.getX() + t * dx;
		double closestY = p1.getY() + t * dy;

		double distX = closestX - c.getX();
		double distY = closestY - c.getY();
		double distSq = distX * distX + distY * distY;

		return distSq <= r * r;
	}
}
