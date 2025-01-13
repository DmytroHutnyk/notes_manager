package hutnyk.notes_app.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteRequestDTO {
    private String title;
    private List<String> statuses;
    private LocalDate finishDate;

}
