package cf.searchforme.physicsengine.engine.geometry.shape.convex;

import cf.searchforme.physicsengine.engine.Constants;
import cf.searchforme.physicsengine.engine.body.Transform;
import cf.searchforme.physicsengine.engine.geometry.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.util.datastructure.Vector;
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

        double x = (a * b) / Math.sqrt(Math.pow(b, 2) + Math.pow(a, 2) * Math.pow(Math.tan(t), 2));
        double y = (a * b) / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) / Math.pow(Math.tan(t), 2));

        if (t < -Constants.PI_OVER_TWO || t > Constants.PI_OVER_TWO) {
            x *= -1;
            y *= -1;
        }

        return new Vector(center.getX() + x, center.getY() + y);
    }

    @Override
    public Vector getCenter() {
        return center;
    }

    @Override
    public void applyTransform(Transform transform) {
        center.add(transform.getLinearTransform());
    }
}
