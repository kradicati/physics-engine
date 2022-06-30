package com.kradicati.engine.geometry.shape.convex;

import com.kradicati.engine.Constants;
import com.kradicati.engine.geometry.Transform;
import com.kradicati.engine.util.datastructure.Vector;
import org.junit.Test;

import java.util.Arrays;

public class PolygonTest {

    @Test
    public void transform() {
        Polygon polygon = new Polygon(new Vector[]{
                new Vector(1, 2),
                new Vector(1, 3),
                new Vector(2, 3),
                new Vector(2, 2)});

        System.out.println(Arrays.toString(polygon.getVertices()));
        polygon.applyTransform(new Transform(null, Constants.DEG_RAD * 90));
        System.out.println(Arrays.toString(polygon.getVertices()));
    }

}