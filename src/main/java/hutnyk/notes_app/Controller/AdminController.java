package hutnyk.notes_app.Controller;

import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Service.Mapper.EntityMapper;
import hutnyk.notes_app.Service.NoteService;
import hutnyk.notes_app.Service.RoleService;
import hutnyk.notes_app.Service.StatusService;
import hutnyk.notes_app.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;
    private final NoteService noteService;
    private final StatusService statusService;
    private final EntityMapper entityMapper;

    public AdminController(RoleService roleService, UserService userService, NoteService noteService, StatusService statusService, EntityMapper entityMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.noteService = noteService;
        this.statusService = statusService;
        this.entityMapper = entityMapper;
    }
    // menu
    @GetMapping("/menu")
    public String showMenu() {
        return "admin/menu";
    }

    //roles
    @GetMapping("/roles")
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/roles";
    }

    @GetMapping("/roles/add")
    public String addRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "admin/role_form";
    }

    @PostMapping("/roles/create")
    public String addRole(@Valid @ModelAttribute Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/role_form";
        }
        System.out.println("Role ID before save: " + role.getId());
        roleService.addRole(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("/roles/edit/{id}")
    public String editRoleForm(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getRoleById(id));
        return "admin/role_form";
    }

    @PostMapping("/roles/edit/{id}")
    public String editRole(@PathVariable Long id, @ModelAttribute Role role) {
        role.setId(id);
        roleService.addRole(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("/roles/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return "redirect:/admin/roles";
    }




    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/notes")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "admin/notes";
    }

    @GetMapping("/statuses")
    public String listStatuses(Model model) {
        model.addAttribute("statuses", statusService.getAllStatuses());
        return "admin/statuses";
    }

    @GetMapping("/detailed/note")
    public String detailedNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "admin/detailed_note";
    }

    @GetMapping("/detailed/user")
    public String detailedUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/detailed_user";
    }



    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user_form";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(entityMapper.userToDTO(user));
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user_form";
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.addUser(entityMapper.userToDTO(user));
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }


}
