package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.StatusDTO;
import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Repository.IStatusRepository;
import hutnyk.notes_app.Service.Interface.IStatusService;
import hutnyk.notes_app.Service.Mapper.StatusMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private final IStatusRepository statusRepository;
    private final StatusMapper statusMapper;

    public StatusService(IStatusRepository statusRepository, StatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    public List<StatusDTO> getAllStatuses() {
        List<Status> statuses = statusRepository.findAll();
        return statuses.stream().map(statusMapper::statusToDTO).collect(Collectors.toList());
    }

    public Page<StatusDTO> getAllStatusesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Status> statusPage = statusRepository.findAll(pageable);
        return statusPage.map(statusMapper::statusToDTO);
    }

    public StatusDTO addStatus(StatusDTO statusDTO) {
        Status status = statusMapper.dtoToStatus(statusDTO);
        Status savedStatus = statusRepository.save(status);
        return statusMapper.statusToDTO(savedStatus);
    }

    public void deleteStatusById(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new EntityNotFoundException("Status not found with id: " + id);
        }
        statusRepository.deleteById(id);
    }

    public StatusDTO getStatusById(Long id) {
        Status status = statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Status not found with id: " + id)
        );
        return statusMapper.statusToDTO(status);
    }

    public Page<StatusDTO> getStatusesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Status> statusPage = statusRepository.findAll(pageable);
        return statusPage.map(statusMapper::statusToDTO);
    }
}
