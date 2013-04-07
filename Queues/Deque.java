import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	// helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

	private Node FIRST;
	private Node LAST;
	private int SIZE;
	
		
	private class DequeIterator implements Iterator<Item> {
		private Node current = FIRST;
		public boolean hasNext() { return current!=null; }
		public void remove() {
		       	throw new java.lang.UnsupportedOperationException(); 
		}
		public Item next() {
			Item toReturn = current.item;
			current=current.next;
			return toReturn;
		}
	}
	private Node createNode(Item item) {
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;
		return newNode;
	}
	
	private Node lastButOne() {
		Node fast = FIRST;
		Node slow = null;
		while(fast!=LAST) {
			slow=fast;
			fast=fast.next;
		}
		return slow;
	}
	public Deque() {
		FIRST=null;
		LAST=null;
		SIZE=0;
	}
	
	public Iterator<Item> iterator() { return new DequeIterator(); }
	public int size()  {
		return SIZE;
	}
	public boolean isEmpty() {
		return ((FIRST == null) && (LAST == null) && (SIZE == 0));
	}
	public void addFirst(Item item) {
		if (item == null) throw new java.lang.NullPointerException();
		Node toAdd = createNode(item);
		SIZE++;
		if( FIRST == null ) {
			FIRST = toAdd;
			LAST = toAdd;
		} else {
			toAdd.next = FIRST;
			FIRST = toAdd;
		}
	}
	public void addLast(Item item) {
		if (item == null) throw new java.lang.NullPointerException();
		Node toAdd = createNode(item);
		SIZE++;
		if( LAST == null ) {
			FIRST = toAdd;
			LAST = toAdd;
		} else {
			LAST.next = toAdd;
			LAST = toAdd;
		}
	}
	public Item removeFirst() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		SIZE--;
		Node toReturn = FIRST;
		FIRST = FIRST.next;
		toReturn.next=null;
		if(FIRST == null) {
			LAST = null;
			assert (SIZE == 0);
		}
		if (FIRST == LAST) assert (SIZE == 1);
		return toReturn.item;
	}
	public Item removeLast() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		SIZE--;
		Node toReturn = LAST;
		Node newLast = lastButOne();
		if (newLast==null) LAST = FIRST;
		else LAST = newLast;
		LAST.next=null;
		return toReturn.item;
	}
}
