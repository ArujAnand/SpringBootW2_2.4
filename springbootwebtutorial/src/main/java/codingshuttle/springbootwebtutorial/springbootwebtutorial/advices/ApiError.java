package codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data //lombok annotation to generate boilerplate code like getters, setters...
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
