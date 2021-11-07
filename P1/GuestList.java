import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuestList implements Serializable {

	Scanner sc = new Scanner(System.in);
	private ArrayList<Guest> mainList;
	private final int mainListMax; // the max number of ppl allowed at the event, provided when the GuestList is
									// initialized
	private static final long serialVersionUID = 1L;
	private ArrayList<Guest> waitList;

	public ArrayList<Guest> getMainList() {
		return mainList;
	}

	public ArrayList<Guest> getWaitList() {
		return waitList;
	}

	public int getMainListCounter() {
		return this.mainList.size();
	}

	public int getWaitListCounter() {
		return this.waitList.size();
	}

	public int getAvailableSeats() {
		return mainListMax - this.mainList.size();
	}

	public int getTotalNumberOfGuests() {
		return this.waitList.size() + this.mainList.size();
	}

	public GuestList(int mainListMax) {
		this.mainListMax = mainListMax; // our first command will always be creating a GuestList with a specified number
										// of max ppl.
		mainList = new ArrayList<Guest>();
		waitList = new ArrayList<Guest>();
	}

	// shows us the mainlist of guests
	public void showMainlist() {
		showList(this.mainList, "This is the current guestlist:", "The guestlist is blank.");
	}

	// shows us the waitlist of guests
	public void showWaitList() {
		showList(this.waitList, "This is the current waitlist:", "The waitlist is blank.");
	}

	// backend for the showing of main/wait lists
	private void showList(ArrayList<Guest> arr, String firstMessage, String secondMessage) {
		if (arr.size() != 0) {
			System.out.println(firstMessage);
			for (Guest g : arr) {
				System.out.println(g.toString());
			}
		} else {
			System.out.println(secondMessage);
		}
	}

	public int addGuest(Guest guest) {
		// first we need to check if the Guest is already added in this event.
		if (findByName(guest) != null || findByEmail(guest) != null || findByPhone(guest) != null) {
			System.out.println("Error! The person you are trying to add is already registered");
			return -1;
		}
		// then we need to check if the mainList has spaces open or not. if not, we add
		// the guest to the waitlist
		if (this.mainList.size() < mainListMax) {
			mainList.add(guest);
			System.out.println(guest.toString() + "\nCongrats! Your spot at the event is confirmed!");
			return 0;
		} else {
			waitList.add(guest);

			System.out.println(guest.toString() + "\nYou have succesfully been added to the waitlist on spot number: "
					+ this.waitList.size() + ".\nWe will contact you when a spot is available.");
			return this.waitList.size();
		}
	}

	// these are search methods, by name/email/phone, which search a generic
	// arraylist in a smart way (against a downcasted guest object basically)
	private Guest findByName(Guest guest, ArrayList<Guest> arr) {
		if (arr == null || arr.size() == 0 || guest == null) {
			return null;
		}
		for (Guest g : arr) {
			if (guest.getFirstName().equalsIgnoreCase(g.getFirstName())
					&& guest.getLastName().equalsIgnoreCase(g.getLastName())) {
				return g;
			}
		}
		return null;
	}

	private Guest findByEmail(Guest guest, ArrayList<Guest> arr) {
		if (arr == null || arr.size() == 0 || guest == null) {
			return null;
		}
		for (Guest g : arr) {
			if (guest.getEmail().equalsIgnoreCase(g.getEmail())) {
				return g;
			}
		}
		return null;
	}

	private Guest findByPhone(Guest guest, ArrayList<Guest> arr) {
		if (arr == null || arr.size() == 0 || guest == null) {
			return null;
		}
		for (Guest g : arr) {
			if (guest.getPhone().equalsIgnoreCase(g.getPhone())) {
				return g;
			}
		}
		return null;
	}

	// same as above, only this time auto searches on both lists, for easier
	// implementation in the general methods
	private Guest findByName(Guest auxGuest) {
		ArrayList<Guest> auxArr = new ArrayList<Guest>(this.mainList);
		auxArr.addAll(this.waitList);
		return findByName(auxGuest, auxArr);
	}

	private Guest findByPhone(Guest auxGuest) {
		ArrayList<Guest> auxArr = new ArrayList<Guest>(this.mainList);
		auxArr.addAll(this.waitList);
		return findByPhone(auxGuest, auxArr);
	}

	private Guest findByEmail(Guest auxGuest) {
		ArrayList<Guest> auxArr = new ArrayList<Guest>(this.mainList);
		auxArr.addAll(this.waitList);
		return findByEmail(auxGuest, auxArr);
	}

	// remove given guest from main list. + adding 1st on waitlist to mainlist logic
	public boolean removeGuestMainList(Guest guest) {
		this.mainList.remove(guest);
		if (this.waitList.size() > 0) {
			this.mainList.add(this.waitList.get(0));
			System.out.println(this.waitList.get(0).toString()
					+ " \nCongrats! You have been moved to the MainList and your spot at the event is confirmed!");
			this.waitList.remove(0);
		}
		return true;
	}

	// remove given guest from main list.
	public boolean removeGuestWaitList(Guest guest) {
		return this.waitList.remove(guest);
	}

	// Guest scanner read from keyboard + some validation on email/phone
	public Guest readGuestInfo() {
		Guest ogGuest = new Guest();
		System.out.println("Please input the first name: ");
		String aux = sc.next();
		ogGuest.setFirstName(aux);
		System.out.println("Please input the last name: ");
		aux = sc.next();
		ogGuest.setLastName(aux);
		System.out.println("Please input the email address: ");
		aux = sc.next();
		if (!aux.contains("@")) {
			throw new EmailAlreadyExistsException("Error! Please introduce a valid email address.");
		}
		ogGuest.setEmail(aux);
		System.out.println("Please input the phone number (0XXX XXX XXX format): ");
		aux = sc.next();
		while (aux.length() != 10 && !aux.startsWith("0")) {
			System.out.println("Error! Please respect the phone number format. Try again.");
			aux = sc.next();
		}
		ogGuest.setPhone(aux);
		return ogGuest;
	}

	// the following methods return a generic guest with a user inputted set of
	// data, names, phone or email, to be used for other methods
	public Guest inputByName() {
		System.out.println("Please input the first name:");
		String firstname = sc.next();
		System.out.println("Please input the last name:");
		String lastname = sc.next();
		Guest auxGuest = new Guest();
		auxGuest.setFirstName(firstname);
		auxGuest.setLastName(lastname);
		return auxGuest;
	}

	public Guest inputByEmail() {
		System.out.println("Please input the email:");
		String email = sc.next();
		Guest auxGuest = new Guest();
		auxGuest.setEmail(email);
		return auxGuest;
	}

	public Guest inputByPhone() {
		System.out.println("Please input the phone number:");
		String phone = sc.next();
		Guest auxGuest = new Guest();
		auxGuest.setPhone(phone);
		return auxGuest;
	}

	// this is the main update method. It searches based on user selection and
	// input, then if the user is found, allows the user to modify any field
	public void updateGuestGeneral() {
		System.out.println("Based on what do you want to search the guestlist? " + "\n1. First + Last name "
				+ "\n2. Email\r\n" + "3. phone\r\n");
		String selection = sc.next();
		Guest foundGuest = null;
		while (!selection.equals("quit")) {
			switch (selection) {
			case "1":
				Guest nameGuest = inputByName();
				foundGuest = findByName(nameGuest);
				if (foundGuest != null) {
					updateGuestData(foundGuest);
					System.out.println("Succes! The guest has data has been updated.");
				} else {
					System.out.println("Error! Guest not found! ");
				}
				return;
			case "2":
				Guest emailGuest = inputByEmail();
				foundGuest = findByEmail(emailGuest);
				if (foundGuest != null) {
					updateGuestData(foundGuest);
					System.out.println("Succes! The guest has data has been updated.");
				} else {
					System.out.println("Error! Guest not found! ");
				}
				return;
			case "3":
				Guest phoneGuest = inputByPhone();
				foundGuest = findByPhone(phoneGuest);
				if (foundGuest != null) {
					updateGuestData(foundGuest);
					System.out.println("Succes! The guest has data has been updated.");
				} else {
					System.out.println("Error! Guest not found! ");
				}
				return;
			default:
				System.out.println("Error! Choice not valid. Input 'quit' to exit this command");
				break;
			}
			selection = sc.next();
		}
	}

	// This allows for the user to select the sub-set of data on which to search,
	// and then outputs either that the guest is added or not.
	public void checkGuestGeneral() {
		System.out.println("Based on what do you want to search the guestlist? " + "\n1. First + Last name "
				+ "\n2. email\r\n" + "3. phone\r\n");
		String selection = sc.next();
		while (!selection.equals("quit")) {
			switch (selection) {
			case "1":
				Guest nameGuest = inputByName();
				if (findByName(nameGuest) != null) {
					System.out.println("The guest is already added!");
				} else {
					System.out.println("Error! Guest not found! ");
				}
				return;
			case "2":
				Guest emailGuest = inputByEmail();
				if (findByEmail(emailGuest) != null) {
					System.out.println("The guest is already added!");
				} else {
					System.out.println("Error! Guest not found! ");
				}
				return;
			case "3":
				Guest phoneGuest = inputByPhone();
				if (findByPhone(phoneGuest) != null) {
					System.out.println("The guest is already added!");
				} else {
					System.out.println("Error! Guest not found! ");
				}
				return;
			default:
				System.out.println("Error! Choice not valid. Input 'quit' to exit this command");
				break;
			}
			selection = sc.next();
		}
	}

	// submethod of the main update function, allows for any field of a gives guest
	// to be modified.
	public void updateGuestData(Guest auxGuest) {
		System.out.println("Success, the guest was found! What field do you wish to modify? " + "\n1. First name "
				+ "\n2. Last name\r\n" + "3. email\r\n" + "4. phone number");
		String selection = sc.next();
		while (!selection.equals("quit")) {
			switch (selection) {
			case "1":
				System.out.println("Input the new first name:");
				String firstName = sc.next();
				auxGuest.setFirstName(firstName);
				return;
			case "2":
				System.out.println("Input the new last name:");
				String lastName = sc.next();
				auxGuest.setLastName(lastName);
				return;
			case "3":
				System.out.println("Input the new email address:");
				String email = sc.next();
				auxGuest.setEmail(email);
				return;
			case "4":
				System.out.println("Input the new phone number:");
				String phoneNumber = sc.next();
				auxGuest.setPhone(phoneNumber);
				return;
			default:
				System.out.println("Error! Choice not valid. Input 'quit' to exit this command");
				break;
			}
			selection = sc.next();
		}
	}

	// main remove method, checks what user input is wanted, based on that searches
	// guestlist and removes if guest is found
	public boolean removeGuest() {
		System.out.println("Based on what do you want to search the guestlist? " + "\n1. First + Last name "
				+ "\n2. email\r\n" + "3. phone\r\n");
		int selection = sc.nextInt();
		Guest foundGuest = null;
		switch (selection) {
		case 1:
			Guest nameGuest = inputByName();
			foundGuest = findByName(nameGuest, this.mainList);
			if (foundGuest != null) {
				System.out.println("Success! The Guest has been removed!");
				this.removeGuestMainList(foundGuest);
				return true;
			}
			foundGuest = findByName(nameGuest, this.waitList);
			if (foundGuest != null) {
				System.out.println("Success! The Guest has been removed!");
				this.removeGuestWaitList(foundGuest);
				return true;
			}
			System.out.println("Error! Guest not found! ");
			return false;
		case 2:
			Guest emailGuest = inputByEmail();
			foundGuest = findByEmail(emailGuest, this.mainList);
			if (foundGuest != null) {
				System.out.println("Success! The Guest has been removed!");
				this.removeGuestMainList(foundGuest);
				return true;
			}
			foundGuest = findByEmail(emailGuest, this.waitList);
			if (foundGuest != null) {
				System.out.println("Success! The Guest has been removed!");
				this.removeGuestWaitList(foundGuest);
				return true;
			}
			System.out.println("Error! Guest not found! ");
			return false;
		case 3:
			Guest phoneGuest = inputByPhone();
			foundGuest = findByPhone(phoneGuest, this.mainList);
			if (foundGuest != null) {
				System.out.println("Success! The Guest has been removed!");
				this.removeGuestMainList(foundGuest);
				return true;
			}
			foundGuest = findByPhone(phoneGuest, this.waitList);
			if (foundGuest != null) {
				System.out.println("Success! The Guest has been removed!");
				this.removeGuestWaitList(foundGuest);
				return true;
			}
			System.out.println("Error! Guest not found! ");
			return false;
		default:
			System.out.println("Error! Choice not valid.");
			break;
		}
		return false;
	}

	// partial search, returns a list of the Guests viable. - was not sure if this
	// is how we need to return info here, so made it like this.
	public void partialSearch() {
		ArrayList<Guest> result = new ArrayList<Guest>();
		System.out.println("Please input what you want to search: ");
		String search = sc.next();
		search.toLowerCase();
		for (Guest g : mainList) {
			String aux = g.getFirstName() + " " + g.getLastName() + " " + g.getEmail() + " " + g.getPhone();
			aux.toLowerCase();
			if (aux.contains(search)) {
				result.add(g);
			}
		}
		for (Guest g : result) {
			System.out.println(g.toString());
		}
	}

	public void writeToBinaryFileMainList(List<Guest> data) throws IOException {
		try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("mainlist.dat")))) {
			binaryFileOut.writeObject(data);
		}
	}
	
	public void writeToBinaryFileWaitList(List<Guest> data) throws IOException {
		try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("waitlist.dat")))) {
			binaryFileOut.writeObject(data);
		}
	}

}
