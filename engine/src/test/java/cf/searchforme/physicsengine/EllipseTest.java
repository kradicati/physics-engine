package cf.searchforme.physicsengine;

import com.kradicati.engine.geometry.shape.convex.Ellipse;
import com.kradicati.engine.util.datastructure.Vector;
import org.junit.Test;

public class EllipseTest {

    @Test
    public void furthestPoint() {
        Ellipse ellipse = new Ellipse(new Vector(0, 0), 3, 4);
        Vector direction = new Vector(-1, -1);

        System.out.println(ellipse.getFurthestPoint(direction));
    }

}
