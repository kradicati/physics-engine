package cf.searchforme.physicsengine.engine;

import cf.searchforme.physicsengine.engine.body.Body;
import cf.searchforme.physicsengine.engine.body.BodyRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class Simulation {

    @Getter @Setter
    private ContextualConfiguration configuration;

    @Getter @Setter
    private BodyRepository bodyRepository = new BodyRepository();

    public Simulation() {
        configuration = ContextualConfiguration.fromFile(new File("simulation.properties"));
    }

    public Simulation(ContextualConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Updates all the bodies in the simulation according to the provided time delta.
     * @param dt The time delta measured in milliseconds.
     */
    public void update(double dt) {
        for (Body body : bodyRepository.getBodies()) {

        }
    }

}
