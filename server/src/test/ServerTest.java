package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.Server; // Asegúrate de que esta importación refleje la ubicación real de tu clase Server

public class ServerTest {

    @Test
    public void testServerInitialization() {
        // Verifica si el servidor se inicia correctamente
        try {
            Server.main(new String[]{});
            // Si no lanza excepciones, se considera éxito
        } catch (Exception e) {
            // Si se lanza alguna excepción, la prueba falla
            assertEquals("Server initialization failed", "", e.getMessage());
        }
    }
}
