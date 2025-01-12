package hutnyk.notes_app.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedNoteDTO {
    private Long id;
    private String title;
    private String description;
    private Date finishDate;
    private String username;
    private String email;
    private Set<String> statuses;
}
