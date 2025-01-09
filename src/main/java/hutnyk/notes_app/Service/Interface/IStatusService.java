package hutnyk.notes_app.Service.Interface;

import hutnyk.notes_app.Model.DTO.StatusDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IStatusService {
    List<StatusDTO> getAllStatuses();
    Page<StatusDTO> getAllStatusesWithPagination(int page, int size);
    StatusDTO addStatus(StatusDTO statusDTO);
    void deleteStatusById(Long id);
    StatusDTO getStatusById(Long id);
    Page<StatusDTO> getStatusesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir);
}
