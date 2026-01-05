package hexlet.code;

/**
 * Интерфейс для правил с методом проверки.
 *
 *  @param <T> тип значения, которое проверяется правилом
 */
public interface Rule<T> {

    /** Абстрактный метод для проверки значений.
     *
     * @param value проверяемое значение
     * @return результат проверки
     */
    boolean check(T value);
}
