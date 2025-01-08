package hutnyk.notes_app.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusDTO {
    private Long id;
    private String name;
    private Set<Long> noteSetId;
}
