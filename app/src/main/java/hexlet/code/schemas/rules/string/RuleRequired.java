package hexlet.code.schemas.rules.string;

import hexlet.code.schemas.rules.Rule;

/**
 * Правило с ограничением, которое не позволяет использовать
 * null или пустую строку в качестве значения.
 * Без установки правила данные в строке
 * не обязательны к заполнению
 */
public final class RuleRequired implements Rule<String> {
    /** Создание объекта с правилом. */
    public RuleRequired() {
    }

    /**
     * Проверка строки на null или пустоту.
     *
     * @param value входные данные в виде строки
     * @return если строка пустая или содержит null, то false, иначе true
     */
    @Override
    public boolean check(final String value) {
        if (value == null) {
            return false;
        }

        return !value.isEmpty();
    }
}
