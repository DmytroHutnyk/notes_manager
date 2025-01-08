package hutnyk.notes_app.Service.Mapper;

import hutnyk.notes_app.Model.DTO.NoteDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Repository.IStatusRepository;
import hutnyk.notes_app.Repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Data
@Service
public class NoteMapper {

    private final IUserRepository userRepository;
    private final IStatusRepository statusRepository;

    public NoteDTO noteToDTO(Note note){
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .finishDate(note.getFinishDate())
                .userId(note.getUser() != null ? note.getUser().getId() : null).
                statusSetId(note.getStatusSet() != null ?
                        note.getStatusSet().stream().map(Status::getId).collect(Collectors.toSet()) :
                        null)
                .build();
    }

    public Note dtotoNote(NoteDTO noteDTO) {
        return new Note(
                noteDTO.getStatusSetId() != null ?
                        noteDTO.getStatusSetId().stream()
                        .map(statusId -> statusRepository.findById(statusId).orElseThrow(
                                        () -> new EntityNotFoundException("Status not found with id: " + statusId))).collect(Collectors.toSet()) :
                        null,
                noteDTO.getUserId() != null ?
                        userRepository.findById(noteDTO.getUserId()).orElseThrow(
                                () -> new EntityNotFoundException("User not found with id: " + noteDTO.getUserId())) :
                        null,
                noteDTO.getFinishDate(),
                noteDTO.getTitle(),
                noteDTO.getDescription()
        );
    }
}
