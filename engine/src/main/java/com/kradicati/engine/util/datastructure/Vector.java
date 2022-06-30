package com.kradicati.engine.util.datastructure;

import com.kradicati.engine.Constants;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vector implements Cloneable {

    private double x, y;

    public Vector set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector set(Vector vector) {
        x = vector.x;
        y = vector.y;
        return this;
    }

    public Vector add(double x, double y) {
        return new Vector(this.x + x, this.y + y);
    }

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY());
    }

    public Vector subtract(double x, double y) {
        return new Vector(this.x - x, this.y - y);
    }

    public Vector subtract(Vector vector) {
        return new Vector(this.x - vector.getX(), this.y - vector.getY());
    }

    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    public Vector to(double x, double y) {
        return new Vector(x - this.x, y - this.y);
    }

    public Vector to(Vector vector) {
        return new Vector(vector.x - x, vector.y - y);
    }

    public Vector negate() {
        return new Vector(-this.x, -this.y);
    }

    public Vector getNegative() {
        return new Vector(-x, -y);
    }

    public double distanceSquared(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;

        return dx * dx + dy * dy;
    }

    public double distanceSquared(Vector vector) {
        double dx = x - vector.x;
        double dy = y - vector.y;

        return dx * dx + dy * dy;
    }

    public double distance(double x, double y) {
        return Math.sqrt(distanceSquared(x, y));
    }

    public double distance(Vector vector) {
        return Math.sqrt(distanceSquared(vector));
    }

    public double getMagnitudeSquared() {
        return distanceSquared(0, 0);
    }

    public double getMagnitude() {
        return distance(0, 0);
    }

    /**
     * @return The direction of this vector in radians.
     */
    public double getDirection() {
        return Math.atan2(y, x);
    }

    public double dot(Vector vector) {
        return x * vector.getX() + y * vector.getY();
    }

    public double cross(Vector vector) {
        return x * vector.y - y * vector.x;
    }

    public boolean isOrthogonal(Vector vector) {
        return dot(vector) == 0;
    }

    public Vector getOrthogonal() {
        return new Vector(y, -x);
    }

    public Vector setMagnitude(double newMagnitude) {
        double magnitude = getMagnitude();

        if (magnitude == 0) return new Vector();

        double inverse = newMagnitude / magnitude;

        x *= inverse;
        y *= inverse;

        return this;
    }

    public Vector normalize() {
        return setMagnitude(1);
    }

    public double getAngleBetween(Vector vector) {
        double a = Math.atan2(vector.y, vector.x) - Math.atan2(this.y, this.x);
        if (a > Math.PI) return a - Constants.TWO_PI;
        if (a < -Math.PI) return a + Constants.TWO_PI;
        return a;
    }

    public Vector right() {
        double temp = this.x;
        this.x = -this.y;
        this.y = temp;
        return this;
    }

    public Vector left() {
        double temp = this.x;
        this.x = this.y;
        this.y = -temp;
        return this;
    }

    public Vector rotate(double cos, double sin) {
        double x = this.x;
        double y = this.y;

        this.x = x * cos - y * sin;
        this.y = x * sin + y * cos;

        return this;
    }

    public Vector rotate(double theta) {
        return this.rotate(Math.cos(theta), Math.sin(theta));
    }

    public Vector clone() {
        try {
            return (Vector) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector fromAngle(double t, double magnitude) {
        double x = Math.cos(t) * magnitude;
        double y = Math.sin(t) * magnitude;

        return new Vector(x, y);
    }

    public static Vector zero() {
        return new Vector(0, 0);
    }

    public static Vector tripleProduct(Vector a, Vector b, Vector c) {
        Vector r = new Vector();

        double ac = a.dot(c);
        double bc = b.dot(c);

        //System.out.println(ac + " " + bc);

        r.x = b.x * ac - a.x * bc;
        r.y = b.y * ac - a.y * bc;

        return r;
    }

}
