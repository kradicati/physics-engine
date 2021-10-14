package cf.searchforme.physicsengine.engine.geometry.simplification;

import cf.searchforme.physicsengine.engine.util.datastructure.Vector;

import java.util.List;

public interface Simplification {

    List<Vector> simplify(List<Vector> polyline);

}
