package com.kradicati.engine.geometry.shape.convex;

import com.kradicati.engine.geometry.Transform;
import com.kradicati.engine.geometry.shape.ConvexShape;
import com.kradicati.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

    public Circle clone() {
        try {
            return (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
