
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HashmapExample{

	private static Scanner sc = new Scanner(System.in);
	private static HashMap<String, String> rentedCars = new HashMap<String, String>(100, 0.5f);
	private static HashMap<String, RentedCars> owners = new HashMap<String, RentedCars>(100, 0.5f);
	private static final long serialVersionUID = 1L;
	
	private static String getPlateNo() {
		System.out.println("Introduceti numarul de inmatriculare:");
		return sc.nextLine();
	}

	private static String getOwnerName() {
		System.out.println("Introduceti numele proprietarului:");
		return sc.nextLine();
	}

	// search for a key in hashtable
	private boolean isCarRent(String plateNo) {
		return rentedCars.containsKey(plateNo);
	}

	// get the value associated to a key + exception homework
	private String getCarRent(String plateNo) {
		if (rentedCars.get(plateNo) == null) {
			throw new NoSuchElementException("Masina nu exista.");
		}
		return rentedCars.get(plateNo);
	}

	// add a new (key, value) pair
	private void rentCar(String plateNo, String ownerName) {
		String aux = rentedCars.put(plateNo, ownerName);
		if (aux == null) {
			System.out.println("Masina a fost adaugata.");
		} else {
			System.out.println("Masina cu numarul de inmatriculare: " + plateNo + " este acum a noului proprietar: "
					+ getCarRent(plateNo));
		}
		if (owners.containsKey(ownerName)) {
			if (aux == null) {
				owners.get(ownerName).addCar(plateNo);
			} else {
				owners.get(ownerName).addCar(plateNo);
				owners.get(aux).removeCar(plateNo);
			}
		} else {
			RentedCars cars = new RentedCars();
			cars.addCar(plateNo);
			if (aux == null) {
				owners.put(ownerName, cars);
			} else {
				owners.get(aux).removeCar(plateNo);
				owners.put(ownerName, cars);
			}
		}
	}

	// remove an existing (key, value) pair + exception hw
	private void returnCar(String plateNo) {
		String aux = rentedCars.remove(plateNo);
		if (aux == null) {
			throw new NoSuchElementException("Masina nu exista.");
		}
		System.out.println("Masina a fost stearsa cu succes.");
		owners.get(aux).removeCar(plateNo);

	}

	// get number of cars which are rented
	private void returnSize() {
		System.out.println("The total number of rented cars is: " + this.rentedCars.size());
	}

	private static void printCommandsList() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi");
		System.out.println("add          - Adauga o noua pereche (masina, sofer)");
		System.out.println("check        - Verifica daca o masina este deja luata");
		System.out.println("totalRented  - Returneaza numarul total de masini inchiriate");
		System.out.println("remove       - Sterge o masina existenta din hashtable");
		System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
		System.out.println("quit         - Inchide aplicatia");
	}

	public void run() {
		boolean quit = false;
		while (!quit) {
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			String command = sc.nextLine();
			switch (command) {
			case "help":
				printCommandsList();
				break;
			case "add":
				rentCar(getPlateNo(), getOwnerName());
				break;
			case "check":
				System.out.println(isCarRent(getPlateNo()));
				break;
			case "remove":
				returnCar(getPlateNo());
				break;
			case "getOwner":
				System.out.println(getCarRent(getPlateNo()));
				break;
			case "totalRented":
				returnSize();
				break;
			case "quit":
				System.out.println("Aplicatia se inchide...");
				quit = true;
				break;
			default:
				System.out.println("Unknown command. Choose from:");
				printCommandsList();
			}
		}
	}
	
	public static void writeToBinaryMainCars(HashMap<String, String> cars) throws IOException {
		try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("mainCars.dat")))) {
			binaryFileOut.writeObject(cars);
		}
	}
	
	public static void writeToBinaryOwners(HashMap<String, RentedCars> cars) throws IOException {
		try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("owners.dat")))) {
			binaryFileOut.writeObject(cars);
		}
	}

	public static void main(String[] args) throws IOException {

		// create and run an instance (for test purpose)
		new HashmapExample().run();
		writeToBinaryMainCars(rentedCars);
		writeToBinaryOwners(owners);

	}
}
