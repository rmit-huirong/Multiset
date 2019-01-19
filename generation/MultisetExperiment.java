import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class MultisetExperiment {
	public static void main(String[] args) {
		Random r = new Random();
		Scanner input = new Scanner(System.in);
		String fileName = "fixedSet.txt";
		Scanner inputStream = null;	
		int i, j, k;
		System.out.print("Type of Multiset: ");
		String type = input.next();
		Multiset<String> multiset = null;
		double estimatedTime = 0;
		switch(type) {
		case "linkedlist":
			multiset = new LinkedListMultiset<String>();
			break;
		case "sortedlinkedlist":
			multiset = new SortedLinkedListMultiset<String>();
			break;
		case "bst":
			multiset = new BstMultiset<String>();
			break;
		case "hash":
			multiset = new HashMultiset<String>();
			break;
		case "baltree":
			multiset = new BalTreeMultiset<String>();
			break;
		default:
			System.err.println("Unknown implmementation type.");
		}
		
		System.out.print("The size of multiset: ");
		int sizeOfMultiset = input.nextInt();
		int sizeOfFixedSet = sizeOfMultiset / 100;
		DataGenerator multi = new DataGenerator(sizeOfMultiset, multiset);
		System.out.print("Do you need a new fixed set? ");
		String option = input.next();
		if (option.equals("y")) {
			DataGenerator fixSet = new DataGenerator(sizeOfFixedSet);
		}
		try {
			inputStream = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e) {
		System.out.println("Error opening the file " + fileName);
		System.exit(0);
		}
		String[] fixedSet = new String[sizeOfFixedSet];
		for (i = 0; i < sizeOfFixedSet; ++i){
			fixedSet[i] = inputStream.nextLine();
		}
		System.out.print("What operations do you want to execute? ");
		String operation = input.next();
		switch(operation) {
		case "a":
			for (i = 0; i < 5; ++i) {
				for(j = 0; j < sizeOfMultiset / 5; ++j) {
					long startTime = System.nanoTime();
					multiset.add(fixedSet[r.nextInt(sizeOfFixedSet)]);
					long endTime = System.nanoTime();
					estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
				}
			}
			System.out.println("Type of Multiset = " + type);
			System.out.println("Size of Multiset = " + sizeOfMultiset);
			System.out.println("Size of Fixed Set = " + sizeOfFixedSet);
			System.out.println("Numbers of add operations = " + sizeOfMultiset / 5);
			System.out.printf("Operations time taken in average = %.6f" + " sec\n",  estimatedTime / 5);
			break;
		case "r":
			for (i = 0; i < 5; ++i) {
				for(j = 0; j < sizeOfMultiset / 5; ++j) {
					long startTime = System.nanoTime();
					multiset.removeAll(fixedSet[r.nextInt(sizeOfFixedSet)]);
					long endTime = System.nanoTime();
					estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
				}
			}
			System.out.println("Type of Multiset = " + type);
			System.out.println("Size of Multiset = " + sizeOfMultiset);
			System.out.println("Size of Fixed Set = " + sizeOfFixedSet);
			System.out.println("Numbers of remove operations = " + sizeOfMultiset / 5);
			System.out.printf("Operations time taken in average = %.6f" + " sec\n",  estimatedTime / 5);
			break;
		case "ar":
			for (i = 0; i < 5; ++i) {
				for(j = 0; j < sizeOfMultiset / 5 / 2; ++j) {
					long startTime = System.nanoTime();
					multiset.add(fixedSet[r.nextInt(sizeOfFixedSet)]);
					multiset.removeAll(fixedSet[r.nextInt(sizeOfFixedSet)]);
					long endTime = System.nanoTime();
					estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
				}
			}
			System.out.println("Type of Multiset = " + type);
			System.out.println("Size of Multiset = " + sizeOfMultiset);
			System.out.println("Size of Fixed Set = " + sizeOfFixedSet);
			System.out.println("Numbers of add/remove operations = " + sizeOfMultiset / 5);
			System.out.printf("Operations time taken in average = %.6f" + " sec\n",  estimatedTime / 5);
			break;
		case "s2":
			for (i = 0; i < 5; ++i) {
				for(j = 0; j < sizeOfMultiset / 5; ++j) {
					
					if (r.nextBoolean()) {
						long startTime = System.nanoTime();
						multiset.add(fixedSet[r.nextInt(sizeOfFixedSet)]);
						long endTime = System.nanoTime();
						estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
					}
					else {
						long startTime = System.nanoTime();
						multiset.removeAll(fixedSet[r.nextInt(sizeOfFixedSet)]);
						long endTime = System.nanoTime();
						estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
					}
					for (k = 0; k < 2; ++k) {
						long startTime = System.nanoTime();
						multiset.search(fixedSet[r.nextInt(sizeOfFixedSet)]);
						long endTime = System.nanoTime();
						estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
					}
				}
			}
			System.out.println("Type of Multiset = " + type);
			System.out.println("Size of Multiset = " + sizeOfMultiset);
			System.out.println("Size of Fixed Set = " + sizeOfFixedSet);
			System.out.println("Numbers of add/remove operations = " + sizeOfMultiset / 5);
			System.out.println("Numbers of search operations = " + sizeOfMultiset / 5 * 2);
			System.out.printf("Operations time taken in average = %.6f" + " sec\n",  estimatedTime / 5);
			break;
		case "s4":
			for (i = 0; i < 5; ++i) {
				for(j = 0; j < sizeOfMultiset / 5; ++j) {
					
					if (r.nextBoolean()) {
						long startTime = System.nanoTime();
						multiset.add(fixedSet[r.nextInt(sizeOfFixedSet)]);
						long endTime = System.nanoTime();
						estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
					}
					else {
						long startTime = System.nanoTime();
						multiset.removeAll(fixedSet[r.nextInt(sizeOfFixedSet)]);
						long endTime = System.nanoTime();
						estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
					}
					for (k = 0; k < 4; ++k) {
						long startTime = System.nanoTime();
						multiset.search(fixedSet[r.nextInt(sizeOfFixedSet)]);
						long endTime = System.nanoTime();
						estimatedTime += ((double)(endTime - startTime)) / Math.pow(10,9);
					}
				}
			}
			System.out.println("Type of Multiset = " + type);
			System.out.println("Size of Multiset = " + sizeOfMultiset);
			System.out.println("Size of Fixed Set = " + sizeOfFixedSet);
			System.out.println("Numbers of add/remove operations = " + sizeOfMultiset / 5);
			System.out.println("Numbers of search operations = " + sizeOfMultiset / 5 * 4);
			System.out.printf("Operations time taken in average = %.6f" + " sec\n",  estimatedTime / 5);
			break;
		}
	}
}                                           
