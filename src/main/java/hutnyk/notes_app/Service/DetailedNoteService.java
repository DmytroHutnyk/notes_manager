package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.DetailedNoteDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Service.Interface.IDetailedNoteService;
import hutnyk.notes_app.Service.Interface.INoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class DetailedNoteService implements IDetailedNoteService {
    private final INoteService noteService;

    public List<DetailedNoteDTO> getAllNotesWithDetails() {
        List<Note> notes = noteService.getAllNotesAdmin();
        return notes.stream().map(this::convertToNoteDTO).collect(Collectors.toList());
    }

    private DetailedNoteDTO convertToNoteDTO(Note note){
        DetailedNoteDTO dto = new DetailedNoteDTO();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setDescription(note.getDescription());
        dto.setFinishDate(note.getFinishDate());

        User user = note.getUser();
        if (user != null) {
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
        }

        dto.setStatuses(note.getStatusSet().stream()
                .map(Status::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}
