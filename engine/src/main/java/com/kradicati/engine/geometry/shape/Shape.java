package com.kradicati.engine.geometry.shape;

import com.kradicati.engine.geometry.Transform;
import com.kradicati.engine.util.datastructure.Vector;

public interface Shape {

    Vector getCenter();

    void applyTransform(Transform transform);

}
