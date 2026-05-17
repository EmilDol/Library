package commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Контекстен клас за съхранение и извличане на данни, необходими за изпълнение на команди.
 * @author Емил Долчинков
 */
public class CommandContext {
    private final Map<String, Object> data = new HashMap<>();

    /**
     * Съхранява стойност в контекста.
     * @param key   Ключът, свързан със стойността.
     * @param value Стойността за съхранение.
     * @param <T>   Типът на стойността.
     */
    public <T> void put(String key, T value) {
        data.put(key, value);
    }

    /**
     * Извлича стойност от контекста.
     * @param key  Ключът, свързан със стойността.
     * @param type Очакваният клас на типа на стойността.
     * @param <T>  Типът на стойността.
     * @return Извлечената стойност, преобразувана към указания тип.
     */
    public <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }
}
