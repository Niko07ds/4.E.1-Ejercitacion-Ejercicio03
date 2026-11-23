package ejercicio03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Sistema de bitácora que agrega cada evento a un archivo de texto.
 */
public class FileAuditor implements AuditObserver {

    private final Path logFile;

    public FileAuditor(Path logFile) {
        this.logFile = logFile;
    }

    @Override
    public void onAuditEvent(AuditEvent event) {
        try {
            Files.writeString(logFile, event.toLogLine() + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir en la bitácora: " + e.getMessage());
        }
    }
}
