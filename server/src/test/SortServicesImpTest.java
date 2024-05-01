package test;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SortServicesImpTest {

    @Test
    public void testBucketSortImplementation() {
        SortServicesImp sortService = new SortServicesImp();

        // Clasificación de la lista vacía
        assertNotNull(sortService.bucketSort(new ArrayList<String>(), null));

        // Clasificación de una lista de entrada
        List<String> inputList = Arrays.asList("abc", "def", "ghi", "jkl");
        List<String> expectedList = Arrays.asList("abc", "def", "ghi", "jkl");
        List<String> sortedList = sortService.bucketSort(inputList, null);
        assertEquals(expectedList, sortedList);

        // Manejo de casos extremos (pueden agregarse más pruebas aquí)
    }
}
