package cf.searchforme.physicsengine.engine.datastructure;

import cf.searchforme.physicsengine.engine.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vector implements Cloneable {

    private double x, y;

    public Vector set(Vector vector) {
        x = vector.x;
        y = vector.y;
        return this;
    }

    public Vector add(double x, double y) {
        this.x += x;
        this.y += y;

        return this;
    }

    public Vector add(Vector vector) {
        x += vector.getX();
        y += vector.getY();

        return this;
    }

    public Vector subtract(double x, double y) {
        this.x -= x;
        this.y -= y;

        return this;
    }

    public Vector subtract(Vector vector) {
        x -= vector.getX();
        y -= vector.getY();

        return this;
    }

    public Vector to(double x, double y) {
        return new Vector(x - this.x, y - this.y);
    }

    public Vector to(Vector vector) {
        return new Vector(vector.x - x, vector.y - y);
    }

    public Vector negate() {
        this.x = -x;
        this.y = -y;

        return this;
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
    
    public Vector normalize() {
        double magnitude = getMagnitude();

        if (magnitude == 0) return new Vector();

        double inverse = 1 / magnitude;

        x *= inverse;
        y *= inverse;

        return this;
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

    Vector rotate(double cos, double sin) {
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

    public static Vector zero() {
        return new Vector(0, 0);
    }

    public static Vector tripleProduct(Vector a, Vector b, Vector c) {
        Vector r = new Vector();

        /*
         * In the following we can substitute ac and bc in r.x and r.y
         * and with some rearrangement get a much more efficient version
         *
         * double ac = a.x * c.x + a.y * c.y;
         * double bc = b.x * c.x + b.y * c.y;
         * r.x = b.x * ac - a.x * bc;
         * r.y = b.y * ac - a.y * bc;
         */

        double dot = a.x * b.y - b.x * a.y;
        r.x = -c.y * dot;
        r.y = c.x * dot;

        return r;
    }

}
