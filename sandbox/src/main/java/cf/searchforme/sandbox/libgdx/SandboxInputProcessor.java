package cf.searchforme.sandbox.libgdx;

import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.geometry.shape.convex.Circle;
import cf.searchforme.engine.util.datastructure.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.logging.Logger;

public class SandboxInputProcessor implements InputProcessor {

    private final Logger logger = Logger.getLogger(SandboxInputProcessor.class.getSimpleName());

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        // Left click
        if (i3 == 0) {
            float x = i / Sandbox.getInstance().getScale();
            float y = (Gdx.graphics.getHeight() - i1) / Sandbox.getInstance().getScale();

            Vector center = new Vector(x, y);

            Sandbox.getInstance().getSimulation().getBodyManager().addBody(new Body(1, center, new Circle(center, 12)));

            System.out.printf("Added circle at %s %s\n", x, y);
        }

        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
