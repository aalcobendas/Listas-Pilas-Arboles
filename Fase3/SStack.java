package Fase3;



public class SStack {
	public SNode peak=null;
	public int size;

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return peak==null;
	}

	
	public void push(int elem) {
		// TODO Auto-generated method stub
			SNode newNode = new SNode(elem);
			newNode.next=peak;
			peak=newNode;
			size++;
	}
	
	public Integer pop(){
		if (isEmpty()){
			System.out.println("Stack is empty");
			return null;
		}
		int elem=peak.Vertex;
		peak=peak.next;
		size--;
		return elem;
	}

	
	public int top() {
		// TODO Auto-generated method stub
		return peak.Vertex;
	}

	
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

}



