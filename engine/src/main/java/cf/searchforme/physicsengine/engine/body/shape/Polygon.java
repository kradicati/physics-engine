package cf.searchforme.physicsengine.engine.body.shape;

import cf.searchforme.physicsengine.engine.body.ConvexBody;
import cf.searchforme.physicsengine.engine.datastructure.Vector;

public class Polygon implements ConvexBody {

    private final Vector[] vertices;



    @Override
    public void remove() {

    }

    @Override
    public Vector getCenter() {
        return null;
    }

    @Override
    public Vector getFurthestPoint(Vector vector) {
        return null;
    }

    @Override
    public boolean collidesWith(ConvexBody other) {
        return false;
    }
}
