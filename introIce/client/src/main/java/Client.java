import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import Demo.MathServicesPrx;
import Demo.SortServicesPrx;

public class Client
{
    public static void main(String[] args)throws Exception
    {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"client.cfg"))
        {
            com.zeroc.Ice.ObjectPrx base = communicator.propertyToProxy("server.proxy");
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);

            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }
            printer.printString(new Demo.Message("Hello word",2));

            com.zeroc.Ice.ObjectPrx baseMath = communicator.propertyToProxy("math.proxy");
            MathServicesPrx mathPrx = MathServicesPrx.checkedCast(baseMath);
            long res = mathPrx.sum(5, 7);
            
            System.out.println("Suma: "+res);

            com.zeroc.Ice.ObjectPrx baseSort = communicator.propertyToProxy("sort.proxy");
            SortServicesPrx sortPrx = SortServicesPrx.checkedCast(baseSort);

            com.zeroc.Ice.ObjectPrx baseSort2 = communicator.propertyToProxy("sort.proxy2");
            SortServicesPrx sortPrx2 = SortServicesPrx.checkedCast(baseSort);



                int bucketAmount = 2;

                List<String> strings = Arrays.asList("zzzz", "cherry", "banana", "apple");
                int maxAsciiValue = findMax(strings);
                System.out.println("Max value" +maxAsciiValue);

                int minAsciiValue = findMin(strings);
                System.out.println("Min value" + minAsciiValue);

                int range = maxAsciiValue - minAsciiValue;

                System.out.println("range "+range);

                int bucketRange = (int) Math.ceil((double) range/bucketAmount);

            System.out.println("bucketRange "+bucketRange);

                ArrayList<String>[] buckets = new ArrayList[bucketAmount];

                for (int i = 0; i < bucketAmount; i++) {
                    buckets[i] = new ArrayList<>();
                }

                for (String string : strings) {
                    //System.out.println(divideNumBuckets(string, bucketRange, bucketAmount));
                    buckets[divideNumBuckets(string, bucketRange, bucketAmount)].add(string);
                }

                System.out.println(buckets[0]);
                System.out.println(buckets[1]);


                //sortPrx.bucketSort(buckets);
                System.out.println("Lista ordenada" + Arrays.toString(sortPrx.bucketSort(buckets[1]).toArray()));


            }
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
        System.out.println("toInt" + stringToInt);
        int bucketNumber = stringToInt / bucketRange;
        System.out.println("bucketNumber" + bucketNumber);
        // Asegurarse de que el número de bucket no exceda bucketAmount - 1
        if (bucketNumber >= bucketAmount){
            bucketNumber = bucketAmount - 1;
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
