package container;

import context.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton контейнер за управление на внедряването на зависимости (dependency injection) на хранилища и потребителската сесия.
 * @author Емил Долчинков
 */
public class Container {
    /**
     * Частен конструктор за предотвратяване на инстанциране.
     */
    private Container() {
    }

    private static final Container instance = new Container();

    /**
     * Връща singleton инстанцията на контейнера.
     * @return Инстанцията на Container.
     */
    public static Container getInstance() {
        return instance;
    }

    private final Map<Class<?>, Class<?>> repositories = new HashMap<>();

    /**
     * Добавя мапинг на хранилище към контейнера.
     * @param contract       Класът на интерфейса на хранилището.
     * @param implementation Класът на имплементацията на хранилището.
     * @param <T>            Типът на договора (contract) на хранилището.
     */
    public <T> void addRepository(Class<T> contract, Class<? extends T> implementation) {
        repositories.put(contract, implementation);
    }

    /**
     * Извлича инстанция на хранилище за дадения договор (contract).
     * @param contract Класът на интерфейса на хранилището.
     * @param <T>      Типът на договора на хранилището.
     * @return Инстанция на имплементацията на хранилището.
     * @throws RuntimeException ако не е намерен мапинг или инстанцирането е неуспешно.
     */
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

    /**
     * Връща инстанцията на текущата сесия.
     * @return Инстанцията на Session.
     */
    public Session getSession() {
        return session;
    }

    private boolean loadedFile;
    private String loadedFileName;

    /**
     * Проверява дали файл е текущо зареден в системата.
     * @return true ако файл е зареден, false в противен случай.
     */
    public boolean isLoadedFile() {
        return loadedFile;
    }

    /**
     * Връща името на текущо заредения файл.
     * @return Низ с името на файла.
     */
    public String getLoadedFileName() {
        return loadedFileName;
    }

    /**
     * Нулира състоянието на заредения файл.
     */
    public void unloadFile() {
        this.loadedFile = false;
        this.loadedFileName = "";
    }

    /**
     * Задава състоянието на текущо заредения файл.
     * @param loadedFile Дали файлът е зареден.
     * @param filename   Името на заредения файл.
     */
    public void setLoadedFile(boolean loadedFile, String filename) {
        this.loadedFile = loadedFile;
        this.loadedFileName = filename;
    }
}
