package cf.searchforme.engine;

import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.geometry.shape.convex.Circle;
import cf.searchforme.engine.util.datastructure.Vector;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SimulationTest {

    @Test
    @SneakyThrows
    // Profoundly unscientific and programmatically terrible
    public void update() {
        long start = System.currentTimeMillis();

        double height = 60;

        Vector center = new Vector(0, height);
        Body body = new Body(12, center, new Circle(center, 5));
        Simulation simulation = new Simulation();

        simulation.getBodyManager().addBody(body);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        service.scheduleAtFixedRate(() -> {
            simulation.update(25);

            if (body.getCenter().getY() <= 0) {
                double taken = (System.currentTimeMillis() - start) / 1000f;
                double expected = Math.sqrt((2 * height) / simulation.getConfiguration().getGravity());
                System.out.printf("t=%f e=%f d=%f", taken, expected, Math.abs(taken - expected));
                service.shutdown();
            }
        }, 0, 25, TimeUnit.MILLISECONDS);

        Thread.sleep(4000);
    }

}