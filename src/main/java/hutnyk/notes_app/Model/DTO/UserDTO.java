package hutnyk.notes_app.Model.DTO;

import java.util.Set;

public class UserDTO {

    private String username;
    private String email;
    private Set<Long> notesId;
    private Set<Long> roleSetId;

    public UserDTO() {
    }

    public UserDTO(String username, String email, Set<Long> notesId, Set<Long> roleSetId) {
        this.username = username;
        this.email = email;
        this.notesId = notesId;
        this.roleSetId = roleSetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getNotesId() {
        return notesId;
    }

    public void setNotesId(Set<Long> notesId) {
        this.notesId = notesId;
    }

    public Set<Long> getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(Set<Long> roleSetId) {
        this.roleSetId = roleSetId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", notesId=" + notesId +
                ", roleSetId=" + roleSetId +
                '}';
    }
}
