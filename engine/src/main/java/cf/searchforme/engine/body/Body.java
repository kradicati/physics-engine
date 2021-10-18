package cf.searchforme.engine.body;

import cf.searchforme.engine.geometry.Transform;
import cf.searchforme.engine.geometry.shape.Shape;
import cf.searchforme.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter @Setter
public class Body {

    private double mass;

    private Vector center;

    private Vector linearVelocity = Vector.zero();
    private double angularVelocity = 0;

    private final List<Force> forces = new ArrayList<>();

    private final Shape shape;

    public Body(double mass, Vector center, Shape shape) {
        this.mass = mass;
        this.center = center;
        this.shape = shape;
    }

    public void applyForce(Force force) {
        if (getMass() == 0.f) return;

        forces.add(force);
    }

    public Vector clearResultantForces() {
        Vector resultant = new Vector();

        Iterator<Force> copied = forces.iterator();

        while (copied.hasNext()) {
            Force force = copied.next();

            resultant.add(force.getAcceleration());

            copied.remove();
        }

        return resultant;
    }

    public void applyTransform(Transform transform) {
        shape.applyTransform(transform);
    }

}
