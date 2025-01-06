package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Repository.IRoleRepository;
import hutnyk.notes_app.Service.Interface.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Page<Role> getAllRolesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findAll(pageable);
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role not found with id: " + id)
        );
    }

    public Page<Role> getRolesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return roleRepository.findAll(pageable);
    }
}
