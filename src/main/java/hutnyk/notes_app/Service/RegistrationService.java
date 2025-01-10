package hutnyk.notes_app.Service;

import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Model.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setEnable(true);
        userService.saveUser(user);
    }



}
