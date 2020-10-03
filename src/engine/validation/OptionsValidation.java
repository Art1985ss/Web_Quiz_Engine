package engine.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = OptionsValidationImpl.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionsValidation {
    String message() default "Quiz should contain at least 2 options";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
