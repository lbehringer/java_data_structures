/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Lyonel Behringer
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree (i.e. for root, this is the size of the entire BST)

        public Node(Key key, Value val, int N) {  // constructor
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);  // if Node is empty, assign key/val and set size to 1
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N) where N is the number of keys in the tree.
     * Explanation: 
     * The isEmpty check has Theta(1) running time and is only performed once.
     * The recursive function itself (without looking at the recursive executions) also has Theta(1) running time but since it is calling itself, 
     * the running time is (always) Theta(N) because the method is called for each key in the tree. 
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
   	 	// 0. if BST is empty, return -1
    	if(isEmpty()) {
    		return -1;
    	}
    	return height(0, 0, root);
    }
    
    //max = maximum height encountered; current = current height; x = node;
    private int height(int max, int current, Node x) {
    	/*
    	 * traverse to both children nodes
    	 * 1. if left != null --> go to x.left, current++;
    	 * 2. if right != null --> go to x.right, current++;
    	 * 
    	 */
    	    	    	
    	// 1. left child
    	if(x.left != null) {
    		//current++;
    		if(current+1>max) {
    			max = current+1;
    		}
    		int leftHeight = height(max, current+1, x.left);
    		if(leftHeight > max) {
    			max = leftHeight;
    		}
    	}
    	
    	// 2. right child
    	if(x.right != null) {
    		//current++;
    		if(current+1>max) {
    			max = current+1;
    		}
    		int rightHeight = height(max, current+1, x.right);
    		if(rightHeight > max) {
    			max = rightHeight;
    		}
    	}
    	return max;
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty()) return null;
      return median(root, size());
    }
    
    private Key median(Node x, int size) {
    	//median = (size+1)/2;
    	int requiredRank = ((size+1)/2)-1;
    	return select(requiredRank);
    }

    /*
     * For median, we need the rank and select function. 
     * These are taken from the lecture slides
    public int rank(Key key) {
    	return rank(key, root);
    }
    
    private int rank(Key key, Node x) {
    	if (x == null) return 0;
    	int cmp = key.compareTo(x.key);
    	if (cmp < 0) return rank(key, x.left);
    	else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
    	else return size(x.left);
    }
*/    
    
    public Key select(int n) {
    	if (n < 0 || n >= size()) return null;
    	Node x = select(root, n);
    	return x.key;
	}
    
	private Node select(Node x, int n) {
    	if (x == null) return null;
    	int t = size(x.left);
    	if (t > n) return select(x.left, n);
    	else if (t < n) return select(x.right, n-t-1);
    	else return x;
	}
    
    
    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D 
     *(A)       
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) return "()";
      return printKeysInOrder(root);
    }
    
    private String printKeysInOrder(Node x) {
    	
    	 
    	//case 0: x == null
    	if(x==null) {
    		return "()";
    	}
    	
    	/*if(size() == 1) {
    	return "(()"+ x.key.toString() + "())";
    	}*/
    	//prio 1: go left
    	String leftChild = printKeysInOrder(x.left);  // STACK OVERFLOW FOR LAST DELETION
    	
    	//prio 2: print
    	String output = "("  + leftChild + x.key.toString();
    	// prio 3: go right
    	String rightChild = printKeysInOrder(x.right);
    	
    	//prio 4: return
    	return output + rightChild + ")";
    }
    
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     * The method should
     * print the key of each node in a separate line
	 * print null nodes
	 * print the left subtree before the right subtree
	 * use correct indentation to print the example below.
	 * have a "\n" even after the last key in the tree
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
      return prettyPrintKeys(root, "");
    }
    /** 
     * @params: prefix: the string before horizontal dash "-"
     * 
     */
    private String prettyPrintKeys(Node x, String prefix) {
    	if(x==null) {
    		return prefix + "-null\n";
    	}
    	
    	//print
    	String output = prefix + "-" + x.key.toString() + "\n";
    	//print "-"+x.key; 

    	//go left
    	output = output + prettyPrintKeys(x.left, prefix + " |");
    	
    	//go right
    	output += prettyPrintKeys(x.right, prefix + "  ");
    	//return
    	return output;
    }

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     * 
     * This is adapted from the lecture slides on Hibbard deletion.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
    	delete(root, key);
    }
    
    private Node delete(Node x, Key key) {
    	
    	if (x==null) {
    		return null;
    	}


    	int cmp = key.compareTo(x.key);
    	if(cmp < 0) {
    		x.left = delete(x.left, key); // search for key in left subtree
    	} else if (cmp > 0) {
    		x.right = delete(x.right, key); // search for key in right subtree
    	} else { // key was found
    		if (x.right == null) { // no right child
    	    	if(size(root) == 1) {
    	    		root = null;
    	    		return null;
    	    	}
    			return x.left;
    		}
    		if (x.left == null) { // no left child
    			return x.right;
    		}
    		
    		// 2 children nodes
    		Node t = x;
    		x = max(t.left); // replace with predecessor
    		x.left = deleteMax(t.left);
    		//deleteMax(t.left);
    		x.right = t.right;
    	}
    	x.N = size(x.left) + size(x.right) + 1; // update counts
    	return x;
    }
    
    
    /*return max node in a tree*/
    private Node max(Node x) {
    	if(x.right != null) {
    		x = max(x.right);
    	}
    	return x;
    }		
    
    
    /*delete max node in a tree*/
    private Node deleteMax(Node x) {
    	if(x.right == null) {
    		return x.left;
    	}
    	x.right = deleteMax(x.right);
    	x.N = 1 + size(x.left) + size(x.right); // update counts
    	return x;
    }
}