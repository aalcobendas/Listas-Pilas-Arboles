package Fase3;

public class SNode {

	public String vertex;
	public SNode next;
	public Pair pair;
	public Integer Vertex;
	
	public SNode(String vertex) {
		this.vertex = vertex;
	}
	
	public SNode(Pair p) {
		this.pair = p;
	}
	
	public SNode (Integer vertex){
		this.Vertex=vertex;
	}
	
}

