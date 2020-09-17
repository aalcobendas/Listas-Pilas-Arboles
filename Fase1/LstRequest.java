package Fase1;

public class LstRequest implements IList{
	
	public DNode header;
	public DNode trailer;
	int size=0;

	public LstRequest() {
		header = new DNode(null);
		trailer = new DNode(null);
		header.next = trailer;
		trailer.prev= header;
	}
	
	public boolean equals(LstRequest list) {
        if (list==this) return true;
        if (list.getSize()!=this.getSize()) return false;
        for (int i=0; i<this.getSize(); i++) {
              Request r1=this.getAt(i);
              Request r2=list.getAt(i);
              if (!r1.equals(r2)) return false;
        }
        return true;
  }

	public void addFirst(Request request) {
		DNode newNode = new DNode(request);
		newNode.next = header.next;
		newNode.prev= header;
		header.next.prev= newNode;
		header.next = newNode;
		size++;
	}
	
	public void addLast(Request request){
		DNode newNode = new DNode (request);
		newNode.next=trailer;
		newNode.prev=trailer.prev;
		trailer.prev.next=newNode;
		trailer.prev=newNode;
		size++;
	}
	
	public void insertAt(int index, Request request){
		int i=0;
		DNode newNode = new DNode(request);
		if (index==0){
			addFirst(request);
		}else if (index==size){
			addLast(request);
		}else{
			for (DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
				if(i==index-1){
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
	
	public boolean isEmpty(){
		return (header.next==trailer);
	}
	
	public boolean contains(String elem) {
		boolean found=false;
		for (DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
			if(nodeIt.request.login.equals(elem)|| nodeIt.request.source.equals(elem)|| nodeIt.request.target.equals(elem)){
				found=true;
			}
		}
		return found;
	}
	
	public int getIndexOf(String elem) {
		int index=-1;
		int i=0;
		for (DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
			if (nodeIt.request.login.equals(elem)||nodeIt.request.source.equals(elem)||nodeIt.request.target.equals(elem)){
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
	
	public void removeAll(String elem){
		Integer pos=getIndexOf(elem);
		while(pos!=-1){
			removeAt(pos);
			pos=getIndexOf(elem);
		}
	}

	public int getSize() {
		return size;
	}

	public Request getFirst() {
		Request result=null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else result=header.next.request;
		return result;
	}

	public Request getLast() {
		Request result=null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else result=trailer.prev.request;
		return result;
	}
	
	public Request getAt(int index){
		int i=0;
		Request result=null;
		for(DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
			if(i==index){
				result=nodeIt.request;
			}
			i++;
		}
		return result;
	}
	public String toString(){
		String mensaje="";
		for(DNode nodeIt=header.next;nodeIt!=trailer;nodeIt=nodeIt.next){
			mensaje+=nodeIt.request.toString() + " \n";
		}
		return mensaje;
	}
	

}
