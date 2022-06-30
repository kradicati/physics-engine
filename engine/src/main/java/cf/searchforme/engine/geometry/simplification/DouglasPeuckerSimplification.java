package cf.searchforme.engine.geometry.simplification;

import cf.searchforme.engine.util.datastructure.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DouglasPeuckerSimplification implements Simplification {
    @Override
    public List<Vector> simplify(List<Vector> polyline) {
        if (polyline == null || polyline.isEmpty()) return polyline;

        // TODO Set
        double epsilon = 10;

        double maxDistance = 0;
        int maxIndex = 0;

        Vector az = polyline.get(polyline.size() - 1).subtract(polyline.get(0));

        for (int i = 1; i < polyline.size() - 1; i++) {
            Vector vertex = polyline.get(i);

            double d = vertex.distance(az);

            if (d > maxDistance) {
                maxDistance = d;
                maxIndex = i;
            }
        }

        if (maxDistance > epsilon) {
            List<Vector> results1 = simplify(polyline.subList(0, maxIndex + 1));
            List<Vector> results2 = simplify(polyline.subList(maxIndex, polyline.size()));

            results2.removeAll(results1);
            results1.addAll(results2);

            return results1;
        } else {
            return new ArrayList<>(Arrays.asList(polyline.get(0), polyline.get(polyline.size() - 1)));
        }
    }
}
