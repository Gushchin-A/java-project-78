package hexlet.code.schemas;

import hexlet.code.schemas.rules.Rule;
import hexlet.code.schemas.rules.string.RuleContains;
import hexlet.code.schemas.rules.string.RuleMinLength;
import hexlet.code.schemas.rules.string.RuleRequired;

import java.util.LinkedHashMap;

/** Класс для проверки типов данных String. */
public final class StringSchema extends BaseSchema<String> {
    /** Коллекция для добавления правил в объект схему. */
    private final LinkedHashMap<String, Rule<String>> rulesMap =
            new LinkedHashMap<>();

    /** Создание объекта схемы. */
    public StringSchema() {
    }

    /**
     * Правило с ограничением использования null или пустоты в значении.
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public StringSchema required() {
        rulesMap.put("required", new RuleRequired());

        return this;
    }

    /**
     * Правило минимальной длины строки.
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @param length минимальная длина строки
     * @return возвращает текущую схему
     */
    public StringSchema minLength(final int length) {
        rulesMap.put("minLength", new RuleMinLength(length));

        return this;
    }

    /**
     * Правило для проверки наличия подстроки в строке.
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @param target подстрока, которая должна содержаться в строке
     * @return возвращает текущую схему
     */
    public StringSchema contains(final String target) {
        rulesMap.put("contains", new RuleContains(target));

        return this;
    }

    /**
     * Передача LinkedHashMap с набором объектов-правил.
     *
     * @return возвращает LinkedHashMap
     */
    public LinkedHashMap<String, Rule<String>> getRulesMap() {
        return rulesMap;
    }

    /**
     * Валидация строки путем перебора LinkedHashMap
     * с объектами-правилами.
     *
     * @param value входные данные в виде строки
     * @return если строка валидна всем правилам,
     * то true, иначе false
     */
    @Override
    public boolean isValid(final String value) {
        boolean empty = value == null || value.isEmpty();
        if (!rulesMap.containsKey("required") && empty) {
            return true;
        }

        for (String key : rulesMap.keySet()) {
            if (!rulesMap.get(key).check(value)) {
                return false;
            }
        }

        return true;
    }
}
