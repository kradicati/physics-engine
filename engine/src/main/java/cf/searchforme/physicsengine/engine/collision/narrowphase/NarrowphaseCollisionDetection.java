package cf.searchforme.physicsengine.engine.collision.narrowphase;

import cf.searchforme.physicsengine.engine.geometry.shape.ConvexShape;

public interface NarrowphaseCollisionDetection {

    boolean collides(ConvexShape body1, ConvexShape body2);

}
