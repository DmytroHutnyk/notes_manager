package hutnyk.notes_app.Service.Interface;

import hutnyk.notes_app.Model.DTO.DetailedNoteDTO;

import java.util.List;

public interface IDetailedNoteService {
    List<DetailedNoteDTO> getAllNotesWithDetails();
}
