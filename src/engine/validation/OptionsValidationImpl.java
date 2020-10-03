package engine.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class OptionsValidationImpl implements ConstraintValidator<OptionsValidation, List<String>> {
    public void initialize(OptionsValidation constraint) {
    }

    public boolean isValid(List<String> obj, ConstraintValidatorContext context) {
        return obj.size() >= 2;
    }
}
