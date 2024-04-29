module Demo
{
    sequence<int> ArrInt;
    sequence<ArrInt> MatrInt;
    sequence<string> ArrString;

    ["java:serializable:java.util.ArrayList"]
    sequence<byte> ArrayList;
    sequence<ArrayList> ListArray;

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

    interface SortServices{
        string bucketSort(ListArray list, int buckets);
    }
}
