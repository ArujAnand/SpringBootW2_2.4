package codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator<EmployeeAgeValidation, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        if (age < 18)
            return false;
        return true;
    }
}