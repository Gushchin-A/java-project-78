package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/** Класс для проверки объектов типа Map. */
public final class MapSchema extends BaseSchema<Map<?, ?>> {

    /** Создание объекта схемы. */
    public MapSchema() {
    }

    /**
     * Правило с ограничением, которое не позволяет использовать
     * null в качестве значения.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public MapSchema required() {
        Predicate<Map<?, ?>> requiredRule = Objects::nonNull;
        this.addRule("required", requiredRule);

        return this;
    }

    /**
     * Правило с ограничением на размер мапы.
     * Количество пар ключ-значений в объекте Map
     * должно быть равно заданному параметру.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @param size допустимый размер мапы
     * @return возвращает текущую схему
     */
    public MapSchema sizeof(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException(
                    "Size should be >= 0");
        }

        Predicate<Map<?, ?>> sizeOfRule = s -> s.size() == size;
        this.addRule("sizeof", sizeOfRule);

        return this;
    }

    /**
     * Правило создает валидацию значений каждого ключа объекта Map.
     * Каждому свойству объекта Map привязывается свой набор правил.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @param schemas мапа с набором схем под каждый ключ
     * @return возвращает текущую схему
     */
    public MapSchema shape(final Map<String, ? extends BaseSchema<?>> schemas) {
        Predicate<Map<?, ?>> shapeRule = map -> {
            for (String key : schemas.keySet()) {
                @SuppressWarnings("unchecked")
                BaseSchema<Object> schema =
                        (BaseSchema<Object>) schemas.get(key);
                Object value = map.get(key);

                if (!schema.isValid(value)) {
                    return false;
                }
            }

            return true;
        };

        this.addRule("shape", shapeRule);

        return this;
    }
}
