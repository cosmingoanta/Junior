
public class MyQueueUsingCircularList<E> {
	
	public class MyQueueUsingCircularListNode<E> {
		private E value;
		private MyQueueUsingCircularListNode<E> next;
		private MyQueueUsingCircularListNode<E> previous;

		public MyQueueUsingCircularListNode(E value, MyQueueUsingCircularListNode<E> next, MyQueueUsingCircularListNode<E> previous) {
			this.value = value;
			this.next = next;
			this.previous = previous;
		}

		public MyQueueUsingCircularListNode(E value) {
			this(value, null, null);
		}

		public MyQueueUsingCircularListNode() {
		}

		public void value(E value) {
			this.value = value;
		}

		public E value() {
			return value;
		}

		public MyQueueUsingCircularListNode<E> next() {
			return next;
		}

		public void next(MyQueueUsingCircularListNode<E> next) {
			this.next = next;
		}

		public MyQueueUsingCircularListNode<E> prev() {
			return previous;
		}

		public void prev(MyQueueUsingCircularListNode<E> previous) {
			this.previous = previous;
		}
	}

	private MyQueueUsingCircularListNode<E> head = null;
	private MyQueueUsingCircularListNode<E> tail = null;
	private int size = 0;
	
	public int getSize() {
		return size;
	}

	MyQueueUsingCircularList() {
		head = null;
		tail = null;
		size = 0;
	}

	MyQueueUsingCircularList(MyQueueUsingCircularList<E> list) {
		MyQueueUsingCircularListNode<E> copyHead = list.head;
		while (copyHead.next != head) {
			add(copyHead.value());
			copyHead = copyHead.next();
			this.size++;
		}
	}

	public boolean add(E e) {
		MyQueueUsingCircularListNode<E> node = new MyQueueUsingCircularListNode<E>(e);
		if (head == null) {
			head = node;
			tail = head;
		} else {
			tail.next(node);
			node.prev(tail);
			tail = tail.next();
		}
		tail.next(head);
		head.prev(tail);
		this.size++;
		return true;
	}

	public void add(int index, E e) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		MyQueueUsingCircularListNode<E> node = new MyQueueUsingCircularListNode<E>(e);
		int counter = 0;
		if (index >= this.size) {
			tail.next(node);
			node.prev(tail);
			tail = tail.next();
		} else if (index == 0) {
			head.prev(node);
			node.next(head);
			head = head.prev();
		} else {
			do {
				if (counter + 1 == index) {
					node.next(copyHead.next());
					node.prev(copyHead.prev());
					copyHead.next.prev(node);
					copyHead.next(node);
				}
				counter++;
				copyHead = copyHead.next();
			}
			while (copyHead != head);
		}
		
		this.size++;
		tail.next(head);
		head.prev(tail);
	}

	public void set(int index, E e) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		int counter = 0;
		if (index >= this.size) {
			System.out.println("Error");
		} else if (index == 0) {
			copyHead.value(e);
		} else {
			do {
				if (counter == index) {
					copyHead.value(e);
				}
				counter++;
				copyHead = copyHead.next();
			}
			while (copyHead != head);
				
		}
	}
	
	public int indexOf(E e) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		int counter = 0;
		do {
			if (copyHead.value() == e) {
				return counter;
			}
			counter++;
			copyHead = copyHead.next();
		}
		while (copyHead != head);
		return -1;
	}

	public E get(int index) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		int counter = 0;
		do {
			if (counter == index) {
				return copyHead.value;
			}
			counter++;
			copyHead = copyHead.next();
		}
		while (copyHead != head);
		return null;
	}

	public boolean contains(E e) {
		if (this.indexOf(e) < 0) {
			return false;
		}
		return true;
	}

	public int lastIndexOf(E e) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		int result = -1;
		int counter = 0;
		do {
			if (copyHead.value() == e) {
				result = counter;
			}
			copyHead = copyHead.next();
			counter++;
		}
		while (copyHead != head);
		return result;
	}

	public boolean remove(E e) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		do {
			if (copyHead.value() == e) {
				if (copyHead.prev() == tail) {
					copyHead.value(copyHead.next().value());
					copyHead.next().prev(tail);
					copyHead.next(copyHead.next().next());
					head = copyHead;
				} else if(copyHead.next() == head){
					copyHead.prev().next(head);
					tail = copyHead.prev();
					head.prev(tail);
				} else {
					copyHead.value(copyHead.next().value());
					copyHead.next().prev(copyHead.prev());
					copyHead.next(copyHead.next().next());
				}
			}
			copyHead = copyHead.next();
		} while (copyHead != head);
		this.size--;
		System.out.println(head.value() + " " + head.prev().value()+ " " + tail.value() + tail.next().value());
		return true;
	}

	public void clear() {
		this.head = null;
		this.tail = null;
	}
	
	public boolean addAll(MyQueueUsingCircularList<E> list) {
		this.tail.next(list.head);
		list.head.prev(this.head);
		this.tail = list.tail;
		this.size = this.size + list.getSize();
		tail.next(head);
		head.prev(tail);
		return true;
	}
	
	public boolean addAll(int index, MyQueueUsingCircularList<E> list) {
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		int counter = 0;
		if (index >= this.size) {
			tail.next(list.head);
			list.head.prev(tail);
			tail = list.tail;
		} else if (index == 0) {
			head.prev(list.tail);
			list.tail.next(head);
			head = list.head;
		} else {
			do {
				if (counter + 1 == index) {
					list.tail.next(copyHead.next());
					list.head.prev(copyHead.prev());
					copyHead.next.prev(list.tail);
					copyHead.next(list.head);
				}
				counter++;
				copyHead = copyHead.next();
			}
			while (copyHead != head);
		}
		this.size = this.size + list.size;
		tail.next(head);
		head.prev(tail);
		return true;
	}
	
	public E peek() {
		return head.value();
	}
	
	public boolean enqueue(E e) {
		this.add(e);
		return true;
	}
	
	public E dequeue() {
		E aux = head.value();
		this.remove(head.value());
		return aux;
	}
	
	public boolean isEmpty() {
		if (this.getSize() == 0) {
			return false;
		}
		return true;
	}
	
	public void prin() {
		System.out.println("--------");
		MyQueueUsingCircularListNode<E> copyHead = this.head;
		do {
			System.out.print(copyHead.value() + " ");
			copyHead = copyHead.next()	;
		}
		while (copyHead != head);
		System.out.println("head is " + this.head.value() + " tail is " + this.tail.value());
		System.out.println("============");
	}
}
