package cf.searchforme.physicsengine;

import cf.searchforme.physicsengine.engine.body.shape.convex.Ellipse;
import cf.searchforme.physicsengine.engine.datastructure.Vector;
import org.junit.Test;

public class EllipseTest {

    @Test
    public void furthestPoint() {
        Ellipse ellipse = new Ellipse(new Vector(-1, 0), 3.6, 2);
        Vector direction = new Vector(1, 1);

        System.out.println(ellipse.getFurthestPoint(direction));
    }

}
