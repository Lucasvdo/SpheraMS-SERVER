package br.com.spherams.infrastructure.dependency;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private static final Map<Class<?>, Object> registry = new HashMap<>();

    public static <T> void bind(Class<T> clazz, T impl) {
        registry.put(clazz, impl);
    }

    public static <T> T get(Class<T> clazz) {
        Object instance = registry.get(clazz);
        if (instance == null) {
            throw new IllegalStateException("Nenhuma instância registrada para " + clazz.getName());
        }
        return clazz.cast(instance);
    }

    public static void clear() {
        registry.clear();
    }

    public static void injectDependencies(Object target) {
        Class<?> clazz = target.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> fieldType = field.getType();
                Object dependency = registry.get(fieldType);
                if (dependency == null) {
                    throw new IllegalStateException("No dependency found for " + fieldType.getName());
                }
                field.setAccessible(true);
                try {
                    field.set(target, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency: " + fieldType.getName(), e);
                }
            }
        }
    }
}
