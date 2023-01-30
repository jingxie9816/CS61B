package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }
        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }


    }

    @Test
    /** get the ith item to the deque*/
    public void getItemAtIndex() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++){
            ad1.addLast(i);
        }
        assertEquals(0, (int) ad1.get(0));
        assertEquals(50, (int) ad1.get(50));
        assertEquals(99, (int) ad1.get(99));

    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            ad1.addLast(i);
        }
        ArrayDeque<Integer> compare1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            compare1.addLast(i);
        }
        System.out.println("Printing out ad1: ");
        ad1.printDeque();
        System.out.println("Printing out compare1: ");
        compare1.printDeque();
        assertTrue(ad1.equals(compare1));

        ArrayDeque<Integer> compare2 = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            compare2.addFirst(i);
        }
        System.out.println("Printing out ad1: ");
        ad1.printDeque();
        System.out.println("Printing out compare2: ");
        compare2.printDeque();
        assertFalse(ad1.equals(compare2));
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> LL = new LinkedListDeque<>();
        ArrayDeque<Integer> AL = new ArrayDeque<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                LL.addLast(randVal);
                AL.addLast(randVal);
                assertEquals(LL.size(), AL.size());
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int LLsize = LL.size();
                int ALsize = AL.size();
                assertEquals(LLsize, ALsize);
                System.out.println("LinkedListDequesize: " + LLsize + ";" + "ArrayListDeque: " + ALsize);
            } else if (operationNumber == 2){
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                LL.addFirst(randVal);
                AL.addFirst(randVal);
                assertEquals(LL.size(), AL.size());
                System.out.println("addFirst(" + randVal + ")");
            } else if (operationNumber == 3) {
                // removeLast
                if (LL.size() != 0 && AL.size() == 0) {
                    int LLlastItem = LL.removeLast();
                    int ALlastItem = AL.removeLast();
                    assertEquals(LLlastItem, ALlastItem);
                    System.out.println("LLremoveLast(" + LLlastItem + ");" + "ALremoveLast(" + ALlastItem + ")");
                }
                }
            }
        }
    }


