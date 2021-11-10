import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomeworkEx2 {

	public static void main(String[] args) throws IOException {
		int nr1, nr2;
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("TextInputEx2.1.txt")))) {
			try (Scanner scanner2 = new Scanner(new BufferedReader(new FileReader("TextInputEx2.2.txt")))) {
				try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("TextOutputEx2v2.txt"))) {
					nr1 = scanner.nextInt();
					nr2 = scanner2.nextInt();
					while (scanner.hasNext() && scanner2.hasNext()) {
						if (nr1 < nr2) {
							outputFile.append(nr1 + " ");
							nr1 = scanner.nextInt();
						} else {
							outputFile.append(nr2 + " ");
							nr2 = scanner2.nextInt();
						}
					}
					if (nr1 < nr2) {
						outputFile.append(nr1 + " " + nr2 + " ");
					} else {
						outputFile.append(nr2 + " " + nr1 + " ");
					}

					while (scanner.hasNext()) {
						outputFile.append(scanner.nextInt() + " ");
					}

					while (scanner2.hasNext()) {
						outputFile.append(scanner2.nextInt() + " ");
					}
				}
			}
		}

		System.out.println("fin");
	}

}
