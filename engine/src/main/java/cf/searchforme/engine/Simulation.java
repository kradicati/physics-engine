package cf.searchforme.engine;

import cf.searchforme.engine.body.BodyManager;
import cf.searchforme.engine.geometry.Transform;
import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.body.Force;
import cf.searchforme.engine.util.datastructure.Vector;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class Simulation {

    @Getter @Setter
    private ContextualConfiguration configuration;

    @Getter @Setter
    private BodyManager bodyManager = new BodyManager();

    public Simulation() {
        configuration = ContextualConfiguration.fromFile(new File("simulation.properties"));
    }

    public Simulation(ContextualConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Executes a physics update, updating all the bodies in the simulation according to the provided time delta.
     * @param dt The time delta measured.
     */
    public void update(long dt) {
        double seconds = dt / 1000f;

        for (Body body : bodyManager.getBodies()) {
            // Calculate initial forces to be applied
            body.applyForce(new Force(body.getMass(), new Vector(0, -1).setMagnitude(configuration.getGravity())));

            Transform transform = new Transform();

            // Calculate forces
            Vector forces = body.clearResultantForces().multiply(seconds);
            Vector velocity = body.getLinearVelocity().add(forces);
            body.setLinearVelocity(velocity.clone());
            transform.setLinearTransform(velocity.multiply(seconds));

            body.applyTransform(transform);
        }
    }

}
