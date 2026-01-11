package hexlet.code.schemas;

import java.util.function.Predicate;

/** Класс для проверки типов данных String. */
public final class StringSchema extends BaseSchema<String> {

    /** Создание объекта схемы. */
    public StringSchema() {
    }

    /**
     * Правило с ограничением, которое не позволяет использовать
     * null или пустую строку в качестве значения.
     * Без установки правила данные в строке не обязательны к заполнению.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public StringSchema required() {
        Predicate<String> requiredRule = s -> !s.isEmpty();
        this.addRule("required", requiredRule);

        return this;
    }

    /**
     * Правило для проверки минимальной длины строки.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @param minLength минимальная длина строки
     * @return возвращает текущую схему
     */
    public StringSchema minLength(final int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException(
                    "Length should be >= 0");
        }

        Predicate<String> minLengthRule = s -> s.length() >= minLength;
        this.addRule("minLength", minLengthRule);

        return this;
    }

    /**
     * Правило для проверки наличия подстроки в строке.
     * Метод создает функцию-правило и добавляет ее в LinkedHashMap.
     *
     * @param target подстрока, которая должна содержаться в строке
     * @return возвращает текущую схему
     */
    public StringSchema contains(final String target) {
        Predicate<String> containsRule = s -> s.contains(target);
        this.addRule("contains", containsRule);

        return this;
    }
}
