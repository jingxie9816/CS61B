package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(320000);
        Ns.addLast(640000);
        Ns.addLast(1280000);
        AList times = new AList();
        for (int j = 0; j < Ns.size(); j = j + 1) {
            Stopwatch sw = new Stopwatch();
            AList AL = createArray(Ns.get(j));
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        AList opCounts;
        opCounts = Ns;
        printTimingTable(Ns, times, opCounts);
    }


    public static AList createArray(int x) {
        AList L = new AList();
        int i = 0;
        while (i < x) {
            L.addLast(i);
            i = i + 1;
        }
        return L;
    }
}