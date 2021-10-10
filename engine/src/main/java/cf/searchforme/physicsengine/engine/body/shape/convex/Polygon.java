package cf.searchforme.physicsengine.engine.body.shape.convex;

import cf.searchforme.physicsengine.engine.body.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Polygon implements ConvexShape {

    private Vector[] vertices;
    private Vector center;

    public Polygon(Vector[] vertices, Vector center) {
        this.vertices = vertices;
        this.center = center;
    }

    public Polygon(Vector[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public Vector getCenter() {
        if (center != null) return center;

        Vector avg = Vector.zero();

        for (Vector vertex : vertices) {
            avg.add(vertex.getX(), vertex.getY());
        }

        avg.set(new Vector(avg.getX() / vertices.length,
                avg.getY() / vertices.length));
        return avg;
    }

    /*
    The furthest vertex in the polygon given a direction is found by computing the dot product between each vertex
    and the direction, and finding the largest one.
     */
    @Override
    public Vector getFurthestPoint(Vector vector) {
        int maxIndex = 0;
        double maxDot = vector.dot(vertices[0]);

        for (int i = 1; i < vertices.length; i++) {
            double dot = vector.dot(vertices[i]);

            if (dot > maxDot) {
                maxIndex = i;
                maxDot = dot;
            }
        }

        return vertices[maxIndex];
    }

}
