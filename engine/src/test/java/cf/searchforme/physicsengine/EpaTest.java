package cf.searchforme.physicsengine;

import cf.searchforme.physicsengine.engine.SimulationContext;
import cf.searchforme.physicsengine.engine.body.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.body.shape.convex.Polygon;
import cf.searchforme.physicsengine.engine.collision.Epa;
import cf.searchforme.physicsengine.engine.collision.Gjk;
import cf.searchforme.physicsengine.engine.datastructure.Vector;
import org.junit.Test;

import java.util.ArrayList;

public class EpaTest {

    @Test
    public void epa_polygons() {
        SimulationContext simulation = new SimulationContext();

        Gjk gjk = new Gjk(simulation);

        Epa epa = new Epa();

        /*
        ConvexBody cvx1 = new ConvexPolygon(array(
                vector(2, 10),
                vector(2, 7),
                vector(5, 7),
                vector(5, 10)));
        ConvexBody cvx2 = new ConvexPolygon(array(
                vector(3.5, 6.5),
                vector(6, 7.5),
                vector(4.5, 5)));
        ConvexBody cvx3 = new ConvexPolygon(array(
                vector(5.5, 9.5),
                vector(5, 9),
                vector(5.2, 9.2)));

         */

        ConvexShape cvx1 = new Polygon(array(
                vector(4, -2),
                vector(6, -2),
                vector(6, 0),
                vector(4, 0)
        ));

        ConvexShape cvx2 = new Polygon(array(
                vector(4, -3),
                vector(6, -3),
                vector(5, -1)
        ));

        ArrayList<Vector> simplex = gjk.getCollisionSimplex(cvx1, cvx2);

        Vector penetration = epa.getPenetration(simplex, cvx1, cvx2);
        System.out.println(penetration);
    }

    private Vector vector(double x, double y) {
        return new Vector(x, y);
    }

    @SafeVarargs
    private final <T> T[] array(T... t) {
        return t;
    }
}
