package hutnyk.notes_app.Repository;

import hutnyk.notes_app.Model.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoteRepository extends JpaRepository<Note, Long> {
}
