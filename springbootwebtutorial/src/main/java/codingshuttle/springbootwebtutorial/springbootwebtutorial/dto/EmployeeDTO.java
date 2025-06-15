package codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeAgeValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    long id;
    @NotNull(message = "required field in Employee: name")
    private String name;
    private String email;
    @EmployeeAgeValidation
    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;
}
