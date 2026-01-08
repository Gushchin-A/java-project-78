package hexlet.code.schemas.rules.map;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.rules.Rule;

import java.util.Map;

/**
 * Правило позволяет создать валидацию для значений каждого ключа объекта Map.
 * Каждому свойству объекта Map привязывается свой набор правил.
 */
public final class RuleShape implements Rule<Map<?, ?>> {
    /** Набор схем для каждого ключа. */
    private final Map<String, ? extends BaseSchema<?>> mapSchemas;

    /**
     * Создание объекта с правилом.
     *
     * @param schemas мапа с набором схем под каждый ключ
     */
    public RuleShape(final Map<String, ? extends BaseSchema<?>> schemas) {
        this.mapSchemas = schemas;
    }

    /**
     * Вызов и валидация каждого значения ключа привязанной схемой.
     *
     * @param value проверяемый объект Map
     * @return если валидация всех схем положительная, то true, иначе false
     */
    @Override
    public boolean check(final Map<?, ?> value) {
        for (String key : mapSchemas.keySet()) {
            @SuppressWarnings("unchecked")
            BaseSchema<Object> schema =
                    (BaseSchema<Object>) mapSchemas.get(key);

            Object v = value.get(key);

            if (!schema.isValid(v)) {
                return false;
            }
        }

        return true;
    }
}
