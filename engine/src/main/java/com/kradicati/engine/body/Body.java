package com.kradicati.engine.body;

import com.kradicati.engine.geometry.Transform;
import com.kradicati.engine.geometry.shape.Shape;
import com.kradicati.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Body {

    private double mass;

    private Vector lastPosition = new Vector();
    private Vector position = new Vector();

    private Vector linearVelocity = Vector.zero();
    private double angularVelocity = 0;

    private Vector resultantForces = new Vector();

    private final Shape shape;

    public Body(double mass, Vector position, Shape shape) {
        this.mass = mass;
        this.position = position;
        this.shape = shape;
    }

    public void applyForce(Vector force) {
        if (getMass() == 0.f) return;

        resultantForces = resultantForces.add(force);
    }

    public void applyTransform(Transform transform) {
        shape.applyTransform(transform);
    }

}
