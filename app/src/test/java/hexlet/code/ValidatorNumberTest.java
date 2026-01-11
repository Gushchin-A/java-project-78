package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorNumberTest {
    /** Тестовое число. */
    private static final int NUM_99 = 99;
    /** Тестовое число. */
    private static final int NUM_11 = 11;
    /** Тестовое число. */
    private static final int NUM_NEG_11 = -11;
    /** Тестовое число. */
    private static final int NUM_43 = 43;
    /** Тестовое число. */
    private static final int NUM_42 = 42;
    /** Тестовое число. */
    private static final int NUM_41 = 41;
    /** Тестовое число. */
    private static final int NUM_40 = 40;
    /** Тестовое число. */
    private static final int NUM_NEG_41 = -41;
    /** Тестовое число. */
    private static final int NUM_0 = 0;
    /** Тестовое число. */
    private static final int NUM_1 = 1;
    /** Тестовое число. */
    private static final int NUM_NEG_2 = -2;

    @DisplayName("Проверка правила NumberSchema: required()")
    @Test
    void testCheckRuleRequiredNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(NUM_99));
    }

    @DisplayName("Проверка правила NumberSchema: positive()")
    @Test
    void testCheckRulePositiveNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(NUM_NEG_11));

        schema.positive();

        assertFalse(schema.isValid(NUM_NEG_11));
        assertTrue(schema.isValid(NUM_11));
    }

    @DisplayName("Проверка правила NumberSchema: range()")
    @Test
    void testCheckRuleRangeNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(NUM_42));

        schema.range(NUM_41, NUM_42);

        assertTrue(schema.isValid(NUM_42));
        assertTrue(schema.isValid(NUM_41));
        assertFalse(schema.isValid(NUM_43));
        assertFalse(schema.isValid(NUM_40));
        assertFalse(schema.isValid(NUM_99));
        assertFalse(schema.isValid(NUM_NEG_11));

        schema.range(NUM_NEG_41, NUM_42);

        assertTrue(schema.isValid(NUM_NEG_11));
        assertTrue(schema.isValid(NUM_NEG_41));
        assertTrue(schema.isValid(NUM_42));

        schema.positive();

        assertFalse(schema.isValid(NUM_NEG_11));
        assertFalse(schema.isValid(NUM_NEG_41));
        assertFalse(schema.isValid(NUM_0));
        assertTrue(schema.isValid(NUM_42));
        assertTrue(schema.isValid(NUM_1));
    }

    @DisplayName("Проверка всех правил NumberSchema одновременно")
    @Test
    void testCheckRuleAllNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(NUM_42));

        schema.range(NUM_0, NUM_42).positive();

        assertTrue(schema.isValid(NUM_42));
        assertFalse(schema.isValid(NUM_0));
        assertTrue(schema.isValid(null));

        schema.range(NUM_NEG_2, NUM_42).positive().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(NUM_99));
        assertTrue(schema.isValid(NUM_42));
        assertTrue(schema.isValid(NUM_11));

        schema.required().positive().range(NUM_1, NUM_1);
        assertTrue(schema.isValid(NUM_1));

        schema.required().positive().range(NUM_0, NUM_0);
        assertFalse(schema.isValid(NUM_0));
    }
}
