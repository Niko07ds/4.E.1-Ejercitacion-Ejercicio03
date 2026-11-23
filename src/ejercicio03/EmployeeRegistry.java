package ejercicio03;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Registro central de empleados (sujeto observable).
 * Dispara un evento de auditoría en cada alta, modificación y baja,
 * sin conocer a los auditores concretos.
 */
public class EmployeeRegistry {

    private final Map<Integer, Employee> employees = new LinkedHashMap<>();
    private final List<AuditObserver> auditors = new ArrayList<>();

    public void addAuditor(AuditObserver auditor) {
        if (auditor != null && !auditors.contains(auditor)) {
            auditors.add(auditor);
        }
    }

    public void removeAuditor(AuditObserver auditor) {
        auditors.remove(auditor);
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
        fireEvent(AuditAction.CREATED, employee,
                "Alta con cargo " + employee.getPosition() + " y salario " + String.format("%.2f", employee.getSalary()));
    }

    public void updateEmployee(int id, String newPosition, double newSalary) {
        Employee employee = findEmployee(id);
        String detail = "Cargo: " + employee.getPosition() + " -> " + newPosition
                + ", salario: " + String.format("%.2f", employee.getSalary()) + " -> " + String.format("%.2f", newSalary);
        employee.setPosition(newPosition);
        employee.setSalary(newSalary);
        fireEvent(AuditAction.MODIFIED, employee, detail);
    }

    public void removeEmployee(int id) {
        Employee employee = findEmployee(id);
        employees.remove(id);
        fireEvent(AuditAction.DELETED, employee, "Baja del empleado del sistema");
    }

    private Employee findEmployee(int id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            throw new IllegalArgumentException("No existe un empleado con id " + id);
        }
        return employee;
    }

    private void fireEvent(AuditAction action, Employee employee, String detail) {
        AuditEvent event = new AuditEvent(action, employee.getId(), employee.getName(), detail, LocalDateTime.now());
        for (AuditObserver auditor : new ArrayList<>(auditors)) {
            auditor.onAuditEvent(event);
        }
    }
}
