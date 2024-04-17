module Demo
{
    sequence<int> ArrInt;
    sequence<ArrInt> MatrInt;

    class Message{
	    string msg;
	    long date;
    }
    interface Printer
    {
        void printString(Message msg);
    }
    interface MathServices{
        long sum(long l1, long l2);
        int vectorDot(ArrInt filaA, ArrInt colB, int i, int j);
    }
}
