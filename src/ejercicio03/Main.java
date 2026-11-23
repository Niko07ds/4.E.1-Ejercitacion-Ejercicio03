package ejercicio03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path logFile = Path.of("audit.log");
        Files.deleteIfExists(logFile);

        EmployeeRegistry registry = new EmployeeRegistry();
        AuditObserver consoleAuditor = new ConsoleAuditor();
        AuditObserver fileAuditor = new FileAuditor(logFile);

        System.out.println("Se registra solamente el auditor de consola");
        registry.addAuditor(consoleAuditor);
        registry.addEmployee(new Employee(1, "Lucía Fernández", "Analista", 850000));

        System.out.println();
        System.out.println("Se suscribe el auditor de archivo en plena ejecución");
        registry.addAuditor(fileAuditor);
        registry.addEmployee(new Employee(2, "Martín Gómez", "Desarrollador", 1200000));
        registry.updateEmployee(2, "Desarrollador Senior", 1500000);

        System.out.println();
        System.out.println("Se desuscribe el auditor de consola");
        registry.removeAuditor(consoleAuditor);
        registry.removeEmployee(1);
        System.out.println("La baja del empleado 1 solo se registró en el archivo");

        System.out.println();
        System.out.println("Contenido del archivo de bitácora (" + logFile + "):");
        for (String line : Files.readAllLines(logFile)) {
            System.out.println(line);
        }
    }
}
