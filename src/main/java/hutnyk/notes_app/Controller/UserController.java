package hutnyk.notes_app.Controller;

import hutnyk.notes_app.Model.DTO.NoteDTO;
import hutnyk.notes_app.Model.DTO.NoteRequestDTO;
import hutnyk.notes_app.Model.DTO.StatusDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Security.UsersDetails;
import hutnyk.notes_app.Service.Interface.INoteService;
import hutnyk.notes_app.Service.Interface.IStatusService;
import hutnyk.notes_app.Service.Interface.IUserService;
import hutnyk.notes_app.Service.Mapper.NoteRequestMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final INoteService noteService;
    private final IStatusService statusService;
    private final NoteRequestMapper requestMapper;
    private final IUserService userService;

    @GetMapping("/")
    public String getMainPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails userDetails = (UsersDetails) authentication.getPrincipal();
        Long id = userDetails.getId();
        model.addAttribute("notes", noteService.getAllNotesByUserId(id));

        List<StatusDTO> statuses = statusService.getAllStatuses();
        model.addAttribute("statuses", statuses);
        return "user/mainPage";
    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<Note> getNotes(@PathVariable Long noteId){
       try {
           Note note = noteService.getNoteByIdAdmin(noteId);
            return ResponseEntity.ok(note);
       }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }

    @DeleteMapping("/notes/{selectedNoteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long selectedNoteId){
        noteService.deleteNoteById(selectedNoteId);
       //System.out.println("note deleted");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@RequestBody NoteRequestDTO noteRequestDTO){
        Note newNote = requestMapper.dtoToNote(noteRequestDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails userDetails = (UsersDetails) authentication.getPrincipal();
        Long id = userDetails.getId();

        newNote.setUser(userService.getUserByIdAdmin(id));

        Note savedNote = noteService.addNoteAdmin(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }


    @PutMapping("/notes/{selectedNoteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Long selectedNoteId, @RequestBody Map<String, Object> updatedData){
        try{
            Note note = noteService.getNoteByIdAdmin(selectedNoteId);
            //System.out.println("before modification, id: " + note.getId() + ", name: " + note.getTitle() + ", desc: " + note.getDescription());

            if (updatedData.containsKey("description")) {
                note.setDescription((String) updatedData.get("description"));
            }
            //System.out.println("after desc set, id: " + note.getId() + ", name: " + note.getTitle() + ", desc: " + note.getDescription());

            if (updatedData.containsKey("statuses")) {
                List<String> statuses = (List<String>) updatedData.get("statuses");
                Set<Status> statusSet = statuses.stream()
                        .map(statusService::findByName)
                        .collect(Collectors.toSet());
                note.setStatusSet(statusSet);
            }
            //System.out.println("after status set, id: " + note.getId() + ", name: " + note.getTitle() + ", desc: " + note.getDescription());

            Note updatedNote = noteService.addNoteAdmin(note);
            return ResponseEntity.ok(updatedNote);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


    }
}
