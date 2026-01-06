package hexlet.code.schemas.rules.string;

import hexlet.code.schemas.rules.Rule;

/** Правило для проверки минимальной длины строки. */
public final class RuleMinLength implements Rule<String> {
    /** Минимальная длина строки. */
    private final int minLength;

    /**
     * Создание объекта с правилом.
     *
     * @param length минимальная длина строки
     */
    public RuleMinLength(final int length) {
        this.minLength = length;
    }

    /**
     * Проверка минимальной длины строки.
     *
     * @param value проверяемая строка
     * @return если длина строки меньше или равна
     * минимальному значению, то true, иначе false
     */
    @Override
    public boolean check(final String value) {
        return value.length() >= minLength;
    }
}
