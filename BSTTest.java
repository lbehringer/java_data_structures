import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Lyonel Behringer
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
	@Test
	public void testContains() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(1, 1);
		assertEquals(true, bst.contains(1));
		assertEquals(false, bst.contains(2));
	}
	

	
	@Test
	public void testHeight() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		//bst.put(0, 0);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		bst.put(9, 9);
		bst.put(10, 10);
		bst.put(11, 11);
		bst.put(12, 12);
		bst.put(12, 12);
		bst.put(13, null);
		
		assertEquals(5, bst.height());
	}
	
	@Test
	public void testHeightLeftHeightSmaller(){
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(2, 2);
		bst.put(1, 1);
		assertEquals(1, bst.height());
	}
	
	@Test
	public void testHeightEmpty() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals(-1, bst.height());
	}
	
	@Test
	public void testHeightOneNode() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		assertEquals(0, bst.height());
	}

	@Test
	public void testMedian() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals(null, bst.median()); // should return null because BST is empty
		bst.put(4, 4);
		bst.put(5, 5);
		bst.put(9, 9);
		bst.put(10, 10);
		
		assertEquals(bst.get(5), bst.median()); // should get position (4-1)/2=1 --> key 5
		
		bst.put(11, 11);
		
		assertEquals(bst.get(9), bst.median()); // should get position (5-1)/2 = 2 --> key 9
		
		
		BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
		bst2.put(2, 2);
		bst2.put(1, 1);
		
		assertEquals(bst2.get(1), bst2.median());
	}
  
	

	
	
	
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         bst2.put(1, 1);
         bst2.delete(1);
         assertEquals("Deleting the only element in a tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);
         				  //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
                 
         bst2.put(4, 4);
         bst2.put(2, 2);
         bst2.put(3, 3);
         
         bst2.delete(2);
         assertEquals("Deleting node with single child on other side",
        		 "((()3())4())", bst2.printKeysInOrder());
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());        
 
         bst.delete(3);
         //bst.prettyPrintKeys();
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
         
         

         
     }
     
}
