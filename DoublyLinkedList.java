import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Lyonel Behringer
 *  @version 05/11/21 15:50:00
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated string of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 * 
 * We want a class T containing a method int compareTo(T o).
 * Because the comparable interface is itself generic, 
 * we have to specify that the classes we want to allow in the place of T here 
 * are those whose objects can be compared with other objects of the same class.
 * 
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  The method only checks the first element, regardless of the total size of the DLL.
     */
    public boolean isEmpty()
    {
      return head == null; // no need to check tail because for empty lists, null is always assigned to both head and tail
    }

    /**
     * Inserts an element at the first position of the doubly linked list
     * @param data : data of type T
     * @return none
     * 
     * Worst-case asymptotic running time cost: Theta(1)
     * 
     * Justification: The number of operations are the same regardless of the size of the DLL
     */
    public void insertFirst(T data) {
    	DLLNode first = new DLLNode(data, null, null);
    	// 2 cases: empty or non-empty DLL
    	if(isEmpty()) {
    		head = first;
    		tail = first;
    	} else {
    		first.next = head; // point old head at newfirst next
    		head.prev = first; // point newfirst at oldfirst prev
    		head = first; // update head
    	}
    }
    
    /**
     * Inserts an element at the last position of the doubly linked list
     * @param data : data of type T
     * @return none
     * 
     * Worst-case asymptotic running time cost: Theta(1)
     * 
     * Justification: The number of operations are the same regardless of the size of the DLL
     */
    public void insertLast(T data) {
    	DLLNode last = new DLLNode(data, null, null);
    	// 2 cases: empty or non-empty DLL
    	if(isEmpty()) {
    		head = last;
    		tail = last;
    	} else {
    		last.prev = tail; // point old tail at newlast prev
    		tail.next = last; // point newlast at oldlast next
    		tail = last; // update tail
    	}
    }
    
    /**
     *  Gets the size of the doubly linked list.
     * @return The size of the list as an int.
     * 
     * Worst-case asymptotic running time cost: Theta(N)
     * Justification: The for loop iterates over all elements in the DLL.
     */
    public int getDLLSize() {
        int sizeOfDLL = 0;
    	for (DLLNode iter = head; iter != null; iter = iter.next) {
        	sizeOfDLL++;
        }
        return sizeOfDLL;
    }
    
    
    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  The most costly operation is moving the pointer to pos, which has a worst-case asymptotic running time cost of Theta(N)
     */
    public void insertBefore( int pos, T data ) 
    {
    	if(pos <= 0) {
    		insertFirst(data);
    		return;
    	}
    	int size = getDLLSize();
    	if(pos >= size) {
    		insertLast(data);
    	} else  // only cases left: pos == size-1 or 0 < pos < size-1
    	{
    		DLLNode newNode = new DLLNode(data, null, null);
    		DLLNode current = head;
    		for(int i = 0; i < pos; i++) {
    			current = current.next;  // move the pointer to pos
    		}
    		DLLNode prevNode = current.prev;
    		prevNode.next = newNode;
    		newNode.prev = prevNode;
    		current.prev = newNode;
    		newNode.next = current;
    	}
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     * The highest-order term is the method getDLLSize(), which has a running time of Theta(N).
     *
     */
    public T get(int pos) 
    {
    	DLLNode current;
    	T data;
    	if(pos < 0) {
    		return null;
    	}
    	int size = getDLLSize();
    	if(pos+1 > size) {
    		return null;
    	}
    	current = head;
		for(int i = 0; i < pos; i++) {
			current = current.next;
    	/*if(size-(pos+1) >= size/2) {  // starting from head is faster than or equal to starting from tail
    		current = head;
    		for(int i = 0; i < pos; i++) {
    			current = current.next;
    		}
		} else {  // start from tail is faster
			current = tail;
    		for(int i = 0; i < pos; i++) {
    			current = current.prev;
    		}*/
		}
    	data = current.data;
    	return data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  Getting the DLL size takes Theta(N) asymptotic time. If the DLL has more than 1 node and pos == DLL.size-1, 
     *  moving the pointer there for deletion is also Theta(N) asymptotic time.
     *  This then gives a total worst-case asymptotic running-time of Theta(N) + Theta(N) = Theta(N) 
     */
    public boolean deleteAt(int pos) 
    {
    	DLLNode current = head;
		if(isEmpty() || pos < 0) { // 
			return false;
		}
    	int size = getDLLSize();
    	if(pos > size-1) {
    		return false;
    	} else if(size == 1) {
			head = null;
			tail = null;
		} else { // 3 cases for >1 nodes
			for(int i = 0; i <= pos; i++) {
				current = current.next;
			}
			if(pos == 0) { // >1 nodes case 1
				DLLNode nextNode = current.next;
				head = nextNode;
				nextNode.prev = null;
				current.next = null;
				current.prev = null;
			} else if(pos == size-1) { // >1 nodes case 2
				DLLNode prevNode = current.prev;
				head = prevNode;
				prevNode.next = null;
				current.next = null;
				current.prev = null;
			} else { // >1 nodes case 3
				DLLNode prevNode = current.prev;
				DLLNode nextNode = current.next;
				prevNode.next = nextNode;
				nextNode.prev = prevNode;
				current.next = null;
				current.prev = null;
			}
			
		}
		return true;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  We assume that assigning values to next, prev, head, and tail takes Theta(1) running time.
     *  For reversal, we use a for-loop which iterates backwards over all elements. This takes Theta(N) running time.
     */
    public void reverse()
    {
    	DLLNode current;
    	if(isEmpty()) {
    		return;
    	}
    	int size = getDLLSize();
    	if(size == 1) {
    		return;
    	}
		current = tail;
		for(int i = 0; i < size-1; i++) {
			DLLNode oldPrevNode = current.prev;
			DLLNode newPrevNode = oldPrevNode.prev;
    		current.next = oldPrevNode;
    		current.prev = newPrevNode;
			if(newPrevNode == null) {
				head = current;
			} else {
	    		newPrevNode.next = current;
			}
    		oldPrevNode.next = null;
    		tail = oldPrevNode;
    		current = newPrevNode;
		}
		return;
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements unique.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(N^2)
     *
     * Justification:
     *  The outer for loop iterates over all elements that should be unique, this is Theta(N).
     *  For each of the elements that should be unique, we check all following elements whether there are duplicates, which is also Theta(N).
     *  These nested for-loops therefore gives us Theta(N)*(Theta(N)+Theta(N)) = Theta(N^2)
     */
     public void makeUnique()
    {
    	 DLLNode current = head;
         int pos = 0;
         int size = getDLLSize();
         while(current.next != null) {
        	 T data = current.data;
        	 for(int j = pos+1; j < size-1; j++) {
        		 if(data == get(j)) {
        			 deleteAt(j);  // Theta(N)
        			 size--;
        			 j--;  // since an element was deleted and the following element took its index, our pos should be the same in the next iteration
        		 }
        	 }
        	 current = current.next;
        	 pos++;
         }

    }


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  insertFirst() has a running time cost of Theta(1).
     */
    public void push(T item) 
    {
      insertFirst(item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  With a better implementation, Theta(1) would be possible, since we would just need to access the first element in the DLL.
     *  However, my get() method calls getDLLSize() which has a running time of Theta(N).
     *  Although I specified above that deleteAt(pos) has a running time cost of Theta(N),
     *  here we know that pos will be 0, in which case no for-loop is run in the deleteAt method.
     *  Therefore, pop() has an asymptotic running time cost of Theta(N)+Theta(1) = Theta(N).
     */
    public T pop() 
    {
    	T data = get(0);	
    	deleteAt(0);
    	return data;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  insertFirst() takes has a running time of Theta(1).
     */
    public void enqueue(T item) 
    {
    	insertFirst(item);
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  We need to iterate to the end of the queue. We need to call getDLLSize(),get(), and deleteAt(), all of which have Theta(N) running time respectively.
     *  Therefore, we get Theta(N)+Theta(N)+Theta(N) = Theta(N).
     */
    public T dequeue() 
    {
    	int size = getDLLSize();
    	T data = get(size-1);
    	deleteAt(size-1);
      return data;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)  // after the first element, a comma will be added before each element
        {
          s.append(",");
        } else {
          isFirst = false;  // if first element, change value to false for the next iteration so a comma will be added
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}


