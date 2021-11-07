import java.io.FileWriter;
import java.io.IOException;

public class IOExample {
	private static String[] getData() {
		String[] cities = { "London", "Bucharest", "Berlin", "Moscow" };
		return cities;
	}

	public static void main(String[] args) {
		String[] dataToWrite = getData();
		FileWriter outputFile = null;
		try {
			// I. open the file (for writing)
			// Note: if the file does not exist, it is created automatically
			outputFile = new FileWriter("output.txt");

			// II. Write the data to file
			for (String city : dataToWrite) {
				outputFile.write(city + " ");
			}

		} catch (IOException e) {
			System.out.println("Something wrong has happened...");
			e.printStackTrace();
		} finally {
			try {
				if (outputFile != null) {
					outputFile.close();
				}
			} catch (IOException e) {
				System.out.println("Exception raised when attempting to close the file");
				e.printStackTrace();
			}
		}
	}
}
