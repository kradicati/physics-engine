package cf.searchforme.sandbox.libgdx;

import cf.searchforme.engine.body.Body;
import cf.searchforme.engine.geometry.shape.convex.Circle;
import cf.searchforme.engine.geometry.shape.convex.Polygon;
import cf.searchforme.sandbox.util.MiscUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Stream;

public class SandboxScreen implements Screen {

    private OrthographicCamera hudCamera;

    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    public void show() {
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.position.set(hudCamera.viewportWidth / 2.0f, hudCamera.viewportHeight / 2.0f, 1.0f);

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();

        font = new BitmapFont();
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (Body body : Sandbox.getInstance().getSimulation().getBodyManager().getBodies()) {
            if (body.getShape() instanceof Circle) {
                shapeRenderer.setColor(0, 1, 0, 1);
                System.out.println(body.getShape().getCenter());
                shapeRenderer.circle((float) body.getShape().getCenter().getX() * Sandbox.getInstance().getScale(),
                        (float) (body.getShape().getCenter().getY() * Sandbox.getInstance().getScale()),
                        (float) ((Circle) body.getShape()).getRadius());
            } else if (body.getShape() instanceof Polygon) {
                shapeRenderer.setColor(0.6f, 0, 1, 1);
                shapeRenderer.polygon(MiscUtil.flattenVectorsToPoints(((Polygon) body.getShape()).getVertices()));
                //System.out.println(Arrays.toString(MiscUtil.flattenVectorsToPoints(((Polygon) body.getShape()).getVertices())));
            }
        }

        shapeRenderer.end();

        hudCamera.update();
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, "fps=" + Gdx.graphics.getFramesPerSecond(), 0, hudCamera.viewportHeight);
        long avg_phys = MiscUtil.getAverage(Sandbox.getInstance().getPhysicsExecutionTime());
        font.draw(spriteBatch, String.format("phys_avg=%s ms, %s ns", decimalFormat.format(avg_phys * 10e-6), avg_phys), 0, hudCamera.viewportHeight - font.getLineHeight());
        font.draw(spriteBatch, String.format("objs=%s", Sandbox.getInstance().getSimulation().getBodyManager().getBodies().size()), 0, hudCamera.viewportHeight - font.getLineHeight() * 2);
        //font.draw(spriteBatch, "Lower left", 0, font.getLineHeight());
        spriteBatch.end();
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
        spriteBatch.dispose();
        font.dispose();
    }
}
