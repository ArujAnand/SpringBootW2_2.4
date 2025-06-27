package codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator<EmployeeAgeValidation, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        return age >= 18;
    }
}