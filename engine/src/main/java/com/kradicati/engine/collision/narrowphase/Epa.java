package com.kradicati.engine.collision.narrowphase;

import com.kradicati.engine.Constants;
import com.kradicati.engine.geometry.shape.ConvexShape;
import com.kradicati.engine.util.datastructure.Vector;

import java.util.ArrayList;

public class Epa {

    public Vector getPenetration(ArrayList<Vector> polytope, ConvexShape body1, ConvexShape body2) {
        if (polytope == null || polytope.isEmpty()) return null;

        int minIndex = 0;
        double minDistance = Double.POSITIVE_INFINITY;
        Vector minNormal = new Vector();

        while (minDistance == Double.POSITIVE_INFINITY) {
            for (int i = 0; i < polytope.size(); i++) {
                int j = (i + 1) % polytope.size();

                Vector vi = polytope.get(i);
                Vector vj = polytope.get(j);

                Vector ij = vj.subtract(vi);

                ij = Vector.tripleProduct(ij, vi, ij).normalize();
                double distance = ij.dot(vi);

                if (distance < minDistance) {
                    minDistance = distance;
                    minNormal = ij;
                    minIndex = j;
                }
            }

            Vector support = ConvexShape.getSupportPoint(body1, body2, minNormal);
            double distance = minNormal.dot(support);

            if (Math.abs(distance - minDistance) > Constants.EPSILON) {
                minDistance = Double.POSITIVE_INFINITY;
                polytope.add(minIndex, support);
            }
        }

        //return minNormal.multiply(minDistance + 0.001);
        return minNormal.multiply(minDistance);
    }

}
