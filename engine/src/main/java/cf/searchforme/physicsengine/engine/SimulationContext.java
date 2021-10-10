package cf.searchforme.physicsengine.engine;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class SimulationContext {

    @Getter @Setter
    private ContextualConfiguration configuration;

    public SimulationContext() {
        configuration = ContextualConfiguration.fromFile(new File("simulation.properties"));
    }

    public SimulationContext(ContextualConfiguration configuration) {
        this.configuration = configuration;
    }

}
