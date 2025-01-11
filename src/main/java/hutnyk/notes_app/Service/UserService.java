package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.DTO.UserDTO;
import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Repository.IUserRepository;
import hutnyk.notes_app.Security.UsersDetails;
import hutnyk.notes_app.Service.Interface.IRoleService;
import hutnyk.notes_app.Service.Interface.IUserService;
import hutnyk.notes_app.Service.Mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
//    private final IRoleService roleService;



    public List<UserDTO> getAllUsersDTO() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::userToDTO).collect(Collectors.toList());
    }

    public List<User> getAllUsersAdmin() {
        return userRepository.findAll();
    }

    public Page<UserDTO> getAllUsersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::userToDTO);
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.dtotoUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToDTO(savedUser);
    }

    public User saveUser(User user){
        return userRepository.save(user);
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
        return userMapper.userToDTO(user);
    }

    public User getUserByIdAdmin(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );
    }

    public Page<UserDTO> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::userToDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found with username + " + username);
        }
        return new UsersDetails(user.get());
    }


    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
