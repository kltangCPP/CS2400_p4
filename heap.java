import java.util.Arrays;

public class heap<T extends Comparable<? super T>> {
	private T[] heap;
	private int lastIndex;
	private int swaps;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public heap() {
		this(DEFAULT_CAPACITY);
	} // end constructor
	
	public heap(int initialCapacity) {
		if (initialCapacity < DEFAULT_CAPACITY) {
			initialCapacity = DEFAULT_CAPACITY;
		} else {
			checkCapacity(initialCapacity);
		}
		
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
		swaps = 0;
	} // end constructor
	
	public heap(T[] entries) {
	    this(entries.length); // Call other constructor
		lastIndex = entries.length;
		// Assertion: integrityOK = true

		// Copy given array to data field
		for (int index = 0; index < entries.length; index++)
	    	heap[index + 1] = entries[index];

		// Create heap
	    for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
	        reheap(rootIndex);
	   } // end constructor
	
	public T getMax() {
		checkInitialization();
		T root = null;
		if (!isEmpty()) {
			root = heap[1];
		}
		return root;
	} // end getmax
	
	public boolean isEmpty() {
		return lastIndex < 1;
	} // end isEmpty
	
	public int getSize() {
		return lastIndex;
	} // end getSize
	
	public void clear() {
		checkInitialization();
		
		while (lastIndex > -1) {
			heap[lastIndex] = null;
			lastIndex--;
		} // end while
		lastIndex = 0;
	} // end clear
	
	public void add(T newEntry) {
		checkInitialization();
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0) {
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			swaps++;
		} // end while
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
	} // end add
	
	public T removeMax() { 
		T root = null;
		if (!isEmpty()) {
			root = heap[1]; // Return value
			heap[1] = heap[lastIndex]; // Form a semiheap
			lastIndex--; // Decrease size
			reheap(1);
		} // end if
		return root; 
	} // end removemax
	
	public void reheap(int rootIndex) {
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		while (!done && (leftChildIndex <= lastIndex) ) {
			int largerChildIndex = leftChildIndex; // Assume larger
			int rightChildIndex = leftChildIndex + 1;
			if ( (rightChildIndex <= lastIndex) && (heap[rightChildIndex]).compareTo(heap[largerChildIndex]) > 0) {
				largerChildIndex = rightChildIndex;
			} 
			if ((orphan).compareTo(heap[largerChildIndex]) < 0) {
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
				swaps++;
			}
			else
				done = true;
		} // end while 
		heap[rootIndex] = orphan;
	} // end reheap
	
	public int getSwaps() {
		return this.swaps;
	} // end getSwaps
	
	private void ensureCapacity() {
		if (lastIndex >= heap.length -1) {
			int newlength = 2 * heap.length;
			checkCapacity(newlength);
			heap = Arrays.copyOf(heap, newlength);
		}
	} // end ensureCapacity
	
	private void checkInitialization() {
		if (!initialized) {
			throw new SecurityException("AList object is corrupt.");
		}
	} // end checkInitialization
	
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a heap whose capacity exceeds allowed minimum.");
		}
	} // end checkCapacity
	
	public String toString(int length) {
		String out = "";
		for (int i = 1; i <= length; i++) {
			out += heap[i] + ",";
		}
		return out+"...";
	} // end toString
}
