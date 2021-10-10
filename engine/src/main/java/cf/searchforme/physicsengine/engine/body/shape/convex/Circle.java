package cf.searchforme.physicsengine.engine.body.shape.convex;

import cf.searchforme.physicsengine.engine.body.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.datastructure.Vector;
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
    public Vector getFurthestPoint(Vector vector) {
        Vector direction = vector.clone().setMagnitude(radius);

        return new Vector(center.getX() + direction.getX(), center.getY() + direction.getY());
    }

}
