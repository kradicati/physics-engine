package cf.searchforme.physicsengine.engine.geometry.shape.convex;

import cf.searchforme.physicsengine.engine.body.Transform;
import cf.searchforme.physicsengine.engine.geometry.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Circle implements ConvexShape {

    private Vector center;
    private double radius;

    public Circle(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getCenter() {
        return center;
    }

    @Override
    public void applyTransform(Transform transform) {
        center.add(transform.getLinearTransform());
    }

    @Override
    public Vector getFurthestPoint(Vector vector) {
        Vector direction = vector.clone().setMagnitude(radius);

        return new Vector(center.getX() + direction.getX(), center.getY() + direction.getY());
    }

}
