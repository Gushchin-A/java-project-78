package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Абстрактный класс с общим контрактом для проверки значений.
 *
 * @param <T> тип проверяемого значения
 */
public abstract class BaseSchema<T> {
    /** Коллекция функций-правил. */
    private final Map<String, Predicate<T>> rules = new LinkedHashMap<>();

    /**
     * Метод добавляет функцию-правило в LinkedHashMap.
     *
     * @param name имя проверки
     * @param rule предикат функция с правилом
     */
    protected final void addRule(final String name, final Predicate<T> rule) {
        rules.put(name, rule);
    }

    /**
     * Валидация входных данных.
     *
     * @param value проверяемое значение
     * @return результат проверки
     */
    public final boolean isValid(final T value) {
        if (value == null) {
            return !rules.containsKey("required");
        }

        for (Predicate<T> rule : rules.values()) {
            if (!rule.test(value)) {
                return false;
            }
        }

        return true;
    }
}
