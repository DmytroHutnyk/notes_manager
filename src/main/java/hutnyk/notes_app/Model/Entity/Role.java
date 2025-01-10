package hutnyk.notes_app.Model.Entity;

public enum Role{
    ROLE_USER,
    ROLE_ADMIN
}






//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//import java.util.Set;
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@Entity
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Role name cannot be blank")
//    @Size(max = 50, message = "Role name cannot exceed 50 characters")
//    private String name;
//
//    @ManyToMany(mappedBy = "roleSet", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    private Set<User> userSet;
//
//    public Role(String name, Set<User> userSet) {
//        this.name = name;
//        this.userSet = userSet;
//    }
//}
