package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.rules.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorMapTest {

    @DisplayName("Создание объектов: Validator, MapSchema")
    @Test
    void testCreateValidatorAndMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        LinkedHashMap<String, Rule<Map<?, ?>>> map = schema.getRulesMap();
        assertTrue(map.isEmpty());
    }

    @DisplayName("Проверка правила MapSchema: required()")
    @Test
    void testCheckRuleRequiredMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        assertTrue(schema.isValid(data));
    }

    @DisplayName("Проверка правила MapSchema: sizeof()")
    @Test
    void testCheckRuleSizeOfMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.required();
        assertFalse(schema.isValid(null));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @DisplayName("Проверка всех правил MapSchema одновременно")
    @Test
    void testCheckRuleAllMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        schema.required().sizeof(2);
        assertFalse(schema.isValid(null));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put(null, null);

        assertTrue(schema.isValid(data));

        data.put("key2", "value2");
        assertFalse(schema.isValid(data));
    }
}
