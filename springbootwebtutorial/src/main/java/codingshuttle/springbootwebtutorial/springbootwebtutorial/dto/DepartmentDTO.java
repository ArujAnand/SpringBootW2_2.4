package codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name of the department cannot be blank")
    @NotNull(message = "Name of department cannot be empty")
    private String title;
    @JsonProperty("isActive")
    private boolean isActive;
    @CreationTimestamp
    private LocalDate createdAt;

    public void settitle (String title) {
        this.title = (title != null) ? title.toUpperCase() : null;
    }
}
