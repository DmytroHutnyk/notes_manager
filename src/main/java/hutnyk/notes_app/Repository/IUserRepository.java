package hutnyk.notes_app.Repository;

import hutnyk.notes_app.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
