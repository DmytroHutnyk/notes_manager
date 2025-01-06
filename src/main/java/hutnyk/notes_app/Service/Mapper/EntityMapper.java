package hutnyk.notes_app.Service.Mapper;

import hutnyk.notes_app.Model.DTO.NoteDTO;
import hutnyk.notes_app.Model.DTO.UserDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Repository.INoteRepository;
import hutnyk.notes_app.Repository.IRoleRepository;
import hutnyk.notes_app.Repository.IStatusRepository;
import hutnyk.notes_app.Repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EntityMapper {

    private final IUserRepository userRepository;
    private final IStatusRepository statusRepository;
    private final IRoleRepository roleRepository;
    private final INoteRepository noteRepository;

    public EntityMapper(IUserRepository userRepository, IStatusRepository statusRepository, IRoleRepository roleRepository, INoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.roleRepository = roleRepository;
        this.noteRepository = noteRepository;
    }

    public NoteDTO noteToDTO(Note note) {
        return new NoteDTO(
                note.getTitle(),
                note.getFinishDate(),
                note.getUser() != null ? note.getUser().getId() : null,
                note.getStatusSet() != null ?
                        note.getStatusSet().stream().map(Status::getId).collect(Collectors.toSet()) : null
        );
    }

    public Note dtoToNote(NoteDTO noteDTO) {
        User user = userRepository.findById(noteDTO.getUserId()).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + noteDTO.getUserId())
        );

        Set<Status> statusSet = noteDTO.getStatusSetId().stream()
                .map(statusId -> statusRepository.findById(statusId).orElseThrow(
                        () -> new EntityNotFoundException("Status not found with id: " + statusId)
                ))
                .collect(Collectors.toSet());

        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setFinishDate(noteDTO.getFinishDate());
        note.setUser(user);
        note.setStatusSet(statusSet);

        return note;
    }

    public UserDTO userToDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getNotes() != null ?
                        user.getNotes().stream().map(Note::getId).collect(Collectors.toSet()) : null,
                user.getRoleSet() != null ?
                        user.getRoleSet().stream().map(Role::getId).collect(Collectors.toSet()) : null
        );
    }

    public User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        if (userDTO.getNotesId() != null) {
            Set<Note> notes = userDTO.getNotesId().stream()
                    .map(noteId -> noteRepository.findById(noteId).orElseThrow(
                            () -> new EntityNotFoundException("Note not found with id: " + noteId)
                    ))
                    .collect(Collectors.toSet());
            user.setNotes(notes);
        }

        if (userDTO.getRoleSetId() != null) {
            Set<Role> roles = userDTO.getRoleSetId().stream()
                    .map(roleId -> roleRepository.findById(roleId).orElseThrow(
                            () -> new EntityNotFoundException("Role not found with id: " + roleId)
                    ))
                    .collect(Collectors.toSet());
            user.setRoleSet(roles);
        }

        return user;
    }
}
