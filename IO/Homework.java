import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Homework {

	public static void main(String[] args) throws IOException {

		//ex1
		Scanner sc = new Scanner(System.in);
		System.out.println("Introdu primul cuvant: ");
		String cuv1 = sc.next();
		System.out.println("Introdu al doilea cuvant: ");
		String cuv2 = sc.next();
		ArrayList<String> text = new ArrayList<String>();
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("TextInput.txt")))) {
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				text.add(nextLine);
			}
		}

		for (int i = 0; i < text.size(); i++) {
			String aux = text.get(i);
			while (aux.contains(cuv1)) {
				String aux2 = aux.replace(cuv1, cuv2);
				aux = aux2;
			}
			text.set(i, aux);
		}

		try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("TextOutput.txt"))) {
			for (int i = 0; i < text.size(); i++) {
				outputFile.append(text.get(i));
				outputFile.newLine();
			}
		}

		// ex2

		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("TextInput.txt")))) {
			try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("TextOutput2.txt"))){
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				String newLine = nextLine.replace(cuv1, cuv2);
				outputFile.append(newLine);
				outputFile.newLine();
				}
			}
		}
	}

}
