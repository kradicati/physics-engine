package cf.searchforme.engine.geometry;

import cf.searchforme.engine.util.datastructure.Vector;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Transform {

    private Vector linearTransform;
    private double angularTransform;

}
