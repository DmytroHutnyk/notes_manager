package hutnyk.notes_app.Service.Interface;

import hutnyk.notes_app.Model.DTO.RoleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
    List<RoleDTO> getAllRoles();
    Page<RoleDTO> getAllRolesWithPagination(int page, int size);
    RoleDTO addRole(RoleDTO roleDTO);
    void deleteRoleById(Long id);
    RoleDTO getRoleById(Long id);
    Page<RoleDTO> getRolesWithPaginationAndSorting(int page, int size, String sortBy, String sortDir);
}
