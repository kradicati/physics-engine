package cf.searchforme.engine;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Properties;

import static cf.searchforme.engine.util.LambdaExceptionUtil.rethrowConsumer;

@Getter
@Setter
@ToString
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
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        return fromProperties(properties);
    }

    @SneakyThrows
    public static ContextualConfiguration fromProperties(Properties properties) {
        ContextualConfiguration configuration = new ContextualConfiguration();

        Arrays.stream(fields)
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .forEach(rethrowConsumer(field -> field.set(configuration,
                        getValue((String) properties.get(field.getName())))));

        return configuration;
    }

    private static Object getValue(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ignored) {
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException ignored) {
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ignored) {
        }
        try {
            return Boolean.parseBoolean(str);
        } catch (NumberFormatException ignored) {
        }

        return str;
    }
}
