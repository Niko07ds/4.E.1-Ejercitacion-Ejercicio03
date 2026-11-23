package ejercicio03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Evento de auditoría inmutable que viaja del sujeto a los auditores.
 */
public record AuditEvent(AuditAction action, int employeeId, String employeeName,
                         String detail, LocalDateTime timestamp) {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Devuelve el evento como una línea de texto lista para mostrar o guardar.
     */
    public String toLogLine() {
        return timestamp.format(FORMATTER) + " | " + action.getLabel()
                + " | Empleado #" + employeeId + " (" + employeeName + ") | " + detail;
    }
}
