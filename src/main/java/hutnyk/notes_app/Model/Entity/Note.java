package hutnyk.notes_app.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;


//    @FutureOrPresent(message = "Finish date must be in the future or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(
            name = "note_status",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id")
    )
    private Set<Status> statusSet;

    public Note(Set<Status> statusSet, User user, Date finishDate, String title, String description) {
        this.statusSet = statusSet;
        this.user = user;
        this.finishDate = finishDate;
        this.title = title;
        this.description = description;
    }
}
