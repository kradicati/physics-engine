package com.kradicati.engine.collision.narrowphase;

import com.kradicati.engine.Simulation;
import com.kradicati.engine.collision.narrowphase.Gjk;
import com.kradicati.engine.util.datastructure.Vector;
import com.kradicati.engine.geometry.shape.ConvexShape;
import com.kradicati.engine.geometry.shape.convex.Circle;
import com.kradicati.engine.geometry.shape.convex.Polygon;
import org.junit.Test;

import java.util.ArrayList;

public class GjkTest {

    @Test
    public void pointOnCircumference() {
        Circle circle = new Circle(vector(0, 0), 3);
        Vector direction = vector(7, -2);

        System.out.println(circle.getFurthestPoint(direction));
    }

    @Test
    public void furthestPoint() {
        ConvexShape cvx = new Polygon(array(vector(-4, 6), vector(-2, 8), vector(21, 5), vector(-2, 2)), vector(5, 5.03));

        System.out.println(cvx.getFurthestPoint(vector(-1, 7)));

        Vector a = new Vector(1, 12);
        Vector b = new Vector(13, 222);

        System.out.println(b.subtract(a));
        System.out.println(a.to(b));
    }

    @Test
    public void supportPoint() {
        ConvexShape cvx1 = new Polygon(array(vector(-5, 3), vector(-5, -1), vector(-1, -1), vector(-1, 3)), vector(-3, 2));

        Vector furthest = cvx1.getFurthestPoint(vector(-5, -0));

        System.out.println(furthest);
    }

    @Test
    public void gjk_polygons() {
        Simulation simulation = new Simulation();

        Gjk gjk = new Gjk(simulation);

        ConvexShape cvx1 = new Polygon(array(
                vector(2, 10),
                vector(2, 7),
                vector(5, 7),
                vector(5, 10)));
        ConvexShape cvx2 = new Polygon(array(
                vector(4, 6),
                vector(6, 9),
                vector(8, 8),
                vector(8, 6)));

        ArrayList<Vector> simplex = gjk.getCollisionSimplex(cvx1, cvx2);

        System.out.println(simplex == null ? "false" : "true: " + simplex);
        System.out.println(cvx1 + " " + cvx2);
    }

    @Test
    public void gjk_polygon_circle() {
        Simulation simulation = new Simulation();

        Gjk gjk = new Gjk(simulation);

        Polygon convexPolygon = new Polygon(array(
                vector(2, 1),
                vector(2, -3),
                vector(5, -3),
                vector(5, 1)
        ));

        Circle circle = new Circle(vector(0, 0), 3);

        System.out.println(gjk.collides(convexPolygon, circle));

        System.out.println(convexPolygon + " " + circle);
    }

    private Vector vector(double x, double y) {
        return new Vector(x, y);
    }

    @SafeVarargs
    private final <T> T[] array(T... t) {
        return t;
    }

}
