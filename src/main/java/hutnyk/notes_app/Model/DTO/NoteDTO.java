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
public class NoteDTO {
    private Long id;
    private String title;
    private String description;
    private Date finishDate;
    private Long userId;
    private Set<Long> statusSetId;
}
