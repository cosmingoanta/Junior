import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeworkEx3 {

	public static void main(String[] args) throws IOException {

		ArrayList<String> arr = new ArrayList<>();
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("TextInputEx3.txt")))) {
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				arr.add(nextLine);
			}
		}

		String shortestWord = arr.get(0);
		String longestWord = arr.get(0);
		String bestWord = arr.get(0);
		String worstWord = arr.get(0);

		for (String s : arr) {
			if (s.compareTo(bestWord) < 0) {
				bestWord = s;
			}
			if (s.compareTo(worstWord) > 0) {
				worstWord = s;
			}
			if (s.length() > longestWord.length()) {
				longestWord = s;
			}
			if (s.length() < shortestWord.length()) {
				shortestWord = s;
			}
		}

		try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("TextOutputEx3.txt"))) {
			outputFile.append("Shortest word is: " + shortestWord);
			outputFile.newLine();
			outputFile.append("Longest word is: " + longestWord);
			outputFile.newLine();
			outputFile.append("bestWord word is: " + bestWord);
			outputFile.newLine();
			outputFile.append("worstWord word is: " + worstWord);
			outputFile.newLine();
			outputFile.append("Number of words is: " + arr.size());
			outputFile.newLine();
		}
	}

}
