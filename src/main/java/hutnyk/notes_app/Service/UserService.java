package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.UserDTO;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Repository.IUserRepository;
import hutnyk.notes_app.Service.Mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final EntityMapper entityMapper;

    public UserService(IUserRepository userRepository, EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    public List<UserDTO> getAllUsersDTO() {
        List<User> users = userRepository.findAll();
        return users.stream().map(entityMapper::userToDTO).collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<UserDTO> getAllUsersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(entityMapper::userToDTO);
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = entityMapper.dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return entityMapper.userToDTO(savedUser);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );
        return entityMapper.userToDTO(user);
    }

    public Page<UserDTO> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(entityMapper::userToDTO);
    }
}
