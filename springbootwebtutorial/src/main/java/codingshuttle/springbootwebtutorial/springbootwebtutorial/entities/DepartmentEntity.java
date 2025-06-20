package codingshuttle.springbootwebtutorial.springbootwebtutorial.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name of the department cannot be blank")
    @NotNull(message = "Name of department cannot be null")
    private String title;
    @JsonProperty("isActive")
    private boolean isActive;
    @CreationTimestamp
    private LocalDate createdAt;

    public void settitle (String title) {
        this.title = (title != null) ? title.toUpperCase() : null;
    }
}
