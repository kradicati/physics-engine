package cf.searchforme.sandbox.util;

import cf.searchforme.engine.util.datastructure.Vector;
import cf.searchforme.sandbox.libgdx.Sandbox;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collection;

@UtilityClass
public class MiscUtil {

    public float[] flattenVectorsToPoints(Vector[] vectors) {
        float[] toReturn = new float[vectors.length * 2];

        for (int i = 0; i < vectors.length; i++) {
            Vector vector = vectors[i];

            toReturn[i * 2] = (float) vector.getX() * Sandbox.getInstance().getScale();
            toReturn[i * 2 + 1] = (float) vector.getY() * Sandbox.getInstance().getScale();
        }

        return toReturn;
    }

    public long getAverage(Collection<Long> numbers) {
        long toReturn = 0;

        for (long number : numbers) {
            toReturn += number;
        }

        return toReturn / numbers.size();
    }

}
