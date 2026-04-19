package container;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private static final Map<Class<?>, Class<?>> repositories = new HashMap<>();

    public static <T> void addRepository(Class<T> contract, Class<? extends T> implementation) {
        repositories.put(contract, implementation);
    }

    public static <T> T getRepository(Class<T> contract) {
        Class<?> impl = repositories.get(contract);
        if (impl == null) {
            throw new RuntimeException("No mapping found for " + contract);
        }

        try {
            return contract.cast(impl.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
