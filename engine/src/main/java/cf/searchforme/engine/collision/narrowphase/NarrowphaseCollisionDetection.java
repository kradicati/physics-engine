package cf.searchforme.engine.collision.narrowphase;

import cf.searchforme.engine.geometry.shape.ConvexShape;

public interface NarrowphaseCollisionDetection {

    boolean collides(ConvexShape body1, ConvexShape body2);

}
