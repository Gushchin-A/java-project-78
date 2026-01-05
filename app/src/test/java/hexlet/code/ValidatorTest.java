package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @DisplayName("Создание объектов: Validator, StringSchema")
    @Test
    void testCreateValidatorAndStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        LinkedHashMap<String, Rule<String>> map = schema.getRulesMap();
        assertTrue(map.isEmpty());
    }

    @DisplayName("Проверка правила StringSchema: required()")
    @Test
    void testCheckRuleRequiredStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("сидим с бобром за столом"));
    }

    @DisplayName("Проверка правила StringSchema: minLength()")
    @Test
    void testCheckRuleMinLengthStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        String s = "сидим с бобром за столом";
        int lengthS = s.length();

        schema.minLength(lengthS);
        assertTrue(schema.isValid("сидим с бобром за столом"));
        schema.minLength(lengthS + 1);
        assertFalse(schema.isValid("сидим с бобром за столом"));

        schema.required();
        assertFalse(schema.isValid("сидим с бобром за столом"));

        schema.minLength(lengthS);
        assertTrue(schema.isValid("сидим с бобром за столом"));
    }

    @DisplayName("Проверка правила StringSchema: contains()")
    @Test
    void testCheckRuleContainsStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();
        schema.contains("столом");
        assertTrue(schema.isValid("сидим с бобром за столом"));
        schema.contains("столллом");
        assertFalse(schema.isValid("сидим с бобром за столом"));
    }

    @DisplayName("Проверка всех правил StringSchema одновременно")
    @Test
    void testCheckRuleAllStringSchema() {
        Validator v = new Validator();
        StringSchema schema1 = v.string();

        String s1 = "сидим с бобром за столом";
        int lengthS1 = s1.length();

        schema1.minLength(lengthS1).contains("сидим");
        assertTrue(schema1.isValid(s1));

        schema1.required().contains("бобром");
        assertTrue(schema1.isValid(s1));

        schema1.required().minLength(lengthS1).contains("сидим");
        assertTrue(schema1.isValid(s1));

        schema1.required()
                .minLength(lengthS1)
                .contains("сидим")
                .contains("кабаном");
        assertFalse(schema1.isValid(s1));

        schema1.required()
                .minLength(lengthS1)
                .minLength(lengthS1 + 1)
                .contains("сидим");
        assertFalse(schema1.isValid(s1));

        StringSchema schema2 = v.string();
        schema2.contains("сидим").minLength(1).required();
        assertTrue(schema2.isValid(s1));

        StringSchema schema3 = v.string();
        String s2 = "";

        assertTrue(schema3.isValid(s2));

        schema3.contains("hello");
        assertTrue(schema3.isValid(s2));
    }
}
