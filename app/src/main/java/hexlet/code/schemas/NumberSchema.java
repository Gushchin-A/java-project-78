package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

/** Класс для проверки типов данных Number. */
public final class NumberSchema extends BaseSchema<Number> {

    /** Создание объекта схемы. */
    public NumberSchema() {
    }

    /**
     * Правило с ограничением, которое не позволяет использовать
     * null в качестве значения.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public NumberSchema required() {
        Predicate<Number> requiredRule = Objects::nonNull;
        this.addRule("required", requiredRule);

        return this;
    }

    /**
     * Правило c проверкой знака в значении. Число должно быть положительным.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public NumberSchema positive() {
        Predicate<Number> positiveRule = n -> n.doubleValue() > 0;
        this.addRule("positive", positiveRule);

        return this;
    }

    /**
     * Правило с допустимым диапазононом, в который должно
     * попадать значение числа включая границы.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @param left левая граница
     * @param right правая граница
     * @return возвращает текущую схему
     */
    public NumberSchema range(final int left, final int right) {
        if (left > right) {
            throw new IllegalArgumentException(
                    "Range left cannot be greater right");
        }

        Predicate<Number> rangeRule = n -> n.doubleValue() >= left
                && n.doubleValue() <= right;
        this.addRule("range", rangeRule);

        return this;
    }
}
