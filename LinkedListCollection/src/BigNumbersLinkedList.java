import java.util.LinkedList;
import java.util.ListIterator;

public class BigNumbersLinkedList {

	public static LinkedList<Integer> BigNumber(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		LinkedList<Integer> resList = new LinkedList<>();

		if (list1.size() > list2.size()) {
			while (list2.size() != list1.size()) {
				list2.addFirst(0);
			}
		} else if (list1.size() < list2.size()) {
			while (list1.size() != list2.size()) {
				list1.addFirst(0);
			}
		}

		ListIterator<Integer> it1 = list1.listIterator(list1.size());
		ListIterator<Integer> it2 = list2.listIterator(list2.size());
		boolean increase = false;
		boolean addedOne = false;

		while (it1.hasPrevious()) {
			if (increase == true) {
				if ((it1.previous() + it2.previous() + 1) > 9) {
					it1.next();
					it2.next();
					resList.addFirst((it1.previous() + it2.previous() + 1) % 10);
					addedOne = true;
				} else {
					it1.next();
					it2.next();
					resList.addFirst(it1.previous() + it2.previous() + 1);
					addedOne = true;
				}
			} else {
				if ((it1.previous() + it2.previous()) > 9) {
					it1.next();
					it2.next();
					resList.addFirst((it1.previous() + it2.previous()) % 10);
					addedOne = false;
				} else {
					it1.next();
					it2.next();
					resList.addFirst(it1.previous() + it2.previous());
					addedOne = false;
				}
			}

			it1.next();
			it2.next();

			if (addedOne == true) {
				if ((it1.previous() + it2.previous()) > 8) {
					increase = true;
				} else {
					increase = false;
				}
			} else {
				if ((it1.previous() + it2.previous()) > 9) {
					increase = true;
				} else {
					increase = false;
				}
			}
		}
		
		if (increase == true) {
			resList.addFirst(1);
		}
		
		return resList;
	}

	public static void printList(LinkedList<Integer> list) {
		for (Object i : list) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();

		list1.add(9);
		list1.add(9);
		list1.add(7);
		list1.add(8);

		list2.add(5);
		list2.add(9);
		list2.add(6);

		System.out.println("See the lists: ");
		
		printList(list1);
		printList(list2);

		System.out.println("Res list");
		
		printList(BigNumber(list1, list2));
	}

}
