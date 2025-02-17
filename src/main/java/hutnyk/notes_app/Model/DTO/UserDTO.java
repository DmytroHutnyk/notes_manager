package hutnyk.notes_app.Model.DTO;

import hutnyk.notes_app.Model.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Long> notesSetId;
    private String role;
    private boolean isEnable;
}
