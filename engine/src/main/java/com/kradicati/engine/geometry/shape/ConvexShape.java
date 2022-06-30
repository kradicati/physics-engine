package com.kradicati.engine.geometry.shape;

import com.kradicati.engine.util.datastructure.Vector;

public interface ConvexShape extends Shape, Cloneable {

    Vector getFurthestPoint(Vector direction);

    static Vector getSupportPoint(ConvexShape body1, ConvexShape body2, Vector direction) {
        Vector point1 = body1.getFurthestPoint(direction);
        Vector point2 = body2.getFurthestPoint(direction.negate());

        return point1.subtract(point2);
    }

    ConvexShape clone();

}
