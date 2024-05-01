module Demo
{
    sequence<int> ArrInt;
    sequence<ArrInt> MatrInt;
    sequence<string> ArrString;

    ["java:type:java.util.ArrayList<String>"]
    sequence<string> ArrayList;
    ["java:type:java.util.ArrayList<String>"]
    sequence<string> ArrayReturn;



    ["java:serializable:java.util.ArrayList"]
    sequence<byte> Array2;

    sequence<ArrayList> ListArray;
    sequence<string> ListReturn;

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
        ArrayReturn bucketSort(ArrayList list);
    }
}
