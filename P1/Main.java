import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		// For exception homework, try initial max waitlist capacity.
		int max;
		while (true) {
			try {
				System.out.println("Please input the max. number of people for your event:");
				max = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("You have not entered a correct int value. Retry.");
			}
		}

		GuestList guestlist = new GuestList(max);
		System.out.println("" + "help - Shows the command list\r\n" + "add - Add a new Guest\r\n"
				+ "check - Check if a Guest is already added\r\n" + "remove - Delete an existing Guest\r\n"
				+ "update - Update Guest info\r\n" + "guests - The current Guest list\r\n"
				+ "waitlist - The current waiting list\r\n" + "available - Number of available spots\r\n"
				+ "guests_no - Number of people participating at the event\r\n"
				+ "waitlist_no - Number of people in the wating list\r\n"
				+ "subscribe_no - Total number of guests invited\r\n"
				+ "search - Search the guestlist based on partial info\r\n" + "quit - Close the app");
		System.out.println("Please enter a command: ");
		String command = sc.next();
		while (!command.equals("quit")) {
			switch (command) {
			case "help":
				System.out.println("" + "help - Shows the command list\r\n" + "add - Add a new Guest\r\n"
						+ "check - Check if a Guest is already added\r\n" + "remove - Delete an existing Guest\r\n"
						+ "update - Update Guest info\r\n" + "guests - The current Guest list\r\n"
						+ "waitlist - The current waiting list\r\n" + "available - Number of available spots\r\n"
						+ "guests_no - Number of people participating at the event\r\n"
						+ "waitlist_no - Number of people in the wating list\r\n"
						+ "subscribe_no - Total number of guests invited\r\n"
						+ "search - Search the guestlist based on partial info\r\n" + "quit - Close the app");
				break;
			case "add":
				guestlist.addGuest(guestlist.readGuestInfo());
				break;
			case "check":
				guestlist.checkGuestGeneral();
				break;
			case "remove":
				guestlist.removeGuest();
				break;
			case "update":
				guestlist.updateGuestGeneral();
				break;
			case "guests":
				guestlist.showMainlist();
				break;
			case "waitlist":
				guestlist.showWaitList();
				break;
			case "available":
				System.out.println(guestlist.getAvailableSeats());
				break;
			case "guests_no":
				System.out.println(guestlist.getMainListCounter());
				break;
			case "waitlist_no":
				System.out.println(guestlist.getWaitListCounter());
				break;
			case "subscribe_no":
				System.out.println(guestlist.getTotalNumberOfGuests());
				break;
			case "search":
				guestlist.partialSearch();
				break;
			case "quit":
				break;
			default:
				System.out.println("You have entered an invalid request. Please retry.");
				break;
			}
			System.out.println("Please enter a command: ");
			command = sc.next();
		}
		guestlist.writeToBinaryFileMainList(guestlist.getMainList());
		guestlist.writeToBinaryFileWaitList(guestlist.getWaitList());
		System.out.println("- The app will now close.");
		sc.close();
	}

}
