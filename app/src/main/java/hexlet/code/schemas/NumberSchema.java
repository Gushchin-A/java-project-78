package hexlet.code.schemas;

import hexlet.code.schemas.rules.Rule;
import hexlet.code.schemas.rules.number.RulePositive;
import hexlet.code.schemas.rules.number.RuleRange;
import hexlet.code.schemas.rules.number.RuleRequired;

import java.util.LinkedHashMap;

/** Класс для проверки типов данных Number. */
public final class NumberSchema extends BaseSchema<Number> {
    /** Коллекция для добавления правил в объект схему. */
    private final LinkedHashMap<String, Rule<Number>> rulesMap =
            new LinkedHashMap<>();

    /** Создание объекта схемы. */
    public NumberSchema() {
    }

    /**
     * Правило с ограничением использования null в качестве значения.
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public NumberSchema required() {
        rulesMap.put("required", new RuleRequired());

        return this;
    }

    /**
     * Проверка знака числа.
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @return возвращает текущую схему
     */
    public NumberSchema positive() {
        rulesMap.put("positive", new RulePositive());

        return this;
    }

    /**
     * Проверка допустимого диапазона.
     * Метод создает объект-правило и добавляет его в LinkedHashMap.
     *
     * @param left левая граница
     * @param right правая граница
     * @return возвращает текущую схему
     */
    public NumberSchema range(final int left, final int right) {
        rulesMap.put("range", new RuleRange(left, right));

        return this;
    }

    /**
     * Передача LinkedHashMap с набором объектов-правил.
     *
     * @return возвращает LinkedHashMap
     */
    public LinkedHashMap<String, Rule<Number>> getRulesMap() {
        return rulesMap;
    }

    /**
     * Валидация числа путем перебора LinkedHashMap
     * с объектами-правилами.
     *
     * @param value входные данные в виде числа
     * @return если число валидно всем правилам,
     * то true, иначе false
     */
    @Override
    public boolean isValid(final Number value) {
        if (!rulesMap.containsKey("required") && value == null) {
            return true;
        }

        if (rulesMap.containsKey("required") && value == null) {
            return false;
        }

        for (String key : rulesMap.keySet()) {
            if (!rulesMap.get(key).check(value)) {
                return false;
            }
        }

        return true;
    }
}
