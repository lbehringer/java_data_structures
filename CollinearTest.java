import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }


    /**
     * Check that the two methods work when required value in sorted a3 is in upper half of values
     */
    
    @Test
    public void testBinarySearchUpperHalf()
    {
        int[] a1 = { 10 };       int[] a2 = { 20 };       int[] a3 = { 7, 4, 9, 4, 2, 30 };

        int expectedResult = 1;

        assertEquals("countCollinear found 1 value in upper half",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast found 1 value in upper half", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    /**
     * Check that the two methods work when required value in sorted a3 is in lower half of values
     */
    @Test
    public void testBinarySearchLowerHalf()
    {
        int[] a3 = { 10 };       int[] a2 = { 20 };       int[] a1 = { 30, 15, 4, 2, 1 };

        int expectedResult = 1;

        assertEquals("countCollinear found 1 value in lower half",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast found 1 value in lower half", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    /**
     * Check that the two methods work when a3 is empty
     */
    @Test
    public void testA3Empty()
    {
    	int[] a1 = { 10 };       int[] a2 = { 20 };       int[] a3 = new int[0];
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }
}
