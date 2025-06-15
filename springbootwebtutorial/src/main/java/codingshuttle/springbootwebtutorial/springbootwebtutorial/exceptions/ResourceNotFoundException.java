package codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException (String messagge) {
        super(messagge);
    }
}
