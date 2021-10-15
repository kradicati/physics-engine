package cf.searchforme.physicsengine;

import cf.searchforme.engine.geometry.shape.convex.Ellipse;
import cf.searchforme.engine.util.datastructure.Vector;
import org.junit.Test;

public class EllipseTest {

    @Test
    public void furthestPoint() {
        Ellipse ellipse = new Ellipse(new Vector(0, 0), 3, 4);
        Vector direction = new Vector(-1, -1);

        System.out.println(ellipse.getFurthestPoint(direction));
    }

}
