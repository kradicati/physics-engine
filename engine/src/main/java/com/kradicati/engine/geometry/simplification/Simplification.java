package com.kradicati.engine.geometry.simplification;

import com.kradicati.engine.util.datastructure.Vector;

import java.util.List;

public interface Simplification {

    List<Vector> simplify(List<Vector> polyline);

}
