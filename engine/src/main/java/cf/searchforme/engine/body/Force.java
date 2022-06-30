package cf.searchforme.engine.body;

import cf.searchforme.engine.util.datastructure.Vector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Force implements Cloneable {

    private double mass;
    private Vector acceleration;

    public Vector getForce() {
        return acceleration.multiply(mass);
    }

    public Force clone() {
        try {
            return (Force) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
