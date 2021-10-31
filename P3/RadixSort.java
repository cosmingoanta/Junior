import java.util.LinkedList;

public class RadixSort {

	private static LinkedList[] q = { new LinkedList(), // 0
			new LinkedList(), // 1
			new LinkedList(), // 2
			new LinkedList(), // 3
			new LinkedList(), // 4
			new LinkedList(), // 5
			new LinkedList(), // 6
			new LinkedList(), // 7
			new LinkedList(), // 8
			new LinkedList() // 9
	};

	public static Object[] sort(Object[] list) {
		// Get the maximum number of digits.
		int maxDigits = getMaxDigits(list);

		// Iterate through the radix depending on max digits.
		for (int i = 1; i <= maxDigits; i++) {

			// Iterate through every number.
			int radix;
			for (int j = 0; j < list.length; j++) {
				// Figure out what queue to put it into.
				radix = getDigitAt((int)list[j], i);
				// Put it into it's queue according to the radix.
				q[radix].offer(list[j]);
			}

			// Go through the queues and put the numbers back into the list.
			int a = 0;
			for (int k = 0; k < q.length; k++) {
				// Go through every element in the queue.
				while (q[k].peek() != null) {
					list[a++] = q[k].poll();
				}
			}
		}

		// Return the list, it is now sorted.
		return list;

	}

	public static int getMaxDigits(Object list[]) {
		// Define the max digits.
		int maxDigits = 0;

		// Iterate through the list.
		int digits = 0;
		for (int i = 0; i < list.length; i++) {

			int aux = (int) list[i];
			while (aux != 0) {
				aux = aux / 10;
				digits++;
			}
			if (digits > maxDigits) {
				maxDigits = digits;
			}
			digits = 0;
		}

		// Return the max digits.
		return maxDigits;
	}

	public static int getDigitAt(int number, int radix) {
		int aux = (int) Math.pow(10, radix - 1);
		number = number / aux;
		return number % 10;
	}

	public static void main(String[] args) {
		Object[] list = new Object[5];
		list[0] = 231231231;
		list[1] = 8234;
		list[2] = 456;
		list[3] = 12341231;
		list[4] = 1;
		System.out.println("List is: ");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}

		Object[] sortedList = sort(list);
		System.out.println("\nAfter sorting: ");
		for (int i = 0; i < list.length; i++) {
			System.out.print(sortedList[i] + " ");
		}

	}
}