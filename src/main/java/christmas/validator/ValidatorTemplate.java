package christmas.validator;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ValidatorTemplate {

    //유효성 검사 로직을 템플릿 화
    // RuntimeException 또는 그 하위 클래스를 Supplier를 Supplier를 통해 공급
    // String을 받아 true/false를 반환하는 Predicate로 input에 대한 테스트를 진행하고
    // 해당 컨디션을 통과하지 못하면 exception을 발생시킴
    public static <X extends RuntimeException> void validateTemplate(
            Predicate<String> condition,
            String input,
            Supplier<X> exceptionSupplier) {
        if (!condition.test(input)) {
            throw exceptionSupplier.get();
        }
    }
}
