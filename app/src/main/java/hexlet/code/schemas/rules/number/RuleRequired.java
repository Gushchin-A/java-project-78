package hexlet.code.schemas.rules.number;

import hexlet.code.schemas.rules.Rule;

/**
 * Правило с ограничением, которое не позволяет использовать
 * null в качестве значения.
 */
public final class RuleRequired implements Rule<Number> {
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
    public boolean check(final Number value) {
        return value != null;
    }
}
