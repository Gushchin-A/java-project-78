package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

/**
 * Валидатор данных.
 * Класс создает объекты-схемы для валидации разных типов данных.
 */
public final class Validator {

    /** Создание объекта валидатора. */
    public Validator() {
    }

    /**
     * Создание схемы для проверки типа данных String.
     *
     * @return возвращает объект StringSchema
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * Создание схемы для проверки типа данных Number.
     *
     * @return возвращает объект NumberSchema
     */
    public NumberSchema number() {
        return new NumberSchema();
    }
}
