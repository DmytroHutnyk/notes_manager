package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.NoteDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Repository.INoteRepository;
import hutnyk.notes_app.Service.Interface.INoteService;
import hutnyk.notes_app.Service.Mapper.NoteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class NoteService implements INoteService {

    private final INoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteService(INoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<NoteDTO> getAllNotesDTO() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(noteMapper::noteToDTO).collect(Collectors.toList());
    }
    public List<Note> getAllNotesAdmin() {
        return noteRepository.findAll();
    }

    public Page<NoteDTO> getAllNotesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Note> notePage = noteRepository.findAll(pageable);
        return notePage.map(noteMapper::noteToDTO);
    }

    public NoteDTO addNote(NoteDTO noteDTO) {
        Note note = noteMapper.dtotoNote(noteDTO);
        Note savedNote = noteRepository.save(note);
        return noteMapper.noteToDTO(savedNote);
    }

    public Note getNoteByIdAdmin(Long id){
        return noteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Note does not exists with id: " + id)
        );

    }
    public Note addNoteAdmin(Note note) {
        return noteRepository.save(note);
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
        return noteMapper.noteToDTO(note);
    }

    public Page<NoteDTO> getNotesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Note> notePage = noteRepository.findAll(pageable);
        return notePage.map(noteMapper::noteToDTO);
    }

    public List<Note> getAllNotesByUserId(Long id){
        return noteRepository.getAllByUserId(id);
    }
}
