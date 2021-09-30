package cf.searchforme.physicsengine.engine;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

import static cf.searchforme.physicsengine.engine.util.LambdaExceptionUtil.rethrowConsumer;

@Getter
@Setter
public class ContextualConfiguration {

    private final static Field[] fields = ContextualConfiguration.class.getDeclaredFields();

    private double gravity = 9.80665d;
    private int maxGjkIterations = 30;

    @SneakyThrows
    public void save(File file) {
        Properties properties = new Properties();
        Arrays.asList(fields).forEach(rethrowConsumer(field -> properties.put(field.getName(), field.get(this))));
        properties.store(new FileWriter(file), null);
    }

    @SneakyThrows
    public static ContextualConfiguration fromFile(File file) {
        ContextualConfiguration configuration = new ContextualConfiguration();

        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        Arrays.asList(fields).forEach(rethrowConsumer(field -> field.set(configuration, properties.get(field.getName()))));

        return configuration;
    }
}
