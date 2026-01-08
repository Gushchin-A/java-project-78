package hexlet.code.schemas;

import hexlet.code.schemas.rules.Rule;
import hexlet.code.schemas.rules.map.RuleRequired;
import hexlet.code.schemas.rules.map.RuleShape;
import hexlet.code.schemas.rules.map.RuleSizeOf;

import java.util.LinkedHashMap;
import java.util.Map;

/** Класс для проверки объектов типа Map. */
public final class MapSchema extends BaseSchema<Map<?, ?>> {
    /** Коллекция для добавления правил в объект схему. */
    private final LinkedHashMap<String, Rule<Map<?, ?>>> rulesMap =
            new LinkedHashMap<>();

    /** Создание объекта схемы. */
    public MapSchema() {
    }

    /**
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public MapSchema required() {
        rulesMap.put("required", new RuleRequired());

        return this;
    }

    /**
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @param size допустимый размер мапы
     * @return возвращает текущую схему
     */
    public MapSchema sizeof(final int size) {
        rulesMap.put("sizeof", new RuleSizeOf(size));

        return this;
    }

    /**
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @param schemas мапа с набором схем под каждый ключ
     * @return возвращает текущую схему
     */
    public MapSchema shape(final Map<String, ? extends BaseSchema<?>> schemas) {
        rulesMap.put("shape", new RuleShape(schemas));

        return this;
    }

    /**
     * Передача LinkedHashMap с набором объектов-правил.
     *
     * @return возвращает LinkedHashMap
     */
    public LinkedHashMap<String, Rule<Map<?, ?>>> getRulesMap() {
        return rulesMap;
    }

    /**
     * Валидация значения путем перебора LinkedHashMap.
     *
     * @param value входные данные в виде объекта Map
     * @return если объект валиден всем правилам,
     * то true, иначе false
     */
    @Override
    public boolean isValid(final Map<?, ?> value) {
        if (value == null) {
            return !rulesMap.containsKey("required");
        }

        for (String key : rulesMap.keySet()) {
            if (!rulesMap.get(key).check(value)) {
                return false;
            }
        }

        return true;
    }
}
