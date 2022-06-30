package com.kradicati.engine;

import com.kradicati.engine.body.Body;
import com.kradicati.engine.geometry.shape.convex.Circle;
import com.kradicati.engine.util.datastructure.Vector;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationTest {

    @Test
    @SneakyThrows
    // Profoundly unscientific and programmatically terrible
    public void update() {
        long start = System.currentTimeMillis();

        double height = 100000;

        Vector center = new Vector(0, height);
        Body body = new Body(12, center, new Circle(center, 5));
        Simulation simulation = new Simulation(new ContextualConfiguration());

        simulation.getBodyManager().addBody(body);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        double expectedTime = Math.sqrt((2 * height) / Math.abs(simulation.getConfiguration().getGravity()));

        service.scheduleAtFixedRate(() -> {
            simulation.update(25);

            if (body.getPosition().getY() <= 0) {
                double taken = (System.currentTimeMillis() - start) / 1000f;
                
                System.out.printf("t=%f e=%f d=%f\n", taken,
                        expectedTime,
                        Math.abs(taken - expectedTime));

                double expectedVelocity = simulation.getConfiguration().getGravity() * expectedTime;
                System.out.printf("v_y=%f e=%f d=%f\n", body.getLinearVelocity().getY(),
                        expectedVelocity,
                        Math.abs(body.getLinearVelocity().getY() - expectedVelocity));
                service.shutdown();
            }
        }, 0, 25, TimeUnit.MILLISECONDS);

        Thread.sleep((long) ((expectedTime * 1.05) * 1000));
    }

}