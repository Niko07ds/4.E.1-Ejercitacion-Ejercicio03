package ejercicio03;

/**
 * Módulo de auditoría que imprime los eventos por consola.
 */
public class ConsoleAuditor implements AuditObserver {

    @Override
    public void onAuditEvent(AuditEvent event) {
        System.out.println("[Auditoría en consola] " + event.toLogLine());
    }
}
