package com.kradicati.engine;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public final double TWO_PI = 2 * Math.PI;

    public final double PI_OVER_TWO = Math.PI / 2;

    public final double EPSILON;

    public final double DEG_RAD = Math.PI / 180;
    public final double RAD_DEG = 180 / Math.PI;

    static {
        double e = 0.5;
        while (1.0 + e > 1.0) {
            e *= 0.5;
        }

        EPSILON = e;
    }

}
