import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;
import javax.swing.text.html.HTMLDocument.Iterator;

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
            SortServicesPrx sortPrx2 = SortServicesPrx.checkedCast(baseSort2);

            // com.zeroc.Ice.ObjectPrx baseSort3 = communicator.propertyToProxy("sort.proxy3");
            // SortServicesPrx sortPrx3 = SortServicesPrx.checkedCast(baseSort3);

                List<String> strings2 = Arrays.asList("date", "cherry", "banana", "apple","hola","adios","aaaaaa", "zzzzzz");
                List<String> strings = Arrays.asList("QgaI8", "tWcyc", "XRpfA", "zY27t", "buLW3", "XpsxB", "QrGxT", "drViY", "BEGZN"
                ,"IoZNZ", "4e1Og", "x9kPQ", "CHvjL", "CXcSr", "81IFD", "8Ca8x", "P0UZ4", "SMA3K", "IlnCm", "krNSu", "yKg2K", "vamQT", "hSbvi"
                , "Sr2ej", "IDu6z", "AYA4Z", "L8SSG", "FveUK", "wrpqO", "RNa9T", "00U8K", "5hKrq", "Z1Mpk", "EdKkq", "rurYB", "Dr4D7", "3l7YT"
                , "SqqDK", "xg64D", "I9DPa", "sUU6R", "QHjTa", "3ZJBC", "aOTFO", "URhuw", "V2e1W", "dFHha", "Gccul", "4xNs3", "gI6jq");
                
                int bucketAmount = 2;
                
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
                    buckets[divideNumBuckets(string, range, bucketAmount)].add(string);
                }


                // List<String> list1 = sortPrx.bucketSort(buckets[0]);
                // List<String> list2 = sortPrx2.bucketSort(buckets[1]);

                // System.out.println("Lista ordenada 19" + list1);
                // System.out.println("Lista ordenada 18" + list2);

                // list1.addAll(list2);

                // System.out.println("Lista completa ordenada" + list1);

                HashMap<Integer, CompletableFuture<List<String>>> futures = new HashMap<>(); 

                CompletableFuture<List<String>> list1Async = sortPrx.bucketSortAsync(buckets[0]);
                futures.put(0, list1Async);
                CompletableFuture<List<String>> list2Async = sortPrx2.bucketSortAsync(buckets[1]);
                futures.put(1, list2Async);

                ArrayList<List<String>> ArrayOfArrays = new ArrayList<>(bucketAmount);

                for (int i = 0; i < bucketAmount; i++) {
                    ArrayOfArrays.add(i, null);
                }
                
                java.util.Iterator<Integer> keys = futures.keySet().iterator();
                while (keys.hasNext()) {
                    Integer k = keys.next();
                    List<String> resuList = futures.get(k).get();
                    ArrayOfArrays.add(k, resuList);
                }

                for (int i = 0; i < bucketAmount; i++) {
                    if (ArrayOfArrays.get(i+1) != null) {
                        ArrayOfArrays.get(0).addAll(ArrayOfArrays.get(i+1));
                    }
                }

                System.out.println("\nLista sin ordenar: "+strings);

                System.out.println("\nLista ordenada: "+ArrayOfArrays.get(0));
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
        int minAsciiValue = Integer.MAX_VALUE; 
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
        //System.out.println("toInt" + stringToInt);
        int bucketNumber = (stringToInt-bucketRange) / bucketRange;
        //System.out.println("bucketNumber" + bucketNumber);
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
