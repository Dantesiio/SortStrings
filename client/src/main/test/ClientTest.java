import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ClientTest {

    @Test
    public void testClientConnection() {
        // Verifica si el cliente se conecta correctamente al servidor sin lanzar excepciones
        try {
            Client.main(new String[]{});
            // Si no lanza excepciones, se considera éxito
            assertTrue(true);
        } catch (Exception e) {
            // Si se lanza alguna excepción, la prueba falla
            assertEquals("Client connection failed", "", e.getMessage());
        }
    }
}
