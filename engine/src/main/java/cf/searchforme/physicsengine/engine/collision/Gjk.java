package cf.searchforme.physicsengine.engine.collision;

import cf.searchforme.physicsengine.engine.SimulationContext;
import cf.searchforme.physicsengine.engine.body.shape.ConvexShape;
import cf.searchforme.physicsengine.engine.datastructure.Vector;

import java.util.ArrayList;
import java.util.List;

public class Gjk implements CollisionDetection {

    private final SimulationContext simulation;

    public Gjk(SimulationContext simulation) {
        this.simulation = simulation;
    }

    public ArrayList<Vector> getCollisionSimplex(ConvexShape body1, ConvexShape body2) {
        ArrayList<Vector> simplex = new ArrayList<>();

        Vector direction = body1.getCenter().subtract(body2.getCenter());

        if (direction.getX() == 0 && direction.getY() == 0) direction.setX(1);

        simplex.add(ConvexShape.getSupportPoint(body1, body2, direction));

        // Didn't pass the origin
        if (simplex.get(0).dot(direction) <= 0) return null;

        direction.set(simplex.get(0).clone().negate());

        for (int i = 0; i < simulation.getConfiguration().getMaxGjkIterations(); i++) {
            Vector support = ConvexShape.getSupportPoint(body1, body2, direction);

            // New support point didn't pass the origin
            if (support.dot(direction) <= 0) return null;

            simplex.add(support);

            if (handleSimplex(simplex, direction)) return simplex;
        }

        return null;
    }

    public boolean collides(ConvexShape body1, ConvexShape body2) {
        return getCollisionSimplex(body1, body2) != null;
    }

    // Checking whether the simplex contains the origin
    public boolean handleSimplex(List<Vector> simplex, Vector direction) {
        Vector a = simplex.get(simplex.size() - 1);

        if (a.dot(direction) <= 0) return false;

        Vector ao = a.clone().negate();

        if (simplex.size() == 2) { // Line case
            Vector b = simplex.get(0);

            Vector ab = b.subtract(a);

            direction.set(Vector.tripleProduct(ab, ao, ab));

            if (direction.getMagnitudeSquared() == 0) direction.set(ab.getOrthogonal());

            return false;
        }

        Vector b = simplex.get(1);
        Vector c = simplex.get(0);

        Vector ab = b.subtract(a);
        Vector ac = c.subtract(a);

        Vector acPerp = Vector.tripleProduct(ab, ac, ac);

        if (acPerp.dot(ao) >= 0) {
            simplex.remove(c);

            return false;
        } else {
            Vector abPerp = Vector.tripleProduct(ac, ab, ab);

            if (abPerp.dot(ao) < 0) return true;

            simplex.remove(0);
            simplex.set(0, b);

            direction.set(abPerp);
        }

        simplex.set(1, a);

        return false;
    }

}
