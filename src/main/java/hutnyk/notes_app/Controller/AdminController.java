package hutnyk.notes_app.Controller;

import hutnyk.notes_app.Model.DTO.DetailedNoteDTO;
import hutnyk.notes_app.Model.DTO.StatusDTO;
import hutnyk.notes_app.Model.Entity.Note;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Security.UsersDetails;
import hutnyk.notes_app.Service.Interface.IDetailedNoteService;
import hutnyk.notes_app.Service.Interface.INoteService;
import hutnyk.notes_app.Service.Interface.IStatusService;
import hutnyk.notes_app.Service.Interface.IUserService;
import hutnyk.notes_app.util.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final IUserService userService;
    private final INoteService noteService;
    private final IStatusService statusService;
    private final UserValidator userValidator;
    private final IDetailedNoteService detailedNoteService;


    @GetMapping("/")
    public String showMenu() {
        return "admin/menuAdmin";
    }

                                                    //USERS
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsersAdmin());
        return "admin/users";
    }

    @GetMapping("/users/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/addUserForm";
    }

    @PostMapping("/users/add/save-user")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "redirect:/admin/users";
        }

        user.setEnable(true);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model ) {
        model.addAttribute("user", userService.getUserByIdAdmin(id));
        return "admin/editUserForm";
    }

    @PostMapping("/users/edit/save-user")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "redirect:/admin/users";
        }
        User existingUser = userService.getUserByIdAdmin(user.getId());

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        userService.saveUser(existingUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUserById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails userDetails = (UsersDetails) authentication.getPrincipal();
        if(userDetails.getId().equals(id)){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return "redirect:/";
        }
        return "redirect:/admin/users";
    }


                                                // NOTES
    @GetMapping("/notes")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotesAdmin());
        return "admin/notes";
    }

    @GetMapping("/notes/add")
    public String addNote(Model model) {
        model.addAttribute("note", new Note());
        return "admin/addNoteForm";
    }

    @PostMapping("/notes/add/save-note")
    public String saveNote(@Valid @ModelAttribute Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "redirect:/admin/notes";
        }
        noteService.addNoteAdmin(note);
        return "redirect:/admin/notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String editNote(@PathVariable Long id, Model model){
        model.addAttribute("note", noteService.getNoteById(id));
        return "admin/editNoteForm";
    }

    @PostMapping("/notes/edit/save-note")
    public String saveEditNote(@Valid @ModelAttribute Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "redirect:/admin/notes";
        }
        noteService.addNoteAdmin(note);
        return "redirect:/admin/notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id){
        noteService.deleteNoteById(id);
        return "redirect:/admin/notes";
    }


                                        // Statuses
    @GetMapping("/statuses")
    public String getStatuses(Model model){
        model.addAttribute("statuses", statusService.getAllStatuses());
        return "admin/statuses";
    }

    @GetMapping("/statuses/add")
    public String addStatus(Model model){
        model.addAttribute("status", new StatusDTO());
        return "admin/addStatusForm";
    }

    @PostMapping("/statuses/add/save-status")
    public String saveStatus(@Valid @ModelAttribute StatusDTO status, BindingResult bindingResult){
        if(statusService.findByName(status.getName()) != null || bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "redirect:/admin/statuses";
        }
        statusService.addStatus(status);
        return "redirect:/admin/statuses";
    }

    @GetMapping("/statuses/edit/{id}")
    public String editStatus(@PathVariable Long id, Model model){
        model.addAttribute("status", statusService.getStatusById(id));
        return "admin/editStatusForm";
    }

    @PostMapping("/statuses/edit/save-status")
    public String saveEditStatus(@Valid @ModelAttribute StatusDTO statusDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "redirect:/admin/statuses";
        }
        statusService.addStatus(statusDTO);
        return "redirect:/admin/statuses";
    }

    @GetMapping("/statuses/delete/{id}")
    public String deleteStatus(@PathVariable Long id){
        statusService.deleteStatusById(id);
        return "redirect:/admin/statuses";
    }


                                                                             //note details
    @GetMapping("/detailed/notes")
    public String getNotesDetails(Model model){
        model.addAttribute("notes", detailedNoteService.getAllNotesWithDetails());
        return "/admin/detailedNotes";
    }
}