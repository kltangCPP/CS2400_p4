import java.util.Scanner;
import java.io.*;

public class testHeap {
	public static void main(String[] args) throws IOException {
		// Sequential
		Scanner input = new Scanner(new File("C:\\\\Users\\\\Kayla\\\\eclipse-workspace\\\\CS2400_hw4\\\\src\\data.txt"));
		int array[] = new int[100];
		int i = 0;
		
		while (input.hasNextLine()) {
			array[i] = input.nextInt(); // move data to an array
			i++;
		} // end while
		input.close();
		
		heap<Integer> sequential = new heap<Integer>(100);
		for (int j = 0; j < i; j++) {
			sequential.add(array[j]); // move array sequentially to heap
		}
		
		FileWriter output = new FileWriter("C:\\Users\\Kayla\\eclipse-workspace\\CS2400_hw4\\src\\output.txt");
		output.write("=====================================================================" + "\n");
		output.write("Heap built using sequential insertions: " + sequential.toString(10) + "\n");
		output.write("Number of swaps in the heap creation: " + sequential.getSwaps() + "\n");	
		
		for (int j = 0; j < 10; j++) {
			sequential.removeMax();
		}
		
		output.write("Heap after 10 removals: " + sequential.toString(10) + "\n");
		output.write("\n");
		
		// Optimal 
		Scanner inputFile = new Scanner(new File("C:\\\\Users\\\\Kayla\\\\eclipse-workspace\\\\CS2400_hw4\\\\src\\data.txt"));
		int size = 0; 
		while(inputFile.hasNextInt()) {
			size++;
			inputFile.nextInt();
		} // end while
		
		Integer [] arr = new Integer[size];
		Scanner inputArray = new Scanner(new File("C:\\\\Users\\\\Kayla\\\\eclipse-workspace\\\\CS2400_hw4\\\\src\\data.txt"));
		for(int k = 0; k < arr.length; k++)
		{
			arr[k] = inputArray.nextInt();
		}
		inputFile.close();
		
		heap<Integer> optimal = new heap<Integer>(arr); // move array optimally to heap
		
		output.write("Heap built using optimal method: " + optimal.toString(10) + "\n");
		output.write("Number of swaps in the heap creation: " + optimal.getSwaps() + "\n");
		
		for (int j = 0; j < 10; j++) {
			optimal.removeMax();
		}
		
		output.write("Heap after 10 removals: " + optimal.toString(10) + "\n");
		output.write("=====================================================================" + "\n");
		output.close();
		
	}
}
