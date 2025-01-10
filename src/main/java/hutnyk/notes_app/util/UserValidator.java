package hutnyk.notes_app.util;

import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.findByUsername(user.getUsername()).isPresent()){
            errors.rejectValue("username", "", "This username is already occupied");
        }
        if(userService.findByEmail(user.getEmail()).isPresent()){
            errors.rejectValue("email", "", "This email is already occupied");
        }
    }
}
