package cf.searchforme.physicsengine.engine.geometry.shape;

import cf.searchforme.physicsengine.engine.body.Transform;
import cf.searchforme.physicsengine.engine.util.datastructure.Vector;

public interface Shape {

    Vector getCenter();

    void applyTransform(Transform transform);

}
