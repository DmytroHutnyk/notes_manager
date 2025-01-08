package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.RoleDTO;
import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Repository.IRoleRepository;
import hutnyk.notes_app.Service.Interface.IRoleService;
import hutnyk.notes_app.Service.Mapper.RoleMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final IRoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(IRoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::roleToDTO).collect(Collectors.toList());
    }

    public Page<RoleDTO> getAllRolesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Role> rolePage = roleRepository.findAll(pageable);
        return rolePage.map(roleMapper::roleToDTO);
    }

    public RoleDTO addRole(RoleDTO roleDTO) {
        Role role = roleMapper.DTOtoRole(roleDTO);
        Role savedRole = roleRepository.save(role);
        return roleMapper.roleToDTO(savedRole);
    }

    public void deleteRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role not found with id: " + id)
        );
        return roleMapper.roleToDTO(role);
    }

    public Page<RoleDTO> getRolesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Role> rolePage = roleRepository.findAll(pageable);
        return rolePage.map(roleMapper::roleToDTO);
    }
}
