package cf.searchforme.physicsengine.engine.collision;

import cf.searchforme.physicsengine.engine.SimulationContext;
import cf.searchforme.physicsengine.engine.body.ConvexBody;
import cf.searchforme.physicsengine.engine.datastructure.Vector;

import java.util.ArrayList;
import java.util.List;

public class Gjk implements CollisionDetection {

    public boolean collides(ConvexBody body1, ConvexBody body2) {
        Vector origin = Vector.zero();
        List<Vector> simplex = new ArrayList<>();

        Vector direction = body2.getCenter().subtract(body2.getCenter()).normalize();

        simplex.add(getSupportPoint(body1, body2, direction));

        // Checks if the support point hasn't passed the origin, thus making it
        // impossible for the bodies to collide
        if (simplex.get(0).dot(direction) <= 0) {
            return false;
        }

        direction = origin.subtract(simplex.get(0));

        for (int i = 0; i < SimulationContext.getInstance().getConfiguration().getMaxGjkIterations(); i++) {
            Vector support = getSupportPoint(body1, body2, direction);

            // New support point didn't pass the origin
            if (support.dot(direction) <= 0) return false;

            simplex.add(support);

            if (handleSimplex(simplex, direction)) return true;
        }

        return false;
    }

    public Vector getSupportPoint(ConvexBody body1, ConvexBody body2, Vector direction) {
        Vector d = direction.clone();

        Vector point1 = body1.getFurthestPoint(d);
        Vector point2 = body2.getFurthestPoint(d.negate());

        return point1.to(point2);
    }

    // Checking whether the simplex contains the origin
    public boolean handleSimplex(List<Vector> simplex, Vector direction) {
        if (simplex.size() == 2) { // Line case
            Vector a = simplex.get(1);
            Vector b = simplex.get(0);

            Vector ab = b.to(a);
            Vector ao = Vector.zero().to(a);

            direction.set(Vector.tripleProduct(ab, ao, ab));

            if (direction.getMagnitudeSquared() <= 0) direction.set(ab.left());
        } else if (simplex.size() == 3) { // Triangle case
            Vector c = simplex.get(2);
            Vector b = simplex.get(1);
            Vector a = simplex.get(0);

            Vector ab = b.to(a);
            Vector ac = c.to(a);
            Vector ao = Vector.zero().subtract(a);

            Vector abPerp = Vector.tripleProduct(ac, ab, ab);
            Vector acPerp = Vector.tripleProduct(ab, ac, ac);

            if (abPerp.dot(ao) > 0) { // Region AB
                simplex.remove(c);

                direction.set(abPerp);

                return false;
            } else if (acPerp.dot(ao) > 0) { // Region AC
                simplex.remove(b);

                direction.set(acPerp);

                return false;
            }

            // contains the origin
            return true;
        } else {
            throw new IllegalArgumentException("Simplex vertices exceed 3");
        }

        return false;
    }

}
