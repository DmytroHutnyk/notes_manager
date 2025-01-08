package hutnyk.notes_app.Service.Mapper;

import hutnyk.notes_app.Model.DTO.StatusDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Repository.INoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Data
@Service
public class StatusMapper {

    private final INoteRepository noteRepository;

    public StatusDTO statusToDTO(Status status){
        return StatusDTO.builder()
                .id(status.getId())
                .name(status.getName())
                .noteSetId(status.getNoteSet() != null ?
                        status.getNoteSet().stream().map(Note::getId).collect(Collectors.toSet()) :
                        null)
                .build();
    }


    public Status dtoToStatus(StatusDTO statusDTO){
        return new Status(
                statusDTO.getNoteSetId() != null ?
                        statusDTO.getNoteSetId().stream().map(noteId -> noteRepository.findById(noteId).orElseThrow(
                                () -> new EntityNotFoundException("Note not found with id: " + noteId))).collect(Collectors.toSet()) :
                        null,
                statusDTO.getName()
        );
    }
}