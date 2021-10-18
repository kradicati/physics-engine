package cf.searchforme.sandbox.libgdx;

import cf.searchforme.engine.Simulation;
import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.geometry.shape.convex.Circle;
import cf.searchforme.engine.geometry.shape.convex.Polygon;
import cf.searchforme.engine.util.datastructure.Vector;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
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

    private final Queue<Long> physicsExecutionTime = new ConcurrentLinkedQueue<>();

    public Sandbox() {
        instance = this;

        final int dt = 5;

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long time = System.nanoTime();
            simulation.update(dt);
            physicsExecutionTime.add(System.nanoTime() - time);

            if (physicsExecutionTime.size() > 2000) {
                physicsExecutionTime.poll();
            }
        }, 0, dt, TimeUnit.MILLISECONDS);
    }

    @Override
    public void create() {
        setScreen(new SandboxScreen());
        Gdx.input.setInputProcessor(new SandboxInputProcessor());

        Polygon polygon = new Polygon(new Vector[] {
                new Vector(0, 0),
                new Vector(Gdx.graphics.getWidth() / scale, 0),
                new Vector(Gdx.graphics.getWidth() / scale, 2),
                new Vector(0, 2)
        });
        simulation.getBodyManager().addBody(new Body(0, polygon.getCenter(), polygon));
    }

    @Override
    public void dispose() {
        scheduledExecutorService.shutdown();
    }
}
