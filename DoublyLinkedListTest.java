import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    @Test
	public void testEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		boolean expectedResult = true;
		assertEquals(expectedResult, list.isEmpty());
	}
	
    @Test
	public void testNotEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.insertFirst(3);
		boolean expectedResult = false;
		assertEquals(expectedResult, list.isEmpty());
	}
	
    @Test
    public void testInsertFirstLast() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.insertFirst(3);
		list.insertFirst(25);
		list.insertFirst(543);
		list.insertLast(777);
		int expectedResultFirst = 543;
		int expectedResultLast = 777;
		int resultFirst = list.get(0);
		int resultLast = list.get(3);
		assertEquals(expectedResultFirst, resultFirst);
		assertEquals(expectedResultLast, resultLast);
    }
	
    @Test
    public void testGetDLLSize() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.insertFirst(3);
		list.insertFirst(25);
		list.insertFirst(543);
		assertEquals(3, list.getDLLSize());
    }
    
    @Test
    public void testGet() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.insertFirst(3);
		list.insertFirst(25);
		list.insertFirst(543);
		int expectedPos1 = 25;
		int pos1 = list.get(1);
		assertEquals(null, list.get(-3)); // below 0
		assertEquals(null, list.get(5)); // out of index
		assertEquals(expectedPos1, pos1);
    }
    
    @Test
    public void testDeleteAt() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		list.push(3);
		list.push(25);
		list.push(543);
		list.push(434);
		list.push(101010);
		
		int pos0 = list.get(0);
		
		assertEquals(false, emptyList.deleteAt(0)); // empty list
		assertEquals(false, emptyList.deleteAt(-1)); // less than 0
		assertEquals(101010, pos0);
		assertEquals(true, list.deleteAt(0)); // deletes 101010
		int newPos0 = list.get(0);
		assertEquals(434, newPos0);
    }
    
    @Test
    public void testReverse() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.push(3);
		list.push(25);
		list.push(543);
		
		int pos0 = list.get(0);
		int pos1 = list.get(1);
		int pos2 = list.get(2);
		
		assertEquals(3, pos0);
		assertEquals(25, pos1);
		assertEquals(543, pos2);
		
		list.reverse();
		
		pos0 = list.get(0);
		pos1 = list.get(1);
		pos2 = list.get(2);
		assertEquals(3, pos0);
		assertEquals(25, pos1);
		assertEquals(543, pos2);

    }
    
    @Test
    public void testMakeUnique() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.push(3);
		list.push(25);
		list.push(3);
		list.push(543);
		list.push(543);
		list.makeUnique();
		int expectedSize = 3;
		int size = list.getDLLSize();
		assertEquals(expectedSize, size);
		int pos0 = list.get(0);
		int pos1 = list.get(1);
		int pos2 = list.get(2);
		assertEquals(543, pos0);
		assertEquals(3, pos1);
		assertEquals(25, pos2);

    }
    
    @Test
    public void testEnqueue() {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.enqueue(3);
		int pos0 = list.get(0);
		assertEquals(3, pos0);
		list.dequeue();
		pos0 = list.get(0);
		assertEquals(false, pos0);
    }
}
