package cf.searchforme.physicsengine.engine.body;

import cf.searchforme.physicsengine.engine.datastructure.Vector;

public interface ConvexBody extends Body {

    Vector getFurthestPoint(Vector vector);

    boolean collidesWith(ConvexBody other);

}
