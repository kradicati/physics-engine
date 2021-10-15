package cf.searchforme.engine.geometry.shape;

import cf.searchforme.engine.geometry.Transform;
import cf.searchforme.engine.util.datastructure.Vector;

public interface Shape {

    Vector getCenter();

    void applyTransform(Transform transform);

}
