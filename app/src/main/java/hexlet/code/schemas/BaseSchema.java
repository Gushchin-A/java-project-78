package hexlet.code.schemas;

/**
 * Абстрактный класс с общим контрактом для проверки значений.
 *
 * @param <T> тип проверяемого значения
 */
public abstract class BaseSchema<T> {

    /**
     * Валидация входных данных.
     *
     * @param value проверяемое значение
     * @return результат проверки
     */
    public abstract boolean isValid(T value);
}
