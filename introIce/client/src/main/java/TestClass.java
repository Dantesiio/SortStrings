import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {

        int bucketAmount = 10;

        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");
        int maxAsciiValue = findMax(strings);
        System.out.println("Max value" +maxAsciiValue);

        int minAsciiValue = findMin(strings);
        System.out.println("Min value" + minAsciiValue);

        int range = maxAsciiValue - minAsciiValue;

        int bucketRange = (int) Math.ceil((double) range/bucketAmount);

        List<String>[] buckets = new List[bucketAmount];

        for (int i = 0; i < bucketAmount; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (String string: strings) {
            buckets[divideNumBuckets(string, bucketRange, bucketAmount)].add(string);
        }

        /**
         int[][] math = new int[2][5];
         int[][] mathB = new int[5][3];
         int[][] mathBT = new int[3][5];

         int[][] mathC = new int[math.length][mathB[0].length];
         List<CompletableFuture<Integer>> futures = new ArrayList<>();
         for (int i = 0; i < mathC.length; i++) {
         for (int j = 0; j < mathC[0].length; j++) {
         CompletableFuture<Integer> result = mathPrx.vectorDotAsync(math[i],mathBT[j], i, j);
         futures.add(result);
         }
         }
         for (CompletableFuture<Integer> cf : futures) {
         int va = cf.get();
         }
         */
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
