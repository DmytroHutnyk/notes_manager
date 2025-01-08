package hutnyk.notes_app.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@Entity
public class Status {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Status name cannot be blank")
    @Size(max = 50, message = "Status name cannot exceed 50 characters")
    private String name;

    @ManyToMany(mappedBy = "statusSet")
    private Set<Note> note;

    public Status(Set<Note> note, String name) {
        this.note = note;
        this.name = name;
    }
}
