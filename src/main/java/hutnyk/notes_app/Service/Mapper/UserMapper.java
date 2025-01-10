package hutnyk.notes_app.Service.Mapper;

import hutnyk.notes_app.Model.DTO.UserDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Repository.INoteRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Data
@Service
public class UserMapper {

//    private final IRoleRepository roleRepository;
    private final INoteRepository noteRepository;

    public UserDTO userToDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .notesSetId(user.getNotesSet() != null ?
                        user.getNotesSet().stream().map(Note::getId).collect(Collectors.toSet()) :
                        null)
                .build();
    }

    public User dtotoUser(UserDTO userDTO){
        return new User(
                userDTO.getNotesSetId() != null ?
                        userDTO.getNotesSetId().stream().map(noteId -> noteRepository.findById(noteId).orElseThrow(
                                () -> new EntityNotFoundException("Note not found with id: " + noteId))).collect(Collectors.toSet()) :
                        null,
                userDTO.getEmail(),
                userDTO.getUsername(),
                true
        );
    }
}