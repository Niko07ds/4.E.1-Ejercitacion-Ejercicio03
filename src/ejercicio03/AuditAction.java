package ejercicio03;

/**
 * Tipos de operación crítica que se auditan.
 */
public enum AuditAction {
    CREATED("ALTA"),
    MODIFIED("MODIFICACIÓN"),
    DELETED("BAJA");

    private final String label;

    AuditAction(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
