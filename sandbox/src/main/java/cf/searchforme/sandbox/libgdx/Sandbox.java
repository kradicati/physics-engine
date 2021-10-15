package cf.searchforme.sandbox.libgdx;

import cf.searchforme.engine.Simulation;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class Sandbox extends Game {

    @Getter
    private static Sandbox instance;

    private final Simulation simulation = new Simulation();

    // Pixels per metre
    @Setter
    private float scale = 20;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public Sandbox() {
        instance = this;

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            simulation.update(20);
        }, 0, 20, TimeUnit.MILLISECONDS);
    }

    @Override
    public void create() {
        setScreen(new SandboxScreen());
        Gdx.input.setInputProcessor(new SandboxInputProcessor());
    }

    @Override
    public void dispose() {
        scheduledExecutorService.shutdown();
    }
}
