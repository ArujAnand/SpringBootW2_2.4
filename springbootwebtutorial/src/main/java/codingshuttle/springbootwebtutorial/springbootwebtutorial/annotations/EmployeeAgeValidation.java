package codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //checked at (is of 3 types)
@Target({ElementType.FIELD, ElementType.PARAMETER}) //to be applied on
@Constraint(validatedBy = {EmployeeAgeValidator.class}) //validator function
public @interface EmployeeAgeValidation {

    //error message
    String message() default "Age has to be greater than 17";

    //represents group of constraints
    Class<?>[] groups() default { };

    //represents additional information about annotation
    Class<? extends Payload>[] payload() default { };
}
