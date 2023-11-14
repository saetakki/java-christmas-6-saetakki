package christmas.validator;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ValidatorTemplate {
    public static <X extends RuntimeException> void validateTemplate(
            Predicate<String> condition,
            String input,
            Supplier<X> exceptionSupplier) {
        if (!condition.test(input)) {
            throw exceptionSupplier.get();
        }
    }
}
