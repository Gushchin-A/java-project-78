package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchemaFunc extends BaseSchemaFunc<String> {

    public StringSchemaFunc() {
    }

    public StringSchemaFunc required() {
        Predicate<String> requiredRule = s -> !s.isEmpty();
        this.addRule("required", requiredRule);

        return this;
    }

    public StringSchemaFunc minLength(final int minLength) {
        Predicate<String> minLengthRule = s -> s.length() >= minLength;
        this.addRule("minLength", minLengthRule);

        return this;
    }

    public StringSchemaFunc contains(final String target) {
        Predicate<String> containsRule = s -> s.contains(target);
        this.addRule("contains", containsRule);

        return this;
    }
}
