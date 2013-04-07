import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	// helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

	private Node FIRST;
	private Node LAST;
	private int SIZE;
	private Node createNode(Item item) {

		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;
		return newNode;
    }
	
		
	private class RandomizedQueueIterator implements Iterator<Item> {
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
    public RandomizedQueue() {
        FIRST=null;
	    LAST=null;
	    SIZE=0;
    }
	public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }
	public int size()  {
		return SIZE;
	}
    public boolean isEmpty() {
		return ((FIRST == null) && (LAST == null) && (SIZE == 0));
    }
    public void enqueue(Item item) {
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
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int pos = StdRandom.uniform(SIZE);
        pos=pos+1;
        int index=1;
        Node curr=FIRST;
        Node prev=null;
        while(index != pos) {
            prev=curr;
            curr=curr.next;
            index++;
        }
        if(curr == FIRST) {
            FIRST = curr.next;
            curr.next = null;
            if(FIRST == null)
                LAST = FIRST;
            SIZE--;
            return curr.item;
        }
        if(curr == LAST) {
            LAST = prev;
            LAST.next = null;
            SIZE--;
            return curr.item;
        }
        SIZE--;
        prev.next=curr.next;
        curr.next=null;
        return curr.item;
    }
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int pos = StdRandom.uniform(SIZE);
        pos=pos+1;
        int index=1;
        Node curr=FIRST;
        Node prev=null;
        while(index != pos) {
            prev=curr;
            curr=curr.next;
            index++;
        }
        return curr.item;
    }
	
}
