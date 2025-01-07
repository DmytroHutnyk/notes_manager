package hutnyk.notes_app.Service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

@Component
public class PersistenceContextSynchronizer {

    private final EntityManager entityManager;

    public PersistenceContextSynchronizer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void synchronize() {
        entityManager.clear();
        System.out.println("Persistence context synchronized with the database.");
    }
}
