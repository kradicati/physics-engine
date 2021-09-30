package cf.searchforme.physicsengine.engine.collision;

import cf.searchforme.physicsengine.engine.body.ConvexBody;

public interface CollisionDetection {

    boolean collides(ConvexBody body1, ConvexBody body2);

}
