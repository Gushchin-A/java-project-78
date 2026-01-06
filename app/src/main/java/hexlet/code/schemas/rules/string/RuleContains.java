package hexlet.code.schemas.rules.string;

import hexlet.code.schemas.rules.Rule;

/** Правило для проверки наличия подстроки в строке. */
public final class RuleContains implements Rule<String> {
    /** Подстрока, которая должна присутствовать в строке. */
    private final String substring;

    /**
     * Создание объекта с правилом.
     *
     * @param target подстрока, которая должна содержаться в строке
     */
    public RuleContains(final String target) {
        this.substring = target;
    }

    /**
     * Проверка наличия подстроки в строке.
     *
     * @param value строка для поиска
     * @return если строка содержит подстроку, то true, иначе false
     */
    @Override
    public boolean check(final String value) {
        return value.contains(substring);
    }
}
