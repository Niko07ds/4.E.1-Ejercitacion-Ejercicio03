package ejercicio03;

/**
 * Interfaz común de los módulos que escuchan eventos de auditoría.
 */
public interface AuditObserver {

    void onAuditEvent(AuditEvent event);
}
