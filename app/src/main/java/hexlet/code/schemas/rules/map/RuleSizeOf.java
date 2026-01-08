package hexlet.code.schemas.rules.map;

import hexlet.code.schemas.rules.Rule;

import java.util.Map;

/**
 * Правило с ограничением на размер мапы.
 * Количество пар ключ-значений в объекте Map
 * должно быть равно заданному параметру
 */
public final class RuleSizeOf implements Rule<Map<?, ?>> {
    /** Допустимый размер мапы. */
    private final int acceptSize;

    /**
     * Создание объекта с правилом.
     *
     * @param size допустимый размер мапы
     */
    public RuleSizeOf(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException(
                    "Size should be >= 0");
        }

        this.acceptSize = size;
    }

    /**
     * Проверка размера мапы.
     *
     * @param value проверяемый объект Map
     * @return если размер мапы равен заданному значению,
     * то true, иначе false
     */
    @Override
    public boolean check(final Map<?, ?> value) {
        return value.size() == acceptSize;
    }
}
