package cf.searchforme.engine.geometry.simplification;

import cf.searchforme.engine.util.datastructure.Vector;

import java.util.List;

public interface Simplification {

    List<Vector> simplify(List<Vector> polyline);

}
