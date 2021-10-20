
public class Main {

	public static void main(String[] args) {
		MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<Integer>();
		list.add(12);
		list.add(45);
		list.add(46);
		list.add(7,7);
	
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		
		list.remove(45);
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		
		MyDoubleLinkedList<Integer> list2 = new MyDoubleLinkedList<Integer>();
		
		list2.add(123);
		list2.add(456);
		list.addAll(6, list2);
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		
		System.out.println(list.peek());
		list.push(100);
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println("");
		
		System.out.println(list.pop());
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
		
		System.out.println(list.pop());
		
		for(int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");
	}

}
