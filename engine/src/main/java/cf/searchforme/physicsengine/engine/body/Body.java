package cf.searchforme.physicsengine.engine.body;

import cf.searchforme.physicsengine.engine.geometry.shape.Shape;
import cf.searchforme.physicsengine.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter @Setter
public class Body {

    private double mass;

    private Vector center;

    private Vector linearVelocity;
    private double angularVelocity;

    private final List<Vector> activeForces = new ArrayList<>();

    private final Shape shape;

    public Body(double mass, Vector center, Shape shape) {
        this.mass = mass;
        this.center = center;
        this.shape = shape;
    }

    public void applyForce(Vector vector) {
        if (mass == 0.0) return;

        activeForces.add(vector);
    }

    public Vector getResultingForces() {
        Vector resulting = new Vector();

        for (Vector force : activeForces) {
            resulting.add(force);
        }

        return resulting;
    }

    public Vector clearResultingForces() {
        Vector resulting = new Vector();

        Iterator<Vector> copied = activeForces.iterator();

        while (copied.hasNext()) {
            Vector force = copied.next();

            resulting.add(force);

            copied.remove();
        }

        return resulting;
    }

    public void setCenter(Vector vector) {

    }

}
