
public class MyDoubleLinkedList<E> {

	public class DoubleLinkedListNode<E> {
		private E value;
		private DoubleLinkedListNode<E> next;
		private DoubleLinkedListNode<E> previous;

		public DoubleLinkedListNode(E value, DoubleLinkedListNode<E> next, DoubleLinkedListNode<E> previous) {
			this.value = value;
			this.next = next;
			this.previous = previous;
		}

		public DoubleLinkedListNode(E value) {
			this(value, null, null);
		}

		public DoubleLinkedListNode() {
		}

		public void value(E value) {
			this.value = value;
		}

		public E value() {
			return value;
		}

		public DoubleLinkedListNode<E> next() {
			return next;
		}

		public void next(DoubleLinkedListNode<E> next) {
			this.next = next;
		}

		public DoubleLinkedListNode<E> prev() {
			return previous;
		}

		public void prev(DoubleLinkedListNode<E> previous) {
			this.previous = previous;
		}
	}

	private DoubleLinkedListNode<E> head = null;
	private DoubleLinkedListNode<E> tail = null;
	private int size = 0;

	public int getSize() {
		return size;
	}

	MyDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	MyDoubleLinkedList(MyDoubleLinkedList<E> list) {
		DoubleLinkedListNode<E> copyHead = list.head;
		while (copyHead != null) {
			add(copyHead.value());
			copyHead = copyHead.next();
			this.size++;
		}
	}

	public boolean add(E e) {
		DoubleLinkedListNode<E> node = new DoubleLinkedListNode<E>(e);
		if (head == null) {
			head = node;
			tail = head;
		} else {
			tail.next(node);
			node.prev(tail);
			tail = tail.next();
		}
		this.size++;
		return true;
	}

	public void add(int index, E e) {
		DoubleLinkedListNode<E> copyHead = this.head;
		DoubleLinkedListNode<E> node = new DoubleLinkedListNode<E>(e);
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
			while (copyHead != null) {
				if (counter + 1 == index) {
					node.next(copyHead.next());
					node.prev(copyHead.prev());
					copyHead.next.prev(node);
					copyHead.next(node);
				}
				counter++;
				copyHead = copyHead.next();
			}
		}
		this.size++;
	}

	public void set(int index, E e) {
		DoubleLinkedListNode<E> copyHead = this.head;
		int counter = 0;
		if (index >= this.size) {
			System.out.println("Error");
		} else if (index == 0) {
			copyHead.value(e);
		} else {
			while (copyHead != null) {
				if (counter + 1 == index) {
					copyHead.value(e);
				}
				counter++;
				copyHead = copyHead.next();
			}
		}
		this.size++;
	}
	
	public int indexOf(E e) {
		DoubleLinkedListNode<E> copyHead = this.head;
		int counter = 0;
		while (copyHead != null) {

			if (copyHead.value() == e) {
				return counter;
			}
			counter++;
			copyHead = copyHead.next();
		}
		return -1;
	}

	public E get(int index) {
		DoubleLinkedListNode<E> copyHead = this.head;
		int counter = 0;
		while (copyHead != null) {

			if (counter == index) {
				return copyHead.value;
			}
			counter++;
			copyHead = copyHead.next();
		}
		return null;
	}

	public boolean contains(E e) {
		if (this.indexOf(e) < 0) {
			return false;
		}
		return true;
	}

	public int lastIndexOf(E e) {
		DoubleLinkedListNode<E> copyHead = this.head;
		int result = -1;
		int counter = 0;
		while (copyHead != null) {

			if (copyHead.value() == e) {
				result = counter;
			}
			copyHead = copyHead.next();
			counter++;
		}
		return result;
	}

	public boolean remove(E e) {
		DoubleLinkedListNode<E> copyHead = this.head;
		while (copyHead != null) {
			if (copyHead.value() == e) {
				if (copyHead.prev() == null) {
					copyHead.value(copyHead.next().value());
					copyHead.next().prev(null);
					copyHead.next(copyHead.next().next());
					head = copyHead;
				} else if(copyHead.next() == null){
					copyHead.prev().next(null);
					tail = copyHead.prev();
				} else {
					copyHead.value(copyHead.next().value());
					copyHead.next().prev(copyHead.prev());
					copyHead.next(copyHead.next().next());
				}
			}
			copyHead = copyHead.next();
		}
		this.size--;
		return true;
	}

	public void clear() {
		this.head = null;
		this.tail = null;
	}
	
	public boolean addAll(MyDoubleLinkedList<E> list) {
		this.tail.next(list.head);
		list.head.prev(this.head);
		this.tail = list.tail;
		this.size = this.size + list.getSize();
		return true;
	}
	
	public boolean addAll(int index, MyDoubleLinkedList<E> list) {
		DoubleLinkedListNode<E> copyHead = this.head;

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
			while (copyHead != null) {
				if (counter + 1 == index) {
					list.tail.next(copyHead.next());
					list.head.prev(copyHead.prev());
					copyHead.next.prev(list.tail);
					copyHead.next(list.head);
				}
				counter++;
				copyHead = copyHead.next();
			}
		}
		this.size = this.size + list.size;
		return true;
	}
	
	public E peek() {
		return tail.value();
	}
	
	public void push(E e) {
		this.add(e);
	}
	
	public E pop() {
		E aux = tail.value();
		tail = tail.prev();
		tail.next(null);
		this.size--;
		return aux;
	}
	
	public boolean isEmpty() {
		if (this.getSize() == 0) {
			return false;
		}
		return true;
	}

}
