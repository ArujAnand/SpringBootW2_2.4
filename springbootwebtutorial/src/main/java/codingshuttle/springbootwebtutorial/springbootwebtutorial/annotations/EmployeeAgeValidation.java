package codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeAgeValidator.class})
public @interface EmployeeAgeValidation {
    String message() default "Have to be certain age";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
