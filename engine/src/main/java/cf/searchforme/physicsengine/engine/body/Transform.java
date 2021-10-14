package cf.searchforme.physicsengine.engine.body;

import cf.searchforme.physicsengine.engine.util.datastructure.Vector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Transform {

    public Vector linearTransform;
    public double angularTransform;

}
