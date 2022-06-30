package com.kradicati.engine;

import com.kradicati.engine.body.Body;
import com.kradicati.engine.body.BodyManager;
import com.kradicati.engine.collision.narrowphase.Epa;
import com.kradicati.engine.collision.narrowphase.Gjk;
import com.kradicati.engine.collision.narrowphase.NarrowphaseCollisionDetection;
import com.kradicati.engine.geometry.Transform;
import com.kradicati.engine.geometry.shape.ConvexShape;
import com.kradicati.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;

@Getter
@Setter
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
     *
     * @param time The time delta measured in milliseconds.
     */
    public void update(long time) {
        double dt = time / 1000f;

        final Vector gravity = new Vector(0, configuration.getGravity());
        final Transform transform = new Transform();

        for (Body body : bodyManager.getBodies()) {
            if (body.getMass() == 0.f) continue;

            // Calculate initial forces to be applied
            body.applyForce(gravity.multiply(body.getMass())); // Applying weight

            // Calculate forces
            Vector accel = body.getResultantForces().multiply(1 / body.getMass());

            body.setPosition(
                    body.getPosition().add(body.getLinearVelocity().multiply(dt).add(accel.multiply(0.5 * dt * dt)))
            );
            body.setLinearVelocity(
                    body.getLinearVelocity().add(accel.multiply(dt))
            );

            transform.setLinearTransform(body.getPosition().subtract(body.getLastPosition()));
            body.setLastPosition(body.getPosition());
            body.applyTransform(transform);

            body.setResultantForces(Vector.zero());
        }
    }

    /*
    public void handleCollisions(Body body) {
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

                Vector vv1np = normal.multiply(v1np);
                Vector vv1tp = tangent.multiply(v1t);
                Vector vv2np = normal.multiply(v2np);
                Vector vv2tp = tangent.multiply(v2t);

                body.clearResultantForces();
                body.setLinearVelocity(vv1np.add(vv1tp));
                other.setLinearVelocity(vv2np.add(vv2tp));
            }
        }
    }
     */

}
