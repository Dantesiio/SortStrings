import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import Demo.MathServicesPrx;

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
    }
}
