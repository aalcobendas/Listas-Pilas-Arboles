package Fase2;




public class DList implements IList {
	public DNode header;
	public DNode trailer;
	int size=0;

	public DList() {
		header = new DNode(null);
		trailer = new DNode(null);
		header.next = trailer;
		trailer.prev= header;
	}

	public void addFirst(String elem) {
		DNode newNode = new DNode(elem);
		newNode.next = header.next;
		newNode.prev= header;
		header.next.prev= newNode;
		header.next = newNode;
		size++;
	}

	
	public void addLast(String elem) {
		DNode newNode = new DNode(elem);
		newNode.next = trailer;
		newNode.prev= trailer.prev;
		trailer.prev.next = newNode;
		trailer.prev= newNode;
		size++;
	}

	
	public void insertAt(int index, String elem) {
		int i=0;
		DNode newNode = new DNode(elem);
		if (index==0){
			addFirst(elem);
		}else{
			for(DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
				if (i==index-1){
					newNode.next=nodeIt.next;
					newNode.prev=nodeIt;
					nodeIt.next.prev=newNode;
					nodeIt.next=newNode;
					size++;
				}
				i++;
			}
		}
		
	}

	public boolean isEmpty() {
		return (header.next == trailer);
	}

	
	public boolean contains(String elem) {
		boolean found=false;
		for (DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
			if(nodeIt.elem.equals(elem)){
				found=true;
			}
		}
		return found;
	}

	
	public int getIndexOf(String elem) {
		int index = -1;
		int i=0;
		for (DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
			if (nodeIt.elem.equals(elem)){
				index=i;
			}
			i++;
		}
		return index;
	}

	
	public void removeFirst() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev= header;
		size--;
	}

	
	public void removeLast() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		trailer.prev= trailer.prev.prev;
		trailer.prev.next = trailer;
		size--;
	}

	
	public void removeAll(String elem){
		Integer pos=getIndexOf(elem);
		while(pos!=-1){
			removeAt(pos);
			pos=getIndexOf(elem);
		}
		
	}

	
	
	public void removeAt(int index) {
		int i=0;
		if (index==0){
			removeFirst();
		}else if (index==size-1){
			removeLast();
		}else{
			for(DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
				if (i==index-1){
					nodeIt.next=nodeIt.next.next;
					nodeIt.next.next.prev=nodeIt;
					size--;
				}
				i++;
			}
		}
			
	}

	
	public int getSize() {
	return size;
	}

	
	public String getFirst() {
		String result=null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else result=header.next.elem;
		return result;
	}

	public String getLast() {
		String result=null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else result=trailer.prev.elem;
		return result;
	}

	
	public String getAt(int index) {
		int i = 0;
		String result=null;
		for (DNode nodeIt = header.next; nodeIt != trailer && result==null; nodeIt = nodeIt.next) {
			if (i == index) {
				result=nodeIt.elem;
			}
			++i;
		}
		if (result==null) System.out.println("DList: Get out of bounds");
		return result;
	}

	public String toString() {
		String result = null;
		for (DNode nodeIt = header.next; nodeIt != trailer; nodeIt = nodeIt.next){
			if (result == null) {
				result = String.valueOf(nodeIt.elem);
			} else {
				result += "," + String.valueOf(nodeIt.elem);
			}
		}
		return result == null ? "empty" : result;
	}

	

	
	
	/*public static void main(String[] args) {
		// incomplete test
		Random rn=new Random();
		DList list = new DList();
		System.out.println(list.toString());
		System.out.println("isEmpty?" + list.isEmpty());
		for (int i=0; i<100; i++) {
			list.insertAt(i, Integer.valueOf(rn.nextInt(100)));
		}
		list.removeLast();
		list.insertAt(2, 30);
		System.out.println("First: " + list.getFirst());
		System.out.println("Last: " + list.getLast());
		list.removeAt(5);
		System.out.println("removed element at position 5"+list.toString());
		list=new DList();
	}*/
}