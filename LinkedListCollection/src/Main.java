import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void removeDuplicates(LinkedList<Integer> list) {
		ListIterator<Integer> it = list.listIterator();
		int p1, p2;
		while (it.hasNext()) {
			p1 = it.next();
			if (it.hasNext()) {
				p2 = it.next();
				if (p1 == p2) {
					it.remove();
				}
				it.previous();
			}
		}
	}

	public static void removeDuplicates2(LinkedList<Integer> list) {
		ListIterator<Integer> it = list.listIterator();
		int p1, p2;
		int aux = 0;

		while (it.hasNext()) {
			p1 = it.next();
			if (p1 == aux) {
				it.remove();
				if (it.hasPrevious()) {
					it.previous();
				}
			}
			if (it.hasNext()) {
				p2 = it.next();
				if (p1 == p2) {
					aux = p1;
					it.remove();
					if (it.previous() != null) {
						it.remove();
					}
				}
				if (it.hasPrevious()) {
					it.previous();
				}
			}
		}
	}

	private static Scanner scanner = new Scanner(System.in);

	public static void printManual() {
		System.out.println("Options are:\n" + "\t0 - quit\n" + "\t1 - next element\n" + "\t2 - previous element\n"
				+ "\t3 - this list\n");
	}

	public static void iterate(LinkedList<String> towns) {
		ListIterator<String> li = towns.listIterator();
		printManual();
		boolean moveForward = true;

		while (true) {

			int option = scanner.nextInt();
			switch (option) {
			case 0:
				return;
			case 1:
				if (!moveForward) { // if we iterated backwards previously
					moveForward = true;
					if (li.hasNext()) {
						li.next(); // move one position forward to skip already printed
									// element (i.e. in the previous backward step)
					}
				}
				if (li.hasNext()) {
					System.out.println(li.next());
				}
				break;
			case 2:
				if (moveForward) { // if we iterated forward previously
					moveForward = false;
					if (li.hasPrevious()) {
						li.previous(); // move one position backwards to skip already printed
						// element (i.e. in the previous forward step)
					}
				}
				if (li.hasPrevious()) {
					System.out.println(li.previous());
				}
				break;
			case 3:
				printManual();
				break;
			default:
				System.out.println("Unknown command");
				printManual();
				break;
			}
		}
	}

	public static int countOccurrences(List<String> list, String key) {
		int counter = 0;
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().equals(key)) {
				counter++;
			}
		}
		return counter;
	}

	public static void main(String[] args) {
		LinkedList<String> towns = new LinkedList<>();
		ArrayList<String> townsArr = new ArrayList<>();
		LinkedList<Integer> intList = new LinkedList<>();
		
		intList.add(1);
		intList.add(1);
		intList.add(1);
		intList.add(2);
		intList.add(2);
		intList.add(3);
		intList.add(3);
		intList.add(4);
		intList.add(4);
		intList.add(4);

		for (int t : intList) {
			System.out.print(t + " ");
		}
		System.out.println();

		removeDuplicates(intList);

		for (int t : intList) {
			System.out.print(t + " ");
		}
		System.out.println();

		System.out.println();

		LinkedList<Integer> intList2 = new LinkedList<>();
		
		intList2.add(1);
		intList2.add(1);
		intList2.add(2);
		intList2.add(2);
		intList2.add(2);
		intList2.add(9);
		intList2.add(9);
		intList2.add(10);
		intList2.add(11);

		for (int t : intList2) {
			System.out.print(t + " ");
		}

	
		removeDuplicates2(intList2);

		System.out.println();

		for (int t : intList2) {
			System.out.print(t + " ");
		}
		System.out.println();
		System.out.println();
		InsertInOrder<String> orderedTownsList = new InsertInOrder<>();
		InsertInOrder<Town> orderedTownsObjList = new InsertInOrder<>();

		Town Timisoara = new Town("Timisoara", 100);
		Town Arad = new Town("Arad", 150);
		Town Ploiesti = new Town("Ploiesti", 30);
		Town Bucuresti = new Town("Bucuresti", 0);

		orderedTownsObjList.addInOrderedList(Timisoara);
		orderedTownsObjList.addInOrderedList(Arad);
		orderedTownsObjList.addInOrderedList(Ploiesti);
		orderedTownsObjList.addInOrderedList(Bucuresti);

		System.out.println(orderedTownsObjList);

		System.out.println();
		System.out.println();
		
		// populate list with towns:
		orderedTownsList.addInOrderedList("Suceava");
		orderedTownsList.addInOrderedList("Cluj");
		orderedTownsList.addInOrderedList("Targu Jiu");
		orderedTownsList.addInOrderedList("Bucuresti");

		System.out.println(orderedTownsList);

		orderedTownsList.addInOrderedList("Brasov");
		orderedTownsList.addInOrderedList("Bacau");
		orderedTownsList.addInOrderedList("Suceava");
		orderedTownsList.addInOrderedList("Bucuresti");
		orderedTownsList.addInOrderedList("Brasov");
		orderedTownsList.addInOrderedList("Brasov");

		System.out.println(orderedTownsList);

		towns.add("Cluj");
		towns.add("Targu Jiu");
		towns.add("Suceava");
		towns.add("Bucuresti");
		towns.add("Brasov");
		towns.add("Brasov");

		townsArr.add("Cluj");
		townsArr.add("Targu Jiu");
		townsArr.add("Suceava");
		townsArr.add("Bucuresti");
		townsArr.add("Brasov");
		townsArr.add("Brasov");

		System.out.println(towns.contains("Bacau"));
		System.out.println(towns.contains("Cluj"));
		System.out.println(towns.contains("brasov"));

		System.out.println(countOccurrences(towns, "Brasov"));
		System.out.println(countOccurrences(townsArr, "Brasov"));

		iterate(towns);
	}

}
