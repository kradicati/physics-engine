package cf.searchforme.engine;

import cf.searchforme.engine.body.BodyManager;
import cf.searchforme.engine.collision.narrowphase.Epa;
import cf.searchforme.engine.collision.narrowphase.Gjk;
import cf.searchforme.engine.collision.narrowphase.NarrowphaseCollisionDetection;
import cf.searchforme.engine.geometry.Transform;
import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.body.Force;
import cf.searchforme.engine.geometry.shape.ConvexShape;
import cf.searchforme.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Simulation {

    private ContextualConfiguration configuration;

    private BodyManager bodyManager = new BodyManager();

    private final NarrowphaseCollisionDetection narrowphaseCollisionDetection;
    private final Epa penetrationSolver = new Epa();

    public Simulation() {
        this(ContextualConfiguration.fromFile(new File("simulation.properties")));
    }

    @SneakyThrows
    public Simulation(ContextualConfiguration configuration) {
        this.configuration = configuration;

        //narrowphaseCollisionDetection = configuration.getNarrowphaseCollisionDetectionClass().newInstance();
        narrowphaseCollisionDetection = new Gjk(this);
    }

    /**
     * Executes a physics update, updating all the bodies in the simulation according to the provided time delta.
     * @param dt The time delta measured.
     */
    public void update(long dt) {
        double seconds = dt / 1000f;

        final Force gravity = new Force(0, new Vector(0, -1).setMagnitude(configuration.getGravity()));
        final Transform transform = new Transform();

        for (Body body : bodyManager.getBodies()) {
            // TODO Crude
            if (body.getMass() == 0.f) continue;

            // Calculate initial forces to be applied
            gravity.setMass(body.getMass());
            body.applyForce(gravity);

            // Detect and handle collisions
            for (Body other : bodyManager.getBodies()) {
                if (other.equals(body)) continue;

                ArrayList<Vector> simplex = ((Gjk) narrowphaseCollisionDetection).getCollisionSimplex((ConvexShape) body.getShape(), (ConvexShape) other.getShape());

                if (simplex != null && simplex.size() != 0) {
                    Vector normal = penetrationSolver.getPenetration(simplex, (ConvexShape) body.getShape(), (ConvexShape) other.getShape());
                    normal.normalize();
                    Vector tangent = normal.getOrthogonal();

                    // n = normal, t = tangent, p = prime, v- vector

                    double v1n = normal.dot(body.getLinearVelocity());
                    double v1t = tangent.dot(body.getLinearVelocity());

                    double v2n = normal.dot(other.getLinearVelocity());
                    double v2t = tangent.dot(other.getLinearVelocity());

                    double v1np = (v1n * (body.getMass() - other.getMass()) + 2 * other.getMass() * v2n)
                            / (body.getMass() + other.getMass());
                    double v2np = (v2n * (other.getMass() - body.getMass()) + 2 * body.getMass() * v1n)
                            / (body.getMass() + other.getMass());

                    Vector vv1np = normal.clone().multiply(v1np);
                    Vector vv1tp = tangent.clone().multiply(v1t);
                    Vector vv2np = normal.multiply(v2np);
                    Vector vv2tp = tangent.multiply(v2t);

                    body.clearResultantForces();
                    body.setLinearVelocity(vv1np.add(vv1tp));
                    other.setLinearVelocity(vv2np.add(vv2tp));
                }
            }

            // Calculate forces
            Vector forces = body.clearResultantForces().multiply(seconds);

            Vector velocity = body.getLinearVelocity().add(forces);
            body.setLinearVelocity(velocity.clone());
            transform.setLinearTransform(velocity.multiply(seconds));

            body.applyTransform(transform);
        }
    }

}
