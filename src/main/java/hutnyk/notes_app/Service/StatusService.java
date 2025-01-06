package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.Entity.Status;
import hutnyk.notes_app.Repository.IStatusRepository;
import hutnyk.notes_app.Service.Interface.IStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final IStatusRepository statusRepository;

    public StatusService(IStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Page<Status> getAllStatusesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return statusRepository.findAll(pageable);
    }

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatusById(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new EntityNotFoundException("Status not found with id: " + id);
        }
        statusRepository.deleteById(id);
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Status not found with id: " + id)
        );
    }

    public Page<Status> getStatusesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return statusRepository.findAll(pageable);
    }
}
