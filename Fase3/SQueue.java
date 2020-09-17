package Fase3;


public class SQueue implements IQueue {
	
	private SNode first;
	private SNode last;
	int size;

	public boolean isEmpty() {
		return first == null;
	}
	
	public int getSize() {
		return size;
	}
	
	public void enqueue(String vertex) {
		SNode node = new SNode(vertex);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
	}
	
	public void enqueue(int i){
		SNode node = new SNode(i);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
	}

	public String dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		} 
		
		String v = first.vertex;
		first = first.next;
		if (first == null) {
			last = null;
		}
		size--;
		return v;	
	}
	
	public Integer dequeueInt(){
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		} 
		
		int i = first.Vertex;
		first = first.next;
		if (first == null) {
			last = null;
		}
		size--;
		return i;
	}
	
	public void enqueue(Pair p) {
		SNode node = new SNode(p);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
	}

	public Pair dequeueP() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		} 
		
		Pair p = first.pair;
		first = first.next;
		if (first == null) {
			last = null;
		}
		size--;
		return p;
		
	}

	public String front() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		}
		return first.vertex;
	}

	
	public String toString() {
		String result = null;
		for (SNode nodeIt = first; nodeIt != null; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "[" + nodeIt.vertex + "]";
			} else {
				result += "," + nodeIt.vertex;
			}
		}
		return result == null ? "empty" : result;
	}

}