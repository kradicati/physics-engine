package cf.searchforme.engine.geometry.shape.convex;

import cf.searchforme.engine.geometry.Transform;
import cf.searchforme.engine.geometry.shape.ConvexShape;
import cf.searchforme.engine.util.datastructure.Vector;
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
            avg = avg.add(vertex.getX(), vertex.getY());
        }

        avg.set(new Vector(avg.getX() / vertices.length,
                avg.getY() / vertices.length));
        return avg;
    }

    @Override
    public void applyTransform(Transform transform) {
        Vector center = getCenter();

        double sin = Math.sin(transform.getAngularTransform());
        double cos = Math.cos(transform.getAngularTransform());

        for (Vector vertex : vertices) {
            Vector relativeVertex = vertex.subtract(center);
            double magnitude = relativeVertex.getMagnitude();

            relativeVertex.normalize();

            double x = cos * relativeVertex.getX() - sin * relativeVertex.getY();
            double y = sin * relativeVertex.getX() + cos * relativeVertex.getY();

            vertex.set(relativeVertex.set(x, y).setMagnitude(magnitude).add(center).add(transform.getLinearTransform()));
        }

        if (center != null) center.add(transform.getLinearTransform());
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

    public Polygon clone() {
        try {
            return (Polygon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
