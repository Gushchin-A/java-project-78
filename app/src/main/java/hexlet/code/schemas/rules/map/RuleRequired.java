package hexlet.code.schemas.rules.map;

import hexlet.code.schemas.rules.Rule;

import java.util.Map;

/**
 * Правило с ограничением, которое не позволяет использовать
 * null в качестве значения.
 */
public final class RuleRequired implements Rule<Map<?, ?>> {

    /** Создание объекта с правилом. */
    public RuleRequired() {
    }

    /**
     * Проверка значения на null.
     *
     * @param value входные данные в виде числа
     * @return если значение содержит null, то false, иначе true
     */
    @Override
    public boolean check(final Map<?, ?> value) {
        return value != null;
    }
}
