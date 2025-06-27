package codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value.length() < 10) {
            context.buildConstraintViolationWithTemplate("Password must be at least 10 characters long.").addConstraintViolation();
            return false;
        }

        boolean hasUpperCase=false, hasLowerCase=false, hasSpecialCharacter=false;
        for (char ch: value.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpperCase=true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase=true;
            } else if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)) {
                hasSpecialCharacter=true;
            }

            if (hasLowerCase && hasUpperCase && hasSpecialCharacter) {
                return true;
            }
        }

        if (!hasLowerCase) {
            context.buildConstraintViolationWithTemplate("Password should have at least 1 small case character").addConstraintViolation();
            return false;
        }

        if (!hasUpperCase) {
            context.buildConstraintViolationWithTemplate("Password should have at least 1 uppercase character").addConstraintViolation();
            return false;
        }

        context.buildConstraintViolationWithTemplate("Password should have at least 1 special character").addConstraintViolation();
        return false;
    }
}
