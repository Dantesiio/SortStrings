import com.zeroc.Ice.Current;

import Demo.MathServices;

public class MathServicesImp implements MathServices {

    @Override
    public long sum(long l1, long l2, Current current) {
        return l1 + l2;
    }

    @Override
    public int vectorDot(int[] filaA, int[] colB, int i, int j, Current current) {
        int value = 0;
        for (int k = 0; k < colB.length; k++) {
            value += filaA[i]*colB[i];
        }
        return value;
    }
    
}
