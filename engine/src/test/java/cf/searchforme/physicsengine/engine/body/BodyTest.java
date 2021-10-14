package cf.searchforme.physicsengine.engine.body;

import cf.searchforme.physicsengine.engine.geometry.shape.convex.Circle;
import cf.searchforme.physicsengine.engine.util.datastructure.Vector;
import org.junit.Test;

public class BodyTest {

    @Test
    public void resultingForces() {
        Vector center = new Vector(0, 0);

        Body body = new Body(1, center, new Circle(center, 3));

        body.applyForce(new Vector(1, 12));
        body.applyForce(new Vector(-5, 15));

        System.out.println(body.clearResultingForces());
    }

    @Test
    public void constantMovement() {
        Vector center = new Vector(0, 0);

        Body body = new Body(1, center, new Circle(center, 3));


    }

}