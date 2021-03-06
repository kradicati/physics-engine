package com.kradicati.engine.body;

import com.kradicati.engine.geometry.shape.convex.Circle;
import com.kradicati.engine.util.datastructure.Vector;
import org.junit.Test;

public class BodyTest {

    @Test
    public void resultingForces() {
        Vector center = new Vector(0, 0);

        Body body = new Body(1, center, new Circle(center, 3));

        //System.out.println(body.clearResultantForces());
    }

    @Test
    public void constantMovement() {
        Vector center = new Vector(0, 0);

        Body body = new Body(1, center, new Circle(center, 3));


    }

}