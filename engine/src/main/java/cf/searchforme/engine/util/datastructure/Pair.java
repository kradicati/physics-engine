package cf.searchforme.engine.util.datastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Pair<X, Y> {

    private X x;
    private Y y;

}
