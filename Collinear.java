/**
 * This class contains only two static methods that search for points on the
 * same line in three arrays of integers.
 *
 * @author Lyonel Behringer
 * @version 8/10/21 19:55:00
 * I have read and I understand the plagiarism provisions in the General Regulations of the University Calendar for the current year, found at http://www.tcd.ie/calendar.
 * I have also completed the Online Tutorial on avoiding plagiarism ‘Ready Steady Write’, located at http://tcd-ie.libguides.com/plagiarism/ready-steady-write."
 */
class Collinear {

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinear(a1,a2,a3)
	 * 
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the
	 *            point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the
	 *            point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the
	 *            point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         Array a1, a2 and a3 contain points on the horizontal line y=1, y=2
	 *         and y=3, respectively. A non-horizontal line will have to cross all
	 *         three of these lines. Thus we are looking for 3 points, each in a1,
	 *         a2, a3 which lie on the same line.
	 *
	 *         Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they
	 *         are on the same line) if
	 * 
	 *         x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0
	 *
	 *         In our case y1=1, y2=2, y3=3.
	 *
	 *         You should implement this using a BRUTE FORCE approach (check all
	 *         possible combinations of numbers from a1, a2, a3)
	 *
	 *         ----------------------------------------------------------
	 *
	 * 
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of growth: Θ(N^3)
	 *
	 *         Explanation: The order of growth is constant for the following steps:
	 *         assigning values to y1, y2, y3, collinearCount, x1, x2, and x3;
	 *         incrementing collinearCount; the return statement. N is the length of
	 *         the int arrays (respectively). The first for-loop iterates over all
	 *         elements in a1 --> Θ(N). Within the first for-loop, there is a second
	 *         for-loop, iterating over all elements in a2 --> in isolation, this
	 *         gives an order of growth of Θ(N). In context of the first for-loop,
	 *         this is Θ(N*N) = Θ(N^3) Within the second for-loop, there is a third
	 *         for-loop, iterating over all elements in a3 --> in isolation, this
	 *         gives an order of growth of Θ(N). In context of the first 2
	 *         for-loops, this is Θ(N*N*N) = Θ(N^3)
	 * 
	 *         - We simplify constant coefficients to be equivalent to 1. 
	 *         - In addition, we only keep the highest order terms. 
	 *         - In multiplication, we multiply inner functions.
	 * 
	 *         The equation to calculate the order of growth is therefore: Θ(1) +
	 *         Θ(1) + Θ(1) + Θ(1) + (Θ(N)*Θ(1))*(Θ(N)*Θ(1))*(Θ(N)*Θ(1)*Θ(1)*Θ(1)) +
	 *         Θ(1) = Θ(N*N*N) = Θ(N^3)
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3) {
		int y1 = 1; // Θ(1)
		int y2 = 2; // Θ(1)
		int y3 = 3; // Θ(1)
		int collinearCount = 0; // Θ(1)
		// 3 for-loops
		for (int i = 0; i < a1.length; i++) { // Θ(N)
			int x1 = a1[i]; // Θ(1)
			for (int j = 0; j < a2.length; j++) { // Θ(N)
				int x2 = a2[j]; // Θ(1)
				for (int k = 0; k < a3.length; k++) { // Θ(N)
					int x3 = a3[k]; // Θ(1)
					if (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) == 0) { // Θ(1)
						collinearCount++; // Θ(1)
					}
				}
			}
		}
		return collinearCount;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinearFast(a1,a2,a3)
	 * 
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the
	 *            point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the
	 *            point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the
	 *            point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         In this implementation you should make non-trivial use of
	 *         InsertionSort and Binary Search. The performance of this method
	 *         should be much better than that of the above method.
	 *
	 *
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: N^2*logN
	 *
	 *         Explanation: Two linear for-loops and a binarySearch. 
	 *         Since in addition, we only keep the highest order terms, the quadratic insertionSort falls out of the equation.
	 *
	 *
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3) {
		int y1 = 1; // Θ(1)
		int y2 = 2; // Θ(1)
		int y3 = 3; // Θ(1)
		int collinearCount = 0;
		
		// 0. make copy of array a3 (adapted from slides for Algorithms & Data
		// Structures 1, Lecture 1, p. 12, by Vasileios Koutavas)
		int[] a3Copy = new int[a3.length];
		for (int i = 0; i < a3.length; i++) {
			a3Copy[i] = a3[i];
		}

		// 1. sort a3Copy via insertion sort
		Collinear.sort(a3Copy);
		
		// 2. determine values to be searched for instead of third for-loop
		// x1*(y2−y3)+x2*(y3−y1)+x3*(y1−y2)=0
		// --> x3 = (-(x1*(y2-y3))-(x2*(y3-y1))) / (y1-y2)
		for (int i = 0; i < a1.length; i++) { // Θ(N)
			int x1 = a1[i]; // Θ(1)
			for (int j = 0; j < a2.length; j++) { // Θ(N)
				int x2 = a2[j]; // Θ(1)
				int requiredX3 = (-(x1*(y2-y3))-(x2*(y3-y1))) / (y1-y2);
				if (binarySearch(a3Copy, requiredX3)) {
					collinearCount++;
				}
			}
		}
		return collinearCount;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort. This method is static,
	 * thus it can be called as Collinear.sort(a)
	 * 
	 * @param a: An UNSORTED array of integers.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 *
	 *         ----------------------------------------------------------
	 *
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: N^2
	 *
	 *         Explanation: Two linear for-loops.
	 *
	 */
	static void sort(int[] a) {
		for (int j = 1; j < a.length; j++) {
			int i = j - 1;
			while (i >= 0 && a[i] > a[i + 1]) {
				int temp = a[i];
				a[i] = a[i + 1];
				a[i + 1] = temp;
				i--;
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers. This method is static,
	 * thus it can be called as Collinear.binarySearch(a,x)
	 * 
	 * @param a: A array of integers SORTED in ascending order.
	 * @param x: An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 *
	 *         ----------------------------------------------------------
	 *
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth:  Θ(logN)
	 *
	 *         Explanation: a while-loop that divides N in half in each iteration
	 *
	 */
	static boolean binarySearch(int[] a, int x) {  // (adapted from slides for Algorithms & Data
		// Structures 1, Lecture 4, p. 46, by Vasileios Koutavas)
			int lo = 0;
			int hi = a.length - 1;
			while (lo <= hi) {  // Θ(logN)
				int mid = lo + (hi - lo) / 2;
				if (x < a[mid]) {
					hi = mid - 1;
				}
				else if (x > a[mid]) {
					lo = mid + 1;
				}
				else {
					return true;
				}
			}
		return false;
	}

}
