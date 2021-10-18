package cf.searchforme.engine.geometry.shape;

import cf.searchforme.engine.util.datastructure.Vector;

public interface ConvexShape extends Shape, Cloneable {

    Vector getFurthestPoint(Vector direction);

    static Vector getSupportPoint(ConvexShape body1, ConvexShape body2, Vector direction) {
        Vector d = direction.clone();

        Vector point1 = body1.getFurthestPoint(d);
        Vector point2 = body2.getFurthestPoint(d.negate());

        return point1.clone().subtract(point2);
    }

    ConvexShape clone();

}
