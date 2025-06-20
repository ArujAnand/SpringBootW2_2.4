package codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //checked at (is of 3 types)
@Target({ElementType.FIELD, ElementType.PARAMETER}) //to be applied on
@Constraint(validatedBy = {EmployeeAgeValidator.class}) //validator function
public @interface EmployeeAgeValidation {
    String message() default "Age has to be greater than 17";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
