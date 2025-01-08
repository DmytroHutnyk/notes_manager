package hutnyk.notes_app.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@ToString
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
    private Set<Note> noteSet;

    public Status(Set<Note> noteSet, String name) {
        this.noteSet = noteSet;
        this.name = name;
    }
}
