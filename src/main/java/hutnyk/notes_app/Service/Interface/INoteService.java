package hutnyk.notes_app.Service.Interface;

import hutnyk.notes_app.Model.DTO.NoteDTO;
import hutnyk.notes_app.Model.DTO.UserDTO;
import hutnyk.notes_app.Model.Entity.Note;
import org.springframework.data.domain.Page;

import java.util.List;

public interface INoteService {
    List<NoteDTO> getAllNotesDTO();
    List<Note> getAllNotesAdmin();
    Page<NoteDTO> getAllNotesWithPagination(int page, int size);
    NoteDTO addNote(NoteDTO noteDTO);
    void deleteNoteById(Long id);
    NoteDTO getNoteById(Long id);
    Page<NoteDTO> getNotesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir);
}
