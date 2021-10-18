package cf.searchforme.sandbox;

import cf.searchforme.sandbox.libgdx.Sandbox;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import lombok.SneakyThrows;

public class DesktopLauncher {

    @SneakyThrows
    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setIdleFPS(60);
        configuration.useVsync(true);
        configuration.setTitle("Sandbox");

        configuration.setWindowedMode(960, 640);

        new Lwjgl3Application(new Sandbox(), configuration);
    }

}
