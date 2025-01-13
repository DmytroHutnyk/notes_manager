package hutnyk.notes_app.Service.Mapper;

import hutnyk.notes_app.Model.DTO.NoteRequestDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Service.Interface.IStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteRequestMapper {

    private final IStatusService statusService;

    public Note dtoToNote(NoteRequestDTO noteRequestDTO){
        return  new Note(
                noteRequestDTO.getStatuses().stream().map(statusService::findByName).collect(Collectors.toSet()),
                null,
                Date.valueOf(noteRequestDTO.getFinishDate()),
                noteRequestDTO.getTitle(),
                null

        );
    }
}
