package cf.searchforme.physicsengine.engine.geometry.simplification;

import cf.searchforme.physicsengine.engine.util.datastructure.Vector;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DouglasPeuckerSimplificationTest {

    @Test
    public void test_dpSimplification() {
        DouglasPeuckerSimplification simplification = new DouglasPeuckerSimplification();

        List<Vector> initial = Arrays.asList(
                vector(-9, 4),
                vector(-8.8, 4.2),
                vector(-8.6, 4),
                vector(-8.4, 4),
                vector(-8.2, 4.4),
                vector(-8, 4),
                vector(-7.8, 3.8),
                vector(-7.6, 4),
                vector(-7.4, 4),
                vector(-7.3, 3.9),
                vector(-7.1, 4.1),
                vector(-7, 4),
                vector(-6.9, 4));

        List<Vector> simplified = simplification.simplify(initial);

        System.out.println(convertArrayToGeogebraPoints(simplified));
    }

    private Vector vector(double x, double y) {
        return new Vector(x, y);
    }

    @SafeVarargs
    private final <T> T[] array(T... t) {
        return t;
    }

    private String convertArrayToGeogebraPoints(List<Vector> points) {
        StringBuilder builder = new StringBuilder();

        for (Vector v : points) {
            builder.append(processHash(v), 0, 2).append("=(").append(v.getX()).append(", ").append(v.getY()).append(")").append("\n");
        }

        return builder.toString();
    }

    private String processHash(Object obj) {
        String hash = Integer.toHexString(obj.hashCode());

        char[] charArray = hash.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (c >= 48 && c <= 57) {
                charArray[i] = (char) (c + 17);
            }
        }

        return new String(charArray);
    }

}