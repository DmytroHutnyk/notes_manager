package hutnyk.notes_app.Service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener {

    private final PersistenceContextSynchronizer synchronizer;

    public ApplicationStartupListener(PersistenceContextSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Application is ready, synchronizing persistence context...");
        synchronizer.synchronize();
    }
}
