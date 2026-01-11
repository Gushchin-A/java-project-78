package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchemaFunc<T> {
    protected Map<String, Predicate<T>> rules = new LinkedHashMap<>();

    protected final void addRule(String name, Predicate<T> rule) {
        rules.put(name, rule);
    }

    public final boolean isValid(T value) {
        if (value == null) {
            return !rules.containsKey("required");
        }

        for (String key : rules.keySet()) {
            if (!rules.get(key).test(value)) {
                return false;
            }
        }

        return true;
    }

    public Map<String, Predicate<T>> getRules() {
        return rules;
    }
}
