package cf.searchforme.sandbox.libgdx;

import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.geometry.shape.convex.Circle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SandboxScreen implements Screen {
    ShapeRenderer shapeRenderer;

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);

        for (Body body : Sandbox.getInstance().getSimulation().getBodyManager().getBodies()) {
            if (body.getShape() instanceof Circle) {
                shapeRenderer.circle((float) body.getShape().getCenter().getX() * Sandbox.getInstance().getScale(),
                        (float) (body.getShape().getCenter().getY() * Sandbox.getInstance().getScale()),
                        (float) ((Circle) body.getShape()).getRadius());
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
