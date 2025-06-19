package codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions;

public class DepartmentExistsException extends RuntimeException{
    public DepartmentExistsException (String message) {
        super(message);
    }
}
