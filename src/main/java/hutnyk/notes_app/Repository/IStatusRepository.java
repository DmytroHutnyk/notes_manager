package hutnyk.notes_app.Repository;

import hutnyk.notes_app.Model.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(String name);
}
