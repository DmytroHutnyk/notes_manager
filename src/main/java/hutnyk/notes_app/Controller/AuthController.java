package hutnyk.notes_app.Controller;

import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Service.RegistrationService;
import hutnyk.notes_app.util.UserValidator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController{

   private final UserValidator userValidator;
   private final RegistrationService registrationService;

   @GetMapping("/login")
   public String loginPage(){
       return "auth/login";
   }
   @GetMapping("/registration")
   public String registrationPage(@ModelAttribute("user")User user){
       return "auth/registration";
   }

   @PostMapping("/registration")
   public String registration(@Valid @ModelAttribute("user")User user, BindingResult bindingResult){
       userValidator.validate(user, bindingResult);
       if(bindingResult.hasErrors()){
           return "auth/registration";
       }
       registrationService.register(user);
       return "redirect:/auth/login";
   }

   @GetMapping("/logout")
   public String logout(HttpServletRequest request, HttpServletResponse response){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if(authentication != null){
           new SecurityContextLogoutHandler().logout(request, response, authentication);
       }
       return "redirect:/auth/login";
   }
}
