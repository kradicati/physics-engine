package com.kradicati.engine.collision.narrowphase;

import com.kradicati.engine.geometry.shape.ConvexShape;

public interface NarrowphaseCollisionDetection {

    boolean collides(ConvexShape body1, ConvexShape body2);

}
