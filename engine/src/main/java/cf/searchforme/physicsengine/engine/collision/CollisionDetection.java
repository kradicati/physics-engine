package cf.searchforme.physicsengine.engine.collision;

import cf.searchforme.physicsengine.engine.body.shape.ConvexShape;

public interface CollisionDetection {

    boolean collides(ConvexShape body1, ConvexShape body2);

}
