package codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Class that handles exceptions globally
 */
public class GlobalExceptionHandler {
    /**
     * If NoSuchElement Exception occurs globally then this method will  handled it
     * @param exception NoSuchElementException
     * @return Status code with message
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception) {
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }
}
