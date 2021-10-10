package cf.searchforme.physicsengine.engine;

public class Constants {

    public static final double TWO_PI = 2 * Math.PI;
    public static final double EPSILON;

    static {
        double e = 0.5;
        while (1.0 + e > 1.0) {
            e *= 0.5;
        }

        EPSILON = e;
    }

}
