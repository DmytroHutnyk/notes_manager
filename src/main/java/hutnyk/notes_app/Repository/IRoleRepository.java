package hutnyk.notes_app.Repository;

import hutnyk.notes_app.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
