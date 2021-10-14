package cf.searchforme.physicsengine.engine.body;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BodyRepository {

    @Getter
    private final Set<Body> bodies = new CopyOnWriteArraySet<>();

    public void addBody(Body body) {
        bodies.add(body);
    }

    public void removeBody(Body body) {
        bodies.remove(body);
    }

    public Set<Body> findAll(Predicate<Body> predicate) {
        return bodies
                .stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

}
