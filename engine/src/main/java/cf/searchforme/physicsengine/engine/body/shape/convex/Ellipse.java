package cf.searchforme.physicsengine.engine.body.shape.convex;

import cf.searchforme.physicsengine.engine.body.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ellipse implements ConvexShape {

    private Vector center;
    private double a;
    private double b;

    public Ellipse(Vector center, double a, double b) {
        this.center = center;
        this.a = a;
        this.b = b;
    }

    @Override
    public Vector getFurthestPoint(Vector direction) {
        double t = Math.atan2(direction.getY(), direction.getX());

        return new Vector(center.getX() + a * Math.cos(t), center.getY() + b * Math.sin(t));
    }

    @Override
    public Vector getCenter() {
        return center;
    }
}
