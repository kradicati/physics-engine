package cf.searchforme.engine.body;

import cf.searchforme.engine.util.datastructure.Vector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Force {

    private double mass;
    private Vector acceleration;

    public Vector getForce() {
        return acceleration.clone().multiply(mass);
    }
}
