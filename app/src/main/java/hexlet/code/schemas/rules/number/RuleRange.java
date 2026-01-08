package hexlet.code.schemas.rules.number;

import hexlet.code.schemas.rules.Rule;

/**
 * Правило с допустимым диапазононом, в который должно
 * попадать значение числа включая границы.
 */
public final class RuleRange implements Rule<Number> {
    /** Левая граница. */
    private final int leftRange;
    /** Правая граница. */
    private final int rightRange;

    /**
     * Создание объекта с правилом.
     *
     * @param left левая граница
     * @param right правая граница
     */
    public RuleRange(final int left, final int right) {
        if (left > right) {
            throw new IllegalArgumentException(
                    "Range left cannot be greater right");
        }

        this.leftRange = left;
        this.rightRange = right;
    }

    /**
     * Проверка диапазона.
     *
     * @param value входные данные в виде числа
     * @return если значение находится в рамках границ,
     * то true, иначе false
     */
    @Override
    public boolean check(final Number value) {

        return value.doubleValue() >= leftRange
                && value.doubleValue() <= rightRange;
    }
}
