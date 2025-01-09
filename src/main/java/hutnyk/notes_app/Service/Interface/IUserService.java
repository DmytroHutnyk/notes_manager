package hutnyk.notes_app.Service.Interface;

import hutnyk.notes_app.Model.DTO.UserDTO;
import hutnyk.notes_app.Model.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsersDTO();
    List<User> getAllUsersAdmin();
    Page<UserDTO> getAllUsersWithPagination(int page, int size);
    UserDTO addUser(UserDTO userDTO);
    void deleteUserById(Long id);
    UserDTO getUserById(Long id);
    Page<UserDTO> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir);



}
