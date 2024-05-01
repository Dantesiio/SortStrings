import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestClass {
    public static void main(String[] args) {

        // int bucketAmount = 10;

        // List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");
        // int maxAsciiValue = findMax(strings);
        // System.out.println("Max value" +maxAsciiValue);

        // int minAsciiValue = findMin(strings);
        // System.out.println("Min value" + minAsciiValue);

        // int range = maxAsciiValue - minAsciiValue;

        // int bucketRange = (int) Math.ceil((double) range/bucketAmount);

        // List<String>[] buckets = new List[bucketAmount];

        // for (int i = 0; i < bucketAmount; i++) {
        //     buckets[i] = new LinkedList<>();
        // }

        // for (String string: strings) {
        //     buckets[divideNumBuckets(string, bucketRange, bucketAmount)].add(string);
        // }

        int longitud = 5;
        //String cadena = cadenaAleatoria(longitud);

        int length = 50;
        ArrayList<String> testList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            String cadena = cadenaAleatoria(longitud);
            testList.add("\""+cadena+"\"");
        }
        System.out.println(testList);
    }


public static String cadenaAleatoria(int longitud) {
    // El banco de caracteres
    String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    // La cadena en donde iremos agregando un carácter aleatorio
    String cadena = "";
    for (int x = 0; x < longitud; x++) {
        int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
        char caracterAleatorio = banco.charAt(indiceAleatorio);
        cadena += caracterAleatorio;
    }
    return cadena;
}

public static int numeroAleatorioEnRango(int minimo, int maximo) {
    // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
}



    public static int findMax(List<String> strings) {
        int maxAsciiValue = 0;
        String maxString = "";

        for (String string : strings) {
            int currentAsciiValue = 0;
            for (int i = 0; i < string.length(); i++) {
                currentAsciiValue += string.charAt(i);
            }
            if (currentAsciiValue > maxAsciiValue) {
                maxAsciiValue = currentAsciiValue;
                maxString = string;
            }
        }

        return maxAsciiValue;
    }

    public static int findMin(List<String> strings) {
        int minAsciiValue = Integer.MAX_VALUE; // Inicializamos con el valor máximo posible
        String minString = "";

        for (String string : strings) {
            int currentAsciiValue = 0;
            for (int i = 0; i < string.length(); i++) {
                currentAsciiValue += string.charAt(i);
            }
            if (currentAsciiValue < minAsciiValue) {
                minAsciiValue = currentAsciiValue;
                minString = string;
            }
        }

        return minAsciiValue;
    }


    public static int divideNumBuckets(String string, int bucketRange, int bucketAmount){
        int stringToInt = getAsciiValue(string);
        int bucketNumber = stringToInt / bucketRange;
        if(bucketNumber == bucketAmount){
            bucketNumber--;
        }
        return bucketNumber;
    }

    public static int getAsciiValue(String str) {
        int asciiValue = 0;
        for (int i = 0; i < str.length(); i++) {
            asciiValue += str.charAt(i);
        }
        return asciiValue;
    }
    
}
