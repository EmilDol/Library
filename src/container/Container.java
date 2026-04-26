package container;

import context.Session;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private Container() {
    }

    private static final Container instance = new Container();

    public static Container getInstance() {
        return instance;
    }

    private final Map<Class<?>, Class<?>> repositories = new HashMap<>();

    public <T> void addRepository(Class<T> contract, Class<? extends T> implementation) {
        repositories.put(contract, implementation);
    }

    public <T> T getRepository(Class<T> contract) {
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

    private final Session session = new Session();

    public Session getSession() {
        return session;
    }

    private boolean loadedFile;

    public boolean isLoadedFile() {
        return loadedFile;
    }

    public void setLoadedFile(boolean loadedFile) {
        this.loadedFile = loadedFile;
    }
}
