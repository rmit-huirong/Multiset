import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class DataGenerator {
	public DataGenerator(int size) {
		Random r = new Random();
		String fileName = "fixedSet.txt";
		PrintWriter outStream = null;
		String data;
		int i, j, max;
		try {
			outStream = new PrintWriter(fileName);
		}
		catch(FileNotFoundException e){
		System.out.println(e);
		System.exit(0);
		}
		//Scanner input = new Scanner(System.in);
		//System.out.print("The size of the set:");
		//size = input.nextInt();
		for (i = 0; i < size; ++i) {
			data = "";
			max = r.nextInt(5) + 3;
			for (j = 0; j < max; j++) {
				data += (char) (r.nextInt(26) + 'a');
			}
			outStream.write(data + '\n');
		}
		outStream.close();
	}
	public DataGenerator(int size, Multiset<String> multiset){
		Random r = new Random();
		String data;
		int i, j, max;
		//Scanner input = new Scanner(System.in);
		//System.out.print("The size of the set:");
		//size = input.nextInt();
		for (i = 0; i < size; ++i) {
			data = "";
			max = r.nextInt(5) + 3;
			for (j = 0; j < max; j++) {
				data += (char) (r.nextInt(26) + 'a');
			}
			multiset.add(data);
		}
	}
}
