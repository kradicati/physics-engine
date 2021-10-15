package cf.searchforme.engine.geometry.shape.convex;

import cf.searchforme.engine.Constants;
import cf.searchforme.engine.geometry.Transform;
import cf.searchforme.engine.util.datastructure.Vector;
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