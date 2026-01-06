package hexlet.code.schemas.rules.number;

import hexlet.code.schemas.rules.Rule;

/** Правило c проверкой знака в значении. Число должно быть положительным. */
public final class RulePositive implements Rule<Number> {
    /** Создание объекта с правилом. */
    public RulePositive() {
    }

    /**
     * Проверка знака числа.
     *
     * @param value входные данные в виде числа
     * @return если значение положительное, то true, иначе false
     */
    @Override
    public boolean check(final Number value) {
        return value.doubleValue() > 0;
    }
}
