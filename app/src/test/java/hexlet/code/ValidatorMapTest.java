package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorMapTest {

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

    @DisplayName("Проверка правил MapSchema required() и sizeof()")
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

    @DisplayName("Проверка правила MapSchema: shape()")
    @Test
    void testCheckRuleShapeMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> people = new HashMap<>();
        people.put("firstName", "John");
        people.put("lastName", "Smith");
        assertTrue(schema.isValid(people));

        Map<String, String> peopleWithNull = new HashMap<>();
        peopleWithNull.put("firstName", "John");
        peopleWithNull.put("lastName", null);
        assertFalse(schema.isValid(peopleWithNull));

        Map<String, String> peopleShortLatName = new HashMap<>();
        peopleShortLatName.put("firstName", "John");
        peopleShortLatName.put("lastName", "C");
        assertFalse(schema.isValid(peopleShortLatName));
    }

    @DisplayName("Проверка правила MapSchema: shape() и микс типов схем")
    @Test
    void testCheckRuleShapeAndMixTypeSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        final int weightMin = 45;
        final int weightMax = 120;
        final int weight70 = 70;
        final int weight50 = 50;
        final int weight77 = 77;
        final int weight90 = 90;

        Map<String, BaseSchema<?>> schemasJumping = new HashMap<>();
        schemasJumping.put("firstName", v.string().required());
        schemasJumping.put("weight", v.number().range(weightMin, weightMax));

        Map<String, Object> peopleJump = new HashMap<>();
        peopleJump.put("firstName", "John");
        peopleJump.put("weight", weight70);

        schema.shape(schemasJumping);
        assertTrue(schema.isValid(peopleJump));

        schemasJumping.put("data", v.map().sizeof(2));
        peopleJump.put("data", new HashMap<>(Map.of(
                "Alex", weight50,
                "Rob", weight77
        )));

        schema.shape(schemasJumping);
        assertTrue(schema.isValid(peopleJump));

        @SuppressWarnings("unchecked")
        Map<String, Integer> dataMap =
                (Map<String, Integer>) peopleJump.get("data");
        dataMap.put("Andy", weight90);
        peopleJump.put("data", dataMap);

        assertFalse(schema.isValid(peopleJump));

        MapSchema internalSchema = v.map();
        Map<String, BaseSchema<?>> internalSchemas = new HashMap<>();
        internalSchemas.put("Alex", v.number().positive());
        internalSchemas.put("Rob", v.number().range(weightMin, weightMax));

        internalSchema.shape(internalSchemas);
        assertTrue(internalSchema.isValid(
                (Map<String, Integer>) peopleJump.get("data")));

    }
}
