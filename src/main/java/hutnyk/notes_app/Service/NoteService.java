package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.NoteDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Repository.INoteRepository;
import hutnyk.notes_app.Service.Mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class NoteService {

    private final INoteRepository noteRepository;
    private final EntityMapper entityMapper;

    public NoteService(INoteRepository noteRepository, EntityMapper entityMapper) {
        this.noteRepository = noteRepository;
        this.entityMapper = entityMapper;
    }

    public List<NoteDTO> getAllNotesDTO() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(entityMapper::noteToDTO).collect(Collectors.toList());
    }
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    public Page<NoteDTO> getAllNotesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Note> notePage = noteRepository.findAll(pageable);
        return notePage.map(entityMapper::noteToDTO);
    }

    public NoteDTO addNote(NoteDTO noteDTO) {
        Note note = entityMapper.dtoToNote(noteDTO);
        Note savedNote = noteRepository.save(note);
        return entityMapper.noteToDTO(savedNote);
    }

    public void deleteNoteById(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }

    public NoteDTO getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Note not found with id: " + id)
        );
        return entityMapper.noteToDTO(note);
    }

    public Page<NoteDTO> getNotesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Note> notePage = noteRepository.findAll(pageable);
        return notePage.map(entityMapper::noteToDTO);
    }
}
