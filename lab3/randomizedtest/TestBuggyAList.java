package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
<<<<<<< HEAD
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> naiveAList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        naiveAList.addLast(4);
        naiveAList.addLast(5);
        naiveAList.addLast(6);

        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);
        assertEquals(naiveAList.size(), buggyAList.size());

        assertEquals(naiveAList.removeLast(), buggyAList.removeLast());
        assertEquals(naiveAList.removeLast(), buggyAList.removeLast());
        assertEquals(naiveAList.removeLast(), buggyAList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                assertEquals(L.size(), BL.size());
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeBL = BL.size();
                assertEquals(size, sizeBL);
                System.out.println("size: " + size);
            } else if (operationNumber == 2){
                // getLast
                if (L.size()==0 || BL.size() == 0){
                    continue;
                } else {
                    int lastItem = L.getLast();
                    int lastItemBL = BL.getLast();
                    assertEquals(lastItem, lastItemBL);
                    System.out.println("getLast(" + lastItem + ")");
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() == 0 || BL.size() == 0) {
                    continue;
                } else {
                    int lastItem = L.removeLast();
                    int lastItemBL = BL.removeLast();
                    assertEquals(lastItem, lastItemBL);
                    System.out.println("removeLast(" + lastItem + ")");
                }
            }
        }
    }

}
