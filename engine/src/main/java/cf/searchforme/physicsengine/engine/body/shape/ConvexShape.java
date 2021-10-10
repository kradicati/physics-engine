package cf.searchforme.physicsengine.engine.body.shape;

import cf.searchforme.physicsengine.engine.datastructure.Vector;

public interface ConvexShape extends Shape {

    Vector getFurthestPoint(Vector direction);

    static Vector getSupportPoint(ConvexShape body1, ConvexShape body2, Vector direction) {
        Vector d = direction.clone();

        Vector point1 = body1.getFurthestPoint(d);
        Vector point2 = body2.getFurthestPoint(d.negate());

        return point1.clone().subtract(point2);
    }

}