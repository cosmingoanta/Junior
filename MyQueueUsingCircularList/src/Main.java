
public class Main {

	public static void main(String[] args) {
		MyQueueUsingCircularList<Integer> list = new MyQueueUsingCircularList<Integer>();
		list.add(12);
		list.add(45);
		list.add(46);
		list.add(47);
		list.add(48);
		list.add(48);
		list.add(48);
		list.add(4,4);
	
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		
		MyQueueUsingCircularList<Integer> list2 = new MyQueueUsingCircularList<Integer>();
		
		list2.add(123);
		list2.add(456);
		list.addAll(list2);
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		list.set(1,10101);
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		list.add(5,4123123);
		list.set(2,10101);
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		
		System.out.println(list.lastIndexOf(48));
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		list.remove(123);
		
		
		
		System.out.println("----------");
		list.prin();
		System.out.println("");
		
		System.out.println(list.peek());
		list.prin();
		System.out.println(list.enqueue(1000));
		list.prin();
		
		
		
		System.out.println(list.dequeue());
		
		list.prin();
		
	}

}
