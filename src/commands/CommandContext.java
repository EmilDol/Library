package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandContext {
    private final Map<String, Object> data = new HashMap<>();

    public <T> void put(String key, T value) {
        data.put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }
}
